package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;

/**
 * WorkoutExerciseDurationTest.java used to test WorkoutExerciseDuration.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-07
 */
public class WorkoutExerciseDurationTest extends TestCase {
    WorkoutExerciseDuration workoutExerciseDuration1, workoutExerciseDuration2, workoutExerciseDuration3;
    Exercise exercise1;

    /**
     * Initializes several instances of WorkoutExerciseDuration to set up testing
     */
    @Before
    public void setUp(){
        workoutExerciseDuration1 = new WorkoutExerciseDuration("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(5, 4));
        workoutExerciseDuration2 = new WorkoutExerciseDuration("Deadlifts", ExerciseIntensity.MEDIUM, ExerciseType.LEG, new ExerciseSets(5, 4));
        exercise1 = new Exercise("Power Cleans", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, true);
        workoutExerciseDuration3 = new WorkoutExerciseDuration(exercise1, new ExerciseSets(5, 4));
    }

    /**
     * Tests various functionality of the WorkoutExerciseDuration class, including proper return value
     * of getter methods, proper updating of recommended quantity, and proper equals checking
     */
    @Test
    public void testWorkoutExerciseTest() {
        System.out.println("\nStarting testWorkoutExerciseTest");

        assertNotNull(workoutExerciseDuration1);
        assertNotNull(workoutExerciseDuration2);
        assertNotNull(workoutExerciseDuration3);

        assertEquals("Power Cleans", workoutExerciseDuration1.getName());
        assertEquals("Deadlifts", workoutExerciseDuration2.getName());
        assertEquals("Power Cleans", workoutExerciseDuration3.getName());

        assertEquals(ExerciseIntensity.HIGH, workoutExerciseDuration1.getIntensity());
        assertEquals(ExerciseIntensity.MEDIUM, workoutExerciseDuration2.getIntensity());
        assertEquals(ExerciseIntensity.HIGH, workoutExerciseDuration3.getIntensity());

        assertEquals(ExerciseType.FULL_BODY, workoutExerciseDuration1.getType());
        assertEquals(ExerciseType.LEG, workoutExerciseDuration2.getType());
        assertEquals(ExerciseType.FULL_BODY, workoutExerciseDuration3.getType());

        assertTrue(workoutExerciseDuration1.getRecommendedDuration().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutExerciseDuration2.getRecommendedDuration().equals(new ExerciseSets(5, 4)));
        assertTrue(workoutExerciseDuration3.getRecommendedDuration().equals(new ExerciseSets(5, 4)));

        assertFalse(workoutExerciseDuration1.getRecommendedDuration().equals(new ExerciseSets(3, 10)));
        assertFalse(workoutExerciseDuration2.getRecommendedDuration().equals(new ExerciseSets(4, 5)));
        assertFalse(workoutExerciseDuration3.getRecommendedDuration().equals(new ExerciseSets(5, 6)));

        workoutExerciseDuration1.updateRecommendedDuration(new ExerciseSets(3, 10));
        workoutExerciseDuration2.updateRecommendedDuration(new ExerciseSets(3, 10));
        workoutExerciseDuration3.updateRecommendedDuration(new ExerciseSets(3, 10));

        assertTrue(workoutExerciseDuration1.getRecommendedDuration().equals(new ExerciseSets(3, 10)));
        assertTrue(workoutExerciseDuration2.getRecommendedDuration().equals(new ExerciseSets(3, 10)));
        assertTrue(workoutExerciseDuration3.getRecommendedDuration().equals(new ExerciseSets(3, 10)));

        assertFalse(workoutExerciseDuration1.getRecommendedDuration().equals(new ExerciseSets(5, 4)));
        assertFalse(workoutExerciseDuration2.getRecommendedDuration().equals(new ExerciseSets(5, 4)));
        assertFalse(workoutExerciseDuration3.getRecommendedDuration().equals(new ExerciseSets(5, 4)));

        assertFalse(workoutExerciseDuration1.equals(workoutExerciseDuration2));
        assertFalse(workoutExerciseDuration2.equals(workoutExerciseDuration1));

        assertTrue(workoutExerciseDuration1.equals(workoutExerciseDuration3));
        assertTrue(workoutExerciseDuration3.equals(workoutExerciseDuration1));

        System.out.println("Finished testWorkoutExerciseTest");
    }
}
