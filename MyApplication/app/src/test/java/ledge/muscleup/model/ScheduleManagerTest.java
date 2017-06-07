package ledge.muscleup.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ledge.muscleup.business.ScheduleManager;
import ledge.muscleup.model.exercise.ExerciseDistance;
/**
 * Tests for the ScheduleManager
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-06
 */

public class ScheduleManagerTest extends TestCase {
    ScheduleManager scheduleManager;

    /**
     * Constructor for the ScheduleManagerTest
     * @param arg0
     */
    public ScheduleManagerTest(String arg0) { super(arg0); }

    /**
     * Initializes the ScheduleManager to be used in the test
     */
    @Before
    public void testInit(){
        scheduleManager = new ScheduleManager();
    }

    /**
     * Tests that getting a day of the current week works properly
     */
    @Test
    public void getWeekdayTest(){

    }

    /**
     * Tests that checking if a workout exists on a day and getting a workout on a day of the
     * current week works properly
     */
    @Test
    public void scheduledWorkoutsTest(){

    }

    /**
     * Tests that changing the current week to the next and previous weeks works properly
     */
    @Test
    public void changeWeekTest(){

    }

    /**
     * Tests that a workout can be added to and removed from the week
     */
    @Test
    public void addRemoveWorkoutTest(){

    }
}