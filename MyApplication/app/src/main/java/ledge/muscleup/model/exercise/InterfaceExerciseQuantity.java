package ledge.muscleup.model.exercise;

/**
 * The interface for a quantity of exercise
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-05-29
 */
public interface InterfaceExerciseQuantity {
    /**
     * Compares the current InterfaceExerciseQuantity to another instance of InterfaceExerciseQuantity
     * @param other the instance of InterfaceExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    boolean equals(InterfaceExerciseQuantity other);

    /**
     * Returns the InterfaceExerciseQuantity as a string
     * @return the InterfaceExerciseQuantity as a string
     */
    String toString();
}
