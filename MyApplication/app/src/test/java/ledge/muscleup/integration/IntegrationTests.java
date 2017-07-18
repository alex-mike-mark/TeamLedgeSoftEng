package ledge.muscleup.integration;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * IntegrationTests.java used to run all integration tests.
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-07-17
 */

public class IntegrationTests {
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Integration tests");
        suite.addTestSuite(BusinessPersistenceSeamTest.class);
        suite.addTestSuite(DataAccessHSQLDBTest.class);
        return suite;
    }
}
