package ledge.muscleup.model.exercise;

/**
 * Used to track the number of sets, the number of reps in each set and the weight used for
 * strength-building exercises
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class ExerciseSetsAndWeight extends ExerciseSets {
    private double weight;
    private WeightUnit unitOfMeasure;

    /**
     * The default constructor for the ExerciseSets class
     *
     * @param sets the number of sets for the exercise
     * @param reps the number of reps for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public ExerciseSetsAndWeight(int sets, int reps, double weight,
                                 WeightUnit unitOfMeasure) throws IllegalArgumentException {
        super(sets, reps);
        if(unitOfMeasure == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.weight = weight;
            this.unitOfMeasure = unitOfMeasure;
        }
    }

    /**
     * Returns the weight to use for each rep of the exercise
     *
     * @return the weight to use
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the unit of measurement for the weight
     *
     * @return the unit of measurement
     */
    public WeightUnit getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Compares the current ExerciseSets to another instance of InterfaceExerciseQuantity
     *
     * @param other the instance of InterfaceExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceExerciseQuantity other) {
        ExerciseSetsAndWeight weight;
        boolean isEqual = false;

        if (other instanceof ExerciseSetsAndWeight) {
            weight = (ExerciseSetsAndWeight) other;
            if (this.getSets() == weight.getSets() && this.getReps() == weight.getReps() &&
                    this.weight == weight.getWeight()) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the ExerciseSets as a string
     *
     * @return the ExerciseSets as a string
     */
    @Override
    public String toString() {
        return super.toString() + " (" + weight + " " + unitOfMeasure.toString() + ")";
    }
}
