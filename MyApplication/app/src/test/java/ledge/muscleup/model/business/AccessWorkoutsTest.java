package ledge.muscleup.model.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.enums.*;
import ledge.muscleup.business.InterfaceAccessWorkouts;

import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;

/**
 * AccessWorkoutsTest.java used to test AccessWorkouts.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-24
 */

public class AccessWorkoutsTest extends TestCase {
    InterfaceAccessWorkouts dataAccess;
    final int xpHighIntensity = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    final int xpMediumIntensity = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    final int xpLowIntensity = (ExerciseIntensity.LOW.ordinal() + 1) * 15;
    /**
     * Constructor for the AccessWorkoutsTest
     */
    public AccessWorkoutsTest(String arg0)
    {
        super(arg0);
    }

    /**
     * Initializes the AccessWorkouts to be used in the test
     */
    @Before
    public void setUp()
    {
        dataAccess = new TemplateAccessWorkouts();
    }
    
    /**
     * Tests that getting a workout works properly
     */
    @Test
    public void testGetWorkout() {
        System.out.println("\nStarting testGetWorkout");

        // Workouts should already be in db
        Workout workout = dataAccess.getWorkout("Welcome to the Gun Show");
        assertNotNull(workout);
        assertEquals("Welcome to the Gun Show", workout.getName());
        assertEquals(2, workout.numExercises());
        assertEquals(false, workout.isFavourite());

        workout = dataAccess.getWorkout("Never Skip Leg Day");
        assertNotNull(workout);
        assertEquals("Never Skip Leg Day", workout.getName());
        assertEquals(2, workout.numExercises());
        assertEquals(false, workout.isFavourite());

        workout = dataAccess.getWorkout("Marathon Training Starts Here");
        assertNotNull(workout);
        assertEquals("Marathon Training Starts Here", workout.getName());
        assertEquals(2, workout.numExercises());
        assertEquals(false, workout.isFavourite());

        workout = dataAccess.getWorkout("Work that Core, Get that Score!");
        assertNotNull(workout);
        assertEquals("Work that Core, Get that Score!", workout.getName());
        assertEquals(2, workout.numExercises());
        assertEquals(false, workout.isFavourite());

        System.out.println("Finishing testGetWorkout\n");
    }

