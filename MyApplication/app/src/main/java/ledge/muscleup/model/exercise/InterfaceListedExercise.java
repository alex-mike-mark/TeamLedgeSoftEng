package ledge.muscleup.model.exercise;

/**
 * The interface for an exercise to be viewed in a list, which includes methods to check if the
 * exercise is a favourite exercise and to change the favourite status of the exercise
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-02
 */

public interface InterfaceListedExercise extends InterfaceExercise {
    /**
     * Returns {@code true} if this exercise is a favourite exercise, and {@code false} otherwise
     * @return a boolean representing is the exercise is a favourite exercise
     */
    boolean isFavourite();

    /**
     * Toggle the favourite status of the exercise
     */
    void toggleFavourite();
}
