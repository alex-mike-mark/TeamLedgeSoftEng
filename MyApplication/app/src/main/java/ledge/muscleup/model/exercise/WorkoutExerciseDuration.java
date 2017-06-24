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

public class WorkoutExerciseDuration {
    private Exercise exercise;
    private InterfaceExerciseDuration recommendedDuration;

    /**
     * The constructor for the WorkoutExerciseDuration class that creates a new Exercise
     *
     * @param name         the name of the exercise
     * @param intensity    the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @param recommendedDuration the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseDuration(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                                   InterfaceExerciseDuration recommendedDuration) throws IllegalArgumentException {
        if(name == null || intensity == null || exerciseType == null || recommendedDuration == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            exercise = new Exercise(name, intensity, exerciseType);
            this.recommendedDuration = recommendedDuration;
        }
    }

    /**
     * The constructor for the WorkoutExerciseDuration class that uses an existing Exercise
     *
     * @param exercise the exercise to create a WorkoutExerciseDuration instance for
     * @param recommendedDuration the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseDuration(Exercise exercise,
                                   InterfaceExerciseDuration recommendedDuration) throws IllegalArgumentException {
        if(exercise == null || recommendedDuration == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.exercise = exercise;
            this.recommendedDuration = recommendedDuration;
        }
    }

    /**
     * Returns the name of the exercise
     *
     * @return the name of the exercise
     */
    public String getName() {
        return exercise.getName();
    }

    /**
     * Returns the intensity of the exercise
     *
     * @return the intensity of the exercise
     */
    public ExerciseIntensity getIntensity() {
        return exercise.getIntensity();
    }

    /**
     * Returns the type of the exercise
     *
     * @return the type of the exercise
     */
    public ExerciseType getType() {
        return exercise.getType();
    }

    /**
     * Returns the recommended duration of exercise for the exercise
     *
     * @return the recommended duration of exercise
     */
    public InterfaceExerciseDuration getRecommendedDuration() { return recommendedDuration; }

    /**
     * Updates the exercise with the new recommended duration
     *
     * @param quantity the recommended quantity to update the exercise to
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing if the suggested quantity could be updated
     */
    public boolean updateRecommendedDuration(InterfaceExerciseDuration quantity) throws IllegalArgumentException {
        boolean quantityUpdated = false;

        if (quantity == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else if (recommendedDuration.getClass().isInstance(quantity)) {
            recommendedDuration = quantity;
            //TODO - requires database update
            quantityUpdated = true;
        }

        return quantityUpdated;
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
                recommendedDuration.equals(other.getRecommendedDuration()));
    }

    /**
     * Returns the WorkoutExerciseDuration as a String
     *
     * @return the WorkoutExerciseDuration as a String
     */
    @Override
    public String toString() {
        return exercise.toString() + "\n  Recommended duration: " + recommendedDuration.toString();
    }
}
