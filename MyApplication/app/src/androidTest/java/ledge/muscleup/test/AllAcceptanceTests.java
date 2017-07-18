package ledge.muscleup;

/**
 * Created by Alexander on 2017-07-18.
 */

import ledge.muscleup.test.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExerciseAcceptanceTest.class,
        ProgressReportAcceptanceTest.class,
        SuggestedAcceptanceTest.class,
        TodayAcceptanceTest.class,
        WorkoutAcceptanceTest.class,
        WorkoutScheduleAcceptanceTest.class
})


public class AllAcceptanceTests {

}
