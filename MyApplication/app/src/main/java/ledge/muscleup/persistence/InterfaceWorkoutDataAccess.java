package ledge.muscleup.persistence;

import java.util.List;


import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;

import ledge.muscleup.model.workout.Workout;

/**
 * An interface for workout database access, including methods for retrieving, inserting, and
 * removing workouts, as well as adding an exercise to a workout in the database
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceWorkoutDataAccess {

    /**
     * Gets a list of all workouts in the database
     * @return a list of all workouts in the database
     */
    List<Workout> getWorkoutsList();

    /**
     * Gets a list of names of all exercises in the database
     * @return a list of names of all workouts in the database
     */
    List<String> getWorkoutNamesList();

    /**
     * Retrieves a workout from the database with the name given as parameter
     * @param workoutName the name of the workout to retrieve from the database
     * @return The workout with name workoutName, or null if no workout exists with that name
     */
    Workout getWorkout(String workoutName);
}
