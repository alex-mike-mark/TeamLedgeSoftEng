package ledge.muscleup.model.exercise;

/**
 * The interface for tracking the number of sets, reps and the weight used for strength-building
 * exercises
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public interface InterfaceExerciseWeight extends InterfaceExerciseSetsAndReps {
    /**
     * Returns the weight to use for each rep of the exercise
     * @return the weight to use
     */
    double getWeight();

    /**
     * Returns the unit of measurement for the weight
     * @return the unit of measurement
     */
    WeightUnit getUnitOfMeasure();
}
