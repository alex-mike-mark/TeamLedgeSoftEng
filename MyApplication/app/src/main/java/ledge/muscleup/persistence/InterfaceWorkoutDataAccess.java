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

    /**
     * Adds a workout to the database
     * @param workout the workout to be added to the database
     */
    void insertWorkout(Workout workout);

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to remove from the database
     */
    void removeWorkout(Workout workout);

    /**
     * Updates the recommended quantity of exercise for a given exercise in a given workout in the database
     *
     * @param workout  the workout that contains the exercise to update
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @return a boolean representing if the exercise was found and updated in the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    boolean updateExerciseQuantity(Workout workout, WorkoutExercise exercise,
                                   InterfaceExerciseQuantity quantity) throws IllegalArgumentException;

    /**
     * Toggles the favourite state of an exercise in the database
     *
     * @param workout the workout to update the status of
     */
    void toggleExerciseFavourite(Workout workout);

    /**
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     * @param workout the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     *
     * @return true if exercise was added successfully, false otherwise
     */
    boolean addWorkoutExercise(Workout workout, WorkoutExercise exercise);

    /**
     * Move the position of an exercise in the list of exercises in the database
     *
     * @param workout  the workout to change the order of exercises for
     * @param exercise the exercise to change the position of
     * @param index    the index of the exercise to move
     * @return a boolean representing if the exercise was found and moved to the new index
     * @throws IllegalArgumentException if passed a {@code null} parameter or if {@code index} is
     *                                  outside the bounds of the list of exercises
     */
    boolean moveWorkoutExercise(Workout workout, WorkoutExercise exercise, int index) throws IllegalArgumentException;

    /**
     * Removes an exercise from a workout in the database
     *
     * @param workout  the workout to remove an exercise from
     * @param exercise the exercise to remove from the list
     * @return the exercise that was removed, or {@code null} if the exercise couldn't be found
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    boolean addExerciseToWorkout (Workout workout, WorkoutExercise exercise);

    boolean removeWorkoutExercise(Workout workout, WorkoutExercise exercise) throws IllegalArgumentException;
}
