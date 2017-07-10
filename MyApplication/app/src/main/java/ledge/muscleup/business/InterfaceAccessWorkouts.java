package ledge.muscleup.business;

import java.util.List;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;

/**
 * An Interface for communicating with the database to retrieve, add, and remove workouts from the
 * database, and add exercises to workouts in the database
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceAccessWorkouts {
    /**
     * This method gets a workout from the database with the given name
     * @param workoutName the name of the workout
     * @return a workout from the database with the given name, if it exists. Otherwise, returns null
     */
    Workout getWorkout(String workoutName);

    /**
     * This method gets a list of workouts in the database
     * @return a list of the workouts stored in the database
     */
    List<Workout> getWorkoutsList();

    /**
     * This method gets the names of all workouts in the database in the form of a list
     * @return a list of workout names in the database
     */
    List<String> getWorkoutNamesList();

    /**
     * Retrieves the name of a the workout that has been completed the least amount of times
     * @return the workout that has been ocmpleted the least amount of times
     */
    String getLeastCompletedWorkout();
}
