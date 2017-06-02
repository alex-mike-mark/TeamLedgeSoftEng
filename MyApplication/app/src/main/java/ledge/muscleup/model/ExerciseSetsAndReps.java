package ledge.muscleup.model;

/**
 * Used to track the number of sets and the number of reps in each set
 * for strength-building exercises such as bicep curls, lunges, etc.
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-05-29
*/

public class ExerciseSetsAndReps implements ExerciseQuantity {
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
     * Sets the number of sets for an exercise
     * @param sets the number of sets for an exercise
     */
    public void setSets(int sets) {
        this.sets = sets;
    }

    /**
     * Returns the number of reps for the exercise
     * @return the number of reps for the exercise
     */
    public int getReps() {
        return reps;
    }

    /**
     * Sets the number of reps for an exercise
     * @param reps the number of reps for an exercise
     */
    public void setReps(int reps) {
        this.reps = reps;
    }

    /**
     * Compares the current ExerciseSetsAndReps to another instance of ExerciseQuantity
     * @param other the instance of ExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(ExerciseQuantity other) {
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
