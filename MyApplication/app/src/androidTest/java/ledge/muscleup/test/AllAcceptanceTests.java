package ledge.muscleup.test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by Alexander on 2017-07-18.
 */

public class AllAcceptanceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance tests");
        suite.addTestSuite(TodayAcceptanceTest.class);
        suite.addTestSuite(WorkoutScheduleAcceptanceTest.class);
        suite.addTestSuite(ExerciseAcceptanceTest.class);
        suite.addTestSuite(ProgressReportAcceptanceTest.class);
        suite.addTestSuite(SuggestedAcceptanceTest.class);
        suite.addTestSuite(WorkoutAcceptanceTest.class);
        return suite;
    }
}

