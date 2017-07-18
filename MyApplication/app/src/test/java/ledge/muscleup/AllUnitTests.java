package ledge.muscleup;

import junit.framework.Test;
import junit.framework.TestSuite;
import ledge.muscleup.unit.model.ModelTests;
import ledge.muscleup.unit.business.BusinessTests;
import ledge.muscleup.unit.persistence.PersistenceTests;

/**
 * Created by matthewsmidt on 2017-07-18.
 */

public class AllUnitTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Unit tests");
        suite.addTest(ModelTests.suite());
        suite.addTest(BusinessTests.suite());
        suite.addTest(PersistenceTests.suite());
        return suite;
    }
}

