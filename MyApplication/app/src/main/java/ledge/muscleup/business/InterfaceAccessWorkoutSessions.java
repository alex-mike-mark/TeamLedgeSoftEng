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
     * Toggles the completed state of a workout
     *
     * @param workoutSession the workout to change the state of
     */
    void toggleWorkoutCompleted(WorkoutSession workoutSession);

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
