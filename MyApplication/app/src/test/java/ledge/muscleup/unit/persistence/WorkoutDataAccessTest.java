package ledge.muscleup.unit.persistence;

import junit.framework.TestCase;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * Used for testing the InterfaceWorkoutDataAccess persistence interface
 *
 * @author: Ryan Koop
 * @version: 1.0
 * @since 2017-06-29
 *
 */
public class WorkoutDataAccessTest extends TestCase {
    private static final int XP_HIGH_INTENSITY = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    private static final int XP_MEDIUM_INTENSITY = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    private static final int XP_LOW_INTENSITY = (ExerciseIntensity.LOW.ordinal() + 1) * 15;

    private static TemplateDataAccessStub dataAccess;

    /**
     * Constructor for the WorkoutDataAccessTest
     */
    public WorkoutDataAccessTest(String arg0) {
        super(arg0);
    }

    /**
     * Initializes the WorkoutDataAccess to be used in the test
     */
    @Before
    public void setUp() {
        dataAccess = new TemplateDataAccessStub("Test Workout");
        dataAccess.open("Test Workout");
    }

    /**
     * Closes the DataAccess connection
     */
    @After
    public void tearDown() {
        dataAccess.close();
    }

    /**
     * Tests that getting a workout works properly
     */
    @Test
    public static void testGetWorkout() {
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
    public static void testGetWorkoutsList() {
        System.out.println("\nStarting testGetWorkoutsList");

        // Workouts by object already in list
        List<Workout> workoutList1 = new ArrayList<>();
        workoutList1.add(new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        XP_MEDIUM_INTENSITY, new ExerciseSets(4, 15)),
                new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        XP_MEDIUM_INTENSITY, new ExerciseSets(3, 10))
        }));
        workoutList1.add(new Workout("Marathon Training Starts Here", new WorkoutExercise[]{
                new WorkoutExerciseDistance(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO),
                        XP_HIGH_INTENSITY, new ExerciseDistance(2.5, DistanceUnit.MILES)),
                new WorkoutExerciseDuration(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM, ExerciseType.CARDIO),
                        XP_MEDIUM_INTENSITY, new ExerciseDuration(45, TimeUnit.MINUTES))
        }));
        workoutList1.add(new Workout("Welcome to the Gun Show", new WorkoutExercise[]{
                new WorkoutExerciseSetsAndWeight(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM),
                        XP_LOW_INTENSITY, new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS)),
                new WorkoutExerciseSets(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM),
                        XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
        }));
        workoutList1.add(new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                        XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                        XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
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
    public static void testGetWorkoutNamesList() {
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
     * Tests that the least completed workout is returned from the database
     */
    @Test
    public void testGetLeastCompletedWorkout() {
        System.out.println("\nStarting testGetLeastCompletedWorkout");

        //None is least completed since none are completed, getLeastCompleted() returns first one
        assertEquals(dataAccess.getWorkoutNamesList().get(0), dataAccess.getLeastCompletedWorkout());

        WorkoutSession session = dataAccess.getWorkoutSession(
                LocalDate.now().minusWeeks(1).withDayOfWeek(DateTimeConstants.THURSDAY));
        dataAccess.toggleWorkoutComplete(session);
        assertFalse(dataAccess.getLeastCompletedWorkout().equals(session.getName()));

        session = dataAccess.getWorkoutSession(
                LocalDate.now().withDayOfWeek(DateTimeConstants.TUESDAY));
        dataAccess.toggleWorkoutComplete(session);
        assertFalse(dataAccess.getLeastCompletedWorkout().equals(session.getName()));

        session = dataAccess.getWorkoutSession(
                LocalDate.now().withDayOfWeek(DateTimeConstants.WEDNESDAY));
        dataAccess.toggleWorkoutComplete(session);
        assertFalse(dataAccess.getLeastCompletedWorkout().equals(session.getName()));

        session = dataAccess.getWorkoutSession(
                LocalDate.now().plusWeeks(1).withDayOfWeek(DateTimeConstants.TUESDAY));
        dataAccess.toggleWorkoutComplete(session);

        //all have been completed once now, check that getLeastCompleted() returns first one again
        assertEquals(dataAccess.getWorkoutNamesList().get(0), dataAccess.getLeastCompletedWorkout());

        System.out.println("Finishing testGetLeastCompletedWorkout\n");
    }
}
