package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.TimeUnit;

/**
 * Used to track the length of time for exercises to be done for a certain
 * length of time, such as cycling for 30 minutes
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-05-29
*/
public class ExerciseDuration implements InterfaceExerciseDuration {
    private int time;
    private TimeUnit unitOfMeasure;

    /**
     * Default constructor for the ExerciseDuration class
     * @param time the number for time exercised
     * @throws IllegalArgumentException if {@code time < 0}
     */
    public ExerciseDuration (int time, TimeUnit unitOfMeasure) throws IllegalArgumentException {
        if(time < 0) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.time = time;
            this.unitOfMeasure = unitOfMeasure;
        }
    }

    /**
     * Get the number for time exercised
     * @return the number for time exercised
     */
    @Override
    public int getTime() {
        return time;
    }

    /**
     * Returns the unit of measurement for the distance
     * @return the unit of measurement
     */
    @Override
    public TimeUnit getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Compares the current ExerciseDuration to another instance of InterfaceExerciseQuantity
     * @param other the instance of InterfaceExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    @Override
    public boolean equals (InterfaceExerciseQuantity other) {
        ExerciseDuration otherDuration;
        boolean isEqual = false;

        if (other instanceof ExerciseDuration) {
            otherDuration = (ExerciseDuration) other;
            if (this.time == otherDuration.getTime()
                    && this.unitOfMeasure == otherDuration.getUnitOfMeasure()) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the ExerciseDuration as a string
     * @return the ExerciseDuration as a string
     */
    @Override
    public String toString() {
        return super.toString() + " (" + time + " " + unitOfMeasure.toString() + ")";
    }
}
