package ledge.muscleup.model.exercise;

/**
 * The interface for tracking the number of sets and the number of reps in each set for repetative
 * exercises
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

interface InterfaceExerciseSets extends InterfaceExerciseQuantity {
    /**
     * Returns the number of sets for the exercise
     * @return the number of sets for the exercise
     */
    int getSets();

    /**
     * Returns the number of reps for the exercise
     * @return the number of reps for the exercise
     */
    int getReps();
}
