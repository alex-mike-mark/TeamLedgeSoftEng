package ledge.muscleup.persistence;

import java.util.List;

import ledge.muscleup.model.exercise.InterfaceExercise;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.InterfaceWorkoutExercise;
import ledge.muscleup.model.workout.InterfaceWorkout;

/**
 * An interface for general database access, such as opening or closing the database
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-04
 */
public interface InterfaceDataAccess {

    void open(String dbName);
    void close();
}
