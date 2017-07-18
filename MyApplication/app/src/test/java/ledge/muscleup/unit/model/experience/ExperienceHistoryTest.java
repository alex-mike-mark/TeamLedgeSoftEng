package ledge.muscleup.unit.model.experience;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import ledge.muscleup.model.experience.CompletedWorkoutRecord;
import ledge.muscleup.model.experience.ExperienceHistory;

import static java.util.Collections.enumeration;

/**
 * Tests for the ExperienceHistory class
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-13
 */
public class ExperienceHistoryTest extends TestCase {
    private ExperienceHistory experienceHistory;

    /**
     * Constructor for the ScheduleWeekTest
     */
    public ExperienceHistoryTest() {
        super();
    }

    /**
     * Sets up the ExperienceHistory test
     */
    @Before
    public void setUp() {
        LocalDateTime dateTime = new LocalDateTime();
        List<CompletedWorkoutRecord> completedWorkoutRecordList = new ArrayList<>();

        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Marathon Training Starts Here", 1550, 1650, dateTime));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 1400, 1550, dateTime.minusDays(1)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 1250, 1400, dateTime.minusDays(3)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 1000, 1250, dateTime.minusDays(4)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Work that Core, Get that Score!", 750, 1000, dateTime.minusDays(5)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 650, 750, dateTime.minusDays(8)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Marathon Training Starts Here", 500, 650, dateTime.minusDays(9)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 250, 500, dateTime.minusDays(10)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 150, 250, dateTime.minusDays(12)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 0, 150, dateTime.minusDays(13)));

        experienceHistory = new ExperienceHistory(completedWorkoutRecordList);
    }

    /**
     * Test that the getters in the ExperienceHistory return the appropriate values
     */
    @Test
    public void testGetters() {
        LocalDateTime dateTime = new LocalDateTime();
        List<CompletedWorkoutRecord> completedWorkoutRecordList = new ArrayList<>();
        Enumeration<CompletedWorkoutRecord> experienceHistoryEnumeration;
        Enumeration<CompletedWorkoutRecord> testListEnumeration;

        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Marathon Training Starts Here", 1550, 1650, dateTime));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 1400, 1550, dateTime.minusDays(1)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 1250, 1400, dateTime.minusDays(3)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 1000, 1250, dateTime.minusDays(4)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Work that Core, Get that Score!", 750, 1000, dateTime.minusDays(5)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 650, 750, dateTime.minusDays(8)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Marathon Training Starts Here", 500, 650, dateTime.minusDays(9)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 250, 500, dateTime.minusDays(10)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 150, 250, dateTime.minusDays(12)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 0, 150, dateTime.minusDays(13)));
        testListEnumeration = enumeration(completedWorkoutRecordList);

        Assert.assertEquals("Returned incorrect number of workouts completed",
                            experienceHistory.getNumWorkoutsCompleted(14), 10);
        Assert.assertEquals("Returned incorrect number of workouts completed",
                            experienceHistory.getNumWorkoutsCompleted(7), 5);
        Assert.assertEquals("Returned incorrect number of workouts completed",
                            experienceHistory.getNumWorkoutsCompleted(1), 1);

        Assert.assertEquals("Returned incorrect amount of xp", experienceHistory.getXPGained(14), 1650);
        Assert.assertEquals("Returned incorrect amount of xp", experienceHistory.getXPGained(7), 900);
        Assert.assertEquals("Returned incorrect amount of xp", experienceHistory.getXPGained(1), 100);

        Assert.assertEquals("Returned incorrect number of levels", experienceHistory.getLevelsGained(14), 2);
        Assert.assertEquals("Returned incorrect number of levels", experienceHistory.getLevelsGained(7), 1);
        Assert.assertEquals("Returned incorrect number of levels", experienceHistory.getLevelsGained(1), 0);

        Assert.assertEquals("Returned incorrect number of levels", experienceHistory.getCurrLevel(), 2);
        Assert.assertEquals("Returned incorrect number of levels", experienceHistory.getNextLevelXPProgress(), 150);
        Assert.assertEquals("Returned incorrect value for next level", experienceHistory.getNextLevelXPTotal(), 1500);

        experienceHistoryEnumeration = experienceHistory.getCompletedWorkoutsEnumeration();

        while(experienceHistoryEnumeration.hasMoreElements() && testListEnumeration.hasMoreElements())
            Assert.assertTrue("Workout record mismatch",
                              experienceHistoryEnumeration.nextElement().equals(testListEnumeration.nextElement()));
        Assert.assertEquals("Incorrect number of elements", experienceHistoryEnumeration.hasMoreElements(),
                            testListEnumeration.hasMoreElements());
    }
}
