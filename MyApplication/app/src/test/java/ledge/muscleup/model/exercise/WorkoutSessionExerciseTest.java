package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * WorkoutSessionExerciseTest.java used to test WorkoutSessionExercise.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-07
 */
public class WorkoutSessionExerciseTest extends TestCase {
    WorkoutSessionExercise workoutSessionExercise1, workoutSessionExercise2, workoutSessionExercise3, workoutSessionExercise4;
    WorkoutExercise workoutExercise1, workoutExercise2;

    /**
     * Initializes several instances of WorkoutSessionExercise to setup testing
     */
    @Before
    public void setUp(){
        workoutSessionExercise1 = new WorkoutSessionExercise("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(5, 4), false);
        workoutSessionExercise2 = new WorkoutSessionExercise("Deadlifts", ExerciseIntensity.MEDIUM, ExerciseType.LEG, new ExerciseSets(5, 4), false);
        workoutExercise1 = new WorkoutExercise("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(5, 4));
        workoutSessionExercise3 = new WorkoutSessionExercise(workoutExercise1, true);
        workoutExercise2 = new WorkoutExercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM, new ExerciseSetsAndWeight(2, 10, 15, WeightUnit.LBS));
        workoutSessionExercise4 = new WorkoutSessionExercise(workoutExercise2, true);
    }

    /**
     * Tests various functionality of the WorkoutSessionExercise class, including proper return
     * values of getter methods, proper implementation of toggleCompleted(), and proper equals
     * checking
     */
    @Test
    public void testWorkoutSessionExerciseTest() {
        System.out.println("\nStarting testWorkoutSessionExerciseTest");

        assertNotNull(workoutSessionExercise1);
        assertNotNull(workoutSessionExercise2);
        assertNotNull(workoutSessionExercise3);


        assertEquals("Power Cleans", workoutSessionExercise1.getName());
        assertEquals("Deadlifts", workoutSessionExercise2.getName());
        assertEquals("Power Cleans", workoutSessionExercise3.getName());
        assertEquals("Bicep Curls", workoutSessionExercise4.getName());

        assertEquals(ExerciseIntensity.HIGH, workoutSessionExercise1.getIntensity());
        assertEquals(ExerciseIntensity.MEDIUM, workoutSessionExercise2.getIntensity());
        assertEquals(ExerciseIntensity.HIGH, workoutSessionExercise3.getIntensity());
        assertEquals(ExerciseIntensity.LOW, workoutSessionExercise4.getIntensity());

        assertEquals(ExerciseType.FULL_BODY, workoutSessionExercise1.getType());
        assertEquals(ExerciseType.LEG, workoutSessionExercise2.getType());
        assertEquals(ExerciseType.FULL_BODY, workoutSessionExercise3.getType());
        assertEquals(ExerciseType.ARM, workoutSessionExercise4.getType());

        assertTrue(workoutSessionExercise1.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutSessionExercise2.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutSessionExercise3.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutSessionExercise4.getRecommendedQuantity().equals(new ExerciseSetsAndWeight(2, 10, 15, WeightUnit.LBS)));

        assertFalse(workoutSessionExercise1.getRecommendedQuantity().equals(new ExerciseSets(3, 10)));
        assertFalse(workoutSessionExercise2.getRecommendedQuantity().equals(new ExerciseSets(4, 5)));
        assertFalse(workoutSessionExercise3.getRecommendedQuantity().equals(new ExerciseSets(5, 6)));
        assertFalse(workoutSessionExercise4.getRecommendedQuantity().equals(new ExerciseSetsAndWeight(2, 10, 15, WeightUnit.KG)));
        assertFalse(workoutSessionExercise4.getRecommendedQuantity().equals(new ExerciseSetsAndWeight(3, 10, 12, WeightUnit.LBS)));

        assertFalse(workoutSessionExercise1.isComplete());
        assertFalse(workoutSessionExercise2.isComplete());
        assertTrue(workoutSessionExercise3.isComplete());
        assertTrue(workoutSessionExercise4.isComplete());

        workoutSessionExercise1.toggleCompleted();
        workoutSessionExercise2.toggleCompleted();
        workoutSessionExercise3.toggleCompleted();
        workoutSessionExercise4.toggleCompleted();

        assertTrue(workoutSessionExercise1.isComplete());
        assertTrue(workoutSessionExercise2.isComplete());
        assertFalse(workoutSessionExercise3.isComplete());
        assertFalse(workoutSessionExercise4.isComplete());

        assertFalse(workoutSessionExercise1.equals(workoutSessionExercise2));
        assertFalse(workoutSessionExercise2.equals(workoutSessionExercise1));

        assertFalse(workoutSessionExercise1.equals(workoutSessionExercise3));
        assertFalse(workoutSessionExercise3.equals(workoutSessionExercise1));

        workoutSessionExercise3.toggleCompleted();

        assertTrue(workoutSessionExercise1.equals(workoutSessionExercise3));
        assertTrue(workoutSessionExercise3.equals(workoutSessionExercise1));

        final int xpLowIntensity = 15;
        final int xpMediumIntensity = 30;
        final int xpHighIntensity = 45;

        assertEquals(workoutSessionExercise1.getExperienceValue(), xpHighIntensity);
        assertEquals(workoutSessionExercise2.getExperienceValue(), xpMediumIntensity);
        assertEquals(workoutSessionExercise3.getExperienceValue(), xpHighIntensity);
        assertEquals(workoutSessionExercise4.getExperienceValue(), xpLowIntensity);

        System.out.println("Finished testWorkoutSessionExerciseTest");
    }
}
