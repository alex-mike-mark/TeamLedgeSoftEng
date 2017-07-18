package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.experience.CompletedWorkoutRecord;
import ledge.muscleup.persistence.InterfaceExperienceDataAccess;

/**
 * This class contains methods for retrieving information about the user's experience
 * level
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */
public class AccessExperience implements InterfaceAccessExperience {
    private InterfaceExperienceDataAccess dataAccess;

    /**
     * Constructor for the AccessExperience class, which initializes the dataAccess variable to the HSQL database
     */
    public AccessExperience() {
        dataAccess = Services.getExperienceDataAccess();
    }

    /**
     * Constructor for the AccessExperience class, which initializes the dataAccess variable to a custom database
     */
    public AccessExperience(InterfaceExperienceDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * Gets a list of all completed workout records
     *
     * @return a list of all completed workout records
     */
    @Override
    public List<CompletedWorkoutRecord> getCompletedWorkouts() {
        return dataAccess.getCompletedWorkouts();
    }

    /**
     * Gets the most recent completed workout
     *
     * @return the most recent completed workout
     */
    @Override
    public CompletedWorkoutRecord getMostRecentCompletedWorkout() {
        return dataAccess.getMostRecentCompletedWorkout();
    }
}
