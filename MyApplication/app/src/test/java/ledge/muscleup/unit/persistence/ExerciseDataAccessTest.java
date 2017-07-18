package ledge.muscleup.unit.persistence;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import ledge.muscleup.application.*;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.persistence.InterfaceExerciseDataAccess;

/**
 * Used for testing the InterfaceExerciseDataAccess persistence interface
 *
 * @author: Ryan Koop
 * @version: 1.0
 * @since 2017-06-29
 *
 */

public class ExerciseDataAccessTest extends TestCase {
    static InterfaceExerciseDataAccess dataAccess;

    /**
     * Constructor for the ExerciseDataAccessTest
     */
    public ExerciseDataAccessTest(String arg0) {
        super(arg0);
    }

    /**
     * Initializes the ExerciseDataAccess to be used in the test
     */
  /* @Before
    public void setUp() {
        dataAccess = new TemplateDataAccessStub("Test Exercise");
        dataAccess.open(null);
    }*/

    /**
     * Closes the DataAccess connection
     */
  /* @After
    public void tearDown() {
        dataAccess.close();
    }*/


    public void testExerciseDataAccess()
    {
        TemplateDataAccessStub dataAccess;

        Services.closeDataAccess();

        System.out.println("\nStarting Persistence test ExerciseDataAccess (using stub)");

        // Use the following statement to run with the stub database
        dataAccess = new TemplateDataAccessStub("Test Exercise");
        dataAccess.open("Test Exercise");

        testGetExercisesList();

        dataAccess.close();
        System.out.println("Finished Persistence test DataAccess (using stub)");
    }


    /**
     * Tests that getting the list of exercises works properly
     */
    @Test
    public static void testGetExercisesList() {
        System.out.println("\nStarting testGetExercisesList");

        InterfaceExerciseDataAccess dataAccess;
        dataAccess = Services.getExerciseDataAccess();

        // Exercises by object already in list
        List<Exercise> exerciseList1 = new ArrayList<>();
        exerciseList1.add(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM));
        exerciseList1.add(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM));
        exerciseList1.add(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO));
        exerciseList1.add(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                ExerciseType.CARDIO));
        exerciseList1.add(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE));
        exerciseList1.add(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE));
        exerciseList1.add(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG));
        exerciseList1.add(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG));

        List<Exercise> exerciseList2 = dataAccess.getExercisesList();
        assertNotNull(exerciseList2);
        assertEquals(exerciseList1.toString(), exerciseList2.toString());

        Services.closeDataAccess();
        System.out.println("Finishing testGetExercisesList\n");
    }
}

