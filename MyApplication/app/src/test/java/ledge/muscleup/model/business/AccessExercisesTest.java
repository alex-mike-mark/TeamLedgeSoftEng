package ledge.muscleup.model.business;

import junit.framework.TestCase;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ledge.muscleup.business.InterfaceAccessExercises;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * AccessExercisesTest.java used to test AccessExercises.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-24
 */

public class AccessExercisesTest extends TestCase {
    InterfaceAccessExercises dataAccess;

    public AccessExercisesTest(String arg0)
    {
        super(arg0);
    }

    @Before
    public void setUp()
    {
        dataAccess = new TemplateAccessExercises();
    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void testGetExercise(){
        System.out.println("\nStarting testGetExercise");

        // Exercises should already be in db
        Exercise exercise = dataAccess.getExercise("Bicep Curls");
        assertEquals("Bicep Curls", exercise.getName());
        assertEquals(ExerciseIntensity.LOW, exercise.getIntensity());
        assertEquals(ExerciseType.ARM, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Push-Ups");
        assertEquals("Push-Ups", exercise.getName());
        assertEquals(ExerciseIntensity.HIGH, exercise.getIntensity());
        assertEquals(ExerciseType.ARM, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Running");
        assertEquals("Running", exercise.getName());
        assertEquals(ExerciseIntensity.HIGH, exercise.getIntensity());
        assertEquals(ExerciseType.CARDIO, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Exercise Bike");
        assertEquals("Exercise Bike", exercise.getName());
        assertEquals(ExerciseIntensity.MEDIUM, exercise.getIntensity());
        assertEquals(ExerciseType.CARDIO, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Crunches");
        assertEquals("Crunches", exercise.getName());
        assertEquals(ExerciseIntensity.LOW, exercise.getIntensity());
        assertEquals(ExerciseType.CORE, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Bicycle Kicks");
        assertEquals("Bicycle Kicks", exercise.getName());
        assertEquals(ExerciseIntensity.HIGH, exercise.getIntensity());
        assertEquals(ExerciseType.CORE, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Squats");
        assertEquals("Squats", exercise.getName());
        assertEquals(ExerciseIntensity.MEDIUM, exercise.getIntensity());
        assertEquals(ExerciseType.LEG, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Lunges");
        assertEquals("Lunges", exercise.getName());
        assertEquals(ExerciseIntensity.MEDIUM, exercise.getIntensity());
        assertEquals(ExerciseType.LEG, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        System.out.println("Finishing testGetExercise\n");
    }

    @Test
    public void testGetExercisesList(){
        System.out.println("\nStarting testGetExercisesList");

        // Exercises by object already in list
        List<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM, false));
        exerciseList.add(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE, false));
        exerciseList.add(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false));
        exerciseList.add(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM, false));
        exerciseList.add(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO, false));
        exerciseList.add(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE,
                false));
        exerciseList.add(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false));
        exerciseList.add(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                ExerciseType.CARDIO, false));

        assertEquals(exerciseList.toString(), dataAccess.getExercisesList().toString());

        System.out.println("Finishing testGetExercisesList\n");
    }

    @Test
    public void testGetExerciseNamesList(){
        System.out.println("\nStarting testGetExerciseNamesList");

        // Exercises by name already in list
        List<String> namesList = new ArrayList<>();
        namesList.add("Bicep Curls");
        namesList.add("Crunches");
        namesList.add("Lunges");
        namesList.add("Push-Ups");
        namesList.add("Running");
        namesList.add("Bicycle Kicks");
        namesList.add("Squats");
        namesList.add("Exercise Bike");

        assertEquals(namesList, dataAccess.getExerciseNamesList());

        System.out.println("Finishing testGetExerciseNamesList\n");
    }

