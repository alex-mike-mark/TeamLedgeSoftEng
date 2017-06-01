package ledge.muscleup.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ledge.muscleup.model.Exercise;
import ledge.muscleup.model.ExerciseDistance;
import ledge.muscleup.model.ExerciseDuration;
import ledge.muscleup.model.ExerciseQuantity;
import ledge.muscleup.model.ExerciseSetsAndReps;
import ledge.muscleup.model.ExerciseType;
import ledge.muscleup.model.Intensity;
import ledge.muscleup.model.Workout;

/**
 * An implementation of the DataAccess interface as a Stub database for Iteration 1
 */
public class DataAccessStub implements DataAccess{
    private String dbName;
    private String dbType = "stub";

    private Map<String, Workout> workoutsByName;
    private Map<String, Exercise> exercisesByName;

    /**
     * Constructor for DataAccessStub
     * @param dbName the name of the database
     */
    public DataAccessStub (String dbName) {
        this.dbName = dbName;
    }

    /**
     * Opens the stub database and populates it with some default values
     * @param dbName the name of the database to open
     */
    public void open(String dbName) {
        Exercise exercise;
        Workout workout;

        exercisesByName = new HashMap<>();
        exercise = new Exercise("Bicep Curls", Intensity.LOW, ExerciseType.ARM);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Push-Ups", Intensity.HIGH, ExerciseType.ARM);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Running", Intensity.HIGH, ExerciseType.CARDIO);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Exercise Bike", Intensity.MEDIUM, ExerciseType.CARDIO);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Crunches", Intensity.LOW, ExerciseType.CORE);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise ("Bicycle Kicks", Intensity.HIGH, ExerciseType.CORE);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Squats", Intensity.MEDIUM, ExerciseType.LEG);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Lunges", Intensity.MEDIUM, ExerciseType.LEG);
        exercisesByName.put(exercise.getName(), exercise);

        workoutsByName = new HashMap<>();
        workout = new Workout("Welcome to the Gun Show", false);
        workout.addExercise(exercisesByName.get("Bicep Curls"), new ExerciseSetsAndReps(2, 15));
        workout.addExercise(exercisesByName.get("Push-Ups"), new ExerciseSetsAndReps(3, 10));
        workoutsByName.put(workout.getName(), workout);
        workout = new Workout("Never Skip Leg Day", false);
        workout.addExercise(exercisesByName.get("Squats"), new ExerciseSetsAndReps(4, 15));
        workout.addExercise(exercisesByName.get("Lunges"), new ExerciseSetsAndReps(3, 10));
        workoutsByName.put(workout.getName(), workout);
        workout = new Workout("Marathon Training Starts Here", false);
        workout.addExercise(exercisesByName.get("Running"), new ExerciseDistance(5, "Miles"));
        workout.addExercise(exercisesByName.get("Exercise Bike"), new ExerciseDuration(45));
        workoutsByName.put(workout.getName(), workout);
        workout = new Workout("Work that Core, Get that Score", false);
        workout.addExercise(exercisesByName.get("Crunches"), new ExerciseSetsAndReps(2, 25));
        workout.addExercise(exercisesByName.get("Bicycle Kicks"), new ExerciseSetsAndReps(3, 15));
        workoutsByName.put(workout.getName(), workout);

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
        return new ArrayList<>(exercisesByName.values());
    }

    /**
     * Gets a list of all workouts in the database
     * @return a list of all workouts in the database
     */
    public List<Workout> getWorkoutsList() {
        return new ArrayList<>(workoutsByName.values());
    }

    /**
     * Retrieves an exercise from the database with the name given as parameter
     * @param exerciseName- the name of the exercise to retrieve from the database
     * @return The exercise with name exerciseName, or null if no exercise exists with that name
     */
    public Exercise getExercise(String exerciseName) {
        return exercisesByName.get(exerciseName);
    }

    /**
     * Retrieves a workout from the database with the name given as parameter
     * @param workoutName the name of the workout to retrieve from the database
     * @return The workout with name workoutName, or null if no workout exists with that name
     */
    public Workout getWorkout(String workoutName) {
        return workoutsByName.get(workoutName);
    }

    /**
     * Adds an exercise to the database
     * @param exercise the exercise to be added to the database
     */
    public void insertExercise(Exercise exercise) {
        exercisesByName.put(exercise.getName(), exercise);
    }

    /**
     * Adds a workout to the database
     * @param workout the workout to be added to the database
     */
    public void insertWorkout(Workout workout) {
        workoutsByName.put(workout.getName(), workout);
    }

    /**
     * Adds an exercise to a workout in the database, if it exists
     * @param workout the workout to add the exercise to
     * @param exercise the exercise to add to the workout
     * @param exerciseQuantity the quantity of the exercise being added to the workout
     */
    public void addExerciseToWorkout (Workout workout, Exercise exercise,
                                      ExerciseQuantity exerciseQuantity) {
        if (workoutsByName.get(workout.getName()) != null) {
            workoutsByName.get(workout.getName()).addExercise(exercise, exerciseQuantity);
        }
    }

    /**
     * Removes an exercise from the database, if it exists
     * @param exercise the exercise to remove from the database
     */
    public void removeExercise(Exercise exercise) {
        exercisesByName.remove(exercise);
    }

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to remove from the database
     */
    public void removeWorkout(Workout workout) {
        workoutsByName.remove(workout);
    }
}
