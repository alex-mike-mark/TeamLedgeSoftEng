package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.persistence.DataAccess;

/**
 * This class contains methods for retrieving, adding, and removing exercises from the database,
 * by calling the methods defined in the InterfaceDataAccess interface.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class AccessExercises implements InterfaceAccessExercises {
    private DataAccess dataAccess;

    /**
     * Constructor for AccessExercises, which initializes the dataAccess variable to the stub database
     */
    public AccessExercises() {
        dataAccess = (DataAccess) Services.getDataAccess();
    }

    /**
     * This method gets exercises stored in the database in the form of a list
     * @return a list of exercises in the database
     */
    public List<Exercise> getExercisesList() {
        return dataAccess.getExercisesList();
    }
}
