package ledge.muscleup.business;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import ledge.muscleup.model.workout.InterfaceWorkoutSession;
import ledge.muscleup.model.workout.WorkoutSession;

import static java.util.Collections.enumeration;

/**
 * Manages a week of scheduled workouts, which includes methods to increment or decrement the current
 * week and iterate through the scheduled workouts
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-05
 */

public class ScheduleManager implements InterfaceScheduleManager {
    private LocalDate firstDayOfWeek;
    private InterfaceWorkoutSession[] workouts;

    public ScheduleManager() {
        firstDayOfWeek = new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY);
        workouts = new InterfaceWorkoutSession[DateTimeConstants.DAYS_PER_WEEK];
        //TODO - requires database lookup
    }

    /**
     * Returns a Date representing the given day for the current week
     *
     * @param dayOfWeek the day of the current week to return
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return the day of the current week
     */
    @Override
    public LocalDate getWeekday(int dayOfWeek) throws IllegalArgumentException {
        LocalDate weekday = firstDayOfWeek;

        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else if (dayOfWeek != DateTimeConstants.MONDAY)
            weekday = weekday.plusDays(dayOfWeek);

        return weekday;
    }

    /**
     * Returns the workout for a given day of the week
     *
     * @param dayOfWeek the day of the week to get the workout for
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return the workout scheduled on that day of the week, or {@code null} if the day was empty
     */
    @Override
    public InterfaceWorkoutSession getScheduledWorkout(int dayOfWeek) throws IllegalArgumentException {
        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else
            return workouts[dayOfWeek];
    }

    /**
     * Returns {@code true} if the given day of the week has no workouts scheduled, or {@code false}
     * otherwise
     *
     * @param dayOfWeek the day of the current week to check
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return a boolean representing whether the given day has no scheduled workouts
     */
    @Override
    public boolean isDayEmpty(int dayOfWeek) throws IllegalArgumentException {
        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else
            return workouts[dayOfWeek - 1] != null;
    }

    /**
     * Sets the manager to contain the scheduled workouts for the previous week
     */
    @Override
    public void lastWeek() {
        firstDayOfWeek.minusWeeks(1);
        //TODO - requires database update
    }

    /**
     * Sets the manager to contain the scheduled workouts for the following week
     */
    @Override
    public void nextWeek() {
        firstDayOfWeek.plusWeeks(1);
        //TODO - requires database update
    }

    /**
     * Adds a workout to a given day
     *
     * @param workout the workout to add
     * @param dayOfWeek the day of the week to add the workout to
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     */
    @Override
    public void addWorkout(InterfaceWorkoutSession workout, int dayOfWeek) throws IllegalArgumentException {
        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
            workouts[dayOfWeek - 1] = workout;
            //TODO - requires database update
        }
    }

    /**
     * Removes a workout from a given day
     *
     * @param dayOfWeek the day to remove the workout from
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return a boolean representing if a workout was removed
     */
    @Override
    public boolean removeWorkout(int dayOfWeek) throws IllegalArgumentException {
        boolean removed = false;

        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else if (workouts[dayOfWeek] != null) {
            workouts[dayOfWeek] = null;
            removed = true;
            //TODO - requires database update
        }

        return removed;
    }

    /**
     * Returns the current week as a String
     *
     * @return the current week as a String
     */
    public String toString() {
        String result = "";

        for (int i = 0; i < workouts.length; i++)
            result += workouts[i].toString() + "\n";

        return result;
    }

    /**
     * Returns if a given weekday is valid
     * @param dayOfWeek the day of the week to check
     * @return a boolean representing if the given weekday is valid
     */
    private boolean isDayWithinWeek(int dayOfWeek) {
        return dayOfWeek >= DateTimeConstants.MONDAY && dayOfWeek <= DateTimeConstants.SUNDAY;
    }
}
