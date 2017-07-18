package ledge.muscleup;
/**
 * AllTests.java used to run all the tests in all the suite classes.
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-05-30
 */

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ledge.muscleup.unit.business.AccessExercisesTest;
import ledge.muscleup.unit.business.AccessExperienceTest;
import ledge.muscleup.unit.business.AccessWorkoutSessionsTest;
import ledge.muscleup.unit.business.AccessWorkoutsTest;
import ledge.muscleup.unit.model.exercise.ExerciseDistanceTest;
import ledge.muscleup.unit.model.exercise.ExerciseDurationTest;
import ledge.muscleup.unit.model.exercise.ExerciseQuantityTest;
import ledge.muscleup.unit.model.exercise.ExerciseSetsAndWeightTest;
import ledge.muscleup.unit.model.exercise.ExerciseSetsTest;
import ledge.muscleup.unit.model.exercise.ExerciseTest;
import ledge.muscleup.unit.model.exercise.WorkoutExerciseDurationTest;
import ledge.muscleup.unit.model.exercise.WorkoutExerciseSubsTest;
import ledge.muscleup.unit.model.exercise.WorkoutExerciseTest;
import ledge.muscleup.unit.model.exercise.WorkoutSessionExerciseTest;
import ledge.muscleup.unit.model.experience.CompletedWorkoutRecordTest;
import ledge.muscleup.unit.model.experience.ExperienceHistoryTest;
import ledge.muscleup.unit.model.experience.LevelProgressTest;
import ledge.muscleup.unit.model.schedule.ScheduleWeekTest;
import ledge.muscleup.unit.model.workout.WorkoutSessionTest;
import ledge.muscleup.unit.model.workout.WorkoutTest;
import ledge.muscleup.unit.persistence.ExerciseDataAccessTest;
import ledge.muscleup.unit.persistence.ExperienceDataAccessTest;
import ledge.muscleup.unit.persistence.WorkoutDataAccessTest;
import ledge.muscleup.unit.persistence.WorkoutSessionDataAccessTest;

public class AllTests {
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testModels();
        testBusiness();
        testPersistence();
        return suite;
    }

    private static void testModels()
    {
        suite.addTestSuite(ExerciseDistanceTest.class);
        suite.addTestSuite(ExerciseDurationTest.class);
        suite.addTestSuite(ExerciseQuantityTest.class);
        suite.addTestSuite(ExerciseSetsAndWeightTest.class);
        suite.addTestSuite(ExerciseSetsTest.class);
        suite.addTestSuite(ExerciseTest.class);
        suite.addTestSuite(WorkoutExerciseDurationTest.class);
        suite.addTestSuite(WorkoutExerciseTest.class);
        suite.addTestSuite(WorkoutSessionExerciseTest.class);
        suite.addTestSuite(WorkoutExerciseSubsTest.class);
        suite.addTestSuite(WorkoutSessionTest.class);
        suite.addTestSuite(WorkoutTest.class);
        suite.addTestSuite(CompletedWorkoutRecordTest.class);
        suite.addTestSuite(ExperienceHistoryTest.class);
        suite.addTestSuite(LevelProgressTest.class);
        suite.addTestSuite(ScheduleWeekTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(AccessExercisesTest.class);
        suite.addTestSuite(AccessWorkoutsTest.class);
        suite.addTestSuite(AccessWorkoutSessionsTest.class);
        suite.addTestSuite(AccessExperienceTest.class);
    }

    private static void testPersistence()
    {
        suite.addTestSuite(ExerciseDataAccessTest.class);
        suite.addTestSuite(WorkoutDataAccessTest.class);
        suite.addTestSuite(WorkoutSessionDataAccessTest.class);
        suite.addTestSuite(ExperienceDataAccessTest.class);
    }
}
