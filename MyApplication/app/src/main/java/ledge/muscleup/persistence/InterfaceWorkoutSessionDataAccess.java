package ledge.muscleup.persistence;

import org.joda.time.LocalDate;

import java.util.List;

import ledge.muscleup.model.workout.WorkoutSession;

/**
 * An interface for workout session database access, including methods for retrieving, inserting,
 * and removing workout sessions
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceWorkoutSessionDataAccess extends InterfaceDataAccessComponent {

    /**
     * Retrieves a workout session scheduled on the given date from the database, if it exists. If
     * no workout session is found for that date, returns null.
     * @param dateOfSession the date to get the workout session for
     * @return the workout session scheduled on the given date
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
     * Inserts a new workout session into the database
     * @param workoutSession the new workout session to insert into the database
     */
    void insertWorkoutSession(WorkoutSession workoutSession);

    /**
     * Removes a workout session from the database, if it exists
     * @param workoutSession the workout session to remove from the database
     */
    void removeWorkoutSession(WorkoutSession workoutSession);

    /**
     * Toggles the completed state of a workout in the database
     *
     * @param workoutSession the workout to change the state of
     */
    void toggleWorkoutComplete(WorkoutSession workoutSession);
}
