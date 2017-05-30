package ledge.muscleup.persistence;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ledge.muscleup.model.Exercise;
import ledge.muscleup.model.ExerciseQuantity;
import ledge.muscleup.model.ExerciseSetsAndReps;
import ledge.muscleup.model.Workout;

/**
 * DataAccessStub- An implementation of the DataAccess interface as a Stub database for Iteration 1
 */
public class DataAccessStub implements DataAccess{
    private String dbName;
    private String dbType = "stub";

    private Map<Integer, Workout> workoutsById;
    private Map<Integer, Exercise> exercisesById;

    public DataAccessStub (String dbName) {
        this.dbName = dbName;
    }

    /**
     * Opens the stub database and populates it with some default values
     * @param dbName- the name of the database to open
     */
    public void open(String dbName) {
        Exercise exercise;
        Workout workout;

        exercisesById = new HashMap<>();
        exercise = new Exercise("Bicep Curls", 1, "Biceps");
        exercisesById.put(exercise.getId(), exercise);
        exercise = new Exercise("Squats", 2, "Quads");
        exercisesById.put(exercise.getId(), exercise);
        exercise = new Exercise("Lunges", 1, "Quads");
        exercise = new Exercise("Curlups", 1, "Abs");
        exercisesById.put(exercise.getId(), exercise);
        exercise = new Exercise("Tricep-Dips", 1, "Triceps");
        exercisesById.put(exercise.getId(), exercise);

        workoutsById = new HashMap<>();
        workout = new Workout("Arm Workout", false);
        workout.addExercise(new Exercise("Bicep Curls", 1, "Biceps"), new ExerciseSetsAndReps(3, 10));
        workout.addExercise(new Exercise("Tricep-Dips", 1, "Triceps"), new ExerciseSetsAndReps(2, 15));
        workoutsById.put(workout.getWorkoutID(), workout);
        workout = new Workout("Work them Quads", false);
        workout.addExercise(new Exercise("Squats", 2, "Quads"), new ExerciseSetsAndReps(4, 15));
        workout.addExercise(new Exercise("Lunges", 1, "Quads"), new ExerciseSetsAndReps(3, 10));
        workoutsById.put(workout.getWorkoutID(), workout);
        workout = new Workout("Full Body Workout", false);
        workout.addExercise(exercisesById.get(0), new ExerciseSetsAndReps(3, 10));
        workout.addExercise(exercisesById.get(1), new ExerciseSetsAndReps(3, 10));
        workout.addExercise(exercisesById.get(2), new ExerciseSetsAndReps(3, 10));
        workout.addExercise(exercisesById.get(3), new ExerciseSetsAndReps(3, 10));
        workout.addExercise(exercisesById.get(4), new ExerciseSetsAndReps(3, 10));
        workoutsById.put(workout.getWorkoutID(), workout);

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    /**
     * Close the stub database
     */
    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    /**
     * Gets a list of all exercises in the database
     * @return a list of all exercises in the database
     */
    public List<Exercise> getExercisesList() {
        return new ArrayList<>(exercisesById.values());
    }

    /**
     * Gets a list of all workouts in the database
     * @return a list of all workouts in the database
     */
    public List<Workout> getWorkoutsList() {
        return new ArrayList<>(workoutsById.values());
    }

    /**
     * Retrieves an exercise from the database with the id given as parameter
     * @param exerciseId- the id of the exercise to retrieve from the database
     * @return The exercise with id exerciseId, or null if no exercise exists with that id
     */
    public Exercise getExerciseById(int exerciseId) {
        return exercisesById.get(exerciseId);
    }

    /**
     * Retrieves a workout from the database with the id given as parameter
     * @param workoutId- the id of workout to retrieve from the database
     * @return The workout with id workoutId, or null if no workout exists with that id
     */
    public Workout getWorkoutById(int workoutId) {
        return workoutsById.get(workoutId);
    }

    /**
     * Adds an exercise to the database
     * @param exercise- the exercise to be added to the database
     */
    public void insertExercise(Exercise exercise) {
        exercisesById.put(exercise.getId(), exercise);
    }

    /**
     * Adds a workout to the database
     * @param workout- the workout to be added to the database
     */
    public void insertWorkout(Workout workout) {
        workoutsById.put(workout.getWorkoutID(), workout);
    }

    /**
     * Removes an exercise from the database, if it exists
     * @param exercise- the exercise to remove from the database
     */
    public void removeExercise(Exercise exercise) {
        exercisesById.remove(exercise.getId());
    }

    /**
     * Removes a workout from the database, if it exists
     * @param workout- the workout to remove from the database
     */
    public void removeWorkout(Workout workout) {
        workoutsById.remove(workout.getWorkoutID());
    }
}
