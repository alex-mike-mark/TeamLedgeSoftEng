package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * Created by Alexander on 2017-06-24.
 */

public abstract class WorkoutExercise {
    Exercise exercise;
    int experienceValue;

    public WorkoutExercise(Exercise exercise, int xpValue) throws IllegalArgumentException {
        if(exercise==null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else{
            this.exercise = exercise;
            this.experienceValue = xpValue;
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
     * Returns the quantity of the exercise in question. It returns an interface, so if there is
     * specific functionality required by a certain implementation, you need to find another way.
     * @return the quantity of the exercise as the interface it implements.
     */
    public abstract InterfaceExerciseQuantity getQuantity();

    /**
     * Updates the quantity for a given WorkoutExercise.
     * Note, this method takes in a generic InterfaceExerciseQuantity where specific subclasses require
     * specific implementations of that interface. Type checking MUST happen in the implementation.
     * I am quite aware this isn't great but whatever.
     * @param quantity
     * @return
     */
    public abstract boolean updateQuantity(InterfaceExerciseQuantity quantity);

    /**
     * Returns the experience value of the WorkoutExercise
     * @return the experience value of the WorkoutExercise
     */
    public int getExperienceValue() {
        return experienceValue;
    }

    /**
     * Compares the current WorkoutExercise to another instance of WorkoutExercise
     *
     * @param other the instance of WorkoutExercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(WorkoutExercise other){
        return (other != null &&
                getName().equals(other.getName()));
    }

    /**
     * Returns the WorkoutExercise as a String
     *
     * @return the WorkoutExercise as a String
     */
    @Override
    public String toString() {
        return exercise.toString();
    }
}
