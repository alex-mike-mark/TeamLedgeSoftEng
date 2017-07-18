package ledge.muscleup;

/**
 * Created by Alexander on 2017-07-18.
 */

import ledge.muscleup.test.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TodayAcceptanceTest.class,
        WorkoutScheduleAcceptanceTest.class,
        ExerciseAcceptanceTest.class,
        ProgressReportAcceptanceTest.class,
        SuggestedAcceptanceTest.class,
        WorkoutAcceptanceTest.class,

})


public class AllAcceptanceTests {

}
