package ledge.muscleup.business;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.persistence.DataAccess;

/**
 * This class contains methods for retrieving, adding, and removing workout sessions from the
 * database, by calling the methods defined in the InterfaceDataAccess interface.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public class AccessWorkoutSessions implements InterfaceAccessWorkoutSessions {
    private DataAccess dataAccess;

    /**
     * Constructor for AccessWorkoutSessions, which initializes the dataAccess variable to the stub database
     */
    public AccessWorkoutSessions() {
        dataAccess = (DataAccess) Services.getDataAccess();
    }

    /**
     * This method gets a workout session from the database with the given date
     * @param dateOfSession the date of the workout session
     * @return a workout session from the database scheduled on the given date
     */
    public WorkoutSession getWorkoutSession(LocalDate dateOfSession) {
        return dataAccess.getWorkoutSession(dateOfSession);
    }

    /**
     * A method that returns a list of workout sessions scheduled in a date range
     * @param startDate the first date of the date range
     * @param endDate the last date of the date range
     * @return a list of all workout sessions scheduled between startDate and endDate, inclusive
     */
    public List<WorkoutSession> getSessionsInDateRange(LocalDate startDate,
                                                                LocalDate endDate) {
        return dataAccess.getSessionsInDateRange(startDate, endDate);
    }

    /**
     * A method that returns a list of workout sessions scheduled in the current week
     * @return a list of all workout sessions scheduled in the current week
     */
    public List<WorkoutSession> getCurrentWeekSessions() {
        LocalDate firstOfThisWeek = new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY);
        return dataAccess.getSessionsInDateRange(firstOfThisWeek, firstOfThisWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1));
    }

    /**
     * Adds a new workout session to the database
     * @param workoutSession the workout session to be added to the database
     */
    public void insertWorkoutSession(WorkoutSession workoutSession) {
        dataAccess.insertWorkoutSession(workoutSession);
    }

    /**
     * Removes a workout session from the database, if it exists
     * @param workoutSession the workout session to be removed
     */
    public void removeWorkoutSession(WorkoutSession workoutSession) {
        dataAccess.removeWorkoutSession(workoutSession);
    }

    /**
     * Toggles the completed state of a workout
     *
     * @param workoutSession the workout to change the state of
     */
    public void toggleWorkoutCompleted(WorkoutSession workoutSession) {
        workoutSession.toggleCompleted();
        dataAccess.toggleWorkoutComplete(workoutSession);
    }

    /**
     * Creates a new ScheduleWeek based on the given date
     *
     * @param dayInWeek a day in the week to created a ScheduleWeek for
     * @return a ScheduleWeek, which contains all WorkoutSessions for the given week
     */
    @Override
    public ScheduleWeek newScheduledWeek(LocalDate dayInWeek) {
        LocalDate firstDayOfWeek = dayInWeek.withDayOfWeek(DateTimeConstants.MONDAY);
        return new ScheduleWeek(getSessionsInDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1)));
    }

    /**
     * Sets the manager to contain the scheduled workouts for the previous week
     *
     * @param scheduleWeek the week to change
     */
    @Override
    public void setToLastWeek(ScheduleWeek scheduleWeek) {
        LocalDate firstDayOfWeek;
        List<WorkoutSession> weekWorkouts;

        firstDayOfWeek = scheduleWeek.getWeekday(DateTimeConstants.MONDAY).minusWeeks(1);
        weekWorkouts = getSessionsInDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1));
        scheduleWeek.lastWeek(weekWorkouts);
    }

    /**
     * Sets the manager to contain the scheduled workouts for the following week
     *
     * @param scheduleWeek the week to change
     */
    @Override
    public void setToNextWeek(ScheduleWeek scheduleWeek) {
        LocalDate firstDayOfWeek;
        List<WorkoutSession> weekWorkouts;

        firstDayOfWeek = scheduleWeek.getWeekday(DateTimeConstants.MONDAY).plusWeeks(1);
        weekWorkouts = getSessionsInDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1));
        scheduleWeek.nextWeek(weekWorkouts);
    }

    /**
     * Sets the manager to contain the scheduled workouts for the current week
     * @param scheduleWeek the week to change
     */
    @Override
    public void setToCurrentWeek(ScheduleWeek scheduleWeek) {
        LocalDate firstDayOfWeek;
        List<WorkoutSession> weekWorkouts;

        firstDayOfWeek = LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY);
        weekWorkouts = getSessionsInDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1));
        scheduleWeek.currentWeek(weekWorkouts);
    }

}
