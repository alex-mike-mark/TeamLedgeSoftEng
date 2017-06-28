package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * An exercise which contains a contains a suggested amount of exercise that uses duration, which can be modified
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutExerciseDuration extends WorkoutExercise{
    private InterfaceExerciseDuration recommendedDuration;

    /**
     * The constructor for the WorkoutExerciseDuration class that uses an existing Exercise
     *
     * @param exercise the exercise to create a WorkoutExerciseDuration instance for
     * @param recommendedDuration the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseDuration(Exercise exercise, int xpValue,
                                   InterfaceExerciseDuration recommendedDuration) throws IllegalArgumentException {
        super(exercise, xpValue);
        if(recommendedDuration == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.recommendedDuration = recommendedDuration;
        }
    }

    /**
     * Returns the recommended duration for the exercise. It returns an interface, so if there is
     * specific functionality required by a certain implementation, you need to find another way.
     * @return the recommended duration of exercise
     */
    public InterfaceExerciseQuantity getQuantity() { return recommendedDuration; }

    /**
     * Updates the recommendedDuration
     * Note, this method takes in a generic InterfaceExerciseQuantity where specific subclasses require
     * specific implementations of that interface. Type checking MUST happen in the implementation.
     * I am quite aware this isn't great but whatever.
     * @param quantity
     * @return
     */
    @Override
    public boolean updateQuantity(InterfaceExerciseQuantity quantity) {
        boolean updated = false;
        if(quantity instanceof InterfaceExerciseDuration){
            recommendedDuration = (InterfaceExerciseDuration) quantity;
            updated = true;
        }
        return updated;
    }

    /**
     * Compares the current WorkoutExerciseDuration to another instance of WorkoutExerciseDuration
     *
     * @param other the instance of WorkoutExerciseDuration to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(WorkoutExerciseDuration other){
        return (other != null &&
                getName().equals(other.getName()) &&
                recommendedDuration.equals(other.getQuantity()));
    }

    /**
     * Returns the WorkoutExerciseDuration as a String
     *
     * @return the WorkoutExerciseDuration as a String
     */
    @Override
    public String toString() {
        return super.toString() + "\n  Recommended duration: " + recommendedDuration.toString();
    }
}
