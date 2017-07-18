package ledge.muscleup.persistence;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.enums.DistanceUnit;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.exercise.enums.TimeUnit;
import ledge.muscleup.model.exercise.enums.WeightUnit;
import ledge.muscleup.model.workout.Workout;

/**
 * The data access class for workout data
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-13
 */
public class WorkoutDataAccess implements InterfaceWorkoutDataAccess {
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Opens the WorkoutDataAccess
     * @param statement the statement to use in WorkoutDataAccess queries
     */
    public void open(Statement statement) {
        this.statement = statement;
    }

    /**
     * Close the WorkoutDataAccess
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
     * Gets a list of all workouts in the database
     *
     * @return a list of all workouts in the database
     */
    @Override
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

        try
        {
            //get the list of workouts from the db
            resultSet = statement.executeQuery(
                    "SELECT		W.Name AS WorkoutName, " +
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
                }
                //if the name of the workout has changed, add the old workout and create a new one
                else if (!workoutName.equals(resultSet.getString("WorkoutName"))) {
                    workoutList.add(new Workout(workoutName, workoutExerciseList.toArray(new WorkoutExercise[workoutExerciseList.size()])));
                    workoutExerciseList = new ArrayList<>();

                    workoutName = resultSet.getString("WorkoutName");
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
                    distance = DataAccess.NULL_NUM;
                distanceUnitString = resultSet.getString("DistanceUnit");
                if (!resultSet.wasNull())
                    distanceUnit = DistanceUnit.valueOf(distanceUnitString);

                duration = resultSet.getInt("Duration");
                if (resultSet.wasNull())
                    duration = DataAccess.NULL_NUM;
                timeUnitString = resultSet.getString("DurationUnit");
                if (!resultSet.wasNull())
                    timeUnit = TimeUnit.valueOf(timeUnitString);

                sets = resultSet.getInt("Sets");
                if (resultSet.wasNull())
                    sets = DataAccess.NULL_NUM;
                reps = resultSet.getInt("Reps");
                if (resultSet.wasNull())
                    reps = DataAccess.NULL_NUM;

                weight = resultSet.getDouble("Weight");
                if (resultSet.wasNull())
                    weight = DataAccess.NULL_NUM;
                weightUnitString = resultSet.getString("WeightUnit");
                if (!resultSet.wasNull())
                    weightUnit = WeightUnit.valueOf(weightUnitString);

                workoutExerciseList.add(DataAccess.createWorkoutExercise(exercise, xpValue, distance, distanceUnit,
                        duration, timeUnit, sets, reps, weight, weightUnit));
            }

            //create and add the final workout
            if (workoutName != null)
                workoutList.add(new Workout(workoutName, workoutExerciseList.toArray(new WorkoutExercise[workoutExerciseList.size()])));

            resultSet.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }
        return workoutList;
    }

    /**
     * Gets a list of names of all exercises in the database
     *
     * @return a list of names of all workouts in the database
     */
    @Override
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
            DataAccess.sqlError(e);
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
     * Retrieves the name of a the workout that has been completed the least amount of times
     *
     * @return the workout that has been ocmpleted the least amount of times
     */
    @Override
    public String getLeastCompletedWorkout() {
        String workoutName = null;

        try
        {
            resultSet = statement.executeQuery(
                    "SELECT TOP 1   W.Name " +
                    "FROM           ( " +
                    "                   SELECT      W.Name, " +
                    "                               COUNT(W.Name) AS WorkoutFreq " +
                    "                   FROM        Workouts W " +
                    "                   RIGHT JOIN  WorkoutSessions WS " +
                    "                               ON W.ID = WS.WorkoutID " +
                    "                   RIGHT JOIN  ProgressHistory PH " +
                    "                               ON WS.ID = PH.WorkoutSessionID " +
                    "                   WHERE		DATEDIFF('day', PH.LoggedDate, CURRENT_TIMESTAMP) > 0 " +
                    "                   GROUP BY    W.Name " +
                    "                   ORDER BY    WorkoutFreq DESC " +
                    "               ) WF " +
                    "RIGHT JOIN     Workouts W " +
                    "               ON WF.Name = W.Name " +
                    "ORDER BY       WF.WorkoutFreq ");

            if (resultSet.next())
                workoutName = resultSet.getString("Name");

            resultSet.close();
        }
        catch (Exception e) {
            DataAccess.sqlError(e);
        }

        return workoutName;
    }
}
