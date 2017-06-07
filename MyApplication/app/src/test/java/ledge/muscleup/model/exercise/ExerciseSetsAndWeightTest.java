package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * ExerciseSetsAndWeightTest.java used to test ExerciseSetsAndWeight.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-06
 */

public class ExerciseSetsAndWeightTest extends TestCase {
    private ExerciseSetsAndWeight exerciseSetsAndWeight;

    @Before
    public void setUp(){
        exerciseSetsAndWeight = new ExerciseSetsAndWeight(3, 10, 30.0, WeightUnit.LBS);
    }

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
