package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * ExerciseDistanceTest.java used to test ExerciseDistance.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-06
 */

public class ExerciseDistanceTest extends TestCase {
    private ExerciseDistance exerciseDistance;

    @Before
    public void setUp(){
        exerciseDistance = new ExerciseDistance(5.0, DistanceUnit.MILES) ;
    }

    @Test
    public void testInvalidInitialization() {
        System.out.println("\nStarting testInvalidInitialization");

        try {
            exerciseDistance = new ExerciseDistance(-1.0, DistanceUnit.KILOMETERS);
            fail("Expected the illegal argument.");
        } catch (IllegalArgumentException e) {
        }
        try {
            exerciseDistance = new ExerciseDistance(5.0, null);
            fail("Expected the illegal argument.");
        } catch (IllegalArgumentException e) {
        }

        System.out.println("Finishing testInvalidInitialization");
    }

    @Test
    public void testExerciseDistance() {
        System.out.println("\nStarting testExerciseDistance");

        assertNotNull(exerciseDistance);

        assertEquals(5.0, exerciseDistance.getDistance());
        assertEquals(DistanceUnit.MILES, exerciseDistance.getUnitOfMeasure());
        assertEquals(true, exerciseDistance.equals(new ExerciseDistance(5.0, DistanceUnit.MILES)));
        assertEquals(false, exerciseDistance.equals(new ExerciseDistance(4.0, DistanceUnit.MILES)));
        assertEquals(false, exerciseDistance.equals(new ExerciseDistance(5.0, DistanceUnit.KILOMETERS)));

        System.out.println("Finished testExerciseDistance");
    }
}
