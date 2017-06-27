package ledge.muscleup.business;

import org.joda.time.LocalDate;

import java.util.List;

import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * An Interface for communicating with the database to retrieve, add, and remove workout sessions
 * from the database
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceAccessWorkoutSessions {

    /**
     * This method gets a workout session from the database with the given date
     * @param dateOfSession the date of the workout session
     * @return a workout session from the database scheduled on the given date
     */
    WorkoutSession getWorkoutSession(LocalDate dateOfSession);

    /**
     * This method gets a list of workout sessions in the database
     * @return a list of the workout sessions stored in the database
     */
    List<WorkoutSession> getWorkoutSessionsList();

    /**
     * A method that returns a list of workout sessions scheduled in a date range
     * @param startDate the first date of the date range
     * @param endDate the last date of the date range
     * @return a list of all workout sessions scheduled between startDate and endDate, inclusive
     */
    List<WorkoutSession> getSessionsInDateRange(LocalDate startDate,
                                                       LocalDate endDate);

    /**
     * A method that returns a list of workout sessions scheduled in the current week
     * @return a list of all workout sessions scheduled in the current week
     */
    List<WorkoutSession> getCurrentWeekSessions();

    /**
     * Adds a new workout session to the database
     * @param workoutSession the workout session to be added to the database
     */
    void insertWorkoutSession(WorkoutSession workoutSession);

    /**
     * Removes a workout session from the database, if it exists
     * @param workoutSession the workout session to be removed
     */
    void removeWorkoutSession(WorkoutSession workoutSession);

    /**
     * Sets the scheduled date of a workout
     *
     * @param workoutSession the workout to change the date for
     * @param newDate the new date of the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    void setWorkoutDate(WorkoutSession workoutSession, LocalDate newDate) throws IllegalArgumentException;

    /**
     * Toggles the completed state of a workout
     *
     * @param workoutSession the workout to change the state of
     */
    void toggleWorkoutCompleted(WorkoutSession workoutSession);

    /**
     * Log an exercise in a workout as complete
     *
     * @param workoutSession the workout which contains the exercise
     * @param exercise the exercise to complete
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing whether the exercise was marked as completed or not
     */
    boolean completeWorkoutExercise(WorkoutSession workoutSession, WorkoutSessionExercise exercise) throws IllegalArgumentException;

    /**
     * Adds a workout session to a given day of a scheduled week
     *
     * @param scheduleWeek the week to add the workout to
     * @param workoutSession the workout session to add
     * @param dayOfWeek the day of the week to add the workout session to
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     */
    void addWorkoutSession(ScheduleWeek scheduleWeek, WorkoutSession workoutSession, int dayOfWeek) throws IllegalArgumentException;

    /**
     * Removes a workout from a given day of a scheduled week
     *
     * @param scheduleWeek the week to remove the workout from
     * @param dayOfWeek the day to remove the workout from
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return a boolean representing if a workout was removed
     */
    boolean removeWorkoutSession(ScheduleWeek scheduleWeek, int dayOfWeek) throws IllegalArgumentException;

    /**
     * Sets the manager to contain the scheduled workouts for the previous week
     *
     * @param scheduleWeek the week to change
     */
    void setToLastWeek(ScheduleWeek scheduleWeek);

    /**
     * Sets the manager to contain the scheduled workouts for the following week
     *
     * @param scheduleWeek the week to change
     */
    void setToNextWeek(ScheduleWeek scheduleWeek);

    /**
     * Sets the manager to contain the scheduled workouts for the current week
     * @param scheduleWeek the week to change
     */
    void setToCurrentWeek(ScheduleWeek scheduleWeek);

    /**
     * Creates a new ScheduleWeek based on the given date
     *
     * @param dayInWeek a day in the week to created a ScheduleWeek for
     * @return a ScheduleWeek, which contains all WorkoutSessions for the given week
     */
    ScheduleWeek newScheduledWeek(LocalDate dayInWeek);
}
