package ledge.muscleup.model.workout;

import org.joda.time.LocalDate;

import java.util.Enumeration;

import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceWorkoutSessionExercise;

/**
 * The interface for a workout for which progress can be logged
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-02
 */

public interface InterfaceWorkoutSession {
    /**
     * Returns the name of the workout
     * @return the name of the workout
     */
    String getName();

    /**
     * Returns the scheduled date of the workout
     * @return the scheduled date of the workout
     */
    LocalDate getDate();

    /**
     * Sets the scheduled date of the workout
     * @param newDate the new date of the workout
     */
    void setDate(LocalDate newDate);

    /**
     * Returns {@code true} if the worokout has been completed, or {@code false} otherwise
     * @return a boolean representing if the workout has been completed
     */
    boolean isComplete();

    /**
     * Toggles the completed state of this workout
     */
    void toggleCompleted();

    /**
     * Returns the number of exercises in the workout
     * @return the number of exercises in the workout
     */
    int numExercises();

    /**
     * Log an exercise as complete
     * @param exercise the exercise to complete
     * @return a boolean representing whether the exercise was marked as completed or not
     */
    boolean completeExercise(InterfaceWorkoutSessionExercise exercise, InterfaceExerciseQuantity quantity);

    /**
     * Returns an enumeration for traversing over the exercises in the workout
     * @return an enumeration of the exercises
     */
    public Enumeration<InterfaceWorkoutSessionExercise> getExerciseEnumeration();

    /**
     * Compares the current InterfaceWorkoutSession to another instance of InterfaceWorkoutSession
     * @param other the instance of InterfaceWorkoutSession to compare to
     * @return a boolean representing whether the two instances were equal
     */
    boolean equals(InterfaceWorkoutSession other);

    /**
     * Returns the InterfaceWorkoutSession as a String
     * @return the InterfaceWorkoutSession as a String
     */
    String toString();
}
