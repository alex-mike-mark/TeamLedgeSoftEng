package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * An exercise which contains a contains a suggested amount of exercise that uses distance, which can be modified
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutExerciseDistance extends WorkoutExercise{
    private InterfaceExerciseDistance recommendedDistance;

    /**
     * The constructor for the WorkoutExerciseDistance class that uses an existing Exercise
     *
     * @param exercise the exercise to create a WorkoutExerciseDistance instance for
     * @param recommendedDistance the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseDistance(Exercise exercise,
                                   InterfaceExerciseDistance recommendedDistance) throws IllegalArgumentException {
        super(exercise);
        if(recommendedDistance == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.recommendedDistance = recommendedDistance;
        }
    }

    /**
     * Returns the recommended distance of exercise for the exercise
     *
     * @return the recommended distance of exercise
     */
    public InterfaceExerciseQuantity getQuantity() { return recommendedDistance; }

    @Override
    public boolean updateQuantity(InterfaceExerciseQuantity quantity) {
        boolean updated = false;
        if(quantity instanceof InterfaceExerciseDistance){
            recommendedDistance = (InterfaceExerciseDistance)quantity;
            updated = true;
        }
        return updated;
    }

    /**
     * Compares the current WorkoutExerciseDistance to another instance of WorkoutExerciseDistance
     *
     * @param other the instance of WorkoutExerciseDistance to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(WorkoutExerciseDistance other){
        return (other != null &&
                getName().equals(other.getName()) &&
                recommendedDistance.equals(other.getQuantity()));
    }

    /**
     * Returns the WorkoutExerciseDistance as a String
     *
     * @return the WorkoutExerciseDistance as a String
     */
    @Override
    public String toString() {
        return exercise.toString() + "\n  Recommended distance: " + recommendedDistance.toString();
    }
}
