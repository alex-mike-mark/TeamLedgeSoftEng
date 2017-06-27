package ledge.muscleup.model.exercise;

/**
 * An exercise which contains a contains a suggested amount of exercise that uses sets and weight, which can be modified
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutExerciseSetsAndWeight extends WorkoutExercise{
    private Exercise exercise;
    private InterfaceExerciseSetsAndWeight recommendedSetsAndWeight;

    /**
     * The constructor for the WorkoutExerciseSetsAndWeight class that uses an existing Exercise
     *
     * @param exercise the exercise to create a WorkoutExerciseSetsAndWeight instance for
     * @param recommendedSetsAndWeight the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseSetsAndWeight(Exercise exercise,
                                        InterfaceExerciseSetsAndWeight recommendedSetsAndWeight) throws IllegalArgumentException {
        super(exercise);
        if(recommendedSetsAndWeight == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.exercise = exercise;
            this.recommendedSetsAndWeight = recommendedSetsAndWeight;
        }
    }

    /**
     * Returns the recommended sets and weight of exercise for the exercise
     *
     * @return the recommended sets and weight of exercise
     */
    public InterfaceExerciseSetsAndWeight getQuantity() { return recommendedSetsAndWeight; }

    /**
     * Updates the exercise with the new recommended sets and weight
     *
     * @param quantity the recommended quantity to update the exercise to
     * @return a boolean representing if the suggested quantity could be updated
     */
    @Override
    public boolean updateQuantity(InterfaceExerciseQuantity quantity) {
        boolean updated = false;
        if(quantity instanceof InterfaceExerciseSetsAndWeight){
            recommendedSetsAndWeight = (InterfaceExerciseSetsAndWeight) quantity;
            updated = true;
        }
        return updated;
    }

    /**
     * Compares the current WorkoutExerciseSetsAndWeight to another instance of WorkoutExerciseSetsAndWeight
     *
     * @param other the instance of WorkoutExerciseSetsAndWeight to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(WorkoutExerciseSetsAndWeight other){
        return (other != null &&
                getName().equals(other.getName()) &&
                recommendedSetsAndWeight.equals(other.getQuantity()));
    }

    /**
     * Returns the WorkoutExerciseSetsAndWeight as a String
     *
     * @return the WorkoutExerciseSetsAndWeight as a String
     */
    @Override
    public String toString() {
        return exercise.toString() + "\n  Recommended sets and weight: " + recommendedSetsAndWeight.toString();
    }
}
