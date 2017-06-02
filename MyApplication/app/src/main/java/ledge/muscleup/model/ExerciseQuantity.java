package ledge.muscleup.model;

/**
 * The interface for a quantity of exercise
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-05-29
 */
public interface ExerciseQuantity {

    /**
     * Compares the current ExerciseQuantity to another instance of ExerciseQuantity
     * @param other the instance of ExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    boolean equals(ExerciseQuantity other);

    /**
     * Returns the ExerciseQuantity as a string
     * @return the ExerciseQuantity as a string
     */
    String toString();
}
