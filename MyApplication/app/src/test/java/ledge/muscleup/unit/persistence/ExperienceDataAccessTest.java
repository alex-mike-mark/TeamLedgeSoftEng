package ledge.muscleup.unit.persistence;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Used for testing the InterfaceExperienceDataAccess persistence interface
 *
 * @author: Cole Kehler
 * @version: 3.0
 * @since 2017-07-14
 *
 */

public class ExperienceDataAccessTest {
    private TemplateDataAccessStub dataAccess;

    /**
     * Constructor for the ExperienceDataAccessTest
     */
    public ExperienceDataAccessTest() {
        super();
    }

    /**
     * Initializes the ExperienceDataAccessTest to be used in the test
     */
    @Before
    public void setUp() {
        dataAccess = new TemplateDataAccessStub("Test Experience");
        dataAccess.open("Test Experience");
    }

    /**
     * Closes the DataAccess connection
     */
    @After
    public void tearDown() {
        dataAccess.close();
    }

    /**
     * Tests that getting the list of completed workout records works properly
     */
    @Test
    public void testGetCompletedWorkouts() {
        System.out.println("\nStarting testGetCompletedWorkouts");

        Assert.assertNotNull("Test not implemented", dataAccess.getCompletedWorkouts());

        System.out.println("Finishing testGetCompletedWorkouts\n");
    }

    /**
     * Tests that getting the most recent completed workout works properly
     */
    @Test
    public void testGetMostRecentCompletedWorkout() {
        System.out.println("\nStarting testGetMostRecentCompletedWorkout");

        Assert.assertNotNull("Test not implemented", dataAccess.getMostRecentCompletedWorkout());

        System.out.println("Finishing testGetMostRecentCompletedWorkout\n");
    }
}
