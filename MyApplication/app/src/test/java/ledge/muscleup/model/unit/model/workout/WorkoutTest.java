package ledge.muscleup.model.unit.model.workout;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


import java.util.Enumeration;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.workout.Workout;

/**
 * WorkoutTest.java used to test Workout.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-07
 */
public class WorkoutTest extends TestCase {
    Workout workout1, workout2, workout3, workout4;
    WorkoutExercise[] exerciseList1, exerciseList2;
    Exercise crunches, russianTwists, sitUps;
    final int xpHighIntensity = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    final int xpMediumIntensity = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    final int xpLowIntensity = (ExerciseIntensity.LOW.ordinal() + 1) * 15;
    /**
     * Initializes several new instances of Workout to setup testing
     */
    @Before
    public void setUp(){
        workout1 = new Workout("Leg Day");
        crunches = new Exercise("Crunches", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY);
        russianTwists = new Exercise("Russian Twists", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY);
        sitUps = new Exercise("Sit-ups", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY);

        exerciseList1 = new WorkoutExerciseSets[]{
                new WorkoutExerciseSets(crunches, xpHighIntensity, new ExerciseSets(3, 20)),
                new WorkoutExerciseSets(russianTwists, xpHighIntensity, new ExerciseSets(3, 20)),
                new WorkoutExerciseSets(sitUps, xpHighIntensity,new ExerciseSets(3, 20))
        };
        workout2 = new Workout("Get Your 6 Pack Abs", true, exerciseList1);

        exerciseList2 = new WorkoutExerciseSets[]{
                new WorkoutExerciseSets(crunches, xpHighIntensity,new ExerciseSets(3, 20)),
                new WorkoutExerciseSets(russianTwists, xpHighIntensity,new ExerciseSets(3, 20)),
                new WorkoutExerciseSets(sitUps, xpHighIntensity,new ExerciseSets(3, 20))
        };
        workout3 = new Workout("Get Your 6 Pack Abs", true, exerciseList2);
        workout4 = new Workout("Nothing does in this.");
    }

    /**
     * Tests various functionality of the Workout class, including proper return values of getter
     * methods, proper functionality of setter methods, removing and moving exercises in a workout,
     * and proper equals checking
     */
    @Test
    public void testWorkout() {
        System.out.println("\nStarting testWorkout");

        assertNotNull(workout1);
        assertNotNull(workout2);
        assertNotNull(workout3);

        assertTrue(workout2.equals(workout3));
        assertTrue(workout3.equals(workout2));
        assertFalse(workout1.equals(workout2));
        assertFalse(workout1.equals(workout3));

        assertEquals("Leg Day", workout1.getName());
        assertEquals("Get Your 6 Pack Abs", workout2.getName());
        assertEquals("Get Your 6 Pack Abs", workout3.getName());

        workout1.setName("KILLER Leg Day");
        workout2.setName("Shred The Abs");
        workout3.setName("Shred The Abs");

        assertEquals("KILLER Leg Day", workout1.getName());
        assertEquals("Shred The Abs", workout2.getName());
        assertEquals("Shred The Abs", workout3.getName());

        WorkoutExerciseSets workoutExerciseDuration1 = new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY), xpHighIntensity,new ExerciseSets(3, 20));
        assertTrue(workout2.setRecommendedQuantity(workoutExerciseDuration1, new ExerciseSets(3, 10)));

        WorkoutExerciseSets workoutExerciseDuration2 = new WorkoutExerciseSets(new Exercise("Russian Twists", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY), xpHighIntensity,new ExerciseSets(3, 20));
        assertTrue(workout2.setRecommendedQuantity(workoutExerciseDuration2, new ExerciseSets(3, 10)));

        WorkoutExerciseSets workoutExerciseDuration3 = new WorkoutExerciseSets(new Exercise("Sit-ups", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY), xpHighIntensity,new ExerciseSets(3, 20));
        assertTrue(workout2.setRecommendedQuantity(workoutExerciseDuration3, new ExerciseSets(3, 10)));

        assertEquals(false, workout1.isFavourite());
        assertEquals(true, workout2.isFavourite());
        assertEquals(true, workout3.isFavourite());

        workout1.toggleFavourite();
        workout2.toggleFavourite();
        workout3.toggleFavourite();

        assertEquals(true, workout1.isFavourite());
        assertEquals(false, workout2.isFavourite());
        assertEquals(false, workout3.isFavourite());

        assertEquals(0, workout1.numExercises());
        assertEquals(3, workout2.numExercises());
        assertEquals(3, workout3.numExercises());

        workout1.addExercise(workoutExerciseDuration1);
        assertEquals(1, workout1.numExercises());

        WorkoutExerciseSets workoutExerciseDuration4 = new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY), xpHighIntensity,new ExerciseSets(3, 10));
        assertTrue(workout2.moveExercise(workoutExerciseDuration4, 2));

        assertEquals(45, workout1.getExperienceValue());
        assertEquals(3 * xpHighIntensity, workout2.getExperienceValue());
        assertEquals(3 * xpHighIntensity, workout3.getExperienceValue());
        assertEquals(0, workout4.getExperienceValue());
        /*
        WorkoutExercise workoutExercise4 = new WorkoutExercise("Crunches", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(3, 10), xpHighIntensity);
        assertTrue(workout2.moveExercise(workoutExercise4, 2));*/

        workout1.removeExercise(workoutExerciseDuration1);
        assertEquals(0, workout1.numExercises());

        workout2.removeExercise(workoutExerciseDuration4);
        assertEquals(2, workout2.numExercises());

        WorkoutExerciseSets workoutExerciseDuration5 = new WorkoutExerciseSets(new Exercise("Russian Twists", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY), xpHighIntensity,new ExerciseSets(3, 10));
        workout2.removeExercise(workoutExerciseDuration5);

        assertEquals(1, workout2.numExercises());

        assertNotNull(workout1.getExerciseEnumeration());
        assertTrue(workout1.getExerciseEnumeration() instanceof Enumeration);

        assertNotNull(workout2.getExerciseEnumeration());
        assertTrue(workout2.getExerciseEnumeration() instanceof Enumeration);

        assertNotNull(workout3.getExerciseEnumeration());
        assertTrue(workout3.getExerciseEnumeration() instanceof Enumeration);

        System.out.println("Finished testWorkout");
    }
}
