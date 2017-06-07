package ledge.muscleup.model.exercise;

/**
 * An exercise which can be marked as favourite
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class ListedExercise extends Exercise implements InterfaceListedExercise {
    boolean isFavourite;

    /**
     * The default constructor for the ListedExercise class
     *
     * @param name         the name of the exercise
     * @param intensity    the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @param isFavourite  a boolean representing if this is a favourite exercise
     */
    public ListedExercise(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                          boolean isFavourite) {
        super(name, intensity, exerciseType);
        this.isFavourite = isFavourite;
    }

    /**
     * Returns {@code true} if this exercise is a favourite exercise, and {@code false} otherwise
     *
     * @return a boolean representing is the exercise is a favourite exercise
     */
    @Override
    public boolean isFavourite() { return isFavourite; }

    /**
     * Toggle the favourite status of the exercise
     */
    @Override
    public void toggleFavourite() { isFavourite = !isFavourite; }

    /**
     * Compares the current ListedExercise to another instance of InterfaceExercise
     *
     * @param other the instance of InterfaceExercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceExercise other) {
        ListedExercise otherExercise;
        boolean isEqual = false;

        if (other instanceof ListedExercise) {
            otherExercise = (ListedExercise) other;
            if (this.getName().equals(otherExercise.getName())) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the ListedExercise as a String
     *
     * @return the ListedExercise as a String
     */
    @Override
    public String toString() {
        String result = "";

        if (isFavourite)
            result += " * ";
        result += super.toString();

        return result;
    }
}
