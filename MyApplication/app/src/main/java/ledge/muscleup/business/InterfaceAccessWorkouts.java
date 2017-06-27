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
     * Adds a new workout to the database
     * @param workout the workout to be added to the database
     */
    void insertWorkout(Workout workout);

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to be removed
     */
    void removeWorkout(Workout workout);

    /**
     * Sets the recommended quantity of exercise for a given exercise in a given workout
     *
     * @param workout the workout that contains the exercise to update
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing if the exercise was found and updated in the workout
     */
    boolean setRecommendedExerciseQuantity(Workout workout, WorkoutExercise exercise,
                                          InterfaceExerciseQuantity quantity) throws IllegalArgumentException;

    /**
     * Toggle the favourite status of a workout
     *
     * @param workout the workout to update the status of
     */
    void toggleWorkoutFavourite(Workout workout);

    /**
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     *
     * @param workout the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     * @return true if exercise was added successfully, false otherwise
     */
    boolean addExerciseToWorkout(Workout workout, WorkoutExercise exercise);

    /**
     * Move the position of an exercise in the list of exercises
     *
     * @param workout the workout to change the order of exercises for
     * @param exercise the exercise to change the position of
     * @param index    the index of the exercise to move
     * @throws IllegalArgumentException if passed a {@code null} parameter or if {@code index} is
     * outside the bounds of the list of exercises
     * @return a boolean representing if the exercise was found and moved to the new index
     */
    boolean moveWorkoutExercise(Workout workout, WorkoutExercise exercise,
                                int index) throws IllegalArgumentException;

    /**
     * Removes an exercise from the list of exercises
     *
     * @param workout the workout to remove an exercise from
     * @param exercise the exercise to remove from the list
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return the exercise that was removed, or {@code null} if the exercise couldn't be found
     */
    boolean removeExerciseFromWorkout(Workout workout, WorkoutExercise exercise) throws IllegalArgumentException;
}
