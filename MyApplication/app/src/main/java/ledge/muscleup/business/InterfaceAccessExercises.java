package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.model.exercise.Exercise;

/**
 * An Interface for communicating with the database to retrieve, add, and remove exercises from the
 * database
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceAccessExercises {
    /**
     * This method gets exercises stored in the database in the form of a list
     * @return a list of exercises in the database
     */
    List<Exercise> getExercisesList();
}
