package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.persistence.DataAccess;
import ledge.muscleup.persistence.InterfaceWorkoutDataAccess;

/**
 * This class contains methods for retrieving, adding, and removing workouts from the database, and
 * adding exercises to a workout, by calling the methods defined in the InterfaceWorkoutDataAccess interface.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class AccessWorkouts implements InterfaceAccessWorkouts {
    private InterfaceWorkoutDataAccess dataAccess;

    /**
     * Constructor for AccessWorkouts, which initializes the dataAccess variable to the HSQL database
     */
    public AccessWorkouts() {
        dataAccess = Services.getWorkoutDataAccess();
    }

    /**
     * This method gets a workout from the database with the given name
     * @param workoutName the name of the workout
     * @return a workout from the database with the given name, if it exists. Otherwise, returns null
     */
    public Workout getWorkout(String workoutName) {
        return dataAccess.getWorkout(workoutName);
    }

    /**
     * This method gets a list of workouts in the database
     * @return a list of the workouts stored in the database
     */
    public List<Workout> getWorkoutsList() {
        return dataAccess.getWorkoutsList();
    }

    /**
     * This method gets the names of all workouts in the database in the form of a list
     * @return a list of workout names in the database
     */
    public List<String> getWorkoutNamesList() {
        return dataAccess.getWorkoutNamesList();
    }

    /**
     * Retrieves the name of the workout that is suggested for the user
     * @return the workout that is suggested for the user
     */
    public String getSuggestedWorkout() {
        return dataAccess.getLeastCompletedWorkout();
    }
}
