package ledge.muscleup.persistence;

import java.util.List;

import ledge.muscleup.model.experience.CompletedWorkoutRecord;

/**
 * An interface for experience and level access
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */
public interface InterfaceExperienceDataAccess extends InterfaceDataAccess {
    /**
     * Returns the list of all completed workout records
     * @return a list of all completed workout records
     */
    List<CompletedWorkoutRecord> getCompletedWorkouts();

    /**
     * Returns the most recent completed workout
     * @return the most recent completed workout
     */
    CompletedWorkoutRecord getMostRecentCompletedWorkout();
}
