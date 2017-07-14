package ledge.muscleup;
/**
 * AllTests.java used to run all the tests in all the suite classes.
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-05-30
 */

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
        WorkoutExerciseTest.class,
        WorkoutSessionExerciseTest.class,
        WorkoutExerciseSubsTest.class,
        // model/workout
        WorkoutSessionTest.class,
        WorkoutTest.class,
        // model/experience
        CompletedWorkoutRecordTest.class,
        ExperienceHistoryTest.class,
        LevelProgressTest.class,
        // model/schedule
        ScheduleWeekTest.class,
        // business
        AccessExercisesTest.class,
        AccessWorkoutsTest.class,
        AccessWorkoutSessionsTest.class,
        AccessExperienceTest.class,
        //persistence
        ExerciseDataAccessTest.class,
        WorkoutDataAccessTest.class,
        WorkoutSessionDataAccessTest.class,
        ExperienceDataAccessTest.class
})

public class AllTests {

}
