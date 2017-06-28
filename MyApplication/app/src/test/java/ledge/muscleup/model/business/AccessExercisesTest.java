package ledge.muscleup.model.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.business.InterfaceAccessExercises;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.enums.*;

/**
 * AccessExercisesTest.java used to test AccessExercises.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-24
 */

public class AccessExercisesTest extends TestCase {
    InterfaceAccessExercises dataAccess;

    /**
     * Constructor for the AccessExercisesTest
     */
    public AccessExercisesTest(String arg0)
    {
        super(arg0);
    }

    /**
     * Initializes the AccessExercises to be used in the test
     */
    @Before
    public void setUp()
    {
        dataAccess = new TemplateAccessExercises();
    }

    /**
     * Tests that getting an exercise works properly
     */
    @Test
    public void testGetExercise() {
        System.out.println("\nStarting testGetExercise");

        // Exercises should already be in db
        Exercise exercise = dataAccess.getExercise("Bicep Curls");
        assertNotNull(exercise);
        assertEquals("Bicep Curls", exercise.getName());
        assertEquals(ExerciseIntensity.LOW, exercise.getIntensity());
        assertEquals(ExerciseType.ARM, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Push-Ups");
        assertNotNull(exercise);
        assertEquals("Push-Ups", exercise.getName());
        assertEquals(ExerciseIntensity.HIGH, exercise.getIntensity());
        assertEquals(ExerciseType.ARM, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Running");
        assertNotNull(exercise);
        assertEquals("Running", exercise.getName());
        assertEquals(ExerciseIntensity.HIGH, exercise.getIntensity());
        assertEquals(ExerciseType.CARDIO, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Exercise Bike");
        assertNotNull(exercise);
        assertEquals("Exercise Bike", exercise.getName());
        assertEquals(ExerciseIntensity.MEDIUM, exercise.getIntensity());
        assertEquals(ExerciseType.CARDIO, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Crunches");
        assertNotNull(exercise);
        assertEquals("Crunches", exercise.getName());
        assertEquals(ExerciseIntensity.LOW, exercise.getIntensity());
        assertEquals(ExerciseType.CORE, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Bicycle Kicks");
        assertNotNull(exercise);
        assertEquals("Bicycle Kicks", exercise.getName());
        assertEquals(ExerciseIntensity.HIGH, exercise.getIntensity());
        assertEquals(ExerciseType.CORE, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Squats");
        assertNotNull(exercise);
        assertEquals("Squats", exercise.getName());
        assertEquals(ExerciseIntensity.MEDIUM, exercise.getIntensity());
        assertEquals(ExerciseType.LEG, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        exercise = dataAccess.getExercise("Lunges");
        assertNotNull(exercise);
        assertEquals("Lunges", exercise.getName());
        assertEquals(ExerciseIntensity.MEDIUM, exercise.getIntensity());
        assertEquals(ExerciseType.LEG, exercise.getType());
        assertEquals(false, exercise.isFavourite());

        System.out.println("Finishing testGetExercise\n");
    }

    /**
     * Tests that getting the list of exercises works properly
     */
    @Test
    public void testGetExercisesList() {
        System.out.println("\nStarting testGetExercisesList");

        // Exercises by object already in list
        List<Exercise> exerciseList1 = new ArrayList<>();
        exerciseList1.add(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM, false));
        exerciseList1.add(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE, false));
        exerciseList1.add(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false));
        exerciseList1.add(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM, false));
        exerciseList1.add(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO, false));
        exerciseList1.add(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE,
                false));
        exerciseList1.add(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false));
        exerciseList1.add(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                ExerciseType.CARDIO, false));

        List<Exercise> exerciseList2 = dataAccess.getExercisesList();
        assertNotNull(exerciseList2);
        assertEquals(exerciseList1.toString(), exerciseList2.toString());

        System.out.println("Finishing testGetExercisesList\n");
    }

    /**
     * Tests that getting the list of exercise names works properly
     */
    @Test
    public void testGetExerciseNamesList() {
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

    /**
     * Tests that inserting an exercise works properly
     */
    @Test
    public void testInsertExercise() {
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

    /**
     * Tests that removing an exercise works properly
     */
    @Test
    public void testRemoveExercise() {
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

