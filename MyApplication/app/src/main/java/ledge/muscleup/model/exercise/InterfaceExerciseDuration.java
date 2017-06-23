package ledge.muscleup.model.exercise;

/**
 * The interface for tracking the length in minutes for exercises to be done for a certain
 * length of time, such as cycling for 30 minutes
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public interface InterfaceExerciseDuration extends InterfaceExerciseQuantity {
    /**
     * Get the number of minutes exercised
     * @return the number of minutes exercised
     */
    int getMinutes();
    TimeUnit getUnitOfMeasure();
}
