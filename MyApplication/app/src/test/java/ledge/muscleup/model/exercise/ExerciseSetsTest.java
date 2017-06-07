package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * ExerciseSetsTest.java used to test ExerciseSets.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-06
 */

public class ExerciseSetsTest extends TestCase {
    private ExerciseSets exerciseSets;

    @Before
    public void setUp(){
        exerciseSets = new ExerciseSets(3, 10);
    }

    @Test
    public void testInvalidInitialization() {
        System.out.println("\nStarting testInvalidInitialization");

        try {
            exerciseSets = new ExerciseSets(-1, 10);
            fail("Expected the illegal argument.");
        } catch (IllegalArgumentException e) {
        }
        try {
            exerciseSets = new ExerciseSets(3, -1);
            fail("Expected the illegal argument.");
        } catch (IllegalArgumentException e) {
        }

        System.out.println("Finishing testInvalidInitialization");
    }

    @Test
    public void testExerciseSets() {
        System.out.println("\nStarting testExerciseSets");

        assertNotNull(exerciseSets);
        assertEquals(3, exerciseSets.getSets());
        assertEquals(10, exerciseSets.getReps());

        assertEquals(true, exerciseSets.equals(new ExerciseSets(3, 10)));
        assertEquals(false, exerciseSets.equals(new ExerciseSets(1, 10)));
        assertEquals(false, exerciseSets.equals(new ExerciseSets(3, 15)));

        System.out.println("Finished testExerciseSets");
    }
}
