package ledge.muscleup.persistence;

import java.util.List;
import ledge.muscleup.model.Exercise;
import ledge.muscleup.model.ExerciseQuantity;
import ledge.muscleup.model.Workout;

/**
 * An interface for the application's database. All database implementations will implement this
 * interface, and other layers of the application will use this interface for accessing the database
 */
public interface DataAccess {

    void open(String dbName);
    void close();
    List<Exercise> getExercisesList();
    List<Workout> getWorkoutsList();
    Exercise getExercise(String exerciseName);
    Workout getWorkout(String workoutName);
    void insertExercise(Exercise exercise);
    void insertWorkout(Workout workout);
    void removeExercise(Exercise exercise);
    void removeWorkout(Workout workout);
    void addExerciseToWorkout (Workout workout, Exercise exercise,
                               ExerciseQuantity exerciseQuantity);

}
