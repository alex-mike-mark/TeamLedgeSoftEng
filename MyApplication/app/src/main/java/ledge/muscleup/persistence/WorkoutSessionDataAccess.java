package ledge.muscleup.persistence;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.exercise.enums.DistanceUnit;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.exercise.enums.TimeUnit;
import ledge.muscleup.model.exercise.enums.WeightUnit;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * The data access class for workout session data
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-13
 */
public class WorkoutSessionDataAccess implements InterfaceWorkoutSessionDataAccess {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final int NULL_NUM = -1;

    private Statement statement;
    private ResultSet resultSet, resultSet2;

    /**
     * Opens the WorkoutSessionDataAccess
     * @param statement the statement to use in WorkoutSessionDataAccess queries
     */
    public void open(Statement statement) {
        this.statement = statement;
    }

    /**
     * Close the WorkoutSessionDataAccess
     */
    public void close() {
        try {
            statement.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }
    }

    /**
     * Retrieves a workout session scheduled on the given date from the database, if it exists. If
     * no workout session is found for that date, returns null.
     *
     * @param dateOfSession the date to get the workout session for
     * @return the workout session scheduled on the given date
     */
    @Override
    public WorkoutSession getWorkoutSession(LocalDate dateOfSession) {
        ArrayList<WorkoutSessionExercise> workoutSessionExerciseList = new ArrayList<>();
        Exercise exercise;
        WorkoutExercise workoutExercise;
        WorkoutSession workoutSession = null;

        String workoutName = null, exerciseName, distanceUnitString, timeUnitString, weightUnitString;
        int xpValue, duration, sets, reps;
        double distance, weight;
        DistanceUnit distanceUnit = null;
        TimeUnit timeUnit = null;
        WeightUnit weightUnit = null;
        LocalDate scheduledDate = null;
        ExerciseIntensity intensity;
        ExerciseType type;
        boolean workoutComplete = false, exerciseComplete;

        try {
            //get the workout session matching the given date from the db
            resultSet = statement.executeQuery(
                    "SELECT		W.Name AS WorkoutName, " +
                    "			WS.ScheduledDate, " +
                    "			WS.Complete AS WorkoutComplete, " +
                    "			E.Name AS ExerciseName, " +
                    "			EI.Intensity, " +
                    "			ET.Type, " +
                    "			WE.Distance, " +
                    "			DiU.DistanceUnit, " +
                    "			WE.Duration, " +
                    "			DuU.DurationUnit, " +
                    "			WE.Sets, " +
                    "			WE.Reps, " +
                    "			WE.Weight, " +
                    "			WU.WeightUnit,  " +
                    "			WSE.Complete AS ExerciseCompleted " +
                    "FROM		WorkoutSessions WS " +
                    "LEFT JOIN	Workouts W " +
                    "			ON WS.WorkoutID = W.ID " +
                    "LEFT JOIN	WorkoutSessionContents WSC " +
                    "			ON WSC.WorkoutSessionID = WS.ID " +
                    "LEFT JOIN	WorkoutSessionExercises WSE " +
                    "			ON WSC.ExerciseID = WSE.ID " +
                    "LEFT JOIN	WorkoutExercises WE " +
                    "			ON WSE.WorkoutExerciseID = WE.ID " +
                    "LEFT JOIN	DistanceUnits DiU " +
                    "			ON WE.DistanceUnitID = DiU.ID " +
                    "LEFT JOIN	DurationUnits DuU " +
                    "			ON WE.DurationUnitID = DuU.ID " +
                    "LEFT JOIN	WeightUnits WU " +
                    "			ON WE.WeightUnitID = WU.ID " +
                    "LEFT JOIN	Exercises E " +
                    "			ON WE.ExerciseID = E.ID " +
                    "LEFT JOIN  ExerciseIntensities EI " +
                    "           ON E.IntensityID = EI.ID  " +
                    "LEFT JOIN  ExerciseTypes ET " +
                    "           ON E.TypeID = ET.ID " +
                    "WHERE		WS.ScheduledDate = DATE'" + DATE_TIME_FORMATTER.print(dateOfSession) + "'");

            while (resultSet.next()) {
                //if the name of the workout hasn't been set yet, get the workout information
                if (workoutName == null) {
                    workoutName = resultSet.getString("WorkoutName");
                    scheduledDate = new LocalDate(resultSet.getDate("ScheduledDate"));
                    workoutComplete = resultSet.getBoolean("WorkoutComplete");
                }

                //build an exercise from the query results
                exerciseName = resultSet.getString("ExerciseName");
                intensity = ExerciseIntensity.valueOf(resultSet.getString("Intensity"));
                type = ExerciseType.valueOf(resultSet.getString("Type"));
                exercise = new Exercise(exerciseName, intensity, type);

                //build a workout exercise using the exercise
                xpValue = DataAccess.XP_PER_INTENSITY * (ExerciseIntensity.valueOf(resultSet.getString("Intensity")).ordinal() + 1);
                distance = resultSet.getDouble("Distance");
                if (resultSet.wasNull())
                    distance = NULL_NUM;
                distanceUnitString = resultSet.getString("DistanceUnit");
                if (!resultSet.wasNull())
                    distanceUnit = DistanceUnit.valueOf(distanceUnitString);

                duration = resultSet.getInt("Duration");
                if (resultSet.wasNull())
                    duration = NULL_NUM;
                timeUnitString = resultSet.getString("DurationUnit");
                if (!resultSet.wasNull())
                    timeUnit = TimeUnit.valueOf(timeUnitString);

                sets = resultSet.getInt("Sets");
                if (resultSet.wasNull())
                    sets = NULL_NUM;
                reps = resultSet.getInt("Reps");
                if (resultSet.wasNull())
                    reps = NULL_NUM;

                weight = resultSet.getDouble("Weight");
                if (resultSet.wasNull())
                    weight = NULL_NUM;
                weightUnitString = resultSet.getString("WeightUnit");
                if (!resultSet.wasNull())
                    weightUnit = WeightUnit.valueOf(weightUnitString);
                workoutExercise = DataAccess.createWorkoutExercise(exercise, xpValue, distance, distanceUnit, duration,
                        timeUnit, sets, reps, weight, weightUnit);

                //build a workout session exercise using the workout exercise
                exerciseComplete = resultSet.getBoolean("ExerciseCompleted");
                workoutSessionExerciseList.add(new WorkoutSessionExercise(workoutExercise, exerciseComplete));
            }

            //create the workout session based on the workout session exercises
            if (workoutName != null)
                workoutSession = new WorkoutSession(workoutName, scheduledDate, workoutComplete, workoutSessionExerciseList);

            resultSet.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }

        return workoutSession;
    }

