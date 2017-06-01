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

    //generic methods to open and close database access
    void open(String dbName);
    void close();

    //methods to access exercise data
    List<Exercise> getExercisesList();
    Exercise getExercise(String exerciseName);
    void insertExercise(Exercise exercise);
    void removeExercise(Exercise exercise);

    //methods to access workout data
    List<Workout> getWorkoutsList();
    Workout getWorkout(String workoutName);
    void insertWorkout(Workout workout);
    void removeWorkout(Workout workout);
    void addExerciseToWorkout (Workout workout, Exercise exercise,
                               ExerciseQuantity exerciseQuantity);

}
