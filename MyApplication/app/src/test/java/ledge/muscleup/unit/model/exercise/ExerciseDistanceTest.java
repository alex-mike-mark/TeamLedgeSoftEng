package ledge.muscleup.unit.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.enums.DistanceUnit;

/**
 * ExerciseDistanceTest.java used to test ExerciseDistance.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-06
 */
public class ExerciseDistanceTest extends TestCase {
    private ExerciseDistance exerciseDistance;

    /**
     * Creates a new instance of ExerciseDistance as setup for testing
     */
    @Before
    public void setUp(){
        exerciseDistance = new ExerciseDistance(5.0, DistanceUnit.MILES) ;
    }

    /**
     * Tests the initialization of an ExerciseDistance
     */
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

    /**
     * Tests that implementing classes of InterfaceExerciseQuantity are equal if they are of the same
     * implementing class and have same values/parameters, and not equal otherwise
     */
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
