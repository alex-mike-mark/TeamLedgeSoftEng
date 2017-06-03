package ledge.muscleup.model.exercise;

/**
 * The interface for an exercise that can be modified, which includes methods to update the
 * favourite status of an exercise
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-02
 */

public interface InterfaceModifiableExercise extends InterfaceExercise {
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
