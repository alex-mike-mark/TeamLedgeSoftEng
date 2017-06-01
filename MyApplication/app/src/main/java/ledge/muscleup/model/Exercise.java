package ledge.muscleup.model;

/**
 * An exercise as part of a workout, which consists of an exercise name, type and intensity
 *
 * @author Alexander Mark
 * @version 1.0
 * @since 2017-05-25
 */
public class Exercise {
    private String name;
    private Intensity intensity;
    private ExerciseType exerciseType;
    private boolean isFavourite;

    /**
     * Constructor for an Exercise
     * @param name the name of the exercise
     * @param intensity the intensity of the exercise, of enumerated type Intensity
     * @param exerciseType the type of the exercise, of enumerated type ExerciseType
     */
    public Exercise(String name, Intensity intensity, ExerciseType exerciseType){
        this.name = name;
        this.intensity = intensity;
        this.exerciseType = exerciseType;
        isFavourite = false;
    }

    /**
     * Checks if this exercise is equal to the other exercise passed as parameter. They are equal
     * if they have the same name, as exercise names are unique
     *
     * @param other is the other exercise
     * @return a boolean representing if the exercise is equal
     */
    public boolean equals(Exercise other){
        return this.name.equals(other.name);
    }

    /**
     * Method to change the value of isFavourite
     */
    public void toggleFavourite(){
        isFavourite=!isFavourite;
    }

    /**
     * Returns if the workout is a favourite
     * @return a boolean specifying whether this exercise is a favourite exercise or not
     */
    public boolean isFavourite(){
        return isFavourite;
    }

    /**
     * This returns the name of the exercise
     * @return the name of the exercise
     */
    public String getName(){
        return name;
    }

    /**
     * This returns the intensity of the exercise, either LOW, MEDIUM, or HIGH
     * @return the intensity of the exercise, as an enum
     */
    public Intensity getIntensity(){
        return intensity;
    }

    /**
     * This returns the type of the exercise, such as ARM, LEG, CORE, etc.
     * @return the type of the exercise, as an enum
     */
    public ExerciseType getExerciseType() {
        return exerciseType;
    }
}
