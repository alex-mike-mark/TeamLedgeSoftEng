package ledge.muscleup.persistence;

import java.util.List;

import ledge.muscleup.model.exercise.InterfaceExercise;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceSuggestedExercise;
import ledge.muscleup.model.workout.InterfaceWorkout;

/**
 * An interface for accessing the database, with abilities to retrieve, add, and remove data from
 * the database, as well as open and close the database
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-04
 */
public interface DataAccess {

    void open(String dbName);
    void close();
    List<InterfaceExercise> getExercisesList();
    List<InterfaceWorkout> getWorkoutsList();
    InterfaceExercise getExercise(String exerciseName);
    InterfaceWorkout getWorkout(String workoutName);
    void insertExercise(InterfaceExercise exercise);
    void insertWorkout(InterfaceWorkout workout);
    void removeExercise(InterfaceExercise exercise);
    void removeWorkout(InterfaceWorkout workout);
    boolean addExerciseToWorkout (InterfaceWorkout workout, InterfaceExercise exercise,
                                  InterfaceExerciseQuantity quantity);
}
