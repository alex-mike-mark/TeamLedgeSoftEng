package ledge.muscleup.unit.business;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * BusinessTests.java used to run all the Business Tests in all the suite classes.
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-07-17
 */

public class BusinessTests {
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Business tests");
        suite.addTestSuite(AccessExercisesTest.class);
        suite.addTestSuite(AccessWorkoutsTest.class);
        suite.addTestSuite(AccessWorkoutSessionsTest.class);
        suite.addTestSuite(AccessExperienceTest.class);
        return suite;
    }
}
