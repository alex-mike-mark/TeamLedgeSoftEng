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

    /**
     * Opens the stub database and populates it with some default values
     * @param dbName the name of the database to open
     */
    void open(String dbName);

    /**
     * Close the stub database
     */
    void close();
}
