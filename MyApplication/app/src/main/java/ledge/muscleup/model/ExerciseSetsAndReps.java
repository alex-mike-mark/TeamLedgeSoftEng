package ledge.muscleup.model;

/*
    ExerciseSetsAndReps used to track the number of sets and the number of reps in each set
    for strength-building exercises such as bicep curls, lunges, etc.
*/

public class ExerciseSetsAndReps implements ExerciseQuantity {
    private int sets;
    private int reps;

    public ExerciseSetsAndReps (int sets, int reps) {
        this.sets = sets;
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

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

    public String toString() {
        return sets + " Sets of " + reps + " Reps";
    }
}
