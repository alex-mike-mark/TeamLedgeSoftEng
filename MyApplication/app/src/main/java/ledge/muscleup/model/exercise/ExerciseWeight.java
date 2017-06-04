package ledge.muscleup.model.exercise;

/**
 * Used to track the number of sets, the number of reps in each set and the weight used for
 * strength-building exercises
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class ExerciseWeight extends ExerciseSetsAndReps implements InterfaceExerciseWeight {
    double weight;
    WeightUnit unitOfMeasure;

    /**
     * The default constructor for the ExerciseSetsAndReps class
     *
     * @param sets
     * @param reps
     */
    public ExerciseWeight(int sets, int reps, double weight, WeightUnit unitOfMeasure) {
        super(sets, reps);
        this.weight = weight;
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Returns the weight to use for each rep of the exercise
     *
     * @return the weight to use
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * Returns the unit of measurement for the weight
     *
     * @return the unit of measurement
     */
    @Override
    public WeightUnit getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Compares the current ExerciseSetsAndReps to another instance of InterfaceExerciseQuantity
     *
     * @param other the instance of InterfaceExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceExerciseQuantity other) {
        ExerciseWeight weight;
        boolean isEqual = false;

        if (other instanceof ExerciseWeight) {
            weight = (ExerciseWeight) other;
            if (this.getSets() == weight.getSets() && this.getReps() == weight.getReps() &&
                    this.weight == weight.getWeight()) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the ExerciseSetsAndReps as a string
     *
     * @return the ExerciseSetsAndReps as a string
     */
    @Override
    public String toString() {
        return super.toString() + " (" + weight + " " + unitOfMeasure.toString() + ")";
    }
}
