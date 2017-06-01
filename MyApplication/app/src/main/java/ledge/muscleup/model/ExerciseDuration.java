package ledge.muscleup.model;

import java.text.DecimalFormat;

/**
    Used to track the length in minutes for exercises to be done for a certain
    length of time, such as cycling for 30 minutes
*/
public class ExerciseDuration implements ExerciseQuantity {
    private double minutes;

    /**
     * Default constructor for the ExerciseDuration class
     * @param minutes the number of minutes exercised
     */
    public ExerciseDuration (double minutes) {
        this.minutes = minutes;
    }

    /**
     * Get the number of minutes exercised
     * @return the number of minutes exercised
     */
    public double getMinutes() {
        return minutes;
    }

    /**
     * Sets the number of minutes exercised
     * @param minutes the number of minutes exercised
     */
    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    /**
     * Compares the current ExerciseDuration to another instance of ExerciseQuantity
     * @param other the instance of ExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals (ExerciseQuantity other) {
        ExerciseDuration otherDuration;
        boolean isEqual = false;

        if (other instanceof ExerciseDuration) {
            otherDuration = (ExerciseDuration) other;
            if (this.minutes == otherDuration.getMinutes()) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the ExerciseDuration as a string
     * @return the ExerciseDuration as a string
     */
    public String toString() {
        return new DecimalFormat("#.00").format(minutes) + " minutes";
    }
}
