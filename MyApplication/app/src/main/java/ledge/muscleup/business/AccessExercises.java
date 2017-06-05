package ledge.muscleup.business;

import java.util.List;
import java.util.Map;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.exercise.InterfaceExercise;
import ledge.muscleup.persistence.DataAccessStub;

/**
 * This class contains methods for retrieving, adding, and removing exercises from the database, by
 * by calling the methods defined in the DataAccess interface.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class AccessExercises {
    private DataAccessStub dataAccess;

    /**
     * Constructor for AccessExercises, which initializes the dataAccess variable to the stub database
     */
    public AccessExercises() {
        dataAccess = (DataAccessStub) Services.getDataAccess();
    }

    /**
     * This method gets exercises stored in the database in the form of a list
     * @return a list of exercises in the database
     */
    public List<InterfaceExercise> getExercisesList() {
        return dataAccess.getExercisesList();
    }

    /**
     * This method inserts a new exercise into the database
     * @param exercise the exercise to be inserted
     */
    public void insertExercise(InterfaceExercise exercise) {
        dataAccess.insertExercise(exercise);
    }

    /**
     * This method removes an exercise from the database
     * @param exercise the exercise to be removed
     */
    public void removeExercise(InterfaceExercise exercise) {
        dataAccess.removeExercise(exercise);
    }
}
