package ledge.muscleup.model.exercise;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.enums.DistanceUnit;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.exercise.enums.TimeUnit;
import ledge.muscleup.model.exercise.enums.WeightUnit;

/**
 * Created by Alexander on 2017-06-27.
 */

public class WorkoutExerciseSubsTest extends TestCase{
    int xp;
    Exercise exercise1;

    ExerciseSets sets;
    ExerciseSetsAndWeight setsAndWeight;
    ExerciseDistance distance;
    ExerciseDuration duration;

    WorkoutExerciseSets WESets;
    WorkoutExerciseSetsAndWeight WESetsAndWeight;
    WorkoutExerciseDistance WEDistance;
    WorkoutExerciseDuration WEDuration;


    @Before
    public void setUp(){
        xp = 10;
        exercise1 = new Exercise("DUMMY", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY);

        sets = new ExerciseSets(10,10);
        setsAndWeight = new ExerciseSetsAndWeight(10,10,10, WeightUnit.KG);
        distance = new ExerciseDistance(10, DistanceUnit.MILES);
        duration = new ExerciseDuration(10, TimeUnit.MINUTES);

        WESets = new WorkoutExerciseSets(exercise1, xp ,sets);
        WESetsAndWeight = new WorkoutExerciseSetsAndWeight(exercise1, xp ,setsAndWeight);
        WEDistance = new WorkoutExerciseDistance(exercise1, xp ,distance);
        WEDuration = new WorkoutExerciseDuration(exercise1, xp ,duration);
    }

    @Test
    public void testWorkoutExerciseSubs(){
        System.out.println("\nStarting TestWorkoutExerciseSubs");

        //Tests to see if these objects actually get made.
        assertNotNull(WESets);
        assertNotNull(WESetsAndWeight);
        assertNotNull(WEDistance);
        assertNotNull(WEDuration);

        //Test to see if getQuantity returns what it was given.
        assertTrue(WESets.getQuantity().equals(sets));
        assertTrue(WESetsAndWeight.getQuantity().equals(setsAndWeight));
        assertTrue(WEDistance.getQuantity().equals(distance));
        assertTrue(WEDuration.getQuantity().equals(duration));

        //Tests to see if getQuantity does not return a different type.
        assertFalse(WESets.getQuantity().equals(WESetsAndWeight.getQuantity()));
        assertFalse(WESets.getQuantity().equals(WEDistance.getQuantity()));
        assertFalse(WESets.getQuantity().equals(WEDuration.getQuantity()));
        assertFalse(WESetsAndWeight.getQuantity().equals(WEDistance.getQuantity()));
        assertFalse(WESetsAndWeight.getQuantity().equals(WEDuration.getQuantity()));
        assertFalse(WEDistance.getQuantity().equals(WEDuration.getQuantity()));

        //Tests to see if updateQuantity returns false after attempting to update with a
        //bad type
        assertFalse(WESets.updateQuantity(setsAndWeight));
        assertFalse(WESets.updateQuantity(distance));
        assertFalse(WESets.updateQuantity(duration));
        assertFalse(WESetsAndWeight.updateQuantity(distance));
        assertFalse(WESetsAndWeight.updateQuantity(duration));
        assertFalse(WEDistance.updateQuantity(duration));

        //Tests to see if updateQuantity changed the quantity.
        assertFalse(WESets.getQuantity().equals(setsAndWeight));
        assertFalse(WESets.getQuantity().equals(distance));
        assertFalse(WESets.getQuantity().equals(duration));
        assertFalse(WESetsAndWeight.getQuantity().equals(distance));
        assertFalse(WESetsAndWeight.getQuantity().equals(duration));
        assertFalse(WEDistance.getQuantity().equals(duration));

        System.out.println("Finished TestWorkoutExerciseSubs.");
    }
}
