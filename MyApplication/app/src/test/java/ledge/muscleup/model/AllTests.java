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

import ledge.muscleup.model.business.AccessExercisesTest;
import ledge.muscleup.model.business.AccessWorkoutSessionsTest;
import ledge.muscleup.model.business.AccessWorkoutsTest;
import ledge.muscleup.model.integration.BusinessPersistenceSeamTest;
import ledge.muscleup.model.integration.DataAccessHSQLDBTest;
import ledge.muscleup.model.persistence.ExerciseDataAccessTest;
import ledge.muscleup.model.persistence.WorkoutDataAccessTest;
import ledge.muscleup.model.persistence.WorkoutSessionDataAccessTest;
import ledge.muscleup.model.workout.*;
import ledge.muscleup.model.exercise.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        // model/exercise
        ExerciseDistanceTest.class,
        ExerciseDurationTest.class,
        ExerciseQuantityTest.class,
        ExerciseSetsAndWeightTest.class,
        ExerciseSetsTest.class,
        ExerciseTest.class,
        WorkoutExerciseDurationTest.class,
        WorkoutSessionExerciseTest.class,
        // model/workout
        WorkoutSessionTest.class,
        WorkoutTest.class,
        // business
        AccessExercisesTest.class,
        AccessWorkoutsTest.class,
        AccessWorkoutSessionsTest.class,
        ScheduleWeekTest.class,
        WorkoutExerciseSubsTest.class,
        // persistence
        ExerciseDataAccessTest.class,
        WorkoutDataAccessTest.class,
        WorkoutSessionDataAccessTest.class,
        // integration
        BusinessPersistenceSeamTest.class,
        DataAccessHSQLDBTest.class
})

public class AllTests {

}
