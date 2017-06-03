package ledge.muscleup.model.exercise;

/**
 * An exercise for which the amount of exercise can be tracked
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class TrackableExercise extends Exercise implements InterfaceTrackableExercise {
    InterfaceExerciseQuantity recommendedQuantity;
    InterfaceExerciseQuantity trackedQuantity;

    /**
     * The default constructor for the TrackableExercise class
     *
     * @param name         the name of the exercise
     * @param intensity    the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @param recommendedQuantity the recommended quantity of exercise for this exercise
     * @param trackedQuantity the amount of exercise complete for this exercise
     */
    public TrackableExercise(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                             InterfaceExerciseQuantity recommendedQuantity,
                             InterfaceExerciseQuantity trackedQuantity) {
        super(name, intensity, exerciseType);
        this.recommendedQuantity = recommendedQuantity;
        this.trackedQuantity = trackedQuantity;
    }

    /**
     * Returns the recommended quantity of exercise for the exercise
     *
     * @return the recommended quantity of exercise
     */
    @Override
    public InterfaceExerciseQuantity getRecommendedQuantity() {
        return recommendedQuantity;
    }

    /**
     * Returns the quantity of exercise logged for this exercise
     *
     * @return the quantity of exercise logged
     */
    @Override
    public InterfaceExerciseQuantity getTrackedQuantity() {
        return trackedQuantity;
    }

    /**
     * Updates the exercise with the new quantity
     *
     * @param quantity the quantity to update the exercise to
     * @returns a boolean representing if the tracked quantity could be updated
     */
    @Override
    public boolean updateTrackedQuantity(InterfaceExerciseQuantity quantity) {
        boolean quantityUpdated = false;

        if (quantity.getClass().isInstance(trackedQuantity.getClass())) {
            trackedQuantity = quantity;
            quantityUpdated = true;
        }

        return quantityUpdated;
    }

    /**
     * Compares the current TrackableExercise to another instance of InterfaceExercise
     *
     * @param other the instance of InterfaceExercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceExercise other) {
        TrackableExercise otherExercise;
        boolean isEqual = false;

        if (other instanceof TrackableExercise) {
            otherExercise = (TrackableExercise) other;
            if (this.getName().equals(otherExercise.getName())) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the TrackableExercise as a String
     * @return the TrackableExercise as a String
     */
    @Override
    public String toString() {
        return super.toString() + "\n  Recommended: " + recommendedQuantity +
                "\n  Logged: " + trackedQuantity + ")";
    }
}
