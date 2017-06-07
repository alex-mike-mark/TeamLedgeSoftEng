package ledge.muscleup.business;

import org.joda.time.LocalDate;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import ledge.muscleup.model.workout.InterfaceWorkoutSession;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * An interface for the schedule manager, which holds a week of scheduled workouts
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-05
 */

public interface InterfaceScheduleManager {
    /**
     * Returns a Date representing the given day for the current week
     *
     * @param dayOfWeek the day of the current week to return
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return the day of the current week
     */
    LocalDate getWeekday(int dayOfWeek) throws IllegalArgumentException;

    /**
     * Returns the workout for a given day of the week
     * @param dayOfWeek the day of the week to get the workout for
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return the workout scheduled on that day of the week, or {@code null} if the day was empty
     */
    WorkoutSession getScheduledWorkout(int dayOfWeek) throws IllegalArgumentException;

    /**
     * Returns {@code true} if the given day of the week has no workouts scheduled, or {@code false}
     * otherwise
     *
     * @param dayOfWeek the day of the current week to check
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return a boolean representing whether the given day has no scheduled workouts
     */
    boolean isDayEmpty(int dayOfWeek) throws IllegalArgumentException;

    /**
     * Sets the manager to contain the scheduled workouts for the previous week
     */
    void lastWeek();

    /**
     * Sets the manager to contain the scheduled workouts for the following week
     */
    void nextWeek();

    /**
     * Adds a workout to a given day
     * @param workout the workout to add
     * @param dayOfWeek the day of the week to add the workout to
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     */
    void addWorkoutSession(WorkoutSession workout, int dayOfWeek) throws IllegalArgumentException;

    /**
     * Removes a workout from a given day
     * @param dayOfWeek the day to remove the workout from
     * @return a boolean representing if a workout was removed
     */
    boolean removeWorkoutSession(int dayOfWeek);

    /**
     * Returns the current week as a String
     *
     * @return the current week as a String
     */
    String toString();
}
