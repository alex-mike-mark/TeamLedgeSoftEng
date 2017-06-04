package ledge.muscleup.model.workout;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceSuggestedExercise;

/**
 * The interface for a workout that can be modified, which includes methods to modify the name,
 * list of exercises and if the workout is a favourite workout
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-02.
 */

public interface InterfaceModifiableWorkout extends InterfaceWorkout {
    /**
     * Set the name of the workout
     * @param newName the new name for the workout
     */
    void setName(String newName);

    /**
     * Sets the recommended quantity of exercise for a given exercise in the workout
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @return a boolean representing if the exercise was found and updated in the workout
     */
    boolean setRecommendedQuantity(InterfaceSuggestedExercise exercise, InterfaceExerciseQuantity quantity);

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
    void addExercise(InterfaceSuggestedExercise exercise);

    /**
     * Move the position of an exercise in the list of exercises
     * @param exercise the exercise to change the position of
     * @param index the index of the exercise to move
     * @return a boolean representing if the exercise was found and moved to the new index
     */
    boolean moveExercise(InterfaceSuggestedExercise exercise, int index);

    /**
     * Removes an exercise from the list of exercises
     * @param exercise the exercise to remove from the list
     * @return a boolean representing if the exercise was removed
     */
    boolean removeExercise(InterfaceSuggestedExercise exercise);
}
