package ledge.muscleup.model.workout;

import java.util.NoSuchElementException;

import ledge.muscleup.model.exercise.InterfaceExercise;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceWorkoutExercise;

/**
 * The interface for a workout, which consists of a workout name and a list of exercises
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-02.
 */

public interface InterfaceWorkout {
    /**
     * Returns the name of the workout
     * @return the name of the workout
     */
    String getName();

    /**
     * Set the name of the workout
     * @param newName the new name for the workout
     */
    void setName(String newName);

    /**
     * Sets the recommended quantity of exercise for a given exercise in the workout
     *
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @return a boolean representing if the exercise was found and updated in the workout
     */
    boolean setRecommendedQuantity(InterfaceWorkoutExercise exercise, InterfaceExerciseQuantity quantity);

    /**
     * Returns {@code true} if the workout is a favourite workout, and {@code false} otherwise
     * @return a boolean represeting whether this workout is a favourite workout
     */
    boolean isFavourite();

    /**
     * Toggle the favourite status of this workout
     */
    void toggleFavourite();

    /**
     * Adds a new exercise to the workout
     * @param exercise the exercise to add to the workout
     */
    void addExercise(InterfaceWorkoutExercise exercise);

    /**
     * Move the position of an exercise in the list of exercises
     * @param exercise the exercise to change the position of
     * @param index the index of the exercise to move
     * @return a boolean representing if the exercise was found and moved to the new index
     */
    boolean moveExercise(InterfaceWorkoutExercise exercise, int index);

    /**
     * Removes an exercise from the list of exercises
     * @param exercise the exercise to remove from the list
     * @return a boolean representing if the exercise was removed
     */
    boolean removeExercise(InterfaceWorkoutExercise exercise);

    /**
     * Initializes the iterator for the list of exercises to the first InterfaceWorkoutExercise in
     * the list
     */
    void initExerciseIteration();

    /**
     * Returns {@code true} if there is another InterfaceWorkoutExercise in the list or
     * {@code false} if not
     * @return a boolean representing whether the iterator is at the end of the list or not
     */
    boolean hasNextExercise();

    /**
     * Returns the next InterfaceWorkoutExercise in the list
     * @return the next InterfaceWorkoutExercise in the list
     * @throws NoSuchElementException if the iterator is at the end of the list
     */
    InterfaceWorkoutExercise nextExercise() throws NoSuchElementException;

    /**
     * Compares the current InterfaceWorkoutExercise to another instance of InterfaceWorkout
     * @param other the instance of InterfaceWorkout to compare to
     * @return a boolean representing whether the two instances were equal
     */
    boolean equals(InterfaceWorkout other);

    /**
     * Returns the InterfaceWorkoutExercise as a String
     * @return the InterfaceWorkoutExercise as a String
     */
    String toString();
}
