package ledge.muscleup.unit.model;

import junit.framework.Test;
import junit.framework.TestSuite;

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

/**
 * ModelTests.java used to run all the Model Tests in all the suite classes.
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-07-17
 */

public class ModelTests {
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Model tests");
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
        return suite;
    }
}
