package ledge.muscleup.model;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.business.ScheduleManager;
import ledge.muscleup.model.exercise.DistanceUnit;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.exercise.WeightUnit;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * Tests for the ScheduleManager
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-06
 */

public class ScheduleManagerTest {
    private ScheduleManager scheduleManager;
    InterfaceAccessWorkoutSessions dataAccess;

    /**
     * Constructor for the ScheduleManagerTest
     */
    public ScheduleManagerTest() { super(); }

    /**
     * Initializes the ScheduleManager to be used in the test
     */
    @Before
    public void testInit(){
        dataAccess = new TemplateAccessWorkoutSessions();
        scheduleManager = new ScheduleManager(dataAccess);
    }

    /**
     * Tests that getting a day of the current week works properly
     */
    @Test
    public void getWeekdayTest(){
        Assert.assertTrue("Returned unexpected weekday value",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY).isEqual(new LocalDate(2017, 6, 5)));
        Assert.assertTrue("Returned unexpected weekday value",
                scheduleManager.getWeekday(DateTimeConstants.SUNDAY).isEqual(new LocalDate(2017, 6, 11)));
    }

    /**
     * Tests that checking if a workout exists on a day and getting a workout on a day of the
     * current week works properly
     */
    @Test
    public void scheduledWorkoutsTest(){
        Assert.assertFalse("Returned that day is empty when it isn't",
                scheduleManager.isDayEmpty(DateTimeConstants.MONDAY));
        Assert.assertTrue("Returned that day isn't empty when it is",
                scheduleManager.isDayEmpty(DateTimeConstants.SATURDAY));

        Assert.assertEquals("Returned a workout for a day where there shouldn't be one",
                scheduleManager.getScheduledWorkout(DateTimeConstants.MONDAY), null);
        Assert.assertEquals("Didn't return the workout scheduled for a day",
                scheduleManager.getScheduledWorkout(DateTimeConstants.SATURDAY).getName(),
                "Work that Core, Get that Score!");
    }

    /**
     * Tests that changing the current week to the next and previous weeks works properly
     */
    @Test
    public void changeWeekTest(){
        LocalDate currWeekStart = scheduleManager.getWeekday(DateTimeConstants.MONDAY);

        scheduleManager.nextWeek();

        Assert.assertEquals("Improperly incremented week",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY), currWeekStart.plusWeeks(1));
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.TUESDAY).getName(),
                "Never Skip Leg Day");
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Marathon Training Starts Here");

        scheduleManager.lastWeek();

        Assert.assertEquals("Improperly decremented week",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY), currWeekStart);
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Welcome to the Gun Show");
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.FRIDAY).getName(),
                "Never Skip Leg Day");
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.SATURDAY).getName(),
                "Work that Core, Get that Score!");

        scheduleManager.lastWeek();

        Assert.assertEquals("Improperly decremented week",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY), currWeekStart.minusWeeks(1));
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.MONDAY), null);
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.SUNDAY), null);

        scheduleManager.nextWeek();

        Assert.assertEquals("Improperly incremented week",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY), currWeekStart);
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Welcome to the Gun Show");
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.FRIDAY).getName(),
                "Never Skip Leg Day");
        Assert.assertEquals("returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.SATURDAY).getName(),
                "Work that Core, Get that Score!");
    }

    /**
     * Tests that a workout can be added to and removed from the week
     */
    @Test
    public void addRemoveWorkoutTest(){
        Assert.assertFalse("Removed a workout when there isn't one",
                scheduleManager.removeWorkoutSession(DateTimeConstants.WEDNESDAY));
        Assert.assertTrue("Didn't remove workout",
                scheduleManager.removeWorkoutSession(DateTimeConstants.THURSDAY));

        Assert.assertEquals("Improperly deleted a workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.WEDNESDAY), null);
        Assert.assertEquals("Improperly deleted a workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.THURSDAY), null);
    }
}

/**
 * A template WorkoutSession accessor that creates a template database stub for use in testing
 */
class TemplateAccessWorkoutSessions implements InterfaceAccessWorkoutSessions {
    private TemplateDataAccessStub dataAccess;

    /**
     * The default constructor for the TemplateAccessWorkoutSessions
     */
    TemplateAccessWorkoutSessions() {
        dataAccess = new TemplateDataAccessStub("testDB");
        dataAccess.open();
    }

    /**
     * This method gets a workout session from the database with the given date
     *
     * @param dateOfSession the date of the workout session
     * @return a workout session from the database scheduled on the given date
     */
    @Override
    public WorkoutSession getWorkoutSession(LocalDate dateOfSession) {
        return dataAccess.getWorkoutSession(dateOfSession);
    }

    /**
     * This method gets a list of workout sessions in the database
     *
     * @return a list of the workout sessions stored in the database
     */
    @Override
    public List<WorkoutSession> getWorkoutSessionsList() {
        return dataAccess.getWorkoutSessionsList();
    }

    /**
     * A method that returns a list of workout sessions scheduled in a date range
     *
     * @param startDate the first date of the date range
     * @param endDate   the last date of the date range
     * @return a list of all workout sessions scheduled between startDate and endDate, inclusive
     */
    @Override
    public List<WorkoutSession> getSessionsInDateRange(LocalDate startDate, LocalDate endDate) {
        return dataAccess.getSessionsInDateRange(startDate, endDate);
    }

    /**
     * Adds a new workout session to the database
     *
     * @param workoutSession the workout session to be added to the database
     */
    @Override
    public void insertWorkoutSession(WorkoutSession workoutSession) {
        dataAccess.insertWorkoutSession(workoutSession);
    }

    /**
     * Removes a workout session from the database, if it exists
     *
     * @param workoutSession the workout session to be removed
     */
    @Override
    public void removeWorkoutSession(WorkoutSession workoutSession) {
        dataAccess.removeWorkoutSession(workoutSession);
    }
}

/**
 * A template database stub for use in testing the ScheduleManager that needs an accessor, which in
 * turn needs a database stub
 * constructor parameter
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-07
 */

class TemplateDataAccessStub implements InterfaceDataAccess {
    private String dbName;
    private String dbType = "testing template";

    private Map<String, Workout> workoutsByName;
    private Map<String, Exercise> exercisesByName;
    private Map<LocalDate, WorkoutSession> workoutSessionsByDate;

    /**
     * Constructor for DataAccessStub
     * @param dbName the name of the database
     */
    public TemplateDataAccessStub (String dbName) {
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
                new ExerciseDuration(45));
        addExerciseToWorkout(workout, workoutExercise);

        workout = new Workout("Work that Core, Get that Score!");
        workoutsByName.put(workout.getName(), workout);
        workoutExercise = new WorkoutExercise(exercisesByName.get("Crunches"),
                new ExerciseSets(2, 25));
        addExerciseToWorkout(workout, workoutExercise);
        workoutExercise = new WorkoutExercise(exercisesByName.get("Bicycle Kicks"),
                new ExerciseSets(2, 15));
        addExerciseToWorkout(workout, workoutExercise);

        workoutSessionsByDate = new HashMap<>();
        workoutSession = new WorkoutSession(
                (workoutsByName.get("Welcome to the Gun Show")),
                new LocalDate(2017, 06, 8),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Never Skip Leg Day")),
                new LocalDate(2017, 06, 9),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Work that Core, Get that Score!")),
                new LocalDate(2017, 06, 10),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Never Skip Leg Day")),
                new LocalDate(2017, 06, 13),
                false);
        workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

        workoutSession = new WorkoutSession(
                (workoutsByName.get("Marathon Training Starts Here")),
                new LocalDate(2017, 06, 15),
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
}