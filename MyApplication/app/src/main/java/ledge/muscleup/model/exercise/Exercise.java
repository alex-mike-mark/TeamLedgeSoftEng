package ledge.muscleup.model.exercise;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * An exercise as part of a workout, which consists of an exercise name, type, intensity, and whether
 * or not this exercise is a favourite exercise
 *
 * @author Alexander Mark
 * @version 1.0
 * @since 2017-05-25
 */
public class Exercise {
    private String name;
    private ExerciseIntensity intensity;
    private ExerciseType exerciseType;
    private boolean isFavourite;

    /**
     * The default constructor for the Exercise class
     * @param name the name of the exercise
     * @param intensity the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @param isFavourite whether the exercise is favourite or not
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public Exercise(String name, ExerciseIntensity intensity, ExerciseType exerciseType,
                       boolean isFavourite) throws IllegalArgumentException {
        if(name == null || intensity == null || exerciseType == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.name = name;
            this.intensity = intensity;
            this.exerciseType = exerciseType;
            this.isFavourite = isFavourite;
        }
    }

    /**
     * The default constructor for the Exercise class
     * @param name the name of the exercise
     * @param intensity the intensity of the exercise
     * @param exerciseType the type of the exercise
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public Exercise(String name, ExerciseIntensity intensity,
                    ExerciseType exerciseType) throws IllegalArgumentException {
        if(name == null || intensity == null || exerciseType == null) {
            throw(new IllegalArgumentException("Invalid or null data passed to a method!!!"));
        }
        else {
            this.name = name;
            this.intensity = intensity;
            this.exerciseType = exerciseType;
        	this.isFavourite = false;
        }
    }

    /**
     * Returns the name of the exercise
     *
     * @return the name of the exercise
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the intensity of the exercise
     *
     * @return the intensity of the exercise
     */
    public ExerciseIntensity getIntensity() {
        return intensity;
    }

    /**
     * Returns the type of the exercise
     *
     * @return the type of the exercise
     */
    public ExerciseType getType() {
        return exerciseType;
    }

    /**
     * Returns {@code true} if this exercise is a favourite exercise, and {@code false} otherwise
     *
     * @return a boolean representing is the exercise is a favourite exercise
     */
    public boolean isFavourite() { return isFavourite; }

    /**
     * Toggle the favourite status of the exercise
     */
    public void toggleFavourite() {
        isFavourite = !isFavourite;
        //TODO - requires database update
    }

    /**
     * Compares the current Exercise to another instance of Exercise
     *
     * @param other the instance of Exercise to compare to
     * @return a boolean representing whether the two instances were equal
     */
    public boolean equals(Exercise other) {
        return other != null && name.equals(other.getName());
    }

    /**
     * Returns the Exercise as a String
     * @return the Exercise as a String
     */
    @Override
    public String toString()
    {
        String result = "";

        if (isFavourite)
            result += " * ";
        result += name + " - " + intensity + " intensity " + exerciseType + " exercise";

        return result;
    }
}
