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
    public WorkoutExerciseSets(Exercise exercise, int xpValue,
                               InterfaceExerciseSets recommendedSets) throws IllegalArgumentException {
        super(exercise, xpValue);
        if(recommendedSets == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.exercise = exercise;
            this.recommendedSets = recommendedSets;
        }
    }
    /**
     * Returns the recommended sets for the exercise. It returns an interface, so if there is
     * specific functionality required by a certain implementation, you need to find another way.
     * @return the recommended sets and weight of exercise
     */
    public InterfaceExerciseQuantity getQuantity() { return recommendedSets; }

    /**
     * Updates the recommendedSets.
     * Note, this method takes in a generic InterfaceExerciseQuantity where specific subclasses require
     * specific implementations of that interface. Type checking MUST happen in the implementation.
     * I am quite aware this isn't great but whatever.
     * @param quantity the quantity of exercise to update the workout exercise with
     * @return a boolean representing whether the quantity was updated or not
     */
    @Override
    public boolean updateQuantity(InterfaceExerciseQuantity quantity) {
        boolean updated = false;
        if(quantity instanceof InterfaceExerciseSets && !(quantity instanceof InterfaceExerciseSetsAndWeight)){
            recommendedSets = (InterfaceExerciseSets)quantity;
            updated = true;
        }
        return updated;
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
