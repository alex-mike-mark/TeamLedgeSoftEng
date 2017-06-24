package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * An exercise which contains a contains a suggested amount of exercise that uses sets, which can be modified
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutExerciseSets {
    private Exercise exercise;
    private WorkoutExerciseSets recommendedSets;

    /**
     * The constructor for the WorkoutExerciseSets class that creates a new Exercise
     *
     * @param name         the name of the exercise
     * @param intensity    the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @param recommendedSets the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseSets(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                               WorkoutExerciseSets recommendedSets) throws IllegalArgumentException {
        if(name == null || intensity == null || exerciseType == null || recommendedSets == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            exercise = new Exercise(name, intensity, exerciseType);
            this.recommendedSets = recommendedSets;
        }
    }

    /**
     * The constructor for the WorkoutExerciseSets class that uses an existing Exercise
     *
     * @param exercise the exercise to create a WorkoutExerciseSets instance for
     * @param recommendedSets the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseSets(Exercise exercise,
                               WorkoutExerciseSets recommendedSets) throws IllegalArgumentException {
        if(exercise == null || recommendedSets == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.exercise = exercise;
            this.recommendedSets = recommendedSets;
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
     * Returns the recommended sets of exercise for the exercise
     *
     * @return the recommended sets of exercise
     */
    public WorkoutExerciseSets getRecommendedSets() { return recommendedSets; }

    /**
     * Updates the exercise with the new recommended sets
     *
     * @param quantity the recommended quantity to update the exercise to
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing if the suggested quantity could be updated
     */
    public boolean updateRecommendedSets(WorkoutExerciseSets quantity) throws IllegalArgumentException {
        boolean quantityUpdated = false;

        if (quantity == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else if (recommendedSets.getClass().isInstance(quantity)) {
            recommendedSets = quantity;
            //TODO - requires database update
            quantityUpdated = true;
        }

        return quantityUpdated;
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
                recommendedSets.equals(other.getRecommendedSets()));
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
