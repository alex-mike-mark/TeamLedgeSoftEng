package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * WorkoutExerciseTest.java used to test WorkoutExercise.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-07
 */
public class WorkoutExerciseTest extends TestCase {
    private WorkoutExercise workoutExercise1, workoutExercise2, workoutExercise3;
    private Exercise exercise1;

    /**
     * Initializes several instances of WorkoutExercise to set up testing
     */
    @Before
    public void setUp(){
        workoutExercise1 = new WorkoutExercise("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(5, 4));
        workoutExercise2 = new WorkoutExercise("Deadlifts", ExerciseIntensity.MEDIUM, ExerciseType.LEG, new ExerciseSets(5, 4));
        exercise1 = new Exercise("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, true);
        workoutExercise3 = new WorkoutExercise(exercise1, new ExerciseSets(5, 4));
    }

    /**
     * Tests various functionality of the WorkoutExercise class, including proper return value
     * of getter methods, proper updating of recommended quantity, and proper equals checking
     */
    @Test
    public void testWorkoutExerciseTest() {
        System.out.println("\nStarting testWorkoutExerciseTest");

        assertNotNull(workoutExercise1);
        assertNotNull(workoutExercise2);
        assertNotNull(workoutExercise3);

        assertEquals("Power Cleans", workoutExercise1.getName());
        assertEquals("Deadlifts", workoutExercise2.getName());
        assertEquals("Power Cleans", workoutExercise3.getName());

        assertEquals(ExerciseIntensity.HIGH, workoutExercise1.getIntensity());
        assertEquals(ExerciseIntensity.MEDIUM, workoutExercise2.getIntensity());
        assertEquals(ExerciseIntensity.HIGH, workoutExercise3.getIntensity());

        assertEquals(ExerciseType.FULL_BODY, workoutExercise1.getType());
        assertEquals(ExerciseType.LEG, workoutExercise2.getType());
        assertEquals(ExerciseType.FULL_BODY, workoutExercise3.getType());

        assertTrue(workoutExercise1.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutExercise2.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutExercise3.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));

        assertFalse(workoutExercise1.getRecommendedQuantity().equals(new ExerciseSets(3, 10)));
        assertFalse(workoutExercise2.getRecommendedQuantity().equals(new ExerciseSets(4, 5)));
        assertFalse(workoutExercise3.getRecommendedQuantity().equals(new ExerciseSets(5, 6)));

        workoutExercise1.updateRecommendedQuantity(new ExerciseSets(3, 10));
        workoutExercise2.updateRecommendedQuantity(new ExerciseSets(3, 10));
        workoutExercise3.updateRecommendedQuantity(new ExerciseSets(3, 10));

        assertTrue(workoutExercise1.getRecommendedQuantity().equals(new ExerciseSets(3, 10)));
        assertTrue(workoutExercise2.getRecommendedQuantity().equals(new ExerciseSets(3, 10)));
        assertTrue(workoutExercise3.getRecommendedQuantity().equals(new ExerciseSets(3, 10)));

        assertFalse(workoutExercise1.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));
        assertFalse(workoutExercise2.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));
        assertFalse(workoutExercise3.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));

        assertFalse(workoutExercise1.equals(workoutExercise2));
        assertFalse(workoutExercise2.equals(workoutExercise1));

        assertTrue(workoutExercise1.equals(workoutExercise3));
        assertTrue(workoutExercise3.equals(workoutExercise1));

        System.out.println("Finished testWorkoutExerciseTest");
    }
}
