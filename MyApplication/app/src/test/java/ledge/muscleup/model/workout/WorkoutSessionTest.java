package ledge.muscleup.model.workout;

import junit.framework.TestCase;

import org.joda.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;

/**
 * WorkoutSessionTest.java used to test WorkoutSession.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-07
 */

public class WorkoutSessionTest extends TestCase {
    WorkoutSession workoutSession1, workoutSession2, workoutSession3;
    Workout workout1, workout2, workout3;
    WorkoutExercise[] exerciseList1, exerciseList2, exerciseList3;

    @Before
    public void setUp() {
        exerciseList1 = new WorkoutExercise[]{
                new WorkoutExercise("Crunches", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(3, 20)),
                new WorkoutExercise("Russian Twists", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(3, 20)),
                new WorkoutExercise("Sit-ups", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(3, 20))
        };
        workout1 = new Workout("Get Your 6 Pack Abs", true, exerciseList1);

        workoutSession1 = new WorkoutSession(workout1, LocalDate.now(), false);

        exerciseList2 = new WorkoutExercise[]{
                new WorkoutExercise("Crunches", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(3, 20)),
                new WorkoutExercise("Russian Twists", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(3, 20))
        };
        workout2 = new Workout("Get Your 6 Pack Abs", true, exerciseList2);

        workoutSession2 = new WorkoutSession(workout2, LocalDate.now(), true);

        exerciseList3 = new WorkoutExercise[]{
                new WorkoutExercise("Crunches", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(3, 20)),
                new WorkoutExercise("Russian Twists", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(3, 20)),
                new WorkoutExercise("Sit-ups", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, new ExerciseSets(3, 20))
        };
        workout3 = new Workout("Get Your 6 Pack Abs", true, exerciseList3);

        workoutSession3 = new WorkoutSession(workout3, LocalDate.now(), false);
    }

    @Test
    public void testWorkoutSession() {
        System.out.println("\nStarting testWorkoutSession");

        assertFalse(workoutSession1.equals(workoutSession2));
        assertFalse(workoutSession2.equals(workoutSession1));
        assertFalse(workoutSession2.equals(workoutSession3));
        assertFalse(workoutSession3.equals(workoutSession2));

        assertTrue(workoutSession1.equals(workoutSession3));
        assertTrue(workoutSession3.equals(workoutSession1));

        assertEquals("Get Your 6 Pack Abs", workoutSession1.getName());

        assertEquals(LocalDate.now(), workoutSession1.getDate());
        assertEquals(LocalDate.now(), workoutSession2.getDate());

        workoutSession1.setDate(LocalDate.now().plusDays(1));
        assertEquals(LocalDate.now().plusDays(1), workoutSession1.getDate());
        workoutSession2.setDate(LocalDate.now().minusDays(1));
        assertEquals(LocalDate.now().minusDays(1), workoutSession2.getDate());

        assertFalse(workoutSession1.isComplete());
        assertTrue(workoutSession2.isComplete());

        workoutSession1.toggleCompleted();
        workoutSession2.toggleCompleted();

        assertTrue(workoutSession1.isComplete());
        assertFalse(workoutSession2.isComplete());

        assertEquals(3, workoutSession1.numExercises());
        assertEquals(2, workoutSession2.numExercises());

        assertTrue(workoutSession1.completeExercise(
                new WorkoutSessionExercise("Crunches", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY,
                        new ExerciseSets(3, 20), false)));
        assertTrue(workoutSession1.completeExercise(
                new WorkoutSessionExercise("Russian Twists", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY,
                        new ExerciseSets(3, 20), false)));
        assertTrue(workoutSession1.completeExercise(
                new WorkoutSessionExercise("Sit-ups", ExerciseIntensity.HIGH,
                        ExerciseType.FULL_BODY, new ExerciseSets(3, 20), false)));

        //Test getExerciseEnumeration

        System.out.println("Finishing testWorkoutSession");
    }
}