    /**
     * A method that returns a list of workout sessions scheduled in a date range
     *
     * @param startDate the first date of the date range
     * @param endDate   the last date of the date range
     * @return a list of all workout sessions scheduled between startDate and endDate, inclusive
     */
    @Override
    public List<WorkoutSession> getSessionsInDateRange(LocalDate startDate, LocalDate endDate) {
        ArrayList<WorkoutSession> workoutSessionList = new ArrayList<>();
        ArrayList<WorkoutSessionExercise> workoutSessionExerciseList = new ArrayList<>();
        Exercise exercise;
        WorkoutExercise workoutExercise;
        WorkoutSession workoutSession;

        String workoutName = null, exerciseName, distanceUnitString, timeUnitString, weightUnitString;
        int xpValue, duration, sets, reps;
        double distance, weight;
        DistanceUnit distanceUnit = null;
        TimeUnit timeUnit = null;
        WeightUnit weightUnit = null;
        LocalDate scheduledDate = null;
        ExerciseIntensity intensity;
        ExerciseType type;
        boolean workoutComplete = false, exerciseComplete;

        try {
            //get the workout sessions from the db
            resultSet = statement.executeQuery(
                    "SELECT		W.Name AS WorkoutName, " +
                    "			WS.ScheduledDate, " +
                    "			WS.Complete AS WorkoutComplete, " +
                    "			E.Name AS ExerciseName, " +
                    "			EI.Intensity, " +
                    "			ET.Type, " +
                    "			WE.Distance, " +
                    "			DiU.DistanceUnit, " +
                    "			WE.Duration, " +
                    "			DuU.DurationUnit, " +
                    "			WE.Sets, " +
                    "			WE.Reps, " +
                    "			WE.Weight, " +
                    "			WU.WeightUnit,  " +
                    "			WSE.Complete AS ExerciseCompleted " +
                    "FROM		WorkoutSessions WS " +
                    "LEFT JOIN	Workouts W " +
                    "			ON WS.WorkoutID = W.ID " +
                    "LEFT JOIN	WorkoutSessionContents WSC " +
                    "			ON WSC.WorkoutSessionID = WS.ID " +
                    "LEFT JOIN	WorkoutSessionExercises WSE " +
                    "			ON WSC.ExerciseID = WSE.ID " +
                    "LEFT JOIN	WorkoutExercises WE " +
                    "			ON WSE.WorkoutExerciseID = WE.ID " +
                    "LEFT JOIN	DistanceUnits DiU " +
                    "			ON WE.DistanceUnitID = DiU.ID " +
                    "LEFT JOIN	DurationUnits DuU " +
                    "			ON WE.DurationUnitID = DuU.ID " +
                    "LEFT JOIN	WeightUnits WU " +
                    "			ON WE.WeightUnitID = WU.ID " +
                    "LEFT JOIN	Exercises E " +
                    "			ON WE.ExerciseID = E.ID " +
                    "LEFT JOIN  ExerciseIntensities EI " +
                    "           ON E.IntensityID = EI.ID  " +
                    "LEFT JOIN  ExerciseTypes ET " +
                    "           ON E.TypeID = ET.ID " +
                    "WHERE		DATEDIFF('day', WS.ScheduledDate, DATE'" + DATE_TIME_FORMATTER.print(startDate) + "') <= 0" +
                    "           AND DATEDIFF('day', WS.ScheduledDate, DATE'" + DATE_TIME_FORMATTER.print(endDate) + "') >= 0");

            while (resultSet.next()) {
                //if the name of the workout hasn't been set yet, get the workout information
                if (workoutName == null) {
                    workoutName = resultSet.getString("WorkoutName");
                    scheduledDate = new LocalDate(resultSet.getDate("ScheduledDate"));
                    workoutComplete = resultSet.getBoolean("WorkoutComplete");
                }
                //if the name of the workout has changed, add the old workout and create a new one
                else if (!scheduledDate.equals(new LocalDate(resultSet.getDate("ScheduledDate")))) {
                    workoutSession = new WorkoutSession(workoutName, scheduledDate, workoutComplete, workoutSessionExerciseList);
                    workoutSessionList.add(workoutSession);
                    workoutSessionExerciseList = new ArrayList<>();

                    workoutName = resultSet.getString("WorkoutName");
                    scheduledDate = new LocalDate(resultSet.getDate("ScheduledDate"));
                    workoutComplete = resultSet.getBoolean("WorkoutComplete");
                }

                //build an exercise from the query results
                exerciseName = resultSet.getString("ExerciseName");
                intensity = ExerciseIntensity.valueOf(resultSet.getString("Intensity"));
                type = ExerciseType.valueOf(resultSet.getString("Type"));
                exercise = new Exercise(exerciseName, intensity, type);

                //build a workout exercise using the exercise
                xpValue = DataAccess.XP_PER_INTENSITY * (ExerciseIntensity.valueOf(resultSet.getString("Intensity")).ordinal() + 1);
                distance = resultSet.getDouble("Distance");
                if (resultSet.wasNull())
                    distance = NULL_NUM;
                distanceUnitString = resultSet.getString("DistanceUnit");
                if (!resultSet.wasNull())
                    distanceUnit = DistanceUnit.valueOf(distanceUnitString);

                duration = resultSet.getInt("Duration");
                if (resultSet.wasNull())
                    duration = NULL_NUM;
                timeUnitString = resultSet.getString("DurationUnit");
                if (!resultSet.wasNull())
                    timeUnit = TimeUnit.valueOf(timeUnitString);

                sets = resultSet.getInt("Sets");
                if (resultSet.wasNull())
                    sets = NULL_NUM;
                reps = resultSet.getInt("Reps");
                if (resultSet.wasNull())
                    reps = NULL_NUM;

                weight = resultSet.getDouble("Weight");
                if (resultSet.wasNull())
                    weight = NULL_NUM;
                weightUnitString = resultSet.getString("WeightUnit");
                if (!resultSet.wasNull())
                    weightUnit = WeightUnit.valueOf(weightUnitString);
                workoutExercise = DataAccess.createWorkoutExercise(exercise, xpValue, distance, distanceUnit, duration,
                        timeUnit, sets, reps, weight, weightUnit);

                //build a workout session exercise using the workout exercise
                exerciseComplete = resultSet.getBoolean("ExerciseCompleted");
                workoutSessionExerciseList.add(new WorkoutSessionExercise(workoutExercise, exerciseComplete));
            }

            //create and add the final workout
            if (workoutName != null) {
                workoutSession = new WorkoutSession(workoutName, scheduledDate, workoutComplete, workoutSessionExerciseList);
                workoutSessionList.add(workoutSession);
            }

            resultSet.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }

        return workoutSessionList;
    }

