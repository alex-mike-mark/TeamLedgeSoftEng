package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * WorkoutSessionExerciseTest.java used to test WorkoutSessionExercise.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-07
 */
public class WorkoutSessionExerciseTest extends TestCase {
    WorkoutSessionExercise workoutSessionExercise1, workoutSessionExercise2, workoutSessionExercise3;

    WorkoutExerciseSets powerClean1, powerClean2, deadlift;

    final int xpHighIntensity = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    final int xpMediumIntensity = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    final int xpLowIntensity = (ExerciseIntensity.LOW.ordinal() + 1) * 15;


    /**
     * Initializes several instances of WorkoutSessionExercise to setup testing
     */
    @Before
    public void setUp(){
        powerClean1 = new WorkoutExerciseSets(new Exercise("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY), xpHighIntensity,new ExerciseSets(5,4));
        powerClean2 = new WorkoutExerciseSets(new Exercise("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY), xpHighIntensity, new ExerciseSets(5,4));
        deadlift = new WorkoutExerciseSets(new Exercise("Deadlifts", ExerciseIntensity.MEDIUM, ExerciseType.LEG), xpMediumIntensity,new ExerciseSets(5,4));

        workoutSessionExercise1 = new WorkoutSessionExercise(powerClean1, false);
        workoutSessionExercise2 = new WorkoutSessionExercise(deadlift, false);
        workoutSessionExercise3 = new WorkoutSessionExercise(powerClean2, true);
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

        assertEquals(ExerciseIntensity.HIGH, workoutSessionExercise1.getIntensity());
        assertEquals(ExerciseIntensity.MEDIUM, workoutSessionExercise2.getIntensity());
        assertEquals(ExerciseIntensity.HIGH, workoutSessionExercise3.getIntensity());

        assertEquals(ExerciseType.FULL_BODY, workoutSessionExercise1.getType());
        assertEquals(ExerciseType.LEG, workoutSessionExercise2.getType());//TODO: Make  this not fail.
        assertEquals(ExerciseType.FULL_BODY, workoutSessionExercise3.getType());

        assertTrue(workoutSessionExercise1.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutSessionExercise2.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutSessionExercise3.getRecommendedQuantity().equals(new ExerciseSets(5, 4)));

        assertFalse(workoutSessionExercise1.getRecommendedQuantity().equals(new ExerciseSets(3, 10)));
        assertFalse(workoutSessionExercise2.getRecommendedQuantity().equals(new ExerciseSets(4, 5)));
        assertFalse(workoutSessionExercise3.getRecommendedQuantity().equals(new ExerciseSets(5, 6)));

        assertFalse(workoutSessionExercise1.isComplete());
        assertFalse(workoutSessionExercise2.isComplete());
        assertTrue(workoutSessionExercise3.isComplete());

        workoutSessionExercise1.toggleCompleted();
        workoutSessionExercise2.toggleCompleted();
        workoutSessionExercise3.toggleCompleted();

        assertTrue(workoutSessionExercise1.isComplete());
        assertTrue(workoutSessionExercise2.isComplete());
        assertFalse(workoutSessionExercise3.isComplete());

        assertFalse(workoutSessionExercise1.equals(workoutSessionExercise2));
        assertFalse(workoutSessionExercise2.equals(workoutSessionExercise1));

        assertFalse(workoutSessionExercise1.equals(workoutSessionExercise3));
        assertFalse(workoutSessionExercise3.equals(workoutSessionExercise1));

        workoutSessionExercise3.toggleCompleted();

        assertTrue(workoutSessionExercise1.equals(workoutSessionExercise3));
        assertTrue(workoutSessionExercise3.equals(workoutSessionExercise1));

        assertEquals(xpHighIntensity, workoutSessionExercise1.getExperienceValue());
        assertEquals(xpMediumIntensity, workoutSessionExercise2.getExperienceValue());
        assertEquals(xpHighIntensity, workoutSessionExercise3.getExperienceValue());

        System.out.println("Finished testWorkoutSessionExerciseTest");
    }
}
