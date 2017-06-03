package ledge.muscleup.model.exercise;

/**
 * An exercise as part of a workout, which consists of an exercise name, type and intensity
 *
 * @author Alexander Mark
 * @version 1.0
 * @since 2017-05-25
 */
public abstract class Exercise implements InterfaceExercise {
    private String name;
    private ExerciseIntensity intensity;
    private ExerciseType exerciseType;

    /**
     * The default constructor for the Exercise class
     * @param name the name of the exercise
     * @param intensity the intensity of the exercise
     * @param exerciseType the type of the exercise
     */
    protected Exercise(String name, ExerciseIntensity intensity, ExerciseType exerciseType) {
        this.name = name;
        this.intensity = intensity;
        this.exerciseType = exerciseType;
    }

    /**
     * Returns the name of the exercise
     *
     * @return the name of the exercise
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the intensity of the exercise
     *
     * @return the intensity of the exercise
     */
    @Override
    public ExerciseIntensity getIntensity() {
        return intensity;
    }

    /**
     * Returns the type of the exercise
     *
     * @return the type of the exercise
     */
    @Override
    public ExerciseType getType() {
        return exerciseType;
    }

    /**
     * Returns the Exercise as a String
     * @return the Exercise as a String
     */
    public String toString()
    {
        return name + " - " + intensity + " intensity " + exerciseType + " workout";
    }
}
