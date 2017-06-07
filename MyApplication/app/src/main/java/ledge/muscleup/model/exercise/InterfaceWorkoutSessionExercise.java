package ledge.muscleup.model.exercise;

/**
 * The interface for an exercise for which the amount of exercise is tracked and the exercise can
 * be marked complete
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-02.
 */

public interface InterfaceWorkoutSessionExercise {
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
     * Returns {@code true} if the exercise has been completed, or {@code false} otherwise
     * @return a boolean representing if the exercise has been completed
     */
    boolean isComplete();

    /**
     * Toggles the completed state of this exercise
     */
    void toggleCompleted();

    /**
     * Compares the current InterfaceWorkoutSessionExercise to another instance of
     * InterfaceWorkoutSessionExercise
     * @param other the instance of InterfaceWorkoutSessionExercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    boolean equals(InterfaceWorkoutSessionExercise other);

    /**
     * Returns the InterfaceWorkoutSessionExercise as a String
     * @return the InterfaceWorkoutSessionExercise as a String
     */
    String toString();
}
