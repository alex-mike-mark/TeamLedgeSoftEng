package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.exercise.enums.TimeUnit;

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
        workoutExerciseDuration1 = new WorkoutExerciseDuration(new Exercise("Run From Your Past", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY), new ExerciseDuration(692040, TimeUnit.HOURS));
        workoutExerciseDuration2 = new WorkoutExerciseDuration(new Exercise("Swim With The Fishes", ExerciseIntensity.MEDIUM, ExerciseType.LEG), new ExerciseDuration(5, TimeUnit.HOURS));
        exercise1 = new Exercise("Cycle Over The Same Path", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, true);
        workoutExerciseDuration3 = new WorkoutExerciseDuration(exercise1, new ExerciseDuration(2, TimeUnit.HOURS));
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

        assertEquals("Rin From Your Past", workoutExerciseDuration1.getName());
        assertEquals("Swim With The Fishes", workoutExerciseDuration2.getName());
        assertEquals("Cycle Over The Same Path", workoutExerciseDuration3.getName());

        assertEquals(ExerciseIntensity.HIGH, workoutExerciseDuration1.getIntensity());
        assertEquals(ExerciseIntensity.MEDIUM, workoutExerciseDuration2.getIntensity());
        assertEquals(ExerciseIntensity.HIGH, workoutExerciseDuration3.getIntensity());

        assertEquals(ExerciseType.FULL_BODY, workoutExerciseDuration1.getType());
        assertEquals(ExerciseType.LEG, workoutExerciseDuration2.getType());
        assertEquals(ExerciseType.FULL_BODY, workoutExerciseDuration3.getType());

        assertTrue(workoutExerciseDuration1.getQuantity().equals(new ExerciseDuration(692040, TimeUnit.HOURS)));
        assertTrue(workoutExerciseDuration2.getQuantity().equals(new ExerciseDuration(5, TimeUnit.HOURS)));
        assertTrue(workoutExerciseDuration3.getQuantity().equals(new ExerciseDuration(2, TimeUnit.HOURS)));

        assertFalse(workoutExerciseDuration1.getQuantity().equals(new ExerciseDuration(692045, TimeUnit.HOURS)));
        assertFalse(workoutExerciseDuration2.getQuantity().equals(new ExerciseDuration(5, TimeUnit.SECONDS)));
        assertFalse(workoutExerciseDuration3.getQuantity().equals(new ExerciseSets(5, 6)));

        workoutExerciseDuration1.updateQuantity(new ExerciseDuration(5, TimeUnit.SECONDS));
        workoutExerciseDuration2.updateQuantity(new ExerciseDuration(5, TimeUnit.SECONDS));
        workoutExerciseDuration3.updateQuantity(new ExerciseDuration(5, TimeUnit.SECONDS));

        assertTrue(workoutExerciseDuration1.getQuantity().equals(new ExerciseDuration(5, TimeUnit.SECONDS)));
        assertTrue(workoutExerciseDuration2.getQuantity().equals(new ExerciseDuration(5, TimeUnit.SECONDS)));
        assertTrue(workoutExerciseDuration3.getQuantity().equals(new ExerciseDuration(5, TimeUnit.SECONDS)));

        assertFalse(workoutExerciseDuration1.getQuantity().equals(new ExerciseDuration(692040, TimeUnit.HOURS)));
        assertFalse(workoutExerciseDuration2.getQuantity().equals(new ExerciseDuration(5, TimeUnit.HOURS)));
        assertFalse(workoutExerciseDuration3.getQuantity().equals(new ExerciseDuration(2, TimeUnit.HOURS)));

        assertFalse(workoutExerciseDuration1.equals(workoutExerciseDuration2));
        assertFalse(workoutExerciseDuration2.equals(workoutExerciseDuration1));

        assertTrue(workoutExerciseDuration1.equals(workoutExerciseDuration3));
        assertTrue(workoutExerciseDuration3.equals(workoutExerciseDuration1));

        System.out.println("Finished testWorkoutExerciseTest");
    }
}
