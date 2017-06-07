package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.persistence.DataAccessStub;

/**
 * This class contains methods for retrieving, adding, and removing workouts from the database, and
 * adding exercises to a workout, by calling the methods defined in the InterfaceDataAccess interface.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class AccessWorkouts {
    private DataAccessStub dataAccess;

    /**
     * Constructor for AccessWorkouts, which initializes the dataAccess variable to the stub database
     */
    public AccessWorkouts() {
        dataAccess = (DataAccessStub) Services.getDataAccess();
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
     * Adds a new workout to the database
     * @param workout the workout to be added to the database
     */
    public void insertWorkout(Workout workout) {
        dataAccess.insertWorkout(workout);
    }

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to be removed
     */
    public void removeWorkout(Workout workout) {
        dataAccess.removeWorkout(workout);
    }

    /**
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     * @param workout the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     *
     * @return true if exercise was added successfully, false otherwise
     */
    public boolean addExerciseToWorkout (Workout workout, WorkoutExercise exercise) {
        return dataAccess.addExerciseToWorkout(workout, exercise);
    }

}
