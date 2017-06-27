package ledge.muscleup.model;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.model.exercise.DistanceUnit;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.TimeUnit;
import ledge.muscleup.model.exercise.WeightUnit;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * Tests for the ScheduleWeek
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-06
 */

public class ScheduleWeekTest {
    private ScheduleWeek scheduleWeek;
    InterfaceAccessWorkoutSessions dataAccess;

    /**
     * Constructor for the ScheduleWeekTest
     */
    public ScheduleWeekTest() { super(); }

    /**
     * Initializes the ScheduleWeek to be used in the test
     */
    @Before
    public void testInit(){
        dataAccess = new TemplateAccessWorkoutSessions();
        scheduleWeek = new ScheduleWeek(dataAccess.getCurrentWeekSessions());
    }

    /**
     * Tests that getting a day of the current week works properly
     */
    @Test
    public void getWeekdayTest(){
        Assert.assertTrue("Returned unexpected weekday value",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY).isEqual(new LocalDate(2017, 6, 5)));
        Assert.assertTrue("Returned unexpected weekday value",
                scheduleWeek.getWeekday(DateTimeConstants.SUNDAY).isEqual(new LocalDate(2017, 6, 11)));
    }

    /**
     * Tests that checking if a workout exists on a day and getting a workout on a day of the
     * current week works properly
     */
    @Test
    public void scheduledWorkoutsTest(){
        Assert.assertFalse("Returned that day is empty when it isn't",
                scheduleWeek.isDayEmpty(DateTimeConstants.MONDAY));
        Assert.assertTrue("Returned that day isn't empty when it is",
                scheduleWeek.isDayEmpty(DateTimeConstants.SATURDAY));

        Assert.assertEquals("Returned a workout for a day where there shouldn't be one",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.MONDAY), null);
        Assert.assertEquals("Didn't return the workout scheduled for a day",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.SATURDAY).getName(),
                "Work that Core, Get that Score!");
    }

    /**
     * Tests that changing the current week to the next and previous weeks works properly
     */
    @Test
    public void changeWeekTest(){
        LocalDate currWeekStart = scheduleWeek.getWeekday(DateTimeConstants.MONDAY);
        List<WorkoutSession> workoutList;

        dataAccess.setToNextWeek(scheduleWeek);

        Assert.assertEquals("Improperly incremented week",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY), currWeekStart.plusWeeks(1));
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.TUESDAY).getName(),
                "Never Skip Leg Day");
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Marathon Training Starts Here");

        dataAccess.setToLastWeek(scheduleWeek);

        Assert.assertEquals("Improperly decremented week",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY), currWeekStart);
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Welcome to the Gun Show");
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.FRIDAY).getName(),
                "Never Skip Leg Day");
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.SATURDAY).getName(),
                "Work that Core, Get that Score!");

        dataAccess.setToLastWeek(scheduleWeek);

        Assert.assertEquals("Improperly decremented week",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY), currWeekStart.minusWeeks(1));
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.MONDAY), null);
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.SUNDAY), null);

        dataAccess.setToNextWeek(scheduleWeek);

        Assert.assertEquals("Improperly incremented week",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY), currWeekStart);
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Welcome to the Gun Show");
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.FRIDAY).getName(),
                "Never Skip Leg Day");
        Assert.assertEquals("returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.SATURDAY).getName(),
                "Work that Core, Get that Score!");
    }

    /**
     * Tests that a workout can be added to and removed from the week
     */
    @Test
    public void addRemoveWorkoutTest(){
        Assert.assertFalse("Removed a workout when there isn't one",
                scheduleWeek.removeWorkoutSession(DateTimeConstants.WEDNESDAY));
        Assert.assertTrue("Didn't remove workout",
                scheduleWeek.removeWorkoutSession(DateTimeConstants.THURSDAY));

        Assert.assertEquals("Improperly deleted a workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.WEDNESDAY), null);
        Assert.assertEquals("Improperly deleted a workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.THURSDAY), null);
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
     * A method that returns a list of workout sessions scheduled in the current week
     *
     * @return a list of all workout sessions scheduled in the current week
     */
    @Override
    public List<WorkoutSession> getCurrentWeekSessions() {
        //TODO remove after refactoring persistence layer or implement and test
        return null;
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

    /**
     * Sets the scheduled date of a workout
     *
     * @param workoutSession the workout to change the date for
     * @param newDate        the new date of the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public void setWorkoutDate(WorkoutSession workoutSession, LocalDate newDate) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
    }

    /**
     * Toggles the completed state of a workout
     *
     * @param workoutSession the workout to change the state of
     */
    @Override
    public void toggleWorkoutCompleted(WorkoutSession workoutSession) {
        //TODO remove after refactoring persistence layer or implement and test
    }

    /**
     * Log an exercise in a workout as complete
     *
     * @param workoutSession the workout which contains the exercise
     * @param exercise       the exercise to complete
     * @return a boolean representing whether the exercise was marked as completed or not
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public boolean completeWorkoutExercise(WorkoutSession workoutSession, WorkoutSessionExercise exercise) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
        return false;
    }

    /**
     * Adds a workout session to a given day of a scheduled week
     *
     * @param scheduleWeek  the week to add the workout to
     * @param workoutSession the workout session to add
     * @param dayOfWeek      the day of the week to add the workout session to
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     *                                  > DateTimeConstants.SUNDAY}
     */
    @Override
    public void addWorkoutSession(ScheduleWeek scheduleWeek, WorkoutSession workoutSession, int dayOfWeek) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
    }

    /**
     * Removes a workout from a given day of a scheduled week
     *
     * @param scheduleWeek the week to remove the workout from
     * @param dayOfWeek     the day to remove the workout from
     * @return a boolean representing if a workout was removed
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     *                                  > DateTimeConstants.SUNDAY}
     */
    @Override
    public boolean removeWorkoutSession(ScheduleWeek scheduleWeek, int dayOfWeek) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
        return false;
    }

    /**
     * Sets the manager to contain the scheduled workouts for the previous week
     *
     * @param scheduleWeek the week to change
     */
    @Override
    public void setToLastWeek(ScheduleWeek scheduleWeek) {
        //TODO remove after refactoring persistence layer or implement and test
    }

    /**
     * Sets the manager to contain the scheduled workouts for the following week
     *
     * @param scheduleWeek the week to change
     */
    @Override
    public void setToNextWeek(ScheduleWeek scheduleWeek) {
        //TODO remove after refactoring persistence layer or implement and test
    }

    /**
     * Sets the manager to contain the scheduled workouts for the current week
     *
     * @param scheduleWeek the week to change
     */
    @Override
    public void setToCurrentWeek(ScheduleWeek scheduleWeek) {
        //TODO remove after refactoring persistence layer or implement and test
    }

    /**
     * Creates a new ScheduleWeek based on the given date
     *
     * @param dayInWeek a day in the week to created a ScheduleWeek for
     * @return a ScheduleWeek, which contains all WorkoutSessions for the given week
     */
    @Override
    public ScheduleWeek newScheduledWeek(LocalDate dayInWeek) {
        //TODO remove after refactoring persistence layer or implement and test
        return null;
    }
}

/**
 * A template database stub for use in testing the ScheduleWeek that needs an accessor, which in
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
        final int xpPerIntensityLevel = 15;

        workout = new Workout("Welcome to the Gun Show");
        workoutsByName.put(workout.getName(), workout);
        exercise = exercisesByName.get("Bicep Curls");
        int exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExercise(exercise, new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS),
                exerciseExperience);
        addExerciseToWorkout(workout, workoutExercise);
        exercise = exercisesByName.get("Push-Ups");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExercise(exercise, new ExerciseSets(2, 15), exerciseExperience);
        addExerciseToWorkout(workout, workoutExercise);

        workout = new Workout("Never Skip Leg Day");
        workoutsByName.put(workout.getName(), workout);
        exercise = exercisesByName.get("Squats");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExercise(exercise, new ExerciseSets(4, 15), exerciseExperience);
        addExerciseToWorkout(workout, workoutExercise);
        exercise = exercisesByName.get("Lunges");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExercise(exercise, new ExerciseSets(3, 10), exerciseExperience);
        addExerciseToWorkout(workout, workoutExercise);

        workout = new Workout("Marathon Training Starts Here");
        workoutsByName.put(workout.getName(), workout);
        exercise = exercisesByName.get("Running");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExercise(exercise, new ExerciseDistance(2.5, DistanceUnit.MILES), exerciseExperience);
        addExerciseToWorkout(workout, workoutExercise);
        exercise = exercisesByName.get("Exercise Bike");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExercise(exercise, new ExerciseDuration(45, TimeUnit.MINUTES), exerciseExperience);
        addExerciseToWorkout(workout, workoutExercise);

        workout = new Workout("Work that Core, Get that Score!");
        workoutsByName.put(workout.getName(), workout);
        exercise = exercisesByName.get("Crunches");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExercise(exercise, new ExerciseSets(2, 25), exerciseExperience);
        addExerciseToWorkout(workout, workoutExercise);
        exercise = exercisesByName.get("Bicycle Kicks");
        exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
        workoutExercise = new WorkoutExercise(exercise, new ExerciseSets(2, 15), exerciseExperience);
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
     * Updates the recommended quantity of exercise for a given exercise in a given workout in the database
     *
     * @param workout  the workout that contains the exercise to update
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @return a boolean representing if the exercise was found and updated in the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public boolean updateExerciseQuantity(Workout workout, WorkoutExercise exercise, InterfaceExerciseQuantity quantity) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
        return false;
    }

    /**
     * Toggles the favourite state of an exercise in the database
     *
     * @param workout the workout to update the status of
     */
    @Override
    public void toggleExerciseFavourite(Workout workout) {
        //TODO remove after refactoring persistence layer or implement and test
    }

    /**
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     *
     * @param workout  the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     * @return true if exercise was added successfully, false otherwise
     */
    @Override
    public boolean addWorkoutExercise(Workout workout, WorkoutExercise exercise) {
        //TODO remove after refactoring persistence layer or implement and test
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
    @Override
    public boolean moveWorkoutExercise(Workout workout, WorkoutExercise exercise, int index) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
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
    @Override
    public boolean removeWorkoutExercise(Workout workout, WorkoutExercise exercise) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
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
    @Override
    public void updateWorkoutDate(WorkoutSession workoutSession, LocalDate newDate) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
    }

    /**
     * Toggles the completed state of a workout in the database
     *
     * @param workoutSession the workout to change the state of
     */
    @Override
    public void toggleWorkoutComplete(WorkoutSession workoutSession) {
        //TODO remove after refactoring persistence layer or implement and test
    }

    /**
     * Toggles the completed state of an exercise in a workout in the database
     *
     * @param workoutSession the workout which contains the exercise
     * @param exercise       the exercise to complete
     * @return a boolean representing whether the exercise was marked as completed or not
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public boolean toggleExerciseComplete(WorkoutSession workoutSession, WorkoutSessionExercise exercise) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
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
    @Override
    public void addWorkoutSession(ScheduleWeek scheduleWeek, WorkoutSession workoutSession, int dayOfWeek) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
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
    @Override
    public boolean removeWorkoutSession(ScheduleWeek scheduleWeek, int dayOfWeek) throws IllegalArgumentException {
        //TODO remove after refactoring persistence layer or implement and test
        return false;
    }
}