package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.model.experience.CompletedWorkoutRecord;

/**
 * An interface for communicating with the database to retrieve information about the user's experience
 * level
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */
public interface InterfaceAccessExperience {
    /**
     * Gets a list of all completed workout records
     * @return a list of all completed workout records
     */
    List<CompletedWorkoutRecord> getCompletedWorkouts();

    /**
     * Gets the most recent completed workout
     * @return the most recent completed workout
     */
    CompletedWorkoutRecord getMostRecentCompletedWorkout();
}
