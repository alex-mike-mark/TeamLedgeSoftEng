package ledge.muscleup.unit.persistence;

import junit.framework.TestCase;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.workout.WorkoutSession;

/**
 * Used for testing the InterfaceExperienceDataAccess persistence interface
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-14
 *
 */

public class ExperienceDataAccessTest extends TestCase {
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

        Assert.assertEquals(0, dataAccess.getCompletedWorkouts().size());
        WorkoutSession session1 = dataAccess.getWorkoutSession(new LocalDate(2017, 06, 21));
        dataAccess.toggleWorkoutComplete(session1);
        Assert.assertEquals(1, dataAccess.getCompletedWorkouts().size());
        Assert.assertEquals(session1.getName(), dataAccess.getCompletedWorkouts().get(0).getWorkoutName());
        Assert.assertEquals(session1.getExperienceValue(), dataAccess.getCompletedWorkouts().get(0).getExperienceGained());

        WorkoutSession session2 = dataAccess.getWorkoutSession(new LocalDate(2017, 06, 25));
        dataAccess.toggleWorkoutComplete(session2);
        Assert.assertEquals(2, dataAccess.getCompletedWorkouts().size());
        Assert.assertEquals(session2.getName(), dataAccess.getCompletedWorkouts().get(0).getWorkoutName());
        Assert.assertEquals(session2.getExperienceValue(), dataAccess.getCompletedWorkouts().get(0).getExperienceGained());
        Assert.assertEquals(session1.getName(), dataAccess.getCompletedWorkouts().get(1).getWorkoutName());
        Assert.assertEquals(session1.getExperienceValue(), dataAccess.getCompletedWorkouts().get(1).getExperienceGained());

        System.out.println("Finishing testGetCompletedWorkouts\n");
    }

    /**
     * Tests that getting the most recent completed workout works properly
     */
    @Test
    public void testGetMostRecentCompletedWorkout() {
        System.out.println("\nStarting testGetMostRecentCompletedWorkout");

        Assert.assertNull("Returned unexpected Most Recent Completed", dataAccess.getMostRecentCompletedWorkout());
        WorkoutSession session = dataAccess.getWorkoutSession(new LocalDate(2017, 06, 21));
        dataAccess.toggleWorkoutComplete(session);
        Assert.assertEquals(session.getName(), dataAccess.getMostRecentCompletedWorkout().getWorkoutName());
        Assert.assertEquals(session.getExperienceValue(), dataAccess.getMostRecentCompletedWorkout().getExperienceGained());

        session = dataAccess.getWorkoutSession(new LocalDate(2017, 06, 25));
        dataAccess.toggleWorkoutComplete(session);
        Assert.assertEquals(session.getName(), dataAccess.getMostRecentCompletedWorkout().getWorkoutName());
        Assert.assertEquals(session.getExperienceValue(), dataAccess.getMostRecentCompletedWorkout().getExperienceGained());

        System.out.println("Finishing testGetMostRecentCompletedWorkout\n");
    }
}
