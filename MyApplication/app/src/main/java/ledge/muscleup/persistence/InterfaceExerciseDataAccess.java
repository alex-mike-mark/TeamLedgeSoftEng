package ledge.muscleup.persistence;

import java.util.List;

import ledge.muscleup.model.exercise.InterfaceExercise;

/**
 * An interface for exercise database access, including methods for retrieving, inserting, and
 * removing exercises
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-07
 */
public interface InterfaceExerciseDataAccess {
    List<InterfaceExercise> getExercisesList();
    List<String> getExerciseNamesList();
    InterfaceExercise getExercise(String exerciseName);
    void insertExercise(InterfaceExercise exercise);
    void removeExercise(InterfaceExercise exercise);
}
