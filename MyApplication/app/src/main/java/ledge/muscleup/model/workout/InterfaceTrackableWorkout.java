package ledge.muscleup.model.workout;

import java.util.Date;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceTrackableExercise;

/**
 * The interface for a workout for which progress can be logged
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-02
 */

public interface InterfaceTrackableWorkout extends InterfaceWorkout {
    /**
     * Returns the scheduled date of the workout
     * @return the scheduled date of the workout
     */
    Date getDate();

    /**
     * Sets the scheduled date of the workout
     * @param newDate the new date of the workout
     */
    void setDate(Date newDate);

    /**
     * Log a quantity of exercise for one of the exercises in the workout
     * @param exercise the exercise to update the quantity for
     * @param quantity the amount of exercise to log
     * @return a boolean representing if the exercise was found and updated in the workout
     */
    boolean logExercise(InterfaceTrackableExercise exercise, InterfaceExerciseQuantity quantity);
}
