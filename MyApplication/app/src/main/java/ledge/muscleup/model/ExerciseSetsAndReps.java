package ledge.muscleup.model;

/**
 * Used to track the number of sets and the number of reps in each set
 * for strength-building exercises such as bicep curls, lunges, etc.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-05-29
*/

public class ExerciseSetsAndReps implements InterfaceExerciseQuantity {
    private int sets;
    private int reps;

    /**
     * The default constructor for the ExerciseSetsAndReps class
     * @param sets
     * @param reps
     */
    public ExerciseSetsAndReps (int sets, int reps) {
        this.sets = sets;
        this.reps = reps;
    }

    /**
     * Returns the number of sets for the exercise
     * @return the number of sets for the exercise
     */
    public int getSets() {
        return sets;
    }

    /**
     * Returns the number of reps for the exercise
     * @return the number of reps for the exercise
     */
    public int getReps() {
        return reps;
    }

    /**
     * Compares the current ExerciseSetsAndReps to another instance of InterfaceExerciseQuantity
     * @param other the instance of InterfaceExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(InterfaceExerciseQuantity other) {
        ExerciseSetsAndReps setsAndReps;
        boolean isEqual = false;

        if (other instanceof ExerciseSetsAndReps) {
            setsAndReps = (ExerciseSetsAndReps) other;
            if (this.sets == setsAndReps.getSets() && this.reps == setsAndReps.getReps()) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the ExerciseSetsAndReps as a string
     * @return the ExerciseSetsAndReps as a string
     */
    public String toString() {
        return sets + " Sets of " + reps + " Reps";
    }
}
