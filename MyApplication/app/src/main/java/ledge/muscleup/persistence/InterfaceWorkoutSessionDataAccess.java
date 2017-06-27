package ledge.muscleup.persistence;

import org.joda.time.LocalDate;

import java.util.List;

import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * An interface for workout session database access, including methods for retrieving, inserting,
 * and removing workout sessions
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceWorkoutSessionDataAccess {

    /**
     * A method that returns a list of all workout sessions in the database
     * @return a list of all workout sessions in the database
     */
    List<WorkoutSession> getWorkoutSessionsList();

    /**
     * Retrieves a workout session scheduled on the given date from the database, if it exists. If
     * no workout session is found for that date, returns null.
     * @param dateOfSession the date to get the workout session for
     * @return the workout session scheduled on the given date
     */
    WorkoutSession getWorkoutSession(LocalDate dateOfSession);

    /**
     * A method that returns a list of workout sessions scheduled in a date range
     * @param startDate the first date of the date range
     * @param endDate the last date of the date range
     * @return a list of all workout sessions scheduled between startDate and endDate, inclusive
     */
    List<WorkoutSession> getSessionsInDateRange(LocalDate startDate,
                                                                LocalDate endDate);

    /**
     * Inserts a new workout session into the database
     * @param workoutSession the new workout session to insert into the database
     */
    void insertWorkoutSession(WorkoutSession workoutSession);

    /**
     * Removes a workout session from the database, if it exists
     * @param workoutSession the workout session to remove from the database
     */
    void removeWorkoutSession(WorkoutSession workoutSession);

    /**
     * Updates the scheduled date of a workout in the database
     *
     * @param workoutSession the workout to change the date for
     * @param newDate        the new date of the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    void updateWorkoutDate(WorkoutSession workoutSession, LocalDate newDate)
            throws IllegalArgumentException;

    /**
     * Toggles the completed state of a workout in the database
     *
     * @param workoutSession the workout to change the state of
     */
    void toggleWorkoutComplete(WorkoutSession workoutSession);

    /**
     * Toggles the completed state of an exercise in a workout in the database
     *
     * @param workoutSession the workout which contains the exercise
     * @param exercise       the exercise to complete
     * @return a boolean representing whether the exercise was marked as completed or not
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    boolean toggleExerciseComplete(WorkoutSession workoutSession, WorkoutSessionExercise exercise)
            throws IllegalArgumentException;

    /**
     * Adds a workout session to a given day in the database
     *
     * @param scheduleWeek the week to add the workout to
     * @param workoutSession the workout session to add
     * @param dayOfWeek the day of the week to add the workout session to
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     */
    void addWorkoutSession(ScheduleWeek scheduleWeek, WorkoutSession workoutSession, int dayOfWeek) throws IllegalArgumentException;

    /**
     * Removes a workout from a given day in the database
     *
     * @param scheduleWeek the week to remove the workout from
     * @param dayOfWeek the day to remove the workout from
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return a boolean representing if a workout was removed
     */
    boolean removeWorkoutSession(ScheduleWeek scheduleWeek, int dayOfWeek) throws IllegalArgumentException;
}
