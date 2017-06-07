package ledge.muscleup.persistence;

import java.util.List;

import ledge.muscleup.model.exercise.InterfaceWorkoutExercise;
import ledge.muscleup.model.workout.InterfaceWorkout;

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
    List<InterfaceWorkout> getWorkoutsList();

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
    InterfaceWorkout getWorkout(String workoutName);

    /**
     * Adds a workout to the database
     * @param workout the workout to be added to the database
     */
    void insertWorkout(InterfaceWorkout workout);

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to remove from the database
     */
    void removeWorkout(InterfaceWorkout workout);

    /**
     * Adds an exercise to a workout in the database, if both the workout and the exercise exist in
     * the database
     * @param workout the workout to add the exercise to
     * @param exercise the exercise to add to the workout
     *
     * @return a boolean indicating whether the exercise was properly added to the workout
     */
    boolean addExerciseToWorkout (InterfaceWorkout workout, InterfaceWorkoutExercise exercise);
}
