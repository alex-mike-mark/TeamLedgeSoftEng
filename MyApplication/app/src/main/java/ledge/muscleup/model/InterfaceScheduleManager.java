package ledge.muscleup.model;

import org.joda.time.LocalDate;

import java.util.NoSuchElementException;

import ledge.muscleup.model.workout.InterfaceTrackableWorkout;

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
     * @throws IllegalArgumentException if {@code dayOfWeek <= 0 || dayOfWeek > 7}
     * @return the day of the current week
     */
    LocalDate getWeekday(int dayOfWeek) throws IllegalArgumentException;

    /**
     * Returns {@code true} if the given day of the week has no workouts scheduled, or {@code false}
     * otherwise
     *
     * @param dayOfWeek the day of the current week to check
     * @throws IllegalArgumentException if {@code dayOfWeek <= 0 || dayOfWeek > 7}
     * @return a boolean representing whether the given day has no scheduled workouts
     */
    boolean dayEmpty(int dayOfWeek) throws IllegalArgumentException;

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
     * @param date the date to add the workout to
     */
    void addWorkout(InterfaceTrackableWorkout workout, LocalDate date);

    /**
     * Removes a workout from a given day
     * @param date the day to remove the workout from
     * @return a boolean representing if a workout was removed
     */
    boolean removeExercise(LocalDate date);

    /**
     * Initializes the iterator for the list of workouts to the first InterfaceTrackableWorkout in
     * the list
     */
    void initWorkoutIteration();

    /**
     * Returns {@code true} if there is another InterfaceTrackableWorkout in the list or
     * {@code false} if not
     *
     * @return a boolean representing whether the iterator is at the end of the list or not
     */
    boolean hasNextWorkout();

    /**
     * Returns the next InterfaceTrackableWorkout in the list
     *
     * @return the next InterfaceTrackableWorkout in the list
     * @throws NoSuchElementException if the iterator is at the end of the list
     */
    InterfaceTrackableWorkout nextWorkout() throws NoSuchElementException;

    /**
     * Returns the InterfaceScheduleManager as a String
     *
     * @return the InterfaceScheduleManager as a String
     */
    String toString();
}
