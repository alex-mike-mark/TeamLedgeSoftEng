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

    @Before
    public void setUp(){
        exerciseDuration = new ExerciseDuration(30.0);
    }

    @Test
    public void testInvalidInitialization() {
        System.out.println("\nStarting testInvalidInitialization");

        try {
            exerciseDuration = new ExerciseDuration(-1);
            fail("Expected the illegal argument.");
        } catch (IllegalArgumentException e) {
        }

        System.out.println("Finishing testInvalidInitialization");
    }

    @Test
    public void testExerciseDuration() {
        System.out.println("\nStarting testExerciseDuration");

        assertNotNull(exerciseDuration);

        assertEquals(30.0, exerciseDuration.getMinutes());
        assertEquals(true, exerciseDuration.equals(new ExerciseDuration(30.0)));
        assertEquals(false, exerciseDuration.equals(new ExerciseDuration(45.0)));

        System.out.println("Finished testExerciseDuration");
    }
}
