package ledge.muscleup.unit.model.experience;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Level;

import ledge.muscleup.model.experience.CompletedWorkoutRecord;
import ledge.muscleup.model.experience.LevelProgress;

/**
 * Tests for the LevelProgress class
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-08
 */
public class LevelProgressTest {
    private final String WORKOUT_NAME = "placeholder name";
    private final int EXPERIENCE_BEFORE = 1025;
    private final int EXPERIENCE_AFTER = 1550;
    private final LocalDateTime COMPLETE_DATE = new LocalDateTime(2017, 10, 20, 8, 30);

    private LevelProgress levelProgress;

    /**
     * Constructor for the ScheduleWeekTest
     */
    public LevelProgressTest() {
        super();
    }

    @Before
    public void setUp() {
        levelProgress = new LevelProgress(new CompletedWorkoutRecord(WORKOUT_NAME, EXPERIENCE_BEFORE,
                EXPERIENCE_AFTER, COMPLETE_DATE));
    }

    @Test
    public void testGetters() {
        Assert.assertEquals("Returned incorrect level",
                levelProgress.getCurrLevel(), 2);
        Assert.assertEquals("Returned incorrect progress to next level",
                levelProgress.getNextLevelXPProgress(), 50);
        Assert.assertEquals("Returned incorrect value of experience to reach next level",
                levelProgress.getNextLevelXPTotal(), 1500);
    }
}
