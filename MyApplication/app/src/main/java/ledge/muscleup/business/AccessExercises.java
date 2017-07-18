package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.persistence.InterfaceExerciseDataAccess;

/**
 * This class contains methods for retrieving, adding, and removing exercises from the database,
 * by calling the methods defined in the InterfaceExerciseDataAccess interface.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class AccessExercises implements InterfaceAccessExercises {
    private InterfaceExerciseDataAccess dataAccess;

    /**
     * Constructor for AccessExercises, which initializes the dataAccess variable to the HSQL database
     */
    public AccessExercises() {
        dataAccess = Services.getExerciseDataAccess();
    }

    /**
     * Constructor for AccessExercises, which initializes the dataAccess variable to a custom database
     */
    public AccessExercises(InterfaceExerciseDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * This method gets exercises stored in the database in the form of a list
     * @return a list of exercises in the database
     */
    public List<Exercise> getExercisesList() {
        return dataAccess.getExercisesList();
    }
}
