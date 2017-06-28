package ledge.muscleup.persistence;

import java.util.List;

import ledge.muscleup.model.exercise.Exercise;

/**
 * An interface for exercise database access, including methods for retrieving, inserting, and
 * removing exercises
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceExerciseDataAccess extends InterfaceDataAccess{

    /**
     * Gets a list of all exercises in the database
     * @return a list of all exercises in the database
     */
    List<Exercise> getExercisesList();
}
