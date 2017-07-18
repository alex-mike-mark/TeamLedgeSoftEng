package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.TimeUnit;

/**
 * The interface for tracking the length in minutes for exercises to be done for a certain
 * length of time, such as cycling for 30 minutes
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

interface InterfaceExerciseDuration extends InterfaceExerciseQuantity {
    /**
     * Get the number for time exercised
     * @return the number for time exercised
     */
    int getTime();

    /**
     * Returns the unit of measurement for the time
     * @return the unit of measurement
     */
    TimeUnit getUnitOfMeasure();
}
