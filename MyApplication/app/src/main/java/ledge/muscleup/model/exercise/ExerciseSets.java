package ledge.muscleup.model.exercise;

/**
 * Used to track the number of sets and the number of reps in each set
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-05-29
*/

public class ExerciseSets implements InterfaceExerciseSetsAndReps {
    private int sets;
    private int reps;

    /**
     * The default constructor for the ExerciseSets class
     * @param sets
     * @param reps
     */
    public ExerciseSets(int sets, int reps) {
        if(sets < 0 || reps < 0){
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else{
            this.sets = sets;
            this.reps = reps;
        }
    }

    /**
     * Returns the number of sets for the exercise
     * @return the number of sets for the exercise
     */
    @Override
    public int getSets() {
        return sets;
    }

    /**
     * Returns the number of reps for the exercise
     * @return the number of reps for the exercise
     */
    @Override
    public int getReps() {
        return reps;
    }

    /**
     * Compares the current ExerciseSets to another instance of InterfaceExerciseQuantity
     * @param other the instance of InterfaceExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceExerciseQuantity other) {
        ExerciseSets setsAndReps;
        boolean isEqual = false;

        if (other instanceof ExerciseSets) {
            setsAndReps = (ExerciseSets) other;
            if (this.sets == setsAndReps.getSets() && this.reps == setsAndReps.getReps()) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the ExerciseSets as a string
     * @return the ExerciseSets as a string
     */
    @Override
    public String toString() {
        return sets + " sets of " + reps + " reps";
    }
}
