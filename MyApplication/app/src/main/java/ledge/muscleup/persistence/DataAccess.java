package ledge.muscleup.persistence;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * This is it. THE REAL DEAL. ARE YOU READY?!?!?!?!?
 *
 * @author Cole Kehler
 * @version 2.0
 * @since 2017-06-27
 */

public class DataAccess implements InterfaceExerciseDataAccess, InterfaceWorkoutDataAccess, InterfaceWorkoutSessionDataAccess {
    private static final String SHUTDOWN_CMD = "shutdown compact";
    private static final DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final int NULL_NUM = -1;

    private String dbName;
    private String dbType = "HSQLDB";
    private String dbPathPrefix = "jdbc:hsqldb:file:";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

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
        return null;
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
        return null;
    }

    /**
     * Inserts a new workout session into the database
     *
     * @param workoutSession the new workout session to insert into the database
     */
    @Override
    public void insertWorkoutSession(WorkoutSession workoutSession) {

    }

    /**
     * Removes a workout session from the database, if it exists
     *
     * @param workoutSession the workout session to remove from the database
     */
    @Override
    public void removeWorkoutSession(WorkoutSession workoutSession) {

    }

    /**
     * Toggles the completed state of a workout in the database
     *
     * @param workoutSession the workout to change the state of
     */
    @Override
    public void toggleWorkoutComplete(WorkoutSession workoutSession) {

    }

    /**
     * Toggles the completed state of an exercise in a workout in the database
     *
     * @param workoutSession the workout which contains the exercise
     * @param exercise       the exercise to complete
     * @return a boolean representing whether the exercise was marked as completed or not
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public boolean toggleExerciseComplete(WorkoutSession workoutSession, WorkoutSessionExercise exercise) throws IllegalArgumentException {
        return false;
    }

    /**
     * Adds a workout session to a given day in the database
     *
     * @param scheduleWeek   the week to add the workout to
     * @param workoutSession the workout session to add
     * @param dayOfWeek      the day of the week to add the workout session to
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     *                                  > DateTimeConstants.SUNDAY}
     */
    @Override
    public void addWorkoutSession(ScheduleWeek scheduleWeek, WorkoutSession workoutSession, int dayOfWeek) throws IllegalArgumentException {

    }

    /**
     * Removes a workout from a given day in the database
     *
     * @param scheduleWeek the week to remove the workout from
     * @param dayOfWeek    the day to remove the workout from
     * @return a boolean representing if a workout was removed
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     *                                  > DateTimeConstants.SUNDAY}
     */
    @Override
    public boolean removeWorkoutSession(ScheduleWeek scheduleWeek, int dayOfWeek) throws IllegalArgumentException {
        return false;
    }

    /**
     * Gets a list of all workouts in the database
     *
     * @return a list of all workouts in the database
     */
    @Override
    public List<Workout> getWorkoutsList() {
        ArrayList<Workout> workoutList = new ArrayList<>();
        Exercise exercise = null;
        Workout workout = null;
        WorkoutExercise workoutExercise = null;

        String workoutName = null, exerciseName = null;
        int xpValue = NULL_NUM, duration = NULL_NUM, sets = NULL_NUM, reps = NULL_NUM;
        double distance = NULL_NUM, weight =NULL_NUM;
        DistanceUnit distanceUnit = null;
        TimeUnit timeUnit = null;
        WeightUnit weightUnit = null;
        LocalDate scheduledDate = null;
        ExerciseIntensity intensity = null;
        ExerciseType type = null;
        boolean workoutFavourite = false, exerciseFavourite = false, workoutComplete = false, exerciseComplete = false;

        try
        {
                    resultSet = statement.executeQuery(
                                    "SELECT     W.Name AS WorkoutName, " +
                                    "           E.Name," +
                                    "           "
                                    "FROM       Workouts W " +
                                    "LEFT JOIN  WorkoutContents WC " +
                                    "           ON W.ID = WC.WorkoutID " +
                                    "LEFT JOIN  WorkoutExercises WE " +
                                    "           ON WE.ID = WC.ExerciseID " +
                                    "LEFT JOIN  Exercises E " +
                                    "           ON E.ID = WE.ExerciseID " +
                                    "LEFT JOIN  DistanceUnits DIU " +
                                      "           ON DIU.ID = WE.DistanceUnitID " +
                                      "LEFT JOIN  DurationUnits DUU " +
                                    "           ON DUU.ID = WE.DurationUnitID " +
                                    "LEFT JOIN  WeightUnits WU " +
                                    "           ON WU.ID = WE.WeightUnitID " +
                                    "LEFT JOIN  ExerciseIntensities EI " +
                                    "           ON E.IntensityID = EI.ID " +
                                    "LEFT JOIN  ExerciseTypes ET "  +
                                    "           ON E.TypeID = ET.ID");

            while (resultSet.next())
            {
                if(workoutName == null){

                }
                workoutName = resultSet.getString("Name");
                workout = new Workout(workoutName);

                exerciseName = resultSet.getString("ExerciseName");
                intensity = ExerciseIntensity.valueOf(resultSet.getString("Intensity"));
                type = ExerciseType.valueOf(resultSet.getString("Type"));
                exerciseFavourite = resultSet.getBoolean("Favourite");
                exercise = new Exercise(exerciseName, intensity, type, exerciseFavourite);

                xpValue = XP_PER_INTENSITY * ExerciseIntensity.valueOf(resultSet.getString("Intensity")).ordinal();
                distance = resultSet.getDouble("Distance");
                distanceUnit = DistanceUnit.valueOf(resultSet.getString("DistanceUnit"));
                duration = resultSet.getInt("Duration");
                timeUnit = TimeUnit.valueOf(resultSet.getString("DurationUnit"));
                sets = resultSet.getInt("Sets");
                reps = resultSet.getInt("Reps");
                weight = resultSet.getDouble("Weight");
                weightUnit = WeightUnit.valueOf(resultSet.getString("WeightUnit"));
                workoutExercise = createWorkoutExercise(exercise, xpValue, distance, distanceUnit, duration,
                        timeUnit, sets, reps, weight, weightUnit);


                workoutList.add(workout);
            }
            resultSet.close();
        } catch (Exception e)
        {
            sqlError(e);
        }
        return workoutList;
    }

    /**
     * Gets a list of names of all workouts in the database
     *
     * @return a list of names of all workouts in the database
     */
    @Override
    public List<String> getWorkoutNamesList() {
        String workoutName = null;
        List<String> nameList = new ArrayList<>();

        try
        {
            resultSet = statement.executeQuery("Select * from Workouts");
            while (resultSet.next())
            {
                workoutName = resultSet.getString("Name");
                nameList.add(workoutName);
            }
            resultSet.close();
        } catch (Exception e)
        {
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
        return null;
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
        InterfaceExerciseQuantity exerciseQuantity = null;

        if (distance != NULL_NUM) {
            exerciseQuantity = new ExerciseDistance(distance, distanceUnit);
            workoutExercise = new WorkoutExerciseDistance(exercise, xpValue, (ExerciseDistance)exerciseQuantity);
        }
        else if (duration != NULL_NUM) {
            exerciseQuantity = new ExerciseDuration(duration, timeUnit);
            workoutExercise = new WorkoutExerciseDuration(exercise, xpValue, (ExerciseDuration)exerciseQuantity);

        }
        else if (sets != NULL_NUM && reps != NULL_NUM) {
            exerciseQuantity = new ExerciseSets(sets, reps);
            workoutExercise = new WorkoutExerciseSets(exercise, xpValue, (ExerciseSets)exerciseQuantity);

        }
        else if (weight != NULL_NUM) {
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
