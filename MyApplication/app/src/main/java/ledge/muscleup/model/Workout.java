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

import java.util.List;

/**
 * Created by Alexander on 2017-05-27.
 */

public class Workout {
    private List<Exercise> exercises;
    /**
     * This is the constructor. Passing any number of exercises generates a workout.
     * @param e The exercises. Length is variable for (hopefully) ease of use. I'm pretty sure this
     *          means you can also pass Collections of Exercises in there and it'll work fine.
     */
    public Workout(Exercise... e){

        for(int i = 0;i<e.length;i++){
            exercises.add(e[i]);
        }
    }
}
