package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * Created by Alexander on 2017-06-24.
 */

public abstract class WorkoutExercise {
    Exercise exercise;

    WorkoutExercise(String name, ExerciseIntensity intensity, ExerciseType exerciseType) throws IllegalArgumentException {
        if(name == null || intensity == null || exerciseType == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else{
            exercise = new Exercise(name, intensity, exerciseType);
        }
    }

    WorkoutExercise(Exercise exercise) throws IllegalArgumentException{
        if(exercise == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        this.exercise = exercise;
    }

    WorkoutExercise(){
        exercise = null;
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

    public abstract InterfaceExerciseQuantity getQuantity();
}
