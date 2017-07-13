package ledge.muscleup.model.unit.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * ExerciseTest.java used to test Exercise.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-07
 */
public class ExerciseTest extends TestCase {
    Exercise exercise1, exercise2, exercise3;

    /**
     * Initializes several instances of Exercise to setup testing
     */
    @Before
    public void setUp(){
        exercise1 = new Exercise("Bicep Curls ;)", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY);
        exercise2 = new Exercise("Tricep Extensions", ExerciseIntensity.LOW, ExerciseType.ARM);
        exercise3 = new Exercise("Bicep Curls ;)", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY);
    }

    /**
     * Tests various functionality of the Exercise class, including proper return values of
     * getter methods, and equals checking
     */
    @Test
    public void testExercise() {
        System.out.println("\nStarting testExercise");

        assertNotNull(exercise1);
        assertNotNull(exercise2);
        assertNotNull(exercise3);

        assertEquals("Bicep Curls ;)", exercise1.getName());
        assertEquals("Tricep Extensions", exercise2.getName());

        assertEquals(ExerciseIntensity.HIGH, exercise1.getIntensity());
        assertEquals(ExerciseIntensity.LOW, exercise2.getIntensity());

        assertEquals(ExerciseType.FULL_BODY, exercise1.getType());
        assertEquals(ExerciseType.ARM, exercise2.getType());

        assertFalse(exercise1.equals(exercise2));
        assertFalse(exercise2.equals(exercise1));

        assertTrue(exercise1.equals(exercise3));
        assertTrue(exercise3.equals(exercise1));

        System.out.println("Finished testExercise");
    }
}
