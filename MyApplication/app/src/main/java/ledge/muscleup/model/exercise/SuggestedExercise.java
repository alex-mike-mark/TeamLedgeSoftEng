package ledge.muscleup.model.exercise;

/**
 * An exercise which contains a suggested amount of exercise, which can be modified
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class SuggestedExercise extends Exercise implements InterfaceSuggestedExercise {
    InterfaceExerciseQuantity recommendedQuantity;

    /**
     * The default constructor for the SuggestedExercise class
     *
     * @param name         the name of the exercise
     * @param intensity    the intensity of the exercise
     * @param exerciseType the type of the exercise
     */
    public SuggestedExercise(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                                InterfaceExerciseQuantity recommendedQuantity) {
        super(name, intensity, exerciseType);

        if(recommendedQuantity == null){
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else{
            this.recommendedQuantity = recommendedQuantity;
        }
    }

    /**
     * Returns the recommended quantity of exercise for the exercise
     *
     * @return the recommended quantity of exercise
     */
    @Override
    public InterfaceExerciseQuantity getRecommendedQuantity() { return recommendedQuantity; }

    /**
     * Updates the exercise with the new recommended quantity
     *
     * @param quantity the recommended quantity to update the exercise to
     * @return a boolean representing if the suggested quantity could be updated
     */
    @Override
    public boolean updateRecommendedQuantity(InterfaceExerciseQuantity quantity) {
        boolean quantityUpdated = false;

        if(quantity != null){
            recommendedQuantity = quantity;
            quantityUpdated = true;
        }
        else{
            throw(new IllegalArgumentException("Invalid or null data passed to a method"));
        }

        return quantityUpdated;
    }

    /**
     * Compares the current SuggestedExercise to another instance of InterfaceExercise
     *
     * @param other the instance of InterfaceExercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceExercise other){
        SuggestedExercise otherExercise;
        boolean isEqual = false;

        if (other instanceof SuggestedExercise) {
            otherExercise = (SuggestedExercise) other;
            if (this.getName().equals(otherExercise.getName())) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the SuggestedExercise as a String
     *
     * @return the SuggestedExercise as a String
     */
    @Override
    public String toString() {
        return super.toString() + "\n  Recommended: " + recommendedQuantity.toString();
    }
}
