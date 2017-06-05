package ledge.muscleup.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import ledge.muscleup.model.exercise.ExerciseIntensity;
import ledge.muscleup.model.exercise.ExerciseType;
import ledge.muscleup.model.exercise.InterfaceExercise;
import ledge.muscleup.model.exercise.ListedExercise;
import ledge.muscleup.model.workout.ModifiableWorkout;

/**
 * Created by Alexander on 2017-05-27.
 */

public class ModifiableWorkoutTest extends TestCase {
    InterfaceExercise exercise;
    ModifiableWorkout workout;
    @Before
    public void testInit(){
        workout = new ModifiableWorkout("Testerworkout");
        exercise = new ListedExercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM, false);
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
