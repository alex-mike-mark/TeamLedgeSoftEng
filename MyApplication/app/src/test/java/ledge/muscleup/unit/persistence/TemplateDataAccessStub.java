package ledge.muscleup.unit.persistence;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.enums.DistanceUnit;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.exercise.enums.TimeUnit;
import ledge.muscleup.model.exercise.enums.WeightUnit;
import ledge.muscleup.model.experience.CompletedWorkoutRecord;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.persistence.InterfaceDataAccess;
import ledge.muscleup.persistence.InterfaceExerciseDataAccess;
import ledge.muscleup.persistence.InterfaceExperienceDataAccess;
import ledge.muscleup.persistence.InterfaceWorkoutDataAccess;
import ledge.muscleup.persistence.InterfaceWorkoutSessionDataAccess;

/**
 * A template database stub for use in testing the ScheduleManager that needs an accessor, which in
 * turn needs a database stub
 * constructor parameter
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-25
 */

class TemplateDataAccessStub implements InterfaceDataAccess, InterfaceExerciseDataAccess,
                                        InterfaceWorkoutDataAccess, InterfaceWorkoutSessionDataAccess,
                                        InterfaceExperienceDataAccess {
    private String dbName;
    private String dbType = "testing template";

    private Map<String, Workout> workoutsByName;
    private Map<String, Exercise> exercisesByName;
    private Map<LocalDate, WorkoutSession> workoutSessionsByDate;

    /**
     * Constructor for DataAccessStub
     * @param dbName the name of the database
     */
    public TemplateDataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Opens the stub database and populates it with some default values
     */
    public void open(String dbPath) {

        Exercise exercise;
        WorkoutExercise workoutExercise;
        Workout workout;
        WorkoutSession workoutSession;

        exercisesByName = new HashMap<>();
        exercise = new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                ExerciseType.CARDIO);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG);
        exercisesByName.put(exercise.getName(), exercise);
        exercise = new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG);
        exercisesByName.put(exercise.getName(), exercise);

        workoutsByName = new HashMap<>();
        final int xpPerIntensityLevel = 15;

        workout = new Workout("Welcome to the Gun Show");
        workoutsByName.put(workout.getName(), workout);
        exercise = exercisesByName.get("Bicep Curls");
        int exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSetsAndWeight(exercise, exerciseExperience,new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS));
        workout.addExercise(workoutExercise);
        exercise = exercisesByName.get("Push-Ups");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience, new ExerciseSets(2, 15));
        workout.addExercise(workoutExercise);

        workout = new Workout("Never Skip Leg Day");
        workoutsByName.put(workout.getName(), workout);
        exercise = exercisesByName.get("Squats");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience, new ExerciseSets(4, 15));
        workout.addExercise(workoutExercise);
        exercise = exercisesByName.get("Lunges");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience, new ExerciseSets(3, 10));
        workout.addExercise(workoutExercise);

        workout = new Workout("Marathon Training Starts Here");
        workoutsByName.put(workout.getName(), workout);
        exercise = exercisesByName.get("Running");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseDistance(exercise, exerciseExperience,new ExerciseDistance(2.5, DistanceUnit.MILES));
        workout.addExercise(workoutExercise);
        exercise = exercisesByName.get("Exercise Bike");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExerciseDuration(exercise, exerciseExperience, new ExerciseDuration(45, TimeUnit.MINUTES));
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
     * Opens a data access class
     *
     * @param statement the statement to use in data access queries
     */
    @Override
    public void open(Statement statement) { }

    /**
     * Close the stub database
     */
    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    /**
     * Get a new statement from the database connection
     *
     * @return a new statement
     */
    @Override
    public Statement getNewStatement() {
        return null;
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
     * Retrieves the name of a the workout that has been completed the least amount of times
     *
     * @return the workout that has been ocmpleted the least amount of times
     */
    @Override
    public String getLeastCompletedWorkout() {
        //TODO implement and write test
        return null;
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
        workoutSessionsByDate.get(workoutSession.getDate()).toggleCompleted();
    }

    /**
     * Returns the list of all completed workout records
     *
     * @return a list of all completed workout records
     */
    @Override
    public List<CompletedWorkoutRecord> getCompletedWorkouts() {
        //TODO implement and write test
        return null;
    }

    /**
     * Returns the most recent completed workout
     *
     * @return the most recent completed workout
     */
    @Override
    public CompletedWorkoutRecord getMostRecentCompletedWorkout() {
        //TODO implement and write test
        return null;
    }
}
