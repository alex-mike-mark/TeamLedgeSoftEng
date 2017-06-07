package ledge.muscleup.persistence;

import java.util.List;

import ledge.muscleup.model.exercise.InterfaceWorkoutExercise;
import ledge.muscleup.model.workout.InterfaceWorkout;

/**
 * An interface for workout database access, including methods for retrieving, inserting, and
 * removing workouts, as well as adding an exercise to a workout in the database
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceWorkoutDataAccess {
    List<InterfaceWorkout> getWorkoutsList();
    List<String> getWorkoutNamesList();
    InterfaceWorkout getWorkout(String workoutName);
    void insertWorkout(InterfaceWorkout workout);
    void removeWorkout(InterfaceWorkout workout);
    boolean addExerciseToWorkout (InterfaceWorkout workout, InterfaceWorkoutExercise exercise);
}
