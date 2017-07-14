package ledge.muscleup.unit.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.enums.*;

/**
 * WorkoutExerciseTest.java used to test WorkoutExercise.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-07
 */
public class WorkoutExerciseTest extends TestCase {
    WorkoutExercise workoutExercise1, workoutExercise2, workoutExercise3, workoutExercise4;
    Exercise exercise1, exercise2;

    final int xpHighIntensity = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    final int xpMediumIntensity = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    final int xpLowIntensity = (ExerciseIntensity.LOW.ordinal() + 1) * 15;
    /**
     * Initializes several instances of WorkoutExercise to set up testing
     */
    @Before
    public void setUp(){

        workoutExercise1 = new WorkoutExerciseSets(new Exercise("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY), xpHighIntensity,new ExerciseSets(5, 4));
        workoutExercise2 = new WorkoutExerciseSets(new Exercise("Deadlifts", ExerciseIntensity.MEDIUM, ExerciseType.LEG), xpMediumIntensity, new ExerciseSets(5, 4));
        exercise1 = new Exercise("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY);
        workoutExercise3 = new WorkoutExerciseSets(exercise1, xpHighIntensity, new ExerciseSets(5, 4));
        exercise2 = new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM);
        workoutExercise4 = new WorkoutExerciseSetsAndWeight(exercise2, xpLowIntensity, new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS));
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
        assertNotNull(workoutExercise4);

        assertEquals("Power Cleans", workoutExercise1.getName());
        assertEquals("Deadlifts", workoutExercise2.getName());
        assertEquals("Power Cleans", workoutExercise3.getName());
        assertEquals("Bicep Curls", workoutExercise4.getName());

        assertEquals(ExerciseIntensity.HIGH, workoutExercise1.getIntensity());
        assertEquals(ExerciseIntensity.MEDIUM, workoutExercise2.getIntensity());
        assertEquals(ExerciseIntensity.HIGH, workoutExercise3.getIntensity());
        assertEquals(ExerciseIntensity.LOW, workoutExercise4.getIntensity());

        assertEquals(ExerciseType.FULL_BODY, workoutExercise1.getType());
        assertEquals(ExerciseType.LEG, workoutExercise2.getType());
        assertEquals(ExerciseType.FULL_BODY, workoutExercise3.getType());
        assertEquals(ExerciseType.ARM, workoutExercise4.getType());

        assertTrue(workoutExercise1.getQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutExercise2.getQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutExercise3.getQuantity().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutExercise4.getQuantity().equals(new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS)));

        assertFalse(workoutExercise1.getQuantity().equals(new ExerciseSets(3, 10)));
        assertFalse(workoutExercise2.getQuantity().equals(new ExerciseSets(4, 5)));
        assertFalse(workoutExercise3.getQuantity().equals(new ExerciseSets(5, 6)));
        assertFalse(workoutExercise4.getQuantity().equals(new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.KG)));
        assertFalse(workoutExercise4.getQuantity().equals(new ExerciseSetsAndWeight(2, 15, 10, WeightUnit.LBS)));

        workoutExercise1.updateQuantity(new ExerciseSets(3, 10));
        workoutExercise2.updateQuantity(new ExerciseSets(3, 10));
        workoutExercise3.updateQuantity(new ExerciseSets(3, 10));
        workoutExercise4.updateQuantity(new ExerciseSetsAndWeight(2, 10, 20, WeightUnit.LBS));

        assertTrue(workoutExercise1.getQuantity().equals(new ExerciseSets(3, 10)));
        assertTrue(workoutExercise2.getQuantity().equals(new ExerciseSets(3, 10)));
        assertTrue(workoutExercise3.getQuantity().equals(new ExerciseSets(3, 10)));
        assertTrue(workoutExercise4.getQuantity().equals(new ExerciseSetsAndWeight(2, 10, 20, WeightUnit.LBS)));

        assertFalse(workoutExercise1.getQuantity().equals(new ExerciseSets(5, 4)));
        assertFalse(workoutExercise2.getQuantity().equals(new ExerciseSets(5, 4)));
        assertFalse(workoutExercise3.getQuantity().equals(new ExerciseSets(5, 4)));
        assertFalse(workoutExercise4.getQuantity().equals(new ExerciseSetsAndWeight(3, 15, 15, WeightUnit.LBS)));

        assertFalse(workoutExercise1.equals(workoutExercise2));
        assertFalse(workoutExercise2.equals(workoutExercise1));
        assertFalse(workoutExercise1.equals(workoutExercise4));
        assertFalse(workoutExercise4.equals(workoutExercise1));

        assertTrue(workoutExercise1.equals(workoutExercise3));
        assertTrue(workoutExercise3.equals(workoutExercise1));

        assertEquals(xpHighIntensity, workoutExercise1.getExperienceValue());
        assertEquals(xpMediumIntensity, workoutExercise2.getExperienceValue());
        assertEquals(xpHighIntensity, workoutExercise3.getExperienceValue());
        assertEquals(xpLowIntensity, workoutExercise4.getExperienceValue());

        System.out.println("Finished testWorkoutExerciseTest");
    }
}
