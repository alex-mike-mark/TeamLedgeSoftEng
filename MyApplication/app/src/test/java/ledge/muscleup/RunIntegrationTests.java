package ledge.muscleup;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * RunIntegrationTests.java used to run all integration tests.
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-07-17
 */

public class RunIntegrationTests {
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTest(IntegrationTests.suite());
        return suite;
    }
}
