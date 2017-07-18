package ledge.muscleup.unit.model.experience;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.model.experience.CompletedWorkoutRecord;

/**
 * Tests for the CompletedWorkoutRecord class
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */
public class CompletedWorkoutRecordTest extends TestCase {
    private final String WORKOUT_NAME = "placeholder name";
    private final int EXPERIENCE_BEFORE = 1025;
    private final int EXPERIENCE_AFTER = 1550;
    private final LocalDateTime COMPLETE_DATE = new LocalDateTime(2017, 10, 20, 8, 30);

    private CompletedWorkoutRecord completedWorkoutRecord;

    /**
     * Constructor for the ScheduleWeekTest
     */
    public CompletedWorkoutRecordTest() {
        super();
    }

    /**
     * Sets up the CompletedWorkoutRecord test
     */
    @Before
    public void setUp() {
        completedWorkoutRecord = new CompletedWorkoutRecord(WORKOUT_NAME, EXPERIENCE_BEFORE, EXPERIENCE_AFTER, COMPLETE_DATE);
    }

    /**
     * Test that the getters in the CompletedWorkoutRecord return the appropriate values
     */
    @Test
    public void testGetters() {
        Assert.assertEquals("Returned incorrect name",
                completedWorkoutRecord.getWorkoutName(), WORKOUT_NAME);
        Assert.assertEquals("Returned incorrect final experience value",
                completedWorkoutRecord.getExperienceAfterCompletion(), EXPERIENCE_AFTER);
        Assert.assertEquals("Returned incorrect workout experience value",
                completedWorkoutRecord.getExperienceGained(), EXPERIENCE_AFTER - EXPERIENCE_BEFORE);
        Assert.assertEquals("Returned incorrect completion date",
                completedWorkoutRecord.getDateOfCompletion(), COMPLETE_DATE.toLocalDate());
    }
}
