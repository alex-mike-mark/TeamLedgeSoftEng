package ledge.muscleup.model;
/**
 * AllTests.java used to run all the tests in all the suite classes.
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-05-30
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ledge.muscleup.model.exercise.*;
import ledge.muscleup.model.exercise.ExerciseDistanceTest;
import ledge.muscleup.model.workout.ModifiableWorkoutTest;
import ledge.muscleup.model.workout.TrackableWorkoutTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExerciseDistanceTest.class,
        ExerciseDurationTest.class,
        ExerciseQuantityTest.class,
        ExerciseSetsTest.class,
        ExerciseSetsAndWeightTest.class,
        ListedExerciseTest.class,
        SuggestedExerciseTest.class,
        TrackableExerciseTest.class,
        ModifiableWorkoutTest.class,
        TrackableWorkoutTest.class
})

public class AllTests {

}
