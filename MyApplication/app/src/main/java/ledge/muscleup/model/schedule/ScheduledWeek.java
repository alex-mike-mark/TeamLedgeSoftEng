package ledge.muscleup.model.schedule;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * Manages a week of scheduled workouts, which includes methods to increment or decrement the current
 * week and iterate through the scheduled workouts
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-05
 */

public class ScheduledWeek {
    private LocalDate firstDayOfWeek;
    private WorkoutSession[] workoutSessions;

    public ScheduledWeek(List<WorkoutSession> workoutList) {
        if (workoutList == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
            firstDayOfWeek = new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY);
            populateWorkoutList(workoutList);
        }
    }

    /**
     * Returns a Date representing the given day for the current week
     *
     * @param dayOfWeek the day of the current week to return
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return the day of the current week
     */
    public LocalDate getWeekday(int dayOfWeek) throws IllegalArgumentException {
        LocalDate weekday = firstDayOfWeek;

        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else if (dayOfWeek != DateTimeConstants.MONDAY)
            weekday = weekday.plusDays(dayOfWeek - 1);

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
    public WorkoutSession getScheduledWorkout(int dayOfWeek) throws IllegalArgumentException {
        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else
            return workoutSessions[dayOfWeek - 1];
    }

    /**
     * Returns a list of all workout sessions in the schedule for the current week
     *
     * @return the list of all workout session in the schedule for the current week
     */
    public List<WorkoutSession> getWorkoutSessionList() {
        ArrayList<WorkoutSession> workoutList = new ArrayList<>();

        for (int i = 0; i < workoutSessions.length; i++)
            if (workoutSessions[i] != null)
                workoutList.add(workoutSessions[i]);

        return workoutList;
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
    public boolean isDayEmpty(int dayOfWeek) throws IllegalArgumentException {
        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else
            return workoutSessions[dayOfWeek - 1] != null;
    }

    /**
     * Sets the manager to contain the scheduled workouts for the previous week
     */
    public void lastWeek(List<WorkoutSession> workoutList) {
        firstDayOfWeek = firstDayOfWeek.minusWeeks(1);
        populateWorkoutList(workoutList);
    }

    /**
     * Sets the manager to contain the scheduled workouts for the following week
     */
    public void nextWeek(List<WorkoutSession> workoutList) {
        firstDayOfWeek = firstDayOfWeek.plusWeeks(1);
        populateWorkoutList(workoutList);
    }

    /**
     * Adds a workout session to a given day
     *
     * @param workoutSession the workout session to add
     * @param dayOfWeek the day of the week to add the workout session to
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     */
    public void addWorkoutSession(WorkoutSession workoutSession, int dayOfWeek) throws IllegalArgumentException {
        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else
            workoutSessions[dayOfWeek - 1] = workoutSession;
    }

    /**
     * Removes a workout from a given day
     *
     * @param dayOfWeek the day to remove the workout from
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     * > DateTimeConstants.SUNDAY}
     * @return a boolean representing if a workout was removed
     */
    public boolean removeWorkoutSession(int dayOfWeek) throws IllegalArgumentException {
        boolean removed = false;

        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else if (workoutSessions[dayOfWeek - 1] != null) {
            workoutSessions[dayOfWeek - 1] = null;
            removed = true;
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

        for (int i = 0; i < workoutSessions.length; i++)
            result += workoutSessions[i].toString() + "\n";

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

    /**
     * Fills the list of workout sessions based on a given list of sessions
     */
    private void populateWorkoutList(List<WorkoutSession> sessionList) {
        WorkoutSession currSession;
        LocalDate lastDayOfWeek = getWeekday(DateTimeConstants.SUNDAY);

        workoutSessions = new WorkoutSession[DateTimeConstants.DAYS_PER_WEEK];

        for (int i = 0; i < sessionList.size(); i++) {
            currSession = sessionList.get(i);
            if (currSession.getDate().isBefore(firstDayOfWeek) ||
                    currSession.getDate().isAfter(lastDayOfWeek))
                throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
            else
                workoutSessions[currSession.getDate().getDayOfWeek() - 1] = currSession;
        }

        for (int i = 0; i < workoutSessions.length; i++)
            if (workoutSessions[i] == null)
                workoutSessions[i] = new WorkoutSession(firstDayOfWeek.plusDays(i));
    }
}
