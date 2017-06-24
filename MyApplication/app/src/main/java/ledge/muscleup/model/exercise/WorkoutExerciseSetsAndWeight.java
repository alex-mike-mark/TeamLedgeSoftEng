package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * An exercise which contains a contains a suggested amount of exercise that uses sets and weight, which can be modified
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutExerciseSetsAndWeight {
    private Exercise exercise;
    private WorkoutExerciseSetsAndWeight recommendedSetsAndWeight;

    /**
     * The constructor for the WorkoutExerciseSetsAndWeight class that creates a new Exercise
     *
     * @param name         the name of the exercise
     * @param intensity    the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @param recommendedSetsAndWeight the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseSetsAndWeight(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                                        WorkoutExerciseSetsAndWeight recommendedSetsAndWeight) throws IllegalArgumentException {
        if(name == null || intensity == null || exerciseType == null || recommendedSetsAndWeight == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            exercise = new Exercise(name, intensity, exerciseType);
            this.recommendedSetsAndWeight = recommendedSetsAndWeight;
        }
    }

    /**
     * The constructor for the WorkoutExerciseSetsAndWeight class that uses an existing Exercise
     *
     * @param exercise the exercise to create a WorkoutExerciseSetsAndWeight instance for
     * @param recommendedSetsAndWeight the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExerciseSetsAndWeight(Exercise exercise,
                                        WorkoutExerciseSetsAndWeight recommendedSetsAndWeight) throws IllegalArgumentException {
        if(exercise == null || recommendedSetsAndWeight == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.exercise = exercise;
            this.recommendedSetsAndWeight = recommendedSetsAndWeight;
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
     * Returns the recommended sets and weight of exercise for the exercise
     *
     * @return the recommended sets and weight of exercise
     */
    public WorkoutExerciseSetsAndWeight getRecommendedSetsAndWeight() { return recommendedSetsAndWeight; }

    /**
     * Updates the exercise with the new recommended sets and weight
     *
     * @param quantity the recommended quantity to update the exercise to
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing if the suggested quantity could be updated
     */
    public boolean updateRecommendedSetsAndWeight(WorkoutExerciseSetsAndWeight quantity) throws IllegalArgumentException {
        boolean quantityUpdated = false;

        if (quantity == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else if (recommendedSetsAndWeight.getClass().isInstance(quantity)) {
            recommendedSetsAndWeight = quantity;
            //TODO - requires database update
            quantityUpdated = true;
        }

        return quantityUpdated;
    }

    /**
     * Compares the current WorkoutExerciseSetsAndWeight to another instance of WorkoutExerciseSetsAndWeight
     *
     * @param other the instance of WorkoutExerciseSetsAndWeight to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(WorkoutExerciseSetsAndWeight other){
        return (other != null &&
                getName().equals(other.getName()) &&
                recommendedSetsAndWeight.equals(other.getRecommendedSetsAndWeight()));
    }

    /**
     * Returns the WorkoutExerciseSetsAndWeight as a String
     *
     * @return the WorkoutExerciseSetsAndWeight as a String
     */
    @Override
    public String toString() {
        return exercise.toString() + "\n  Recommended sets and weight: " + recommendedSetsAndWeight.toString();
    }
}
