package ledge.muscleup.model.exercise;

/**
 * An exercise which contains a contains a suggested amount of exercise, which can be modified
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutExercise implements InterfaceWorkoutExercise {
    private Exercise exercise;
    private InterfaceExerciseQuantity recommendedQuantity;

    /**
     * The constructor for the WorkoutExercise class that creates a new Exercise
     *
     * @param name         the name of the exercise
     * @param intensity    the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @param recommendedQuantity the quantity of exercise recommended for the exercise
     */
    public WorkoutExercise(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                           InterfaceExerciseQuantity recommendedQuantity) {
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
     */
    public WorkoutExercise(Exercise exercise, InterfaceExerciseQuantity recommendedQuantity) {
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
    @Override
    public String getName() {
        return exercise.getName();
    }

    /**
     * Returns the intensity of the exercise
     *
     * @return the intensity of the exercise
     */
    @Override
    public ExerciseIntensity getIntensity() {
        return exercise.getIntensity();
    }

    /**
     * Returns the type of the exercise
     *
     * @return the type of the exercise
     */
    @Override
    public ExerciseType getType() {
        return exercise.getType();
    }

    /**
     * Returns the recommended quantity of exercise for the exercise
     *
     * @return the recommended quantity of exercise
     */
    @Override
    public InterfaceExerciseQuantity getRecommendedQuantity() { return recommendedQuantity; }

    /**
     * Updates the exercise with the new recommended quantity
     *
     * @param quantity the recommended quantity to update the exercise to
     * @return a boolean representing if the suggested quantity could be updated
     */
    @Override
    public boolean updateRecommendedQuantity(InterfaceExerciseQuantity quantity) {
        boolean quantityUpdated = false;

        if (quantity == null)
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        else if (recommendedQuantity.getClass().isInstance(quantity)) {
            recommendedQuantity = quantity;
            quantityUpdated = true;
        }

        return quantityUpdated;
    }

    /**
     * Compares the current WorkoutExercise to another instance of InterfaceWorkoutExercise
     *
     * @param other the instance of InterfaceWorkoutExercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceWorkoutExercise other){
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
