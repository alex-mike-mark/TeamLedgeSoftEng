package ledge.muscleup.model;

/**
 * The interface for an exercise for which the amount of exercise is tracked
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-02.
 */

public interface InterfaceTrackableExercise extends InterfaceExercise {
    /**
     * Returns the quantity of exercise logged for this exercise
     * @return the quantity of exercise logged
     */
    InterfaceExerciseQuantity getTrackedQuantity();

    /**
     * Updates the exercise with the new quantity
     * @param quantity the quantity to update the exercise to
     */
    void updateTrackedQuantity(InterfaceExerciseQuantity quantity);
}
