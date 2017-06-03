package ledge.muscleup.model;

/**
 * Created by koope on 2017-05-29.
 */

import junit.framework.TestCase;

import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.ExerciseQuantity;
import ledge.muscleup.model.exercise.ExerciseSetsAndReps;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;

public class ExerciseQuantityTest  extends TestCase{

    public ExerciseQuantityTest(String arg0) {
        super(arg0);
    }

    public void testExerciseQuantity() {
        InterfaceExerciseQuantity quantity1, quantity2, quantity3, quantity4;

        System.out.println ("Starting testExerciseQuantity");

        quantity1 = new ExerciseSetsAndReps(3, 10);
        assertNotNull(quantity1);

        quantity2 = new ExerciseDistance(2.5, "Miles");
        assertNotNull(quantity2);

        quantity3 = new ExerciseDuration(45);
        assertNotNull(quantity3);

        assertFalse(quantity1.equals(quantity2));
        assertFalse(quantity1.equals(quantity3));
        assertFalse(quantity2.equals(quantity3));

        quantity4 = new ExerciseSetsAndReps(2, 15);
        assertNotNull(quantity4);
        assertFalse(quantity1.equals(quantity4));
        quantity4 = new ExerciseSetsAndReps(3, 10);
        assertNotNull(quantity4);
        assertTrue(quantity1.equals(quantity4));

        quantity4 = new ExerciseDistance(1, "Km");
        assertNotNull(quantity4);
        assertFalse(quantity2.equals(quantity4));
        quantity4 = new ExerciseDistance(2.5, "Miles");
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