    /**
     * Tests that getting the list of workouts works properly
     */
    @Test
    public void testGetWorkoutsList() {
        System.out.println("\nStarting testGetWorkoutsList");

        // Workouts by object already in list
        List<Workout> workoutList1 = new ArrayList<>();
        workoutList1.add(new Workout("Never Skip Leg Day", false, new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        xpMediumIntensity,new ExerciseSets(4, 15)),
                new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        xpMediumIntensity, new ExerciseSets(3, 10))
        }));
        workoutList1.add(new Workout("Marathon Training Starts Here", false, new WorkoutExercise[]{
                new WorkoutExerciseDistance(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO),
                        xpHighIntensity, new ExerciseDistance(2.5, DistanceUnit.MILES)),
                new WorkoutExerciseDuration(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM, ExerciseType.CARDIO),
                        xpMediumIntensity, new ExerciseDuration(45, TimeUnit.MINUTES))
        }));
        workoutList1.add(new Workout("Welcome to the Gun Show", false, new WorkoutExercise[]{
                new WorkoutExerciseSetsAndWeight(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM),
                        xpLowIntensity, new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS)),
                new WorkoutExerciseSets(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM),
                        xpHighIntensity, new ExerciseSets(2, 15))
        }));
        workoutList1.add(new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                        xpLowIntensity, new ExerciseSets(2, 25)),
                new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                        xpHighIntensity, new ExerciseSets(2, 15))
        }));

        List<Workout> workoutList2 = dataAccess.getWorkoutsList();
        assertNotNull(workoutList2);
        assertEquals(workoutList1.toString(), workoutList2.toString());

        System.out.println("Finishing testGetWorkoutsList\n");
    }

    /**
     * Tests that getting the list of workout names works properly
     */
    @Test
    public void testGetWorkoutNamesList() {
        System.out.println("\nStarting testGetWorkoutNamesList");

        // Workouts by name already in list
        List<String> namesList = new ArrayList<>();

        namesList.add("Never Skip Leg Day");
        namesList.add("Marathon Training Starts Here");
        namesList.add("Welcome to the Gun Show");
        namesList.add("Work that Core, Get that Score!");

        assertEquals(namesList, dataAccess.getWorkoutNamesList());

        System.out.println("Finishing testGetWorkoutNamesList\n");
    }

    /**
     * Tests that inserting a workout works properly
     */
    @Test
    public void testInsertWorkout() {
        System.out.println("\nStarting testInsertWorkout");

        assertEquals(4, dataAccess.getWorkoutsList().size());
        Workout workout1 = new Workout("New Workout");
        dataAccess.insertWorkout(workout1);
        Workout workout2 = dataAccess.getWorkout("New Workout");
        assertEquals("New Workout", workout2.getName());
        assertEquals(0, workout2.numExercises());
        assertEquals(false, workout2.isFavourite());
        assertEquals(5, dataAccess.getWorkoutsList().size());

        System.out.println("Finished testInsertWorkout\n");
    }

    /**
     * Tests that removing a workout works properly
     */
    @Test
    public void testRemoveWorkout() {
        System.out.println("\nStarting testRemoveWorkout");

        // Remove first workout in list
        assertEquals(4, dataAccess.getWorkoutsList().size());
        Workout workout = new Workout("Never Skip Leg Day");
        dataAccess.removeWorkout(workout);
        workout = dataAccess.getWorkout("Never Skip Leg Day");
        assertNull(workout);
        assertEquals(3, dataAccess.getWorkoutsList().size());

        // Remove middle workout in list
        assertEquals(3, dataAccess.getWorkoutsList().size());
        workout = new Workout("Welcome to the Gun Show");
        dataAccess.removeWorkout(workout);
        workout = dataAccess.getWorkout("Welcome to the Gun Show");
        assertNull(workout);
        assertEquals(2, dataAccess.getWorkoutsList().size());

        // Remove last workout in list
        assertEquals(2, dataAccess.getWorkoutsList().size());
        workout = new Workout("Work that Core, Get that Score!");
        dataAccess.removeWorkout(workout);
        workout = dataAccess.getWorkout("Work that Core, Get that Score!");
        assertNull(workout);
        assertEquals(1, dataAccess.getWorkoutsList().size());

        System.out.println("Finished testRemoveWorkout\n");
    }

    /**
     * Tests that setting recommended quantity for a exercise in a workout works properly
     */
    @Test
    public void testSetRecommendedQuantity(){
        System.out.println("\nStarting testSetRecommendedQuantity");
        //TODO
        //public boolean setRecommendedQuantity(Workout workout, WorkoutExercise exercise, InterfaceExerciseQuantity quantity)

        System.out.println("Finishing testSetRecommendedQuantity\n");
    }

    /**
     * Tests that toggling a workout as favourite works properly
     */
    @Test
    public void testToggleFavourite(){
        System.out.println("\nStarting testToggleFavourite");

        Workout workout = dataAccess.getWorkout("Never Skip Leg Day");

        assertFalse(dataAccess.getWorkout("Never Skip Leg Day").isFavourite());
        dataAccess.toggleWorkoutFavourite(workout);
        assertTrue(dataAccess.getWorkout("Never Skip Leg Day").isFavourite());
        dataAccess.toggleWorkoutFavourite(workout);
        assertFalse(dataAccess.getWorkout("Never Skip Leg Day").isFavourite());

        workout = dataAccess.getWorkout("Work that Core, Get that Score!");
        assertFalse(dataAccess.getWorkout("Work that Core, Get that Score!").isFavourite());
        dataAccess.toggleWorkoutFavourite(workout);
        assertTrue(dataAccess.getWorkout("Work that Core, Get that Score!").isFavourite());
        dataAccess.toggleWorkoutFavourite(workout);
        assertFalse(dataAccess.getWorkout("Work that Core, Get that Score!").isFavourite());

        System.out.println("Finishing testToggleFavourite\n");
    }

    /**
     * Tests that adding an exercise to a workout works properly
     */
    @Test
    public void testAddExercise() {
        System.out.println("\nStarting testAddExercise");

        assertEquals(2, dataAccess.getWorkout("Never Skip Leg Day").numExercises());

        dataAccess.addExerciseToWorkout(new Workout("Never Skip Leg Day"),
                new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        xpMediumIntensity ,new ExerciseSets(3, 10)));

        assertEquals(3, dataAccess.getWorkout("Never Skip Leg Day").numExercises());

        System.out.println("Finished testAddExercise\n");
    }

    /**
     * Tests that moving an exercise in a workout works properly
     */
    @Test
    public void testMoveExercise(){
        System.out.println("\nStarting testMoveExercise");
        //TODO
        //moveExercise(Workout workout, WorkoutExercise exercise, int index)

        System.out.println("Finishing testMoveExercise\n");
    }

    /**
     * Tests that removing an exercise from a workout works properly
     */
    @Test
    public void testRemoveExercise(){
        System.out.println("\nStarting testRemoveExercise");

        Workout workout = dataAccess.getWorkout("Never Skip Leg Day");
        WorkoutExercise workoutExercise = new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                xpMediumIntensity, new ExerciseSets(4, 15));

        assertEquals(2, dataAccess.getWorkout("Never Skip Leg Day").numExercises());
        dataAccess.removeExerciseFromWorkout(workout, workoutExercise);
        assertEquals(1, dataAccess.getWorkout("Never Skip Leg Day").numExercises());

        workoutExercise = new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                xpMediumIntensity ,new ExerciseSets(3, 10));

        assertEquals(1, dataAccess.getWorkout("Never Skip Leg Day").numExercises());
        dataAccess.removeExerciseFromWorkout(workout, workoutExercise);
        assertEquals(0, dataAccess.getWorkout("Never Skip Leg Day").numExercises());

        workout = dataAccess.getWorkout("Marathon Training Starts Here");
        workoutExercise = new WorkoutExerciseDuration(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM, ExerciseType.CARDIO),
                xpMediumIntensity, new ExerciseDuration(45, TimeUnit.MINUTES));


        assertEquals(2, dataAccess.getWorkout("Marathon Training Starts Here").numExercises());
        dataAccess.removeExerciseFromWorkout(workout, workoutExercise);
        assertEquals(1, dataAccess.getWorkout("Marathon Training Starts Here").numExercises());

        workoutExercise = new WorkoutExerciseDistance(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO),
                xpHighIntensity, new ExerciseDistance(2.5, DistanceUnit.MILES));

        assertEquals(1, dataAccess.getWorkout("Marathon Training Starts Here").numExercises());
        dataAccess.removeExerciseFromWorkout(workout, workoutExercise);
        assertEquals(0, dataAccess.getWorkout("Marathon Training Starts Here").numExercises());

        System.out.println("Finishing testRemoveExercise\n");
    }
}

