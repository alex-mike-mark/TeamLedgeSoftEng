package ledge.muscleup.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
import ledge.muscleup.model.exercise.enums.DistanceUnit;
import ledge.muscleup.model.exercise.enums.TimeUnit;
import ledge.muscleup.model.exercise.enums.WeightUnit;

/**
 * This is THE REAL implementation of the HSQLDB
 *
 * @author Cole Kehler
 * @version 2.0
 * @since 2017-06-27
 */
public class DataAccess implements InterfaceDataAccess {
    private static final String SHUTDOWN_CMD = "shutdown compact";

    static final int NULL_NUM = -1;
    static final int XP_PER_INTENSITY = 50;

    private String dbName;
    private String dbType = "HSQLDB";
    private String dbPathPrefix = "jdbc:hsqldb:file:";
    private Connection connection;

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
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeQuery(SHUTDOWN_CMD);
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            sqlError(e);
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    /**
     * Get a new statement from the database connection
     * @return a new statement
     */
    public Statement getNewStatement() {
        Statement statement = null;

        try {
            statement = connection.createStatement();
        }
        catch (Exception e) {
            sqlError(e);
        }

        return statement;
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
    static WorkoutExercise createWorkoutExercise(Exercise exercise, int xpValue, double distance,
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
            if (weight != NULL_NUM && weightUnit != null) {
                exerciseQuantity = new ExerciseSetsAndWeight(sets, reps, weight, weightUnit);
                workoutExercise = new WorkoutExerciseSetsAndWeight(exercise, xpValue, (ExerciseSetsAndWeight)exerciseQuantity);
            } else {
                exerciseQuantity = new ExerciseSets(sets, reps);
                workoutExercise = new WorkoutExerciseSets(exercise, xpValue, (ExerciseSets)exerciseQuantity);

            }
        }

        return workoutExercise;
    }

    /**
     * Gets the error message message of an SQL exception and prints the stack trace
     * @param e the exception thrown
     * @return the error message for the SQL exception
     */
    static String sqlError(Exception e)
    {
        String result; //the error message to print

        result = "SQL Error: " + e.getMessage();
        e.printStackTrace();

        return result;
    }
}
