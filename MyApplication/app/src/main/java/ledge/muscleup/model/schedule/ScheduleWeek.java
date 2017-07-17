package ledge.muscleup.model.schedule;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ledge.muscleup.model.workout.WorkoutSession;

/**
 * Manages a week of scheduled workouts, which includes methods to increment or decrement the current
 * week and iterate through the scheduled workouts
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-05
 */

public class ScheduleWeek {
    private LocalDate firstDayOfWeek;
    private WorkoutSession[] workoutSessions;

    /**
     * Constructor for a ScheduleWeek object, which takes the day of week the week should start on
     * as an int (1= Monday... 7 = Sunday), as well as a list of workout sessions
     * @param weekStartDay day to start week at
     * @param workoutSessionList workout session to be scheduled
     */
    public ScheduleWeek(int weekStartDay, List<WorkoutSession> workoutSessionList) {
        if (workoutSessionList == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
            firstDayOfWeek = new LocalDate().withDayOfWeek(weekStartDay);
            if (firstDayOfWeek.isAfter(LocalDate.now())) {
                firstDayOfWeek = firstDayOfWeek.minusWeeks(1);
            }
            populateWorkoutList(workoutSessionList);
        }
    }

    /**
     * Returns the first date of the week
     * @return the first date of the week
     */
    public LocalDate getFirstDayOfWeek() {
        return firstDayOfWeek;
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
        else if (dayOfWeek != firstDayOfWeek.getDayOfWeek())
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
        WorkoutSession scheduledWorkout;
        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
           LocalDate scheduledDate = firstDayOfWeek.withDayOfWeek(dayOfWeek);
            if (scheduledDate.isBefore(firstDayOfWeek)) {
                scheduledDate = scheduledDate.plusWeeks(1);
            }
            int workoutSessionIndex = Days.daysBetween(firstDayOfWeek, scheduledDate).getDays();
            scheduledWorkout = workoutSessions[workoutSessionIndex];
        }
        return scheduledWorkout;
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
        boolean isEmpty = false;
        if (!isDayWithinWeek(dayOfWeek))
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else {
            LocalDate scheduleDate = firstDayOfWeek.withDayOfWeek(dayOfWeek);
            if (scheduleDate.isBefore(firstDayOfWeek)) {
                scheduleDate = scheduleDate.plusWeeks(1);
            }
            int workoutSessionIndex = Days.daysBetween(firstDayOfWeek, scheduleDate).getDays();
            if (workoutSessions[workoutSessionIndex] == null || workoutSessions[workoutSessionIndex].getName() == null) {
                isEmpty = true;
            }
        }
        return isEmpty;
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
     * Gets the manager to contain the scheduled workouts for the week containing today's date
     */
    public void currentWeek(List<WorkoutSession> workoutList) {
        firstDayOfWeek = LocalDate.now().withDayOfWeek(firstDayOfWeek.getDayOfWeek());
        if (firstDayOfWeek.isAfter(LocalDate.now())) {
            firstDayOfWeek = firstDayOfWeek.minusWeeks(1);
        }
        populateWorkoutList(workoutList);
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
        else {
            LocalDate scheduleDate = firstDayOfWeek.withDayOfWeek(dayOfWeek);
            if (scheduleDate.isBefore(firstDayOfWeek)) {
                scheduleDate = scheduleDate.plusWeeks(1);
            }
            int workoutSessionIndex = Days.daysBetween(firstDayOfWeek, scheduleDate).getDays();
            if (workoutSessions[workoutSessionIndex] != null && workoutSessions[workoutSessionIndex].getName() != null) {
                workoutSessions[workoutSessionIndex] = null;
                removed = true;
            }
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
        LocalDate lastDayOfWeek = firstDayOfWeek.plusWeeks(1).minusDays(1);

        workoutSessions = new WorkoutSession[DateTimeConstants.DAYS_PER_WEEK];

        for (int i = 0; i < sessionList.size(); i++) {
            currSession = sessionList.get(i);
            if (currSession.getDate().isBefore(firstDayOfWeek) ||
                    currSession.getDate().isAfter(lastDayOfWeek))
                throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
            else {
                LocalDate scheduleDate = firstDayOfWeek.withDayOfWeek(currSession.getDate().getDayOfWeek());
                if (scheduleDate.isBefore(firstDayOfWeek)) {
                    scheduleDate = scheduleDate.plusWeeks(1);
                }
                int workoutSessionIndex = Days.daysBetween(firstDayOfWeek, scheduleDate).getDays();
                workoutSessions[workoutSessionIndex] = currSession;
            }

        }

        for (int i = 0; i < workoutSessions.length; i++)
            if (workoutSessions[i] == null)
                workoutSessions[i] = new WorkoutSession(firstDayOfWeek.plusDays(i));
    }

    /**
     * Returns the number of actual sessions in a list of sessions, as some of them may just be a
     * date with a null workout name
     * @param sessionList a list of sessions (may contain sessions that are just a date with no workout)
     * @return number of actual sessions (dates with workouts)
     */
    public int getNumSessionsInWeek(List<WorkoutSession> sessionList) {
        int numSessions = 0;
        for (WorkoutSession session: sessionList) {
            if (session.getName() != null) {
                numSessions++;
            }
        }
        return numSessions;
    }
}
