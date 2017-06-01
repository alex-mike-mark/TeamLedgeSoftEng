package ledge.muscleup.model;

/**
 * The interface for a quantity of exercise
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
