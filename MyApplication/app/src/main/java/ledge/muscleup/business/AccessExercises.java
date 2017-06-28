package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.persistence.DataAccessStub;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * This class contains methods for retrieving, adding, and removing exercises from the database,
 * by calling the methods defined in the InterfaceDataAccess interface.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class AccessExercises implements InterfaceAccessExercises {
    private InterfaceDataAccess dataAccess;

    /**
     * Constructor for AccessExercises, which initializes the dataAccess variable to the stub database
     */
    public AccessExercises() {
        dataAccess = (DataAccessStub) Services.getDataAccess();
    }

    /**
     * This method gets an exercise from the database with the given name
     * @param exerciseName the name of the exercise
     * @return an exercise from the database with the given name, or null if it does not exist
     */
    public Exercise getExercise(String exerciseName) {
        return dataAccess.getExercise(exerciseName);
    }

    /**
     * This method gets exercises stored in the database in the form of a list
     * @return a list of exercises in the database
     */
    public List<Exercise> getExercisesList() {
        return dataAccess.getExercisesList();
    }

    /**
     * This method gets the names of all exercises in the database in the form of a list
     * @return a list of exercise names in the database
     */
    public List<String> getExerciseNamesList() {
        return dataAccess.getExerciseNamesList();
    }

    /**
     * This method inserts a new exercise into the database
     * @param exercise the exercise to be inserted
     */
    public void insertExercise(Exercise exercise) {
        dataAccess.insertExercise(exercise);
    }

    /**
     * This method removes an exercise from the database
     * @param exercise the exercise to be removed
     */
    public void removeExercise(Exercise exercise) {
        dataAccess.removeExercise(exercise);
    }
}
