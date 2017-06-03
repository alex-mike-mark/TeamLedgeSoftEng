package ledge.muscleup.model.workout;

import ledge.muscleup.model.exercise.InterfaceModifiableExercise;

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
     * Returns {@code true} if the workout is a favourite workout, and {@code false} otherwise
     * @return a boolean represeting whether this workout is a favourite workout
     */
    boolean isFavourite();

    /**
     * Toggle the favourite status of this workout
     */
    void toggleFavourite();

    /**
     * Adds a new exercise to the workout at the given position
     * @param exercise the exercise to add to the workout
     */
    void addExercise(InterfaceModifiableExercise exercise);

    /**
     * Move the position of an exercise in the list of exercises
     * @param exercise the exercise to change the position of
     * @param index the index of the exercise to move
     */
    void moveExercise(InterfaceModifiableExercise exercise, int index);

    /**
     * Removes an exercise from the list of exercises
     * @param exercise the exercise to remove from the list
     * @return the exercise that was removed, or {@code null} if the exercise couldn't be found
     */
    InterfaceModifiableExercise removeExercise(InterfaceModifiableExercise exercise);
}
