package ledge.muscleup.persistence;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.exercise.enums.DistanceUnit;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.exercise.enums.TimeUnit;
import ledge.muscleup.model.exercise.enums.WeightUnit;
import ledge.muscleup.model.experience.CompletedWorkoutRecord;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * This is THE REAL implementation of the HSQLDB
 *
 * @author Cole Kehler
 * @version 2.0
 * @since 2017-06-27
 */

public class DataAccess implements InterfaceExerciseDataAccess, InterfaceWorkoutDataAccess,
        InterfaceWorkoutSessionDataAccess, InterfaceExperienceDataAccess {
    private static final String SHUTDOWN_CMD = "shutdown compact";
    private static final DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final int NULL_NUM = -1;
    private static final int XP_PER_INTENSITY = 10;

    private String dbName;
    private String dbType = "HSQLDB";
    private String dbPathPrefix = "jdbc:hsqldb:file:";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet, resultSet2;

    /**
     * Constructor for DataAccess
     *
     * @param dbName the name of the database
     */
    public DataAccess (String dbName) {
        this.dbName = dbName;
    }

    /**
     * Opens the database
     */
    @Override
    public void open(String dbPath) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            connection = DriverManager.getConnection(dbPathPrefix + dbPath, "SA", "");
            statement = connection.createStatement();
        }
        catch (Exception e) {
            sqlError(e);
        }
        System.out.println("Opened " + dbType + " database " + dbName);
    }

    /**
     * Close the database
     */
    @Override
    public void close() {
        try {
            resultSet = statement.executeQuery(SHUTDOWN_CMD);
            connection.close();
        }
        catch (Exception e) {
            sqlError(e);
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    /**
     * Gets a list of all exercises in the database
     *
     * @return a list of all exercises in the database
     */
    @Override
    public List<Exercise> getExercisesList() {
        List<Exercise> exerciseList = new ArrayList<>();
        String name;
        ExerciseIntensity intensity;
        ExerciseType type;
        boolean favourite;

        try {
            //get the exercises from the db
            resultSet = statement.executeQuery(
                    "SELECT     E.Name, " +
                    "           EI.Intensity, " +
                    "           ET.Type, " +
                    "           E.Favourite " +
                    "FROM       Exercises E " +
                    "LEFT JOIN  ExerciseIntensities EI " +
                    "           ON E.IntensityID = EI.ID " +
                    "LEFT JOIN  ExerciseTypes ET " +
                    "           ON E.TypeID = ET.ID");

            while (resultSet.next()) {
                //build an exercise from the query results
                name = resultSet.getString("Name");
                intensity = ExerciseIntensity.valueOf(resultSet.getString("Intensity"));
                type = ExerciseType.valueOf(resultSet.getString("Type"));
                favourite = resultSet.getBoolean("Favourite");

                exerciseList.add(new Exercise(name, intensity, type, favourite));
            }
            resultSet.close();
        }
        catch (Exception e) {
            sqlError(e);
        }

        return exerciseList;
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
        boolean exerciseFavourite, workoutComplete = false, exerciseComplete;

        try {
            //get the workout session matching the given date from the db
            resultSet = statement.executeQuery(
                    "SELECT		W.Name AS WorkoutName, " +
                    "			WS.ScheduledDate, " +
                    "			WS.Complete AS WorkoutComplete, " +
                    "			E.Name AS ExerciseName, " +
                    "			EI.Intensity, " +
                    "			ET.Type, " +
                    "			E.Favourite, " +
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
                    "WHERE		WS.ScheduledDate = DATE'" + format.print(dateOfSession) + "'");

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
                exerciseFavourite = resultSet.getBoolean("Favourite");
                exercise = new Exercise(exerciseName, intensity, type, exerciseFavourite);

                //build a workout exercise using the exercise
                xpValue = XP_PER_INTENSITY * ExerciseIntensity.valueOf(resultSet.getString("Intensity")).ordinal();
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
                workoutExercise = createWorkoutExercise(exercise, xpValue, distance, distanceUnit, duration,
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
            sqlError(e);
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
        boolean exerciseFavourite, workoutComplete = false, exerciseComplete;

        try {
            //get the workout sessions from the db
            resultSet = statement.executeQuery(
                    "SELECT		W.Name AS WorkoutName, " +
                    "			WS.ScheduledDate, " +
                    "			WS.Complete AS WorkoutComplete, " +
                    "			E.Name AS ExerciseName, " +
                    "			EI.Intensity, " +
                    "			ET.Type, " +
                    "			E.Favourite, " +
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
                    "WHERE		DATEDIFF('day', WS.ScheduledDate, DATE'" + format.print(startDate) + "') <= 0" +
                    "           AND DATEDIFF('day', WS.ScheduledDate, DATE'" + format.print(endDate) + "') >= 0");

            while (resultSet.next()) {
                //if the name of the workout hasn't been set yet, get the workout information
                if (workoutName == null) {
                    workoutName = resultSet.getString("WorkoutName");
                    scheduledDate = new LocalDate(resultSet.getDate("ScheduledDate"));
                    workoutComplete = resultSet.getBoolean("WorkoutComplete");
                }
                //if the name of the workout has changed, add the old workout and create a new one
                else if (!workoutName.equals(resultSet.getString("WorkoutName"))) {
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
                exerciseFavourite = resultSet.getBoolean("Favourite");
                exercise = new Exercise(exerciseName, intensity, type, exerciseFavourite);

                //build a workout exercise using the exercise
                xpValue = XP_PER_INTENSITY * ExerciseIntensity.valueOf(resultSet.getString("Intensity")).ordinal();
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
                workoutExercise = createWorkoutExercise(exercise, xpValue, distance, distanceUnit, duration,
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
            sqlError(e);
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
                        "VALUES         (DATE'" + format.print(workoutSession.getDate()) + "', " + workoutID + ", FALSE)");

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
            sqlError(e);
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
                    "WHERE	WS.ScheduledDate = DATE'" + format.print(workoutSession.getDate()) + "'");
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
                        "WHERE		    WS.ScheduledDate = DATE'" + format.print(workoutSession.getDate()) + "'");
            }
            resultSet.close();
		}
        catch (Exception e) {
            sqlError(e);
        }
    }

    /**
     * Toggles the completed state of a workout in the database
     *
     * @param workoutSession the workout to change the state of
     */
    @Override
    public void toggleWorkoutComplete(WorkoutSession workoutSession) {
        int workoutSessionID, previousXPValue = 0;

        try {
            //get the ID of the workout session to be updated
            resultSet = statement.executeQuery(
                    "SELECT     WS.ID" +
                    "FROM       WorkoutSessions WS " +
                    "WHERE      WS.ScheduledDate = DATE'" + format.print(workoutSession.getDate()) + "'");
            if (resultSet.next()) {
                workoutSessionID = resultSet.getInt("ID");

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
            sqlError(e);
        }
    }

    /**
     * Gets a list of all workouts in the database
     *
     * @return a list of all workouts in the database
     */
    public List<Workout> getWorkoutsList() {
        ArrayList<Workout> workoutList = new ArrayList<>();
        ArrayList<WorkoutExercise> workoutExerciseList = new ArrayList<>();
        Exercise exercise;

        String workoutName = null, exerciseName, distanceUnitString, timeUnitString, weightUnitString;
        int xpValue, duration, sets, reps;
        double distance, weight;
        DistanceUnit distanceUnit = null;
        TimeUnit timeUnit = null;
        WeightUnit weightUnit = null;
        ExerciseIntensity intensity;
        ExerciseType type;
        boolean workoutFavourite = false;

        try
        {
            //get the list of workouts from the db
            resultSet = statement.executeQuery(
                    "SELECT		W.Name AS WorkoutName, " +
                    "			E.Name AS ExerciseName, " +
                    "			EI.Intensity, " +
                    "			ET.Type, " +
                    "			W.Favourite, " +
                    "			WE.Distance, " +
                    "			DiU.DistanceUnit, " +
                    "			WE.Duration, " +
                    "			DuU.DurationUnit, " +
                    "			WE.Sets, " +
                    "			WE.Reps, " +
                    "			WE.Weight, " +
                    "			WU.WeightUnit " +
                    "FROM		Workouts W " +
                    "LEFT JOIN	WorkoutContents WC " +
                    "			ON WC.WorkoutID = W.ID " +
                    "LEFT JOIN	WorkoutExercises WE " +
                    "			ON WC.ExerciseID = WE.ID " +
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
                    "           ON E.TypeID = ET.ID ");

            while (resultSet.next()) {
                //if the name of the workout hasn't been set yet, get the workout information
                if (workoutName == null) {
                    workoutName = resultSet.getString("WorkoutName");
                    workoutFavourite = resultSet.getBoolean("Favourite");
                }
                //if the name of the workout has changed, add the old workout and create a new one
                else if (!workoutName.equals(resultSet.getString("WorkoutName"))) {
                    workoutList.add(new Workout(workoutName, workoutFavourite, workoutExerciseList.toArray(new WorkoutExercise[workoutExerciseList.size()])));
                    workoutExerciseList = new ArrayList<>();

                    workoutName = resultSet.getString("WorkoutName");
                    workoutFavourite = resultSet.getBoolean("Favourite");
                }

                //build an exercise from the query results
                exerciseName = resultSet.getString("ExerciseName");
                intensity = ExerciseIntensity.valueOf(resultSet.getString("Intensity"));
                type = ExerciseType.valueOf(resultSet.getString("Type"));
                exercise = new Exercise(exerciseName, intensity, type);

                //build a workout exercise using the exercise
                xpValue = XP_PER_INTENSITY * ExerciseIntensity.valueOf(resultSet.getString("Intensity")).ordinal();
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

                workoutExerciseList.add(createWorkoutExercise(exercise, xpValue, distance, distanceUnit,
                        duration, timeUnit, sets, reps, weight, weightUnit));
            }

            //create and add the final workout
            if (workoutName != null)
                workoutList.add(new Workout(workoutName, workoutFavourite, workoutExerciseList.toArray(new WorkoutExercise[workoutExerciseList.size()])));

            resultSet.close();
        }
        catch (Exception e) {
            sqlError(e);
        }
        return workoutList;
    }

    /**
     * Gets a list of names of all workouts in the database
     *
     * @return a list of names of all workouts in the database
     */
    public List<String> getWorkoutNamesList() {
        String workoutName;
        List<String> nameList = new ArrayList<>();

        try
        {
            resultSet = statement.executeQuery(
                    "SELECT *" +
                    "FROM   Workouts");
            while (resultSet.next())
            {
                workoutName = resultSet.getString("Name");
                nameList.add(workoutName);
            }
            resultSet.close();
        }
        catch (Exception e) {
            sqlError(e);
        }
        return nameList;
    }

    /**
     * Retrieves a workout from the database with the name given as parameter
     *
     * @param workoutName the name of the workout to retrieve from the database
     * @return The workout with name workoutName, or null if no workout exists with that name
     */
    @Override
    public Workout getWorkout(String workoutName) {
        Workout workout = null;
        Workout maybeWorkout;
        List<Workout> workoutList = getWorkoutsList();
        Iterator<Workout> workoutIterator = workoutList.iterator();

        //loop through all workouts until we find the one that we need, and return it
        while(workoutIterator.hasNext() && workout == null){
            maybeWorkout = workoutIterator.next();
            if(maybeWorkout.getName().equals(workoutName)){
                workout = maybeWorkout;
            }
        }

        return workout;
    }

    /**
     * Returns the list of all completed workout records
     *
     * @return a list of all completed workout records
     */
    @Override
    public List<CompletedWorkoutRecord> getCompletedWorkoutsInDateRange() {
        List<CompletedWorkoutRecord> completedWorkoutRecordList = new ArrayList<>();
        CompletedWorkoutRecord completedWorkoutRecord = null;
        String workoutName = null;
        DateTime loggedDate = null;
        int currentXP = -1;
        int previousXP;

        try
        {
            resultSet = statement.executeQuery(
                    "SELECT         W.Name, " +
                    "               PH.LoggedDate, " +
                    "               PH.CurrentXP " +
                    "FROM           ProgressHistory PH " +
                    "LEFT JOIN      WorkoutSession WS" +
                    "               ON PH.WorkoutSessionID = WS.ID " +
                    "LEFT JOIN      Workouts W" +
                    "               ON WS.WorkoutID = W.ID " +
                    "ORDER BY       PH.LoggedDate DESC ");

            while (resultSet.next())
            {
                previousXP = resultSet.getInt("CurrentXP");

                if (workoutName != null) {
                    completedWorkoutRecord = new CompletedWorkoutRecord(workoutName, previousXP, currentXP, loggedDate);
                    completedWorkoutRecordList.add(completedWorkoutRecord);
                }

                workoutName = resultSet.getString("Name");
                loggedDate = new DateTime(resultSet.getDate("LoggedDate"));
                currentXP = previousXP;
            }

            if (workoutName != null) {
                completedWorkoutRecord = new CompletedWorkoutRecord(workoutName, 0, currentXP, loggedDate);
                completedWorkoutRecordList.add(completedWorkoutRecord);
            }

            resultSet.close();
        }
        catch (Exception e) {
            sqlError(e);
        }

        return completedWorkoutRecordList;
    }

    /**
     * Returns the most recent completed workout
     *
     * @return the most recent completed workout
     */
    @Override
    public CompletedWorkoutRecord getMostRecentCompletedWorkout() {
        CompletedWorkoutRecord completedWorkoutRecord = null;
        String workoutName;
        DateTime loggedDate;
        int currentXP;
        int previousXP = 0;

        try {
            resultSet = statement.executeQuery(
                    "SELECT TOP 2   W.Name, " +
                    "               PH.LoggedDate, " +
                    "               PH.CurrentXP " +
                    "FROM           ProgressHistory PH " +
                    "LEFT JOIN      WorkoutSession WS" +
                    "               ON PH.WorkoutSessionID = WS.ID " +
                    "LEFT JOIN      Workouts W" +
                    "               ON WS.WorkoutID = W.ID " +
                    "ORDER BY       PH.LoggedDate DESC ");

            if (resultSet.next())
            {
                workoutName = resultSet.getString("Name");
                loggedDate = new DateTime(resultSet.getDate("LoggedDate"));
                currentXP = resultSet.getInt("CurrentXP");

                if (resultSet.next())
                    previousXP = resultSet.getInt("CurrentXP");

                completedWorkoutRecord = new CompletedWorkoutRecord(workoutName, previousXP, currentXP, loggedDate);
            }

            resultSet.close();
        }
        catch (Exception e) {
            sqlError(e);
        }

        return completedWorkoutRecord;
    }

    /**
     * Handles the creation of a workout exercise based on the values that are stored with a workout exercise
     * in the database
     *
     * @param exercise the exercise for the workout exercise
     * @param xpValue the xp value for the workout exercise
     * @param distance the recommended distance
     * @param distanceUnit the unit of measure for the distance
     * @param duration the recommended duration
     * @param timeUnit the unit of measure for the duration
     * @param sets the recommended number of sets
     * @param reps the recommended number of reps
     * @param weight the recommended weight
     * @param weightUnit the unit of measure for the weight
     * @return the workout exercise based on the passed values
     */
    private WorkoutExercise createWorkoutExercise(Exercise exercise, int xpValue, double distance,
                                                  DistanceUnit distanceUnit, int duration, TimeUnit timeUnit,
                                                  int sets, int reps, double weight, WeightUnit weightUnit) {
        WorkoutExercise workoutExercise = null;
        InterfaceExerciseQuantity exerciseQuantity;

        if (distance != NULL_NUM && distanceUnit != null) {
            exerciseQuantity = new ExerciseDistance(distance, distanceUnit);
            workoutExercise = new WorkoutExerciseDistance(exercise, xpValue, (ExerciseDistance)exerciseQuantity);
        }
        else if (duration != NULL_NUM && timeUnit != null) {
            exerciseQuantity = new ExerciseDuration(duration, timeUnit);
            workoutExercise = new WorkoutExerciseDuration(exercise, xpValue, (ExerciseDuration)exerciseQuantity);

        }
        else if (sets != NULL_NUM && reps != NULL_NUM) {
            exerciseQuantity = new ExerciseSets(sets, reps);
            workoutExercise = new WorkoutExerciseSets(exercise, xpValue, (ExerciseSets)exerciseQuantity);

        }
        else if (weight != NULL_NUM && weightUnit != null) {
            exerciseQuantity = new ExerciseSetsAndWeight(sets, reps, weight, weightUnit);
            workoutExercise = new WorkoutExerciseSetsAndWeight(exercise, xpValue, (ExerciseSetsAndWeight)exerciseQuantity);
        }

        return workoutExercise;
    }

    /**
     * Gets the error message message of an SQL exception and prints the stack trace
     * @param e the exception thrown
     * @return the error message for the SQL exception
     */
    private String sqlError(Exception e)
    {
        String result; //the error message to print

        result = "SQL Error: " + e.getMessage();
        e.printStackTrace();;

        return result;
    }
}
