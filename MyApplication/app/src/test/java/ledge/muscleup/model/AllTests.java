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

import ledge.muscleup.model.workout.*;
import ledge.muscleup.model.exercise.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExerciseDistanceTest.class,
        ExerciseDurationTest.class,
        ExerciseQuantityTest.class,
        ExerciseSetsAndWeightTest.class,
        ExerciseSetsTest.class,
        ExerciseTest.class,
        WorkoutExerciseDurationTest.class,
        WorkoutSessionExerciseTest.class,
        WorkoutSessionTest.class,
        WorkoutTest.class,
        ScheduleManagerTest.class
})

public class AllTests {

}
