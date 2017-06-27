package ledge.muscleup.model.exercise;

/**
 * An exercise which contains a contains a suggested amount of exercise that uses sets, which can be modified
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutExerciseSets extends WorkoutExercise{
    private Exercise exercise;
    private InterfaceExerciseSets recommendedSets;

    /**
     * The constructor for the WorkoutExerciseSets class that uses an existing Exercise
     *
     * @param exercise the exercise to create a WorkoutExerciseSets instance for
     * @param recommendedSets the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseSets(Exercise exercise,
                               InterfaceExerciseSets recommendedSets) throws IllegalArgumentException {
        super(exercise);
        if(recommendedSets == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.exercise = exercise;
            this.recommendedSets = recommendedSets;
        }
    }

    public InterfaceExerciseQuantity getQuantity() { return recommendedSets; }

    /**
     * Updates the exercise with the new recommended sets
     *
     * @param quantity the recommended quantity to update the exercise to
     * @return a boolean representing if the suggested quantity could be updated
     */
    @Override
    public boolean updateQuantity(InterfaceExerciseQuantity quantity) {
        boolean updated = false;
        if(quantity instanceof InterfaceExerciseSets){
            recommendedSets = (InterfaceExerciseSets)quantity;
            updated = true;
        }
        return false;
    }

    /**
     * Compares the current WorkoutExerciseSets to another instance of WorkoutExerciseSets
     *
     * @param other the instance of WorkoutExerciseSets to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(WorkoutExerciseSets other){
        return (other != null &&
                getName().equals(other.getName()) &&
                recommendedSets.equals(other.getQuantity()));
    }

    /**
     * Returns the WorkoutExerciseSets as a String
     *
     * @return the WorkoutExerciseSets as a String
     */
    @Override
    public String toString() {
        return exercise.toString() + "\n  Recommended sets: " + recommendedSets.toString();
    }
}
