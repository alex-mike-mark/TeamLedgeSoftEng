package ledge.muscleup;

import java.text.DecimalFormat;

public class ExerciseDistance implements ExerciseQuantity {
    private double distance;
    private String unitOfMeasure;

    public ExerciseDistance (double distance, String unitOfMeasure) {
        this.distance = distance;
        this.unitOfMeasure = unitOfMeasure;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

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

    public String toString() {
        return new DecimalFormat("#.00").format(distance) + " " + unitOfMeasure;
    }
}
