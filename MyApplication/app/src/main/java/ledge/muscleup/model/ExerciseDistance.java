package ledge.muscleup.model;

import java.text.DecimalFormat;

/**
    Used to track distance metrics for exercises such as running
*/
public class ExerciseDistance implements ExerciseQuantity {
    private double distance;
    private String unitOfMeasure;

    /**
     * Default constructor for the ExerciseDistance class
     * @param distance the distance for which an exercise is completed
     * @param unitOfMeasure the unit of measurement for the distance
     */
    public ExerciseDistance (double distance, String unitOfMeasure) {
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
     * Sets the distance for which an exercise is completed
     * @param distance the distance for which an exercise is completed
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Returns the unit of measurement for the distance
     * @return the unit of measurement
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Compares the current ExerciseDistance to another instance of ExerciseQuantity
     * @param other the instance of ExerciseQuantity to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals (ExerciseQuantity other) {
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
