package ledge.muscleup.persistence;

import java.util.List;

import ledge.muscleup.model.exercise.InterfaceExercise;

/**
 * An interface for exercise database access, including methods for retrieving, inserting, and
 * removing exercises
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceExerciseDataAccess {

    /**
     * Gets a list of all exercises in the database
     * @return a list of all exercises in the database
     */
    List<InterfaceExercise> getExercisesList();

    /**
     * Gets a list of names of all exercises in the database
     * @return a list of names of all exercises in the database
     */
    List<String> getExerciseNamesList();

    /**
     * Retrieves an exercise from the database with the name given as parameter
     * @param exerciseName- the name of the exercise to retrieve from the database
     * @return The exercise with name exerciseName, or null if no exercise exists with that name
     */
    InterfaceExercise getExercise(String exerciseName);

    /**
     * Adds an exercise to the database
     * @param exercise the exercise to be added to the database
     */
    void insertExercise(InterfaceExercise exercise);

    /**
     * Removes an exercise from the database, if it exists
     * @param exercise the exercise to remove from the database
     */
    void removeExercise(InterfaceExercise exercise);
}
