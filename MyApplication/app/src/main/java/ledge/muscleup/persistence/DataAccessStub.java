package ledge.muscleup.persistence;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.enums.*;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.schedule.ScheduleWeek;
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
        final int xpPerIntensityLevel = 15;

        workout = new Workout("Welcome to the Gun Show");
        workoutsByName.put(workout.getName(), workout);
        exercise = exercisesByName.get("Bicep Curls");
        int exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSetsAndWeight(exercisesByName.get("Bicep Curls"), exerciseExperience, new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS));
        workout.addExercise(workoutExercise);


        workoutExercise = new WorkoutExerciseSets(exercisesByName.get("Push-Ups"), exerciseExperience, new ExerciseSets(2, 15));
        workoutExercise = new WorkoutExerciseSetsAndWeight(exercise, exerciseExperience,new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS));
        workout.addExercise(workoutExercise);
        exercise = exercisesByName.get("Push-Ups");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience,new ExerciseSets(2, 15));
        workout.addExercise(workoutExercise);

        workout = new Workout("Never Skip Leg Day");
        workoutsByName.put(workout.getName(), workout);

        exercise = exercisesByName.get("Squats");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience,new ExerciseSets(4, 15));
        workout.addExercise(workoutExercise);
        exercise = exercisesByName.get("Lunges");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience,new ExerciseSets(3, 10));
        workout.addExercise(workoutExercise);

        workout = new Workout("Marathon Training Starts Here");
        workoutsByName.put(workout.getName(), workout);

        exercise = exercisesByName.get("Running");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseDistance(exercise, exerciseExperience,new ExerciseDistance(2.5, DistanceUnit.MILES));
        workout.addExercise(workoutExercise);
        exercise = exercisesByName.get("Exercise Bike");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseDuration(exercise, exerciseExperience,new ExerciseDuration(45, TimeUnit.MINUTES));

        workout.addExercise(workoutExercise);

        workout = new Workout("Work that Core, Get that Score!");
        workoutsByName.put(workout.getName(), workout);

        exercise = exercisesByName.get("Crunches");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience,new ExerciseSets(2, 25));
        workout.addExercise(workoutExercise);
        exercise = exercisesByName.get("Bicycle Kicks");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience,new ExerciseSets(2, 15));
        workout.addExercise(workoutExercise);

        workoutSessionsByDate = new TreeMap<>();
        workoutSession = new WorkoutSession(
                (workoutsByName.get("Welcome to the Gun Show")),
                new LocalDate().minusWeeks(1).withDayOfWeek(DateTimeConstants.THURSDAY),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Never Skip Leg Day")),
                new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Work that Core, Get that Score!")),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Never Skip Leg Day")),
                new LocalDate().withDayOfWeek(DateTimeConstants.FRIDAY),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Marathon Training Starts Here")),
                new LocalDate().plusWeeks(1).withDayOfWeek(DateTimeConstants.TUESDAY),
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
     * Retrieves a workout from the database with the name given as parameter
     * @param workoutName the name of the workout to retrieve from the database
     * @return The workout with name workoutName, or null if no workout exists with that name
     */
    public Workout getWorkout(String workoutName) {
        return workoutsByName.get(workoutName);
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

    /**
     * Adds a workout session to a given day in the database
     *
     * @param scheduleWeek  the week to add the workout to
     * @param workoutSession the workout session to add
     * @param dayOfWeek      the day of the week to add the workout session to
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     *                                  > DateTimeConstants.SUNDAY}
     */
    public void addWorkoutSession(ScheduleWeek scheduleWeek, WorkoutSession workoutSession, int dayOfWeek) throws IllegalArgumentException {
        //TODO implement when implementing SQL database
    }

    /**
     * Removes a workout from a given day in the database
     *
     * @param scheduleWeek the week to remove the workout from
     * @param dayOfWeek     the day to remove the workout from
     * @return a boolean representing if a workout was removed
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     *                                  > DateTimeConstants.SUNDAY}
     */
    public boolean removeWorkoutSession(ScheduleWeek scheduleWeek, int dayOfWeek) throws IllegalArgumentException {
        //TODO implement when implementing SQL database
        return false;
    }
}
