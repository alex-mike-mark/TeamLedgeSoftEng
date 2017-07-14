package ledge.muscleup.model.unit.business;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.business.AccessExercises;
import ledge.muscleup.business.AccessExperience;
import ledge.muscleup.business.InterfaceAccessExercises;
import ledge.muscleup.business.InterfaceAccessExperience;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.experience.CompletedWorkoutRecord;
import ledge.muscleup.persistence.InterfaceExperienceDataAccess;

/**
 * Tests the AccessExperience class
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-13
 */

public class AccessExperienceTest {
    private InterfaceAccessExperience dataAccess;

    /**
     * Default constructor for the AccessExperienceTest
     */
    public AccessExperienceTest() {
        super();
    }

    /**
     * Initializes the AccessExperience to be used in the test
     */
    @Before
    public void setUp() {
        dataAccess = new AccessExperience(new TemplateExperienceDataAccess());
    }

    /**
     * Tests that getting the list of completed workout records works properly
     */
    @Test
    public void testGetCompletedWorkouts() {
        DateTime dateTime = new DateTime(2017, 7, 13, 12, 0, 0);
        List<CompletedWorkoutRecord> completedWorkoutRecordList = new ArrayList<>();

        System.out.println("\nStarting testGetCompletedWorkouts");

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

        Assert.assertEquals("Returned incorrect list of completed workout records", completedWorkoutRecordList, dataAccess.getCompletedWorkouts());

        System.out.println("Finishing testGetCompletedWorkouts\n");
    }

    /**
     * Tests that getting the most recent completed workout works properly
     */
    @Test
    public void testGetMostRecentCompletedWorkout() {
        CompletedWorkoutRecord completedWorkoutRecord = new CompletedWorkoutRecord("Welcome to the Gun Show", 1550, 1650, new DateTime(2017, 7, 26, 12, 0, 0));

        System.out.println("\nStarting testGetMostRecentCompletedWorkout");

        Assert.assertEquals("Returned completed workout that wasn't the most recent completed workout", completedWorkoutRecord, dataAccess.getMostRecentCompletedWorkout());

        System.out.println("Finishing testGetMostRecentCompletedWorkout\n");
    }
}

class TemplateExperienceDataAccess implements InterfaceExperienceDataAccess {
    List<CompletedWorkoutRecord> completedWorkoutRecordList;

    /**
     * Opens a data access class
     *
     * @param statement the statement to use in data access queries
     */
    @Override
    public void open(Statement statement) {
        DateTime dateTime = new DateTime(2017, 7, 13, 12, 0, 0);

        completedWorkoutRecordList = new ArrayList<>();

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
    }

    /**
     * Closes a data access class
     */
    @Override
    public void close() { }

    /**
     * Returns the list of all completed workout records
     *
     * @return a list of all completed workout records
     */
    @Override
    public List<CompletedWorkoutRecord> getCompletedWorkouts() {
        return completedWorkoutRecordList;
    }

    /**
     * Returns the most recent completed workout
     *
     * @return the most recent completed workout
     */
    @Override
    public CompletedWorkoutRecord getMostRecentCompletedWorkout() {
        return completedWorkoutRecordList.get(completedWorkoutRecordList.size() - 1);
    }
}