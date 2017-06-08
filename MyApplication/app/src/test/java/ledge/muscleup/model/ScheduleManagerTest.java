package ledge.muscleup.model;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.business.ScheduleManager;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * Tests for the ScheduleManager
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-06
 */

public class ScheduleManagerTest {
    private ScheduleManager scheduleManager;
    InterfaceDataAccess dataAccess;

    /**
     * Constructor for the ScheduleManagerTest
     */
    public ScheduleManagerTest() { super(); }

    /**
     * Initializes the ScheduleManager to be used in the test
     */
    @Before
    public void testInit(){
        dataAccess = new TemplateDataAccessStub("schedule manager test");
        dataAccess.open();
        scheduleManager = new ScheduleManager(dataAccess);
    }

    /**
     * Closes the database access
     */
    @After
    public void testCleanup(){
        dataAccess.close();
    }

    /**
     * Tests that getting a day of the current week works properly
     */
    @Test
    public void getWeekdayTest(){
        Assert.assertTrue("Returned unexpected weekday value",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY).isEqual(new LocalDate(2017, 6, 5)));
        Assert.assertTrue("Returned unexpected weekday value",
                scheduleManager.getWeekday(DateTimeConstants.SUNDAY).isEqual(new LocalDate(2017, 6, 11)));
    }

    /**
     * Tests that checking if a workout exists on a day and getting a workout on a day of the
     * current week works properly
     */
    @Test
    public void scheduledWorkoutsTest(){
        Assert.assertFalse("Returned that day is empty when it isn't",
                scheduleManager.isDayEmpty(DateTimeConstants.MONDAY));
        Assert.assertTrue("Returned that day isn't empty when it is",
                scheduleManager.isDayEmpty(DateTimeConstants.SATURDAY));

        Assert.assertEquals("Returned a workout for a day where there shouldn't be one",
                scheduleManager.getScheduledWorkout(DateTimeConstants.MONDAY), null);
        Assert.assertEquals("Didn't return the workout scheduled for a day",
                scheduleManager.getScheduledWorkout(DateTimeConstants.SATURDAY).getName(),
                "Work that Core, Get that Score!");
    }

    /**
     * Tests that changing the current week to the next and previous weeks works properly
     */
    @Test
    public void changeWeekTest(){
        LocalDate currWeekStart = scheduleManager.getWeekday(DateTimeConstants.MONDAY);

        scheduleManager.nextWeek();

        Assert.assertEquals("Improperly incremented week",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY), currWeekStart.plusWeeks(1));
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.TUESDAY).getName(),
                "Never Skip Leg Day");
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Marathon Training Starts Here");

        scheduleManager.lastWeek();

        Assert.assertEquals("Improperly decremented week",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY), currWeekStart);
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Welcome to the Gun Show");
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.FRIDAY).getName(),
                "Never Skip Leg Day");
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.SATURDAY).getName(),
                "Work that Core, Get that Score!");

        scheduleManager.lastWeek();

        Assert.assertEquals("Improperly decremented week",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY), currWeekStart.minusWeeks(1));
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.MONDAY), null);
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.SUNDAY), null);

        scheduleManager.nextWeek();

        Assert.assertEquals("Improperly incremented week",
                scheduleManager.getWeekday(DateTimeConstants.MONDAY), currWeekStart);
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Welcome to the Gun Show");
        Assert.assertEquals("Returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.FRIDAY).getName(),
                "Never Skip Leg Day");
        Assert.assertEquals("returned unexpected workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.SATURDAY).getName(),
                "Work that Core, Get that Score!");
    }

    /**
     * Tests that a workout can be added to and removed from the week
     */
    @Test
    public void addRemoveWorkoutTest(){
        Assert.assertFalse("Removed a workout when there isn't one",
                scheduleManager.removeWorkoutSession(DateTimeConstants.WEDNESDAY));
        Assert.assertTrue("Didn't remove workout",
                scheduleManager.removeWorkoutSession(DateTimeConstants.THURSDAY));

        Assert.assertEquals("Improperly deleted a workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.WEDNESDAY), null);
        Assert.assertEquals("Improperly deleted a workout",
                scheduleManager.getScheduledWorkout(DateTimeConstants.THURSDAY), null);
    }
}