    /**
     * Inserts a new workout session into the database
     *
     * @param workoutSession the new workout session to insert into the database
     */
    @Override
    public void insertWorkoutSession(WorkoutSession workoutSession) {
        int workoutID, workoutSessionID, workoutExerciseID, workoutSessionExerciseID;

        try {
            //get the ID of the workout to add
            resultSet = statement.executeQuery(
                    "SELECT	W.ID " +
                    "FROM	Workouts W " +
                    "WHERE	W.Name = '" + workoutSession.getName() + "'");
            if (resultSet.next()) {
                workoutID = resultSet.getInt("ID");

                //create the workout session
                statement.executeQuery(
                        "INSERT INTO    WorkoutSessions (ScheduledDate, WorkoutID, Complete) " +
                        "VALUES         (DATE'" + DATE_TIME_FORMATTER.print(workoutSession.getDate()) + "', " + workoutID + ", FALSE)");

                //get the ID of the newly created workout session
                resultSet = statement.executeQuery(
                        "SELECT	MAX(WS.ID) AS NewestID " +
                        "FROM	WorkoutSessions WS");
                if (resultSet.next()) {
                    workoutSessionID = resultSet.getInt("NewestID");

                    //get the ID of the exercises to add
                    resultSet = statement.executeQuery(
                            "SELECT		WE.ID " +
                            "FROM		WorkoutExercises WE " +
                            "LEFT JOIN	Exercises E " +
                            "			ON WE.ExerciseID = E.ID " +
                            "RIGHT JOIN	WorkoutContents WC " +
                            "			ON WE.ID = WC.ExerciseID " +
                            "WHERE		WC.WorkoutID = " + workoutID);

                    while (resultSet.next()) {
                        workoutExerciseID = resultSet.getInt("ID");

                        //create the workout session exercises
                        statement.executeQuery(
                                "INSERT INTO    WorkoutSessionExercises (WorkoutExerciseID, Complete) " +
                                "VALUES         (" + workoutExerciseID + ", FALSE) ");

                        //get the ID of the newly created workout session exercise
                        resultSet2 = statement.executeQuery(
                                "SELECT	MAX(WSE.ID) AS NewestID " +
                                "FROM	WorkoutSessionExercises WSE ");
                        if (resultSet2.next()) {
                            workoutSessionExerciseID = resultSet2.getInt("NewestID");

                            //create the contents of the workout session
                            statement.executeQuery(
                                    "INSERT INTO    WorkoutSessionContents (WorkoutSessionID, ExerciseID) " +
                                    "VALUES         (" + workoutSessionID + ", " + workoutSessionExerciseID + ") ");
                        }
                    }
                }
            }

            resultSet.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }
    }

