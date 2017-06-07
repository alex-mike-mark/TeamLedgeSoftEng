package ledge.muscleup.model.exercise;

/**
 * The interface for an exercise that is part of a workout, which includes a recommended quantity
 * of exercise for that workout and methods to get and change the recommended quantity
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public interface InterfaceWorkoutExercise {
    /**
     * Returns the name of the exercise
     * @return the name of the exercise
     */
    String getName();

    /**
     * Returns the intensity of the exercise
     * @return the intensity of the exercise
     */
    ExerciseIntensity getIntensity();

    /**
     * Returns the type of the exercise
     * @return the type of the exercise
     */
    ExerciseType getType();

    /**
     * Returns the recommended quantity of exercise for the exercise
     * @return the recommended quantity of exercise
     */
    InterfaceExerciseQuantity getRecommendedQuantity();

    /**
     * Updates the exercise with the new recommended quantity
     * @param quantity the recommended quantity to update the exercise to
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing if the suggested quantity could be updated
     */
    boolean updateRecommendedQuantity(InterfaceExerciseQuantity quantity) throws IllegalArgumentException;

    /**
     * Compares the current InterfaceWorkoutExercise to another instance of InterfaceWorkoutExercise
     * @param other the instance of InterfaceWorkoutExercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    boolean equals(InterfaceWorkoutExercise other);

    /**
     * Returns the InterfaceWorkoutExercise as a String
     * @return the InterfaceWorkoutExercise as a String
     */
    String toString();
}
