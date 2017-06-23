package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * ExerciseDurationTest.java used to test ExerciseDuration.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-06
 */
public class ExerciseDurationTest extends TestCase {
    private ExerciseDuration exerciseDuration;

    /**
     * Initializes a new instance of ExerciseDuration as setup for testing
     */
    @Before
    public void setUp(){
        exerciseDuration = new ExerciseDuration(30, TimeUnit.MINUTES);
    }

    /**
     * Tests the initialization of an ExerciseDuration instance
     */
    @Test
    public void testInvalidInitialization() {
        System.out.println("\nStarting testInvalidInitialization");

        try {
            exerciseDuration = new ExerciseDuration(-1, TimeUnit.MINUTES);
            fail("Expected the illegal argument.");
        } catch (IllegalArgumentException e) {
        }

        System.out.println("Finishing testInvalidInitialization");
    }

    /**
     * Tests the various functionality of the ExerciseDuration class, including proper creation,
     * correct return of getTime(), and correct equal checking
     */
    @Test
    public void testExerciseDuration() {
        System.out.println("\nStarting testExerciseDuration");

        assertNotNull(exerciseDuration);

        assertEquals(30, exerciseDuration.getTime());
        assertEquals(true, exerciseDuration.equals(new ExerciseDuration(30, TimeUnit.MINUTES)));
        assertEquals(false, exerciseDuration.equals(new ExerciseDuration(45, TimeUnit.MINUTES)));

        System.out.println("Finished testExerciseDuration");
    }
}
