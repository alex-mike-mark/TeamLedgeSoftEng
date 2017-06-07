package ledge.muscleup.model.exercise;

/**
 * An exercise which contains a suggested amount of exercise and for which the exercise can be
 * marked completed
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-03
 */

public class WorkoutSessionExercise implements InterfaceWorkoutSessionExercise {
    private WorkoutExercise exercise;
    private boolean isComplete;

    /**
     * The constructor for the WorkoutSessionExercise class that creates a new WorkoutExercise
     *
     * @param name         the name of the exercise
     * @param intensity    the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @param recommendedQuantity the recommended quantity of exercise for this exercise
     * @param isComplete whether the exercise has been completed
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutSessionExercise(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                                  InterfaceExerciseQuantity recommendedQuantity,
                                  boolean isComplete) throws IllegalArgumentException {
        if(name == null || intensity == null || exerciseType == null || recommendedQuantity == null){
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            exercise = new WorkoutExercise(name, intensity, exerciseType, recommendedQuantity);
            this.isComplete = isComplete;
        }
    }

    /**
     * The constructor for the WorkoutSessionExercise class that uses an existing WorkoutExercise
     *
     * @param exercise the exercise to create a WorkoutSessionExercise instance for
     * @param isComplete whether the exercise has been completed
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public WorkoutSessionExercise(WorkoutExercise exercise, boolean isComplete) throws IllegalArgumentException {
        if(exercise == null) {
            throw (new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.exercise = exercise;
            this.isComplete = isComplete;
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
    public InterfaceExerciseQuantity getRecommendedQuantity() {
        return exercise.getRecommendedQuantity();
    }

    /**
     * Returns {@code true} if the exercise has been completed, or {@code false} otherwise
     *
     * @return a boolean representing if the exercise has been completed
     */
    @Override
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Toggles the completed state of this exercise
     */
    @Override
    public void toggleCompleted() {
        isComplete = !isComplete;
        //TODO - requires database update
    }

    /**
     * Compares the current WorkoutSessionExercise to another instance of
     * InterfaceWorkoutSessionExercise
     *
     * @param other the instance of InterfaceWorkoutSessionExercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals(InterfaceWorkoutSessionExercise other) {
        return (other != null &&
                getName().equals(other.getName()) &&
                getRecommendedQuantity().equals(other.getRecommendedQuantity()) &&
                isComplete == other.isComplete());
    }

    /**
     * Returns the WorkoutSessionExercise as a String
     *
     * @return the WorkoutSessionExercise as a String
     */
    @Override
    public String toString() {
        return exercise.toString() + "\n  Recommended: " + getRecommendedQuantity().toString() +
                "\n  Completed";
    }
}
