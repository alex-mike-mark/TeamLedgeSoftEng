package ledge.muscleup.model;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import ledge.muscleup.model.workout.InterfaceTrackableWorkout;

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
    private Map<LocalDate, InterfaceTrackableWorkout> workoutMap;
    private Iterator<Map.Entry<LocalDate, InterfaceTrackableWorkout>> workoutMapIterator;

    public ScheduleManager() {
        firstDayOfWeek = new LocalDate();
        workoutMap = new LinkedHashMap<>();
        //TODO Update with call to ScheduleAccess class to get scheduled workouts for a given week
    }

    /**
     * Returns a Date representing the given day for the current week
     *
     * @param dayOfWeek the day of the current week to return
     * @throws IllegalArgumentException if {@code dayOfWeek <= 0 || dayOfWeek > 7}
     * @return the day of the current week
     */
    @Override
    public LocalDate getWeekday(int dayOfWeek) {
        LocalDate weekday = null;

        if (dayOfWeek <= 0 || dayOfWeek > 7)
            throw new IllegalArgumentException();
        else if (dayOfWeek != DateTimeConstants.SUNDAY)
            weekday = firstDayOfWeek.plusDays(dayOfWeek);

        return weekday;
    }

    /**
     * Returns {@code true} if the given day of the week has no workouts scheduled, or {@code false}
     * otherwise
     *
     * @param dayOfWeek the day of the current week to check
     * @throws IllegalArgumentException if {@code dayOfWeek <= 0 || dayOfWeek > 7}
     * @return a boolean representing whether the given day has no scheduled workouts
     */
    @Override
    public boolean dayEmpty(int dayOfWeek) {
        if (dayOfWeek <= 0 || dayOfWeek > 7)
            throw new IllegalArgumentException();
        else
            return workoutMap.containsKey(firstDayOfWeek.plusDays(dayOfWeek));
    }

    /**
     * Sets the manager to contain the scheduled workouts for the previous week
     */
    @Override
    public void lastWeek() {
        firstDayOfWeek.minusWeeks(1);
        //TODO Update with call to ScheduleAccess class to get scheduled workouts for new week
    }

    /**
     * Sets the manager to contain the scheduled workouts for the following week
     */
    @Override
    public void nextWeek() {
        firstDayOfWeek.plusWeeks(1);
        //TODO Update with call to ScheduleAccess class to get scheduled workouts for new week
    }

    /**
     * Adds a workout to a given day
     *
     * @param workout the workout to add
     * @param date    the date to add the workout to
     */
    @Override
    public void addWorkout(InterfaceTrackableWorkout workout, LocalDate date) {
        workoutMap.put(date, workout);
    }

    /**
     * Removes a workout from a given day
     *
     * @param date the day to remove the workout from
     * @return a boolean representing if a workout was removed
     */
    @Override
    public boolean removeExercise(LocalDate date) {
        boolean removed = false;

        if (workoutMap.remove(date) != null)
            removed = true;

        return removed;
    }

    /**
     * Initializes the iterator for the list of workouts to the first InterfaceTrackableWorkout in
     * the list
     */
    @Override
    public void initWorkoutIteration() {
        workoutMapIterator = workoutMap.entrySet().iterator();
    }

    /**
     * Returns {@code true} if there is another InterfaceTrackableWorkout in the list or
     * {@code false} if not
     *
     * @return a boolean representing whether the iterator is at the end of the list or not
     */
    @Override
    public boolean hasNextWorkout() {
        return workoutMapIterator.hasNext();
    }

    /**
     * Returns the next InterfaceTrackableWorkout in the list
     *
     * @return the next InterfaceTrackableWorkout in the list
     * @throws NoSuchElementException if the iterator is at the end of the list
     */
    @Override
    public InterfaceTrackableWorkout nextWorkout() throws NoSuchElementException {
        try {
            return workoutMapIterator.next().getValue();
        } catch (NoSuchElementException nsee) {
            throw nsee;
        }
    }

    /**
     * Returns the ScheduleManager as a String
     * @return the ScheduleManager as a String
     */
    public String toString() {
        String result = "";
        Iterator<Map.Entry<LocalDate, InterfaceTrackableWorkout>> tempIterator;

        tempIterator = workoutMap.entrySet().iterator();
        while(tempIterator.hasNext())
            result += tempIterator.next().getValue().toString() + "\n";

        return result;
    }
}