    @Test
    public void testInsertExercise()
    {
        System.out.println("\nStarting testInsertExercise");

        List<Exercise> list = dataAccess.getExercisesList();
        assertEquals(8, list.size());
        Exercise exercise1 = new Exercise("Dead Lifts", ExerciseIntensity.HIGH, ExerciseType.LEG, false);
        dataAccess.insertExercise(exercise1);
        Exercise exercise2 = dataAccess.getExercise("Dead Lifts");
        assertEquals(exercise1, exercise2);
        assertEquals("Dead Lifts", exercise2.getName());
        assertEquals(ExerciseIntensity.HIGH, exercise2.getIntensity());
        assertEquals(ExerciseType.LEG, exercise2.getType());
        assertEquals(false, exercise2.isFavourite());
        list = dataAccess.getExercisesList();
        assertEquals(9, list.size());

        exercise1 = new Exercise("Military Press", ExerciseIntensity.LOW, ExerciseType.ARM, true);
        dataAccess.insertExercise(exercise1);
        exercise2 = dataAccess.getExercise("Military Press");
        assertEquals(exercise1, exercise2);
        assertEquals("Military Press", exercise2.getName());
        assertEquals(ExerciseIntensity.LOW, exercise2.getIntensity());
        assertEquals(ExerciseType.ARM, exercise2.getType());
        assertEquals(true, exercise2.isFavourite());
        list = dataAccess.getExercisesList();
        assertEquals(10, list.size());

        System.out.println("Finished testInsertExercise\n");
    }

    @Test
    public void testRemoveExercise(){
        System.out.println("\nStarting testRemoveExercise");

        // Remove first exercise in list
        List<Exercise> list = dataAccess.getExercisesList();
        assertEquals(8, list.size());
        Exercise exercise = new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM, false);
        dataAccess.removeExercise(exercise);
        exercise = dataAccess.getExercise("Bicep Curls");
        assertNull(exercise);
        list = dataAccess.getExercisesList();
        assertEquals(7, list.size());

        // Remove last exercise in list
        exercise = new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false);
        dataAccess.removeExercise(exercise);
        exercise = dataAccess.getExercise("Bicep Curls");
        assertNull(exercise);
        list = dataAccess.getExercisesList();
        assertEquals(6, list.size());

        // Remove middle exercise in list
        exercise = new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE, false);
        dataAccess.removeExercise(exercise);
        exercise = dataAccess.getExercise("Bicep Curls");
        assertNull(exercise);
        list = dataAccess.getExercisesList();
        assertEquals(5, list.size());

        System.out.println("Finished testRemoveExercise\n");
    }
}

/**
 * A template Exercises accessor that creates a template database stub for use in testing
 */
class TemplateAccessExercises implements InterfaceAccessExercises {
    private TemplateDataAccessStub dataAccess;

    /**
     * The default constructor for the TemplateAccessExercises
     */
    TemplateAccessExercises() {
        dataAccess = new TemplateDataAccessStub("testDB");
        dataAccess.open();
    }

    /**
     * This method gets an exercise from the database with the given name
     * @param exerciseName the name of the exercise
     * @return an exercise from the database with the given name, or null if it does not exist
     */
    @Override
    public Exercise getExercise(String exerciseName) {
        return dataAccess.getExercise(exerciseName);
    }

    /**
     * This method gets exercises stored in the database in the form of a list
     * @return a list of exercises in the database
     */
    @Override
    public List<Exercise> getExercisesList() {
        return dataAccess.getExercisesList();
    }

    /**
     * This method gets the names of all exercises in the database in the form of a list
     * @return a list of exercise names in the database
     */
    @Override
    public List<String> getExerciseNamesList() {
        return dataAccess.getExerciseNamesList();
    }

    /**
     * This method inserts a new exercise into the database
     * @param exercise the exercise to be inserted
     */
    @Override
    public void insertExercise(Exercise exercise) {
        dataAccess.insertExercise(exercise);
    }

    /**
     * This method removes an exercise from the database
     * @param exercise the exercise to be removed
     */
    @Override
    public void removeExercise(Exercise exercise) {
        dataAccess.removeExercise(exercise);
    }
}

/**
 * A template database stub for use in testing the AccessExercises that needs an accessor, which in
 * turn needs a database stub
 * constructor parameter
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-24
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