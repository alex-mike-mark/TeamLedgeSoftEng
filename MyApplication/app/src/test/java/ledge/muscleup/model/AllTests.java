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
        ExerciseSetsTest.class,
        ExerciseSetsAndWeightTest.class,
        ListedExerciseTest.class,
        SuggestedExerciseTest.class,
        TrackableExerciseTest.class,
        ModifiableWorkoutTest.class,
        TrackableWorkoutTest.class
        TrackableWorkoutTest.class,
        ScheduleManagerTest.class
})

public class AllTests {

}
