package ledge.muscleup.model.unit.business;

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
    public AccessWorkoutsTest(String arg0) {
        super(arg0);
    }

    /**
     * Initializes the AccessWorkouts to be used in the test
     */
    @Before
    public void setUp() {
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

        workout = dataAccess.getWorkout("Never Skip Leg Day");
        assertNotNull(workout);
        assertEquals("Never Skip Leg Day", workout.getName());
        assertEquals(2, workout.numExercises());

        workout = dataAccess.getWorkout("Marathon Training Starts Here");
        assertNotNull(workout);
        assertEquals("Marathon Training Starts Here", workout.getName());
        assertEquals(2, workout.numExercises());

        workout = dataAccess.getWorkout("Work that Core, Get that Score!");
        assertNotNull(workout);
        assertEquals("Work that Core, Get that Score!", workout.getName());
        assertEquals(2, workout.numExercises());

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
        workoutList1.add(new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        xpMediumIntensity, new ExerciseSets(4, 15)),
                new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        xpMediumIntensity, new ExerciseSets(3, 10))
        }));
        workoutList1.add(new Workout("Marathon Training Starts Here", new WorkoutExercise[]{
                new WorkoutExerciseDistance(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO),
                        xpHighIntensity, new ExerciseDistance(2.5, DistanceUnit.MILES)),
                new WorkoutExerciseDuration(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM, ExerciseType.CARDIO),
                        xpMediumIntensity, new ExerciseDuration(45, TimeUnit.MINUTES))
        }));
        workoutList1.add(new Workout("Welcome to the Gun Show", new WorkoutExercise[]{
                new WorkoutExerciseSetsAndWeight(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM),
                        xpLowIntensity, new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS)),
                new WorkoutExerciseSets(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM),
                        xpHighIntensity, new ExerciseSets(2, 15))
        }));
        workoutList1.add(new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
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
     * Retrieves the name of the workout that is suggested for the user
     *
     * @return the workout that is suggested for the user
     */
    @Override
    public String getSuggestedWorkout() {
        //TODO implement or remove
        return null;
    }
}

