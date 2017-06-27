package ledge.muscleup.persistence;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ledge.muscleup.model.exercise.DistanceUnit;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.TimeUnit;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WeightUnit;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * A stub implementation of the database for Iteration 1
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-04
 */

public class DataAccessStub implements InterfaceDataAccess {
    private String dbName;
    private String dbType = "stub";

    private Map<String, Workout> workoutsByName;
    private Map<String, Exercise> exercisesByName;
    private Map<LocalDate, WorkoutSession> workoutSessionsByDate;

    /**
     * Constructor for DataAccessStub
     * @param dbName the name of the database
     */
    public DataAccessStub (String dbName) {
        this.dbName = dbName;
    }

    /**
     * Opens the stub database and populates it with some default values
     */
    public void open() {

        Exercise exercise;
        WorkoutExercise workoutExercise;
        Workout workout;
        WorkoutSession workoutSession;

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

        workout = new Workout("Welcome to the Gun Show");
        workoutsByName.put(workout.getName(), workout);

        workoutExercise = new WorkoutExercise(exercisesByName.get("Bicep Curls"),
        new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS));
        addExerciseToWorkout(workout, workoutExercise);
        workoutExercise = new WorkoutExercise(exercisesByName.get("Push-Ups"),
        new ExerciseSets(2, 15));
        addExerciseToWorkout(workout, workoutExercise);

        workout = new Workout("Never Skip Leg Day");
        workoutsByName.put(workout.getName(), workout);
        workoutExercise = new WorkoutExercise(exercisesByName.get("Squats"),
        new ExerciseSets(4, 15));
        addExerciseToWorkout(workout, workoutExercise);
        workoutExercise = new WorkoutExercise(exercisesByName.get("Lunges"),
        new ExerciseSets(3, 10));
        addExerciseToWorkout(workout, workoutExercise);

        workout = new Workout("Marathon Training Starts Here");
        workoutsByName.put(workout.getName(), workout);
        workoutExercise = new WorkoutExercise(exercisesByName.get("Running"),
        new ExerciseDistance(2.5, DistanceUnit.MILES));
        addExerciseToWorkout(workout, workoutExercise);
        workoutExercise = new WorkoutExercise(exercisesByName.get("Exercise Bike"),
        new ExerciseDuration(45, TimeUnit.MINUTES));
        addExerciseToWorkout(workout, workoutExercise);

        workout = new Workout("Work that Core, Get that Score!");
        workoutsByName.put(workout.getName(), workout);
        workoutExercise = new WorkoutExercise(exercisesByName.get("Crunches"),
        new ExerciseSets(2, 25));
        addExerciseToWorkout(workout, workoutExercise);
        workoutExercise = new WorkoutExercise(exercisesByName.get("Bicycle Kicks"),
        new ExerciseSets(2, 15));
        addExerciseToWorkout(workout, workoutExercise);

