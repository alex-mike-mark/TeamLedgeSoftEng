package ledge.muscleup.model.unit.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ledge.muscleup.business.AccessExercises;
import ledge.muscleup.business.InterfaceAccessExercises;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.enums.*;
import ledge.muscleup.persistence.InterfaceExerciseDataAccess;

/**
 * AccessExercisesTest.java used to test AccessExercises.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-24
 */

public class AccessExercisesTest extends TestCase {
    private InterfaceAccessExercises dataAccess;

    /**
     * Constructor for the AccessExercisesTest
     */
    public AccessExercisesTest(String arg0) {
        super(arg0);
    }

    /**
     * Initializes the AccessExercises to be used in the test
     */
    @Before
    public void setUp() {
        dataAccess = new AccessExercises(new TemplateExerciseDataAccess());
    }

    /**
     * Tests that getting the list of exercises works properly
     */
    @Test
    public void testGetExercisesList() {
        System.out.println("\nStarting testGetExercisesList");

        // Exercises by object already in list
        List<Exercise> exerciseList1 = new ArrayList<>();
        exerciseList1.add(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM));
        exerciseList1.add(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE));
        exerciseList1.add(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG));
        exerciseList1.add(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM));
        exerciseList1.add(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO));
        exerciseList1.add(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE));
        exerciseList1.add(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG));
        exerciseList1.add(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                ExerciseType.CARDIO));

        List<Exercise> exerciseList2 = dataAccess.getExercisesList();
        assertNotNull(exerciseList2);
        assertEquals(exerciseList1.toString(), exerciseList2.toString());

        System.out.println("Finishing testGetExercisesList\n");
    }
}

/**
 * A template data access class for use in testing
 */
class TemplateExerciseDataAccess implements InterfaceExerciseDataAccess {
    private Map<String, Exercise> exercisesByName;

    /**
     * Opens a data access class
     *
     * @param statement the statement to use in data access queries
     */
    @Override
    public void open(Statement statement) {
        Exercise exercise;

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
    }

    /**
     * Closes a data access class
     */
    @Override
    public void close() { }

    /**
     * Gets a list of all exercises in the database
     *
     * @return a list of all exercises in the database
     */
    @Override
    public List<Exercise> getExercisesList() {
        return new ArrayList<>(exercisesByName.values());
    }
}