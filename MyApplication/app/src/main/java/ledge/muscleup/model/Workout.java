/**
 * Workout
 * This class is used to store information about workouts.
 * They contain exercises, a list of required equipment, muscle groups worked, sets/reps or duration
 * for each exercise and a workout type, maybe.  Considering the
 * goals for this class there is currently no simple way to associate exercises
 * with any sort of duration. Of course, duration for an exercise is specific to a workout so
 * this is a problem we'll have to solve, sooner rather than later.
 *
 * This is all future iteration stuff and is subject to change.
 *
 * @author Alexander Mark
 * @version 1.0
 * @since 2017-05-27
 *
 * @param exercises This is the list of exercises in the workout.
 */

package ledge.muscleup.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexander on 2017-05-27.
 */

public class Workout {
    static int nextID;
    int workoutID;
    private String name;
    boolean isCustom;
    boolean isFavourite;
    private HashMap<Exercise, ExerciseQuantity> exercises;

    /**
     *
     * @param name
     * @param isCustom
     */
    public Workout(String name, boolean isCustom){
        workoutID = nextID;
        nextID++;
        this.name = name;
        this.isCustom = isCustom;
        isFavourite = false;
        exercises = new HashMap<Exercise, ExerciseQuantity>();
    }

    /**
     *
     * @param exercise
     */
    public void addExercise(Exercise exercise){

    }

    /**
     *
     * @param exercise
     */
    public void removeExercise(Exercise exercise){

    }
}
