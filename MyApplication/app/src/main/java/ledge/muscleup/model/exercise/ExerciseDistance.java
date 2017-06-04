package ledge.muscleup.model.exercise;

import java.text.DecimalFormat;

/**
 * Used to track distance metrics for exercises such as running
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-05-29
*/
public class ExerciseDistance implements InterfaceExerciseDistance {
    private double distance;
    private DistanceUnit unitOfMeasure;

    /**
     * Default constructor for the ExerciseDistance class
     * @param distance the distance for which an exercise is completed
     * @param unitOfMeasure the unit of measurement for the distance
     */
    public ExerciseDistance (double distance, DistanceUnit unitOfMeasure) {
        this.distance = distance;
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Returns the distance for which an exercise is completed
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the unit of measurement for the distance
     * @return the unit of measurement
     */
    public DistanceUnit getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Compares the current ExerciseDistance to another instance of InterfaceExerciseQuantity
     * @param other the instance of InterfaceExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals (InterfaceExerciseQuantity other) {
        ExerciseDistance otherDistance;
        boolean isEqual = false;

        if (other instanceof ExerciseDistance) {
            otherDistance = (ExerciseDistance) other;
            if (this.distance == otherDistance.getDistance()
                    && this.unitOfMeasure.equals(otherDistance.getUnitOfMeasure())) {
                isEqual = true;
            }
        }

        return isEqual;
    }

    /**
     * Returns the ExerciseDistance as a string
     * @return the ExerciseDistance as a string
     */
    public String toString() {
        return new DecimalFormat("#.00").format(distance) + " " + unitOfMeasure;
    }
}
