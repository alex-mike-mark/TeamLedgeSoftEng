package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * ExerciseQuantityTest.java used to test InterfaceExerciseQuantity.java
 *
 * @author Ryan Koope
 * @version 1.0
 * @since 2017-05-29
 */

public class ExerciseQuantityTest  extends TestCase {

    InterfaceExerciseQuantity quantity1, quantity2, quantity3, quantity4;

    @Before
    public void setUp(){
        quantity1 = new ExerciseSets(3, 10);
        quantity2 = new ExerciseDistance(2.5, DistanceUnit.MILES);
        quantity3 = new ExerciseDuration(45);
        quantity4 = new ExerciseSets(2, 15);
    }

    @Test
    public void testExerciseQuantity() {

        System.out.println("\nStarting testExerciseQuantity");

        assertNotNull(quantity1);
        assertNotNull(quantity2);
        assertNotNull(quantity3);
        assertNotNull(quantity4);

        assertFalse(quantity1.equals(quantity2));
        assertFalse(quantity1.equals(quantity3));
        assertFalse(quantity2.equals(quantity3));
        assertFalse(quantity1.equals(quantity4));

        quantity4 = new ExerciseSets(3, 10);
        assertNotNull(quantity4);
        assertTrue(quantity1.equals(quantity4));

        quantity4 = new ExerciseDistance(1, DistanceUnit.KILOMETERS);
        assertNotNull(quantity4);
        assertFalse(quantity2.equals(quantity4));
        quantity4 = new ExerciseDistance(2.5, DistanceUnit.MILES);
        assertNotNull(quantity4);
        assertTrue(quantity2.equals(quantity4));

        quantity4 = new ExerciseDuration(15);
        assertNotNull(quantity4);
        assertFalse(quantity3.equals(quantity4));
        quantity4 = new ExerciseDuration(45);
        assertNotNull(quantity4);
        assertTrue(quantity3.equals(quantity4));

        System.out.println("Finished testExerciseQuantity");
    }
}
