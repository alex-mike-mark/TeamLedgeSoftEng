package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.DistanceUnit;

/**
 * The interface for tracking distance metrics for exercises such as running
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

interface InterfaceExerciseDistance extends InterfaceExerciseQuantity {
    /**
     * Returns the distance for which an exercise is completed
     * @return the distance
     */
    double getDistance();

    /**
     * Returns the unit of measurement for the distance
     * @return the unit of measurement
     */
    DistanceUnit getUnitOfMeasure();
}