    /**
     * Removes a workout session from the database, if it exists
     *
     * @param workoutSession the workout session to remove from the database
     */
    @Override
    public void removeWorkoutSession(WorkoutSession workoutSession) {
        int workoutSessionID;
        String command;

        try {
            //get the ID of the workout session to remove
            resultSet = statement.executeQuery(
                    "SELECT	WS.ID " +
                    "FROM	WorkoutSessions WS " +
                    "WHERE	WS.ScheduledDate = DATE'" + DATE_TIME_FORMATTER.print(workoutSession.getDate()) + "'");
            if (resultSet.next()) {
                workoutSessionID = resultSet.getInt("ID");

                //get the IDs of the workout session exercises that are in the session
                resultSet = statement.executeQuery(
                        "SELECT	WSC.ExerciseID " +
                        "FROM	WorkoutSessionContents WSC " +
                        "WHERE	WSC.WorkoutSessionID = " + workoutSessionID);

                //delete the contents of the workout session
                statement.executeQuery(
                        "DELETE FROM	WorkoutSessionContents WSC " +
                        "WHERE		    WSC.WorkoutSessionID = " + workoutSessionID);

                //delete all workout session exercises in the workout session
                command = "DELETE FROM  WorkoutSessionExercises WSE " +
                          "WHERE        WSE.ID IN (";
                if (resultSet.next())
                    command += resultSet.getInt("ExerciseID");
                while (resultSet.next())
                    command += ", " + resultSet.getInt("ExerciseID");
                command += ")";
                statement.executeQuery(command);

                //delete the workout session
                statement.executeQuery(
                        "DELETE FROM	WorkoutSessions WS " +
                        "WHERE		    WS.ScheduledDate = DATE'" + DATE_TIME_FORMATTER.print(workoutSession.getDate()) + "'");
            }
            resultSet.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }
    }

