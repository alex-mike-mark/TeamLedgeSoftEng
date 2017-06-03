package ledge.muscleup.model.workout;

import java.util.NoSuchElementException;

import ledge.muscleup.model.exercise.InterfaceExercise;

/**
 * The interface for a workout, which consists of a workout name and a list of exercises
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-02.
 */

public interface InterfaceWorkout {
    /**
     * Returns the name of the workout
     * @return the name of the workout
     */
    String getName();

    /**
     * Initializes the iterator for the list of exercises to the first InterfaceExercise in the list
     */
    void initExerciseIteration();

    /**
     * Returns {@code true} if there is another InterfaceExercise in the list or {@code false} if not
     * @return a boolean representing whether the iterator is at the end of the list or not
     */
    boolean hasNextExercise();

    /**
     * Returns the next InterfaceExercise in the list
     * @return the next InterfaceExercise in the list
     * @throws NoSuchElementException if the iterator is at the end of the list
     */
    InterfaceExercise nextExercise() throws NoSuchElementException;

    /**
     * Compares the current InterfaceWorkout to another instance of InterfaceWorkout
     * @param other the instance of InterfaceWorkout to compare to
     * @return a boolean representing whether the two instances were equal
     */
    boolean equals(InterfaceWorkout other);

    /**
     * Returns the InterfaceWorkout as a String
     * @return the InterfaceWorkout as a String
     */
    String toString();
}
