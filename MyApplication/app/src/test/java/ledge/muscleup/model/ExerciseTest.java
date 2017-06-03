package ledge.muscleup.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.Exercise;

/**
 * Created by Alexander on 2017-05-27.
 */

public class ExerciseTest extends TestCase {

    Exercise testExercise;

    @Before
    public void create(){
        testExercise = new Exercise("Testercize",5,"Brain");
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
        assertEquals("Something's up with exercise intensity",5,testExercise.getIntensity());
        assertEquals("Something's up with exercise muscles worked","Brain",testExercise.getMusclesWorked());
        assertEquals(false,testExercise.isFavourite());
    }
}