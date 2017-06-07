package ledge.muscleup.model.exercise;

/**
 * An exercise which contains a contains a suggested amount of exercise, which can be modified
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutExercise {
    private Exercise exercise;
    private InterfaceExerciseQuantity recommendedQuantity;

    /**
     * The constructor for the WorkoutExercise class that creates a new Exercise
     *
     * @param name         the name of the exercise
     * @param intensity    the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @param recommendedQuantity the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExercise(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                           InterfaceExerciseQuantity recommendedQuantity) throws IllegalArgumentException {
        if(name == null || intensity == null || exerciseType == null || recommendedQuantity == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            exercise = new Exercise(name, intensity, exerciseType);
            this.recommendedQuantity = recommendedQuantity;
        }
    }

    /**
     * The constructor for the WorkoutExercise class that uses an existing Exercise
     *
     * @param exercise the exercise to create a WorkoutExercise instance for
     * @param recommendedQuantity the quantity of exercise recommended for the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutExercise(Exercise exercise,
                           InterfaceExerciseQuantity recommendedQuantity) throws IllegalArgumentException {
        if(exercise == null || recommendedQuantity == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.exercise = exercise;
            this.recommendedQuantity = recommendedQuantity;
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
     * Returns the recommended quantity of exercise for the exercise
     *
     * @return the recommended quantity of exercise
     */
    public InterfaceExerciseQuantity getRecommendedQuantity() { return recommendedQuantity; }

    /**
     * Updates the exercise with the new recommended quantity
     *
     * @param quantity the recommended quantity to update the exercise to
     * @throws IllegalArgumentException if passed a {@code null} parameter
     * @return a boolean representing if the suggested quantity could be updated
     */
    public boolean updateRecommendedQuantity(InterfaceExerciseQuantity quantity) throws IllegalArgumentException {
        boolean quantityUpdated = false;

        if (quantity == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else if (recommendedQuantity.getClass().isInstance(quantity)) {
            recommendedQuantity = quantity;
            //TODO - requires database update
            quantityUpdated = true;
        }

        return quantityUpdated;
    }

    /**
     * Compares the current WorkoutExercise to another instance of WorkoutExercise
     *
     * @param other the instance of WorkoutExercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(WorkoutExercise other){
        return (other != null &&
                getName().equals(other.getName()) &&
                recommendedQuantity.equals(other.getRecommendedQuantity()));
    }

    /**
     * Returns the WorkoutExercise as a String
     *
     * @return the WorkoutExercise as a String
     */
    @Override
    public String toString() {
        return exercise.toString() + "\n  Recommended: " + recommendedQuantity.toString();
    }
}
