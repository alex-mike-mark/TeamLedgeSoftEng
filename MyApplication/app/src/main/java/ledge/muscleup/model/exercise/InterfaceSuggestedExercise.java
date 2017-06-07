package ledge.muscleup.model.exercise;

/**
 * The interface for an exercise that is part of a workout, which includes a recommended quantity
 * of exercise for that workout and methods to get and change the recommended quantity
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public interface InterfaceSuggestedExercise extends InterfaceExercise {
    /**
     * Returns the recommended quantity of exercise for the exercise
     * @return the recommended quantity of exercise
     */
    InterfaceExerciseQuantity getRecommendedQuantity();

    /**
     * Updates the exercise with the new recommended quantity
     * @param quantity the recommended quantity to update the exercise to
     * @return a boolean representing if the suggested quantity could be updated
     */
    boolean updateRecommendedQuantity(InterfaceExerciseQuantity quantity);
}
