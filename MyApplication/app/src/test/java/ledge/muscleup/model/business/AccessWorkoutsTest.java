package ledge.muscleup.model.business;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.business.InterfaceAccessWorkouts;
import ledge.muscleup.model.exercise.DistanceUnit;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.exercise.TimeUnit;
import ledge.muscleup.model.exercise.WeightUnit;
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
     * Safely closes the AccessWorkouts
     */
    @After
    public void tearDown() {
        //TODO:
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
                new WorkoutExercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG,
                        new ExerciseSets(4, 15)),
                new WorkoutExercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG,
                        new ExerciseSets(3, 10))
        }));
        workoutList1.add(new Workout("Marathon Training Starts Here", false, new WorkoutExercise[]{
                new WorkoutExercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO,
                        new ExerciseDistance(2.5, DistanceUnit.MILES)),
                new WorkoutExercise("Exercise Bike", ExerciseIntensity.MEDIUM, ExerciseType.CARDIO,
                        new ExerciseDuration(45, TimeUnit.MINUTES))
        }));
        workoutList1.add(new Workout("Welcome to the Gun Show", false, new WorkoutExercise[]{
                new WorkoutExercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM,
                        new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS)),
                new WorkoutExercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM,
                        new ExerciseSets(2, 15))
        }));
        workoutList1.add(new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                new WorkoutExercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE,
                        new ExerciseSets(2, 25)),
                new WorkoutExercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE,
                        new ExerciseSets(2, 15))
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
     * Tests that adding an exercise to a workout works properly
     */
    @Test
    public void testAddExerciseToWorkout() {
        // public boolean addExerciseToWorkout (Workout workout, WorkoutExercise exercise)

        System.out.println("\nStarting testAddExerciseToWorkout");

        assertEquals(2, dataAccess.getWorkout("Never Skip Leg Day").numExercises());

        dataAccess.addExerciseToWorkout(new Workout("Never Skip Leg Day"),
                new WorkoutExercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG,
                        new ExerciseSets(3, 10)));
        System.out.println("Finished testAddExerciseToWorkout\n");

        assertEquals(3, dataAccess.getWorkout("Never Skip Leg Day").numExercises());
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
        dataAccess.open();
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
     * Adds an exercise stored in the database to a workout stored in the database with the given
     * quantity of the exercise to be done
     * @param workout the workout to add an exercise to
     * @param exercise the exercise to add to the workout
     *
     * @return true if exercise was added successfully, false otherwise
     */
    @Override
    public boolean addExerciseToWorkout (Workout workout, WorkoutExercise exercise) {
        return dataAccess.addExerciseToWorkout(workout, exercise);
    }
}

