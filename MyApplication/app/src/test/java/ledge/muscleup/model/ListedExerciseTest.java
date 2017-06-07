package ledge.muscleup.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.exercise.ListedExercise;
import ledge.muscleup.model.workout.ModifiableWorkout;

/**
 * Created by Alexander on 2017-05-27.
 */

public class ListedExerciseTest extends TestCase {

    ListedExercise testExercise;
    @Before
    public void testInit(){
        testExercise = new ListedExercise("Testersize", ExerciseIntensity.HIGH, ExerciseType.FULL_BODY, false);
    }

    @Test
    public void isFavouriteTest(){
        assertTrue("isFavourite ain't working.",!testExercise.isFavourite());
        testExercise.toggleFavourite();
        assertTrue("toggleFavourite ain't working.",testExercise.isFavourite());
        testExercise.toggleFavourite();
        assertTrue("toggleFavourite ain't working.",!testExercise.isFavourite());
    }
    @Test
    public void initalizeTest(){
        assertEquals("Something's up with exercise name.","Testercize",testExercise.getName());
        assertEquals("Something's up with exercise intensity",ExerciseIntensity.HIGH,testExercise.getIntensity());
        assertEquals("Something's up with exercise muscles worked",ExerciseType.FULL_BODY,testExercise.getType());
        assertEquals(false,testExercise.isFavourite());
    }
}