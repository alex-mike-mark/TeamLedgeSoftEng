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
     * This method gets an exercise from the database with the given name
     * @param exerciseName the name of the exercise
     * @return an exercise from the database with the given name, or null if it does not exist
     */
    Exercise getExercise(String exerciseName);

    /**
     * This method gets exercises stored in the database in the form of a list
     * @return a list of exercises in the database
     */
    List<Exercise> getExercisesList();

    /**
     * This method gets the names of all exercises in the database in the form of a list
     * @return a list of exercise names in the database
     */
    List<String> getExerciseNamesList();

    /**
     * This method inserts a new exercise into the database
     * @param exercise the exercise to be inserted
     */
    void insertExercise(Exercise exercise);

    /**
     * This method removes an exercise from the database
     * @param exercise the exercise to be removed
     */
    void removeExercise(Exercise exercise);

}
