package ledge.muscleup.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.workout.Workout;

/**
 * Created by Alexander on 2017-05-27.
 */

public class ModifiableWorkoutTest extends TestCase {
    Exercise exercise;
    Workout workout;
    @Before
    public void testInit(){
        workout = new Workout("Testerworkout");
        exercise = new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM, false);
    }
    @Test
    public void initializeTest(){

    }
    @Test
    public void exerciseAddTest(){

    }
    @Test
    public void exerciseRemoveTest(){

    }
}
