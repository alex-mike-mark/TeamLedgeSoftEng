package ledge.muscleup;

import java.text.DecimalFormat;

/*
    ExerciseDuration used to track the length in minutes for exercises to be done for a certain
    length of time, such as cycling for 30 minutes
*/
public class ExerciseDuration implements ExerciseQuantity {
    private double minutes;

    public ExerciseDuration (double minutes) {
        this.minutes = minutes;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

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

    public String toString() {
        return new DecimalFormat("#.00").format(minutes) + " minutes";
    }
}
