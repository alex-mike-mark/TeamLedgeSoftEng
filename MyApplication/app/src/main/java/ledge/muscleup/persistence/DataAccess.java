package ledge.muscleup.persistence;

import org.joda.time.LocalDate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
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

public class DataAccess implements InterfaceDataAccess {
    private final String SHUTDOWN_CMD = "shutdown compact";

    private String dbName;
    private String dbType = "HSQLDB";
    private String dbPathPrefix = "jdbc:hsqldb:file:";

    private Connection connection;
    private String command;
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
        return null;
    }

    /**
     * Gets a list of names of all exercises in the database
     *
     * @return a list of names of all exercises in the database
     */
    @Override
    public List<String> getExerciseNamesList() {
        return null;
    }

    /**
     * Retrieves an exercise from the database with the name given as parameter
     *
     * @param exerciseName - the name of the exercise to retrieve from the database
     * @return The exercise with name exerciseName, or null if no exercise exists with that name
     */
    @Override
    public Exercise getExercise(String exerciseName) {
        return null;
    }

    /**
     * Adds an exercise to the database
     *
     * @param exercise the exercise to be added to the database
     */
    @Override
    public void insertExercise(Exercise exercise) {

    }

    /**
     * Removes an exercise from the database, if it exists
     *
     * @param exercise the exercise to remove from the database
     */
    @Override
    public void removeExercise(Exercise exercise) {

    }

    /**
     * Gets a list of all workouts in the database
     *
     * @return a list of all workouts in the database
     */
    @Override
    public List<Workout> getWorkoutsList() {
        return null;
    }

    /**
     * Gets a list of names of all exercises in the database
     *
     * @return a list of names of all workouts in the database
     */
    @Override
    public List<String> getWorkoutNamesList() {
        return null;
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
     * Adds a workout to the database
     *
     * @param workout the workout to be added to the database
     */
    @Override
    public void insertWorkout(Workout workout) {

    }

    /**
     * Removes a workout from the database, if it exists
     *
     * @param workout the workout to remove from the database
     */
    @Override
    public void removeWorkout(Workout workout) {

    }

    /**
     * Updates the recommended quantity of exercise for a given exercise in a given workout in the database
     *
     * @param workout  the workout that contains the exercise to update
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @return a boolean representing if the exercise was found and updated in the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public boolean updateExerciseQuantity(Workout workout, WorkoutExercise exercise, InterfaceExerciseQuantity quantity) throws IllegalArgumentException {
        return false;
    }

    /**
     * Toggles the favourite state of an exercise in the database
     *
     * @param workout the workout to update the status of
     */
    @Override
    public void toggleExerciseFavourite(Workout workout) {

    }

    /**
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     *
     * @param workout  the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     * @return true if exercise was added successfully, false otherwise
     */
    @Override
    public boolean addWorkoutExercise(Workout workout, WorkoutExercise exercise) {
        return false;
    }

    /**
     * Move the position of an exercise in the list of exercises in the database
     *
     * @param workout  the workout to change the order of exercises for
     * @param exercise the exercise to change the position of
     * @param index    the index of the exercise to move
     * @return a boolean representing if the exercise was found and moved to the new index
     * @throws IllegalArgumentException if passed a {@code null} parameter or if {@code index} is
     *                                  outside the bounds of the list of exercises
     */
    @Override
    public boolean moveWorkoutExercise(Workout workout, WorkoutExercise exercise, int index) throws IllegalArgumentException {
        return false;
    }

    /**
     * Removes an exercise from a workout in the database
     *
     * @param workout  the workout to remove an exercise from
     * @param exercise the exercise to remove from the list
     * @return the exercise that was removed, or {@code null} if the exercise couldn't be found
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public boolean removeWorkoutExercise(Workout workout, WorkoutExercise exercise) throws IllegalArgumentException {
        return false;
    }

    /**
     * A method that returns a list of all workout sessions in the database
     *
     * @return a list of all workout sessions in the database
     */
    @Override
    public List<WorkoutSession> getWorkoutSessionsList() {
        return null;
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
     * Updates the scheduled date of a workout in the database
     *
     * @param workoutSession the workout to change the date for
     * @param newDate        the new date of the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public void updateWorkoutDate(WorkoutSession workoutSession, LocalDate newDate) throws IllegalArgumentException {

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
