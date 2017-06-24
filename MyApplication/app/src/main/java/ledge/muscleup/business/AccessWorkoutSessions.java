package ledge.muscleup.business;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.persistence.DataAccessStub;

/**
 * This class contains methods for retrieving, adding, and removing workout sessions from the
 * database, by calling the methods defined in the InterfaceDataAccess interface.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public class AccessWorkoutSessions implements InterfaceAccessWorkoutSessions {
    private DataAccessStub dataAccess;

    /**
     * Constructor for AccessWorkoutSessions, which initializes the dataAccess variable to the stub database
     */
    public AccessWorkoutSessions() {
        dataAccess = (DataAccessStub) Services.getDataAccess();
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
     * This method gets a list of workout sessions in the database
     * @return a list of the workout sessions stored in the database
     */
    public List<WorkoutSession> getWorkoutSessionsList() {
        return dataAccess.getWorkoutSessionsList();
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
}
