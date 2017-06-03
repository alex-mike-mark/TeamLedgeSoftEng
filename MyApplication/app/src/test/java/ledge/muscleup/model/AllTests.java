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

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExerciseDistanceTest.class,
        ExerciseDurationTest.class,
        ExerciseQuantityTest.class,
        ExerciseSetsAndRepsTest.class,
        ExerciseTest.class,
        WorkoutTest.class
})

public class AllTests {

}
