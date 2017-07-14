package ledge.muscleup.unit.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.enums.WeightUnit;

/**
 * ExerciseSetsAndWeightTest.java used to test ExerciseSetsAndWeight.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-06
 */

public class ExerciseSetsAndWeightTest extends TestCase {
    private ExerciseSetsAndWeight exerciseSetsAndWeight;

    /**
     * Initializes an instance of ExerciseSetsAndWeight to setup testing
     */
    @Before
    public void setUp(){
        exerciseSetsAndWeight = new ExerciseSetsAndWeight(3, 10, 30.0, WeightUnit.LBS);
    }

    /**
     * Tests the initialization of an ExerciseSetsAndWeight instance
     */
    @Test
    public void testInvalidInitialization() {
        System.out.println("\nStarting testInvalidInitialization");

        try {
            exerciseSetsAndWeight = new ExerciseSetsAndWeight(-1, 10, 30.0, WeightUnit.KG);
            fail("Expected the illegal argument.");
        } catch (IllegalArgumentException e) {
        }
        try {
            exerciseSetsAndWeight = new ExerciseSetsAndWeight(3, -1, 30.0, WeightUnit.KG);
            fail("Expected the illegal argument.");
        } catch (IllegalArgumentException e) {
        }
        try {
            exerciseSetsAndWeight = new ExerciseSetsAndWeight(3, 10, 30.0, null);
            fail("Expected the illegal argument.");
        } catch (IllegalArgumentException e) {
        }

        System.out.println("Finishing testInvalidInitialization");
    }

    /**
     * Tests various functionality of the ExerciseSetsAndWeight class, including proper return values
     * of getWeight() and getUnitOfMeasure(), as well as proper equal checking
     */
    @Test
    public void testExerciseSetsAndWeight() {
        System.out.println("\nStarting testExerciseSetsAndWeight");

        assertNotNull(exerciseSetsAndWeight);
        assertEquals(30.0, exerciseSetsAndWeight.getWeight());
        assertEquals(WeightUnit.LBS, exerciseSetsAndWeight.getUnitOfMeasure());

        assertEquals(true, exerciseSetsAndWeight.equals(new ExerciseSetsAndWeight(3, 10, 30.0, WeightUnit.LBS)));
        assertEquals(false, exerciseSetsAndWeight.equals(new ExerciseSetsAndWeight(1, 10, 30.0, WeightUnit.LBS)));
        assertEquals(false, exerciseSetsAndWeight.equals(new ExerciseSetsAndWeight(3, 15, 30.0, WeightUnit.LBS)));
        assertEquals(false, exerciseSetsAndWeight.equals(new ExerciseSetsAndWeight(3, 10, 40.0, WeightUnit.LBS)));

        System.out.println("Finished testExerciseSetsAndWeight");
    }
}
