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
import java.util.Map;

/**
 * A workout, which consists of a workout name and set of exercises
 */

public class Workout {
    private String name;
    private boolean isCustom;
    private boolean isFavourite;
    private Map<Exercise, ExerciseQuantity> exercises;

    /**
     * Constructor for a Workout
     * @param name the name of the workout
     * @param isCustom true if workout is a custom created workout, false otherwise
     */
    public Workout(String name, boolean isCustom){
        this.name = name;
        this.isCustom = isCustom;
        isFavourite = false;
        exercises = new HashMap<>();
    }

    /**
     * This returns the name of the workout
     * @return the name of the workout
     */
    public String getName() {
        return name;
    }

    /**
     * This sets the name of the workout to be parameter name
     * @param name the new name of the workout
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This returns a boolean indicating whether this workout is a custom created one or not
     * @return true if workout is a custom workout, false otherwise
     */
    public boolean isCustom() {
        return isCustom;
    }

    /**
     * This sets whether the workout is custom or not
     * @param custom a boolean indicating whether the workout is a custom workout or not
     */
    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    /**
     * This returns a boolean indicating whether this workout is a favourited workout
     * @return true if workout is a favourite workout, false otherwise
     */
    public boolean isFavourite() {
        return isFavourite;
    }

    /**
     * This toggles whether the workout is favourite or not. If it was not favourite, it becomes
     * favourite and vice versa
     */
    public void toggleFavourite() {
       this.isFavourite = !this.isFavourite;
    }

    /**
     * This returns the exercises in the workout, along with the quantities of each to be done in
     * the workout in the form of a map
     * @return a map with exercises in the workout as the keys, and the quantities of them to be done
     * as the values
     */
    public Map<Exercise, ExerciseQuantity> getExercises() {
        return exercises;
    }

    /**
     * Adds a new exercise to the workout, along with the quantity of the exercise to do
     * @param exercise the exercise to be added
     * @param quantity the quantity of this new exercise to do
     */
    public void addExercise(Exercise exercise, ExerciseQuantity quantity){
        exercises.put(exercise, quantity);
    }

    /**
     * Removes an exercise from the workout, if it exists
     * @param exercise the exercise to remove from the workout
     */
    public void removeExercise(Exercise exercise){
        exercises.remove(exercise);
    }
}
