package ledge.muscleup.business;

import org.joda.time.LocalDate;

import java.util.List;

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
     * Adds a new workout session to the database
     * @param workoutSession the workout session to be added to the database
     */
    void insertWorkoutSession(WorkoutSession workoutSession);

    /**
     * Removes a workout session from the database, if it exists
     * @param workoutSession the workout session to be removed
     */
    void removeWorkoutSession(WorkoutSession workoutSession);
}