/**
 * A template Workouts accessor that creates a template database stub for use in testing
 */
class TemplateAccessWorkouts implements InterfaceAccessWorkouts {
    private TemplateDataAccessStub dataAccess;

    /**
     * The default constructor for the TemplateAccessExercises
     */
    TemplateAccessWorkouts() {
        dataAccess = new TemplateDataAccessStub("testDB");
        dataAccess.open("testDB");
    }

    /**
     * This method gets a workout from the database with the given name
     * @param workoutName the name of the workout
     * @return a workout from the database with the given name, if it exists. Otherwise, returns null
     */
    @Override
    public Workout getWorkout(String workoutName) {
        return dataAccess.getWorkout(workoutName);
    }

    /**
     * This method gets a list of workouts in the database
     * @return a list of the workouts stored in the database
     */
    @Override
    public List<Workout> getWorkoutsList() {
        return dataAccess.getWorkoutsList();
    }

    /**
     * This method gets the names of all workouts in the database in the form of a list
     * @return a list of workout names in the database
     */
    @Override
    public List<String> getWorkoutNamesList() {
        return dataAccess.getWorkoutNamesList();
    }

    /**
     * Adds a new workout to the database
     * @param workout the workout to be added to the database
     */
    @Override
    public void insertWorkout(Workout workout) {
        dataAccess.insertWorkout(workout);
    }

    /**
     * Removes a workout from the database, if it exists
     * @param workout the workout to be removed
     */
    @Override
    public void removeWorkout(Workout workout) {
        dataAccess.removeWorkout(workout);
    }

    /**
     * Sets the recommended quantity of exercise for a given exercise in a given workout
     *
     * @param workout  the workout that contains the exercise to update
     * @param exercise the exercise to set the quantity for
     * @param quantity the quantity to assign to the exercise
     * @return a boolean representing if the exercise was found and updated in the workout
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public boolean setRecommendedExerciseQuantity(Workout workout, WorkoutExercise exercise, InterfaceExerciseQuantity quantity) throws IllegalArgumentException {
        return workout.setRecommendedQuantity(exercise, quantity) &&
                dataAccess.updateExerciseQuantity(workout, exercise, quantity);
    }

    /**
     * Toggle the favourite status of a workout
     *
     * @param workout the workout to update the status of
     */
    @Override
    public void toggleWorkoutFavourite(Workout workout) {
        workout.toggleFavourite();
        dataAccess.toggleExerciseFavourite(workout);
    }

    /**
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     * @param workout the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     *
     * @return true if exercise was added successfully, false otherwise
     */
    @Override
    public boolean addExerciseToWorkout(Workout workout, WorkoutExercise exercise) {
        boolean exerciseAdded = false; //if the exercise was added

        exerciseAdded = dataAccess.addExerciseToWorkout(workout, exercise);
        if (exerciseAdded)
            workout.addExercise(exercise);

        return exerciseAdded;
    }

    /**
     * Move the position of an exercise in the list of exercises
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
        return workout.moveExercise(exercise, index) &&
                dataAccess.moveWorkoutExercise(workout, exercise, index);
    }

    /**
     * Removes an exercise from the list of exercises
     *
     * @param workout  the workout to remove an exercise from
     * @param exercise the exercise to remove from the list
     * @return the exercise that was removed, or {@code null} if the exercise couldn't be found
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public boolean removeExerciseFromWorkout(Workout workout, WorkoutExercise exercise) throws IllegalArgumentException {
        return workout.removeExercise(exercise) &&
                dataAccess.removeWorkoutExercise(workout, exercise);
    }
}

