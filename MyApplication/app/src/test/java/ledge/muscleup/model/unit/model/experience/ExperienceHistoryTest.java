package ledge.muscleup.model.unit.model.experience;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
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
public class ExperienceHistoryTest {
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
        DateTime dateTime = new DateTime(2017, 7, 13, 12, 0, 0);
        List<CompletedWorkoutRecord> completedWorkoutRecordList = new ArrayList<>();

        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Marathon Training Starts Here", 0, 150, dateTime));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 150, 250, dateTime.plusDays(1)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 250, 500, dateTime.plusDays(3)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 500, 650, dateTime.plusDays(4)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Work that Core, Get that Score!", 650, 750, dateTime.plusDays(5)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 750, 1000, dateTime.plusDays(8)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Marathon Training Starts Here", 1000, 1250, dateTime.plusDays(9)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 1250, 1400, dateTime.plusDays(10)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 1400, 1550, dateTime.plusDays(12)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 1550, 1650, dateTime.plusDays(13)));

        experienceHistory = new ExperienceHistory(completedWorkoutRecordList);
    }

    /**
     * Test that the getters in the ExperienceHistory return the appropriate values
     */
    @Test
    public void testGetters() {
        DateTime dateTime = new DateTime(2017, 7, 13, 12, 0, 0);
        List<CompletedWorkoutRecord> completedWorkoutRecordList = new ArrayList<>();
        Enumeration<CompletedWorkoutRecord> experienceHistoryEnumeration;
        Enumeration<CompletedWorkoutRecord> testListEnumeration;

        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Marathon Training Starts Here", 0, 150, dateTime));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 150, 250, dateTime.plusDays(1)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 250, 500, dateTime.plusDays(3)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 500, 650, dateTime.plusDays(4)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Work that Core, Get that Score!", 650, 750, dateTime.plusDays(5)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 750, 1000, dateTime.plusDays(8)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Marathon Training Starts Here", 1000, 1250, dateTime.plusDays(9)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 1250, 1400, dateTime.plusDays(10)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Never Skip Leg Day", 1400, 1550, dateTime.plusDays(12)));
        completedWorkoutRecordList.add(new CompletedWorkoutRecord("Welcome to the Gun Show", 1550, 1650, dateTime.plusDays(13)));
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
        Assert.assertEquals("Returned incorrect number of levels", experienceHistory.getLevelsGained(7), 2);
        Assert.assertEquals("Returned incorrect number of levels", experienceHistory.getLevelsGained(1), 1);

        Assert.assertEquals("Returned incorrect number of levels", experienceHistory.getCurrLevel(), 2);
        Assert.assertEquals("Returned incorrect number of levels", experienceHistory.getNextLevelXPProgress(), 1350);
        Assert.assertEquals("Returned incorrect value for next level", experienceHistory.getNextLevelXPTotal(), 3000);

        experienceHistoryEnumeration = experienceHistory.getCompletedWorkoutsEnumeration();

        while(experienceHistoryEnumeration.hasMoreElements() && testListEnumeration.hasMoreElements())
            Assert.assertEquals("Workout record mismatch", experienceHistoryEnumeration.nextElement(),
                                testListEnumeration.nextElement());
        Assert.assertEquals("Incorrect number of elements", experienceHistoryEnumeration.hasMoreElements(),
                            testListEnumeration.hasMoreElements());
    }
}