        workoutSessionsByDate = new TreeMap<>();
        workoutSession = new WorkoutSession(
                (workoutsByName.get("Welcome to the Gun Show")),
                new LocalDate(2017, 06, 5),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Never Skip Leg Day")),
                new LocalDate(2017, 06, 6),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Work that Core, Get that Score!")),
                new LocalDate(2017, 06, 7),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Never Skip Leg Day")),
                new LocalDate(2017, 06, 9),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Marathon Training Starts Here")),
                new LocalDate(2017, 06, 10),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

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
    public List<Workout> getWorkoutsList() {
        return new ArrayList<>(workoutsByName.values());
    }

    /**
     * Gets a list of names of all exercises in the database
     * @return a list of names of all workouts in the database
     */
    public List<String> getWorkoutNamesList() {
        return new ArrayList<>(workoutsByName.keySet());
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

     * Adds an exercise to a workout in the database, if both the workout and the exercise exist in
     * the database
     * @param workout the workout to add the exercise to
     * @param exercise the exercise to add to the workout
     *
     * @return a boolean indicating whether the exercise was properly added to the workout
     */
    public boolean addExerciseToWorkout (Workout workout, WorkoutExercise exercise) {
        boolean added = false;
        Workout dbWorkout;

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

    public void removeExercise(Exercise exercise) {
        exercisesByName.remove(exercise.getName());
    }

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to remove from the database
     */

    public void removeWorkout(Workout workout) {
        workoutsByName.remove(workout.getName());
    }

    /**
     * Updates the recommended quantity of exercise for a given exercise in a given workout in the database
     *
     * @param workout  the workout that contains the exercise to update
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @return a boolean representing if the exercise was found and updated in the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public boolean updateExerciseQuantity(Workout workout, WorkoutExercise exercise, InterfaceExerciseQuantity quantity) throws IllegalArgumentException {
        //TODO implement when implementing SQL database
        return false;
    }

    /**
     * Toggles the favourite state of an exercise in the database
     *
     * @param workout the workout to update the status of
     */
    public void toggleExerciseFavourite(Workout workout) {
        //TODO implement when implementing SQL database
    }

    /**
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     *
     * @param workout  the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     * @return true if exercise was added successfully, false otherwise
     */
    public boolean addWorkoutExercise(Workout workout, WorkoutExercise exercise) {
        //TODO implement when implementing SQL database
        return false;
    }

    /**
     * Move the position of an exercise in the list of exercises in the database
     *
     * @param workout  the workout to change the order of exercises for
     * @param exercise the exercise to change the position of
     * @param index    the index of the exercise to move
     * @return a boolean representing if the exercise was found and moved to the new index
     * @throws IllegalArgumentException if passed a {@code null} parameter or if {@code index} is
     *                                  outside the bounds of the list of exercises
     */
    public boolean moveWorkoutExercise(Workout workout, WorkoutExercise exercise, int index) throws IllegalArgumentException {
        //TODO implement when implementing SQL database
        return false;
    }

    /**
     * Removes an exercise from a workout in the database
     *
     * @param workout  the workout to remove an exercise from
     * @param exercise the exercise to remove from the list
     * @return the exercise that was removed, or {@code null} if the exercise couldn't be found
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public boolean removeWorkoutExercise(Workout workout, WorkoutExercise exercise) throws IllegalArgumentException {
        //TODO implement when implementing SQL database
        return false;
    }

    /**
     * A method that returns a list of all workout sessions in the database
     * @return a list of all workout sessions in the database
     */
    public List<WorkoutSession> getWorkoutSessionsList() {
        return new ArrayList<>(workoutSessionsByDate.values());
    }

    /**
     * A method that returns a list of workout sessions scheduled in a date range
     * @param startDate the first date of the date range
     * @param endDate the last date of the date range
     * @return a list of all workout sessions scheduled between startDate and endDate, inclusive
     */
    public List<WorkoutSession> getSessionsInDateRange(LocalDate startDate,
                                                                LocalDate endDate) {
        List<WorkoutSession> sessionsInDateRange = new ArrayList<>();

        LocalDate currDate = startDate;
        while (!currDate.isAfter(endDate)) {
            if (workoutSessionsByDate.containsKey(currDate)) {
                sessionsInDateRange.add(workoutSessionsByDate.get(currDate));
            }
            currDate = currDate.plusDays(1);
        }

        return sessionsInDateRange;
    }

    /**
     * Retrieves a workout session scheduled on the given date from the database, if it exists. If
     * no workout session is found for that date, returns null.
     * @param dateOfSession the date to get the workout session for
     * @return the workout session scheduled on the given date
     */
    public WorkoutSession getWorkoutSession(LocalDate dateOfSession) {
        return workoutSessionsByDate.get(dateOfSession);
    }

    /**
     * Inserts a new workout session into the database
     * @param workoutSession the new workout session to insert into the database
     */
    public void insertWorkoutSession(WorkoutSession workoutSession) {
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);
    }

    /**
     * Removes a workout session from the database, if it exists
     * @param workoutSession the workout session to remove from the database
     */
    public void removeWorkoutSession(WorkoutSession workoutSession) {
        workoutSessionsByDate.remove(workoutSession.getDate());
    }

    /**
     * Updates the scheduled date of a workout in the database
     *
     * @param workoutSession the workout to change the date for
     * @param newDate        the new date of the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public void updateWorkoutDate(WorkoutSession workoutSession, LocalDate newDate) throws IllegalArgumentException {
        //TODO implement when implementing SQL database
    }

    /**
     * Toggles the completed state of a workout in the database
     *
     * @param workoutSession the workout to change the state of
     */
    public void toggleWorkoutComplete(WorkoutSession workoutSession) {
        //TODO implement when implementing SQL database
    }

    /**
     * Toggles the completed state of an exercise in a workout in the database
     *
     * @param workoutSession the workout which contains the exercise
     * @param exercise       the exercise to complete
     * @return a boolean representing whether the exercise was marked as completed or not
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    public boolean toggleExerciseComplete(WorkoutSession workoutSession, WorkoutSessionExercise exercise) throws IllegalArgumentException {
        //TODO implement when implementing SQL database
        return false;
    }
}
