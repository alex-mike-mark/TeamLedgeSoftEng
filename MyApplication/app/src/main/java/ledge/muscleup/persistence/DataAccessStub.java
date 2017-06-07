package ledge.muscleup.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ledge.muscleup.model.exercise.DistanceUnit;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.InterfaceWorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WeightUnit;
import ledge.muscleup.model.exercise.InterfaceExercise;
import ledge.muscleup.model.workout.InterfaceWorkout;
import ledge.muscleup.model.workout.Workout;

/**
 * A stub implementation of the database for Iteration 1
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-04
 */

public class DataAccessStub implements DataAccess{
    private String dbName;
    private String dbType = "stub";


    private Map<String, InterfaceWorkout> workoutsByName;
    private Map<String, InterfaceExercise> exercisesByName;

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

        InterfaceExercise exercise;
        InterfaceWorkoutExercise workoutExercise;
        InterfaceWorkout workout;

        exercisesByName = new HashMap<>();
        exercise = new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM, false);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM, false);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO, false);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                ExerciseType.CARDIO, false);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE, false);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE,
                false);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false);
        exercisesByName.put(exercise.getName(), exercise);

        workoutsByName = new HashMap<>();

        workout = new Workout("Welcome to the Gun Show", false);
        workoutExercise = new WorkoutExercise((Exercise)exercisesByName.get("Bicep Curls"),
                new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS));
        addExerciseToWorkout(workout, workoutExercise);
        workoutExercise = new WorkoutExercise((Exercise)exercisesByName.get("Push-Ups"),
                new ExerciseSets(2, 15));
        addExerciseToWorkout(workout, workoutExercise);
        workoutsByName.put(workout.getName(), workout);

        workout = new Workout("Never Skip Leg Day", false);
        workoutExercise = new WorkoutExercise((Exercise)exercisesByName.get("Squats"),
                new ExerciseSets(4, 15));
        addExerciseToWorkout(workout, workoutExercise);
        workoutExercise = new WorkoutExercise((Exercise)exercisesByName.get("Lunges"),
                new ExerciseSets(3, 10));
        addExerciseToWorkout(workout, workoutExercise);
        workoutsByName.put(workout.getName(), workout);

        workout = new Workout("Marathon Training Starts Here", false);
        workoutExercise = new WorkoutExercise((Exercise)exercisesByName.get("Running"),
                new ExerciseDistance(2.5, DistanceUnit.MILES));
        addExerciseToWorkout(workout, workoutExercise);
        workoutExercise = new WorkoutExercise((Exercise)exercisesByName.get("Exercise Bike"),
                new ExerciseDuration(45));
        addExerciseToWorkout(workout, workoutExercise);
        workoutsByName.put(workout.getName(), workout);

        workout = new Workout("Work that Core, Get that Score!", false);
        workoutExercise = new WorkoutExercise((Exercise)exercisesByName.get("Crunches"),
                new ExerciseSets(2, 25));
        addExerciseToWorkout(workout, workoutExercise);
        workoutExercise = new WorkoutExercise((Exercise)exercisesByName.get("Bicycle Kicks"),
                new ExerciseSets(2, 15));
        addExerciseToWorkout(workout, workoutExercise);
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
    public List<InterfaceExercise> getExercisesList() {
        return new ArrayList<>(exercisesByName.values());
    }

    /**
     * Gets a list of names of all exercises in the database
     * @return a list of names of all exercises in the database
     */
    public List<String> getExerciseNamesList() {
        return new ArrayList<>(exercisesByName.keySet());
    }

    /**
     * Gets a list of all workouts in the database
     * @return a list of all workouts in the database
     */
    public List<InterfaceWorkout> getWorkoutsList() {
        return new ArrayList<>(workoutsByName.values());
    }

    /**
     * Gets a list of names of all exercises in the database
     * @return a list of names of all workouts in the database
     */
    public List<String> getWorkoutNamesList() {
        return new ArrayList<>(exercisesByName.keySet());
    }

    /**
     * Retrieves an exercise from the database with the name given as parameter
     * @param exerciseName- the name of the exercise to retrieve from the database
     * @return The exercise with name exerciseName, or null if no exercise exists with that name
     */
    public InterfaceExercise getExercise(String exerciseName) {
        return exercisesByName.get(exerciseName);
    }

    /**
     * Retrieves a workout from the database with the name given as parameter
     * @param workoutName the name of the workout to retrieve from the database
     * @return The workout with name workoutName, or null if no workout exists with that name
     */
    public InterfaceWorkout getWorkout(String workoutName) {
        return workoutsByName.get(workoutName);
    }

    /**
     * Adds an exercise to the database
     * @param exercise the exercise to be added to the database
     */

    public void insertExercise(InterfaceExercise exercise) {
        exercisesByName.put(exercise.getName(), exercise);
    }

    /**
     * Adds a workout to the database
     * @param workout the workout to be added to the database
     */

    public void insertWorkout(InterfaceWorkout workout) {
        workoutsByName.put(workout.getName(), workout);
    }

    /**

     * Adds an exercise to a workout in the database, if both the workout and the exercise exist in
     * the database
     * @param workout the workout to add the exercise to
     * @param exercise the exercise to add to the workout
     *
     * @return a boolean indicating whether the exercise was properly added to the workout
     */
    public boolean addExerciseToWorkout (InterfaceWorkout workout, InterfaceWorkoutExercise exercise) {
        boolean added = false;
        InterfaceWorkout dbWorkout;

        if (workoutsByName.containsKey(workout.getName())) {
            dbWorkout = workoutsByName.get(workout.getName());
                if (exercisesByName.containsKey(exercise.getName())) {
                    dbWorkout.addExercise(exercise);
                    added = true;
                }
        }
        return added;
    }

    /**
     * Removes an exercise from the database, if it exists
     * @param exercise the exercise to remove from the database
     */

    public void removeExercise(InterfaceExercise exercise) {
        exercisesByName.remove(exercise.getName());
    }

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to remove from the database
     */

    public void removeWorkout(InterfaceWorkout workout) {
        workoutsByName.remove(workout.getName());
    }
}