    /**
     * Toggles the completed state of a workout in the database
     *
     * @param workoutSession the workout to change the state of
     */
    @Override
    public void toggleWorkoutComplete(WorkoutSession workoutSession) {
        int workoutSessionID, workoutSessionExerciseID, previousXPValue = 0;

        try {
            //get the ID of the workout session to be updated
            resultSet = statement.executeQuery(
                    "SELECT     WS.ID " +
                    "FROM       WorkoutSessions WS " +
                    "WHERE      WS.ScheduledDate = DATE'" + DATE_TIME_FORMATTER.print(workoutSession.getDate()) + "'");
            if (resultSet.next()) {
                workoutSessionID = resultSet.getInt("ID");

                //mark each exercise as complete
                resultSet = statement.executeQuery(
                        "SELECT     WSC.ExerciseID " +
                        "FROM       WorkoutSessionContents WSC " +
                        "WHERE      WSC.WorkoutSessionID = " + workoutSessionID);
                while (resultSet.next()) {
                    workoutSessionExerciseID = resultSet.getInt("ExerciseID");
                    statement.executeQuery(
                            "UPDATE     WorkoutSessionExercises WSE " +
                            "SET        WSE.Complete = True " + 
                            "WHERE      WSE.ID = " + workoutSessionExerciseID);
                }

                //mark the workout as complete
                statement.executeQuery(
                        "UPDATE     WorkoutSessions WS " +
                        "SET        WS.Complete = True " +
                        "WHERE      WS.ID = " + workoutSessionID);

                //get the last experience value from the history table
                resultSet = statement.executeQuery(
                        "SELECT TOP 1   PH.CurrentXP " +
                        "FROM           ProgressHistory PH " +
                        "ORDER BY       LoggedDate DESC ");
                if (resultSet.next())
                    previousXPValue = resultSet.getInt("CurrentXP");

                statement.executeQuery(
                        "INSERT INTO    ProgressHistory (WorkoutSessionID, LoggedDate, CurrentXP) " +
                        "VALUES         (" + workoutSessionID + ", CURRENT_TIMESTAMP, "  + (previousXPValue + workoutSession.getExperienceValue()) + ")");
            }
        }
        catch(Exception e) {
            DataAccess.sqlError(e);
        }
    }
}
