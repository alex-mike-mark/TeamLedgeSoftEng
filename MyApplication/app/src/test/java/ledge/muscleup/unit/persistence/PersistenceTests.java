package ledge.muscleup.unit.persistence;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * PersistenceTests.java used to run all the Persistence Tests in all the suite classes.
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-07-17
 */

public class PersistenceTests {
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Persistence tests");
        suite.addTestSuite(ExerciseDataAccessTest.class);
        suite.addTestSuite(WorkoutDataAccessTest.class);
        suite.addTestSuite(WorkoutSessionDataAccessTest.class);
        suite.addTestSuite(ExperienceDataAccessTest.class);
        return suite;
    }
}
