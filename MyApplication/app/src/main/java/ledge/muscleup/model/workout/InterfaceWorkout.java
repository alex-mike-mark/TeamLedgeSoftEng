package ledge.muscleup.model.workout;

import java.util.Enumeration;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceWorkoutExercise;
/**
 * The interface for a workout, which consists of a workout name, a list of exercises and a boolean
 * that keeps track of if this workout is a favourite workout
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
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing if the exercise was found and updated in the workout
     */
    boolean setRecommendedQuantity(InterfaceWorkoutExercise exercise,
                                   InterfaceExerciseQuantity quantity)throws IllegalArgumentException;

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
     * Returns the number of exercises in the workout
     * @return the number of exercises in the workout
     */
    int numExercises();

    /**
     * Adds a new exercise to the workout
     * @param exercise the exercise to add to the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    void addExercise(InterfaceWorkoutExercise exercise) throws IllegalArgumentException;

    /**
     * Move the position of an exercise in the list of exercises
     * @param exercise the exercise to change the position of
     * @param index the index of the exercise to move
     * @throws IllegalArgumentException if passed a {@code null} parameter or if {@code index} is
     * outside the bounds of the list of exercises
     * @return a boolean representing if the exercise was found and moved to the new index
     */
    boolean moveExercise(InterfaceWorkoutExercise exercise, int index) throws IllegalArgumentException;

    /**
     * Removes an exercise from the list of exercises
     * @param exercise the exercise to remove from the list
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing if the exercise was removed
     */
    boolean removeExercise(InterfaceWorkoutExercise exercise) throws IllegalArgumentException;

    /**
     * Returns an enumeration for traversing over the exercises in the workout
     * @return an enumeration of the exercises
     */
    public Enumeration<InterfaceWorkoutExercise> getExerciseEnumeration();

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
