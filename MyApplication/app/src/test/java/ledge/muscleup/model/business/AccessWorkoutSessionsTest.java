package ledge.muscleup.model.business;

import android.util.Log;

import junit.framework.TestCase;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.model.exercise.enums.*;

/**
 * AccessWorkoutSessionsTest.java used to test AccessWorkoutSessions.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-24
 */

public class AccessWorkoutSessionsTest extends TestCase {
    InterfaceAccessWorkoutSessions dataAccess;
    final int xpHighIntensity = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    final int xpMediumIntensity = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    final int xpLowIntensity = (ExerciseIntensity.LOW.ordinal() + 1) * 15;

    ScheduleWeek scheduleWeek;
    /**
     * Constructor for the AccessWorkoutSessionsTest
     */
    public AccessWorkoutSessionsTest(String arg0)
    {
        super(arg0);
    }

    /**
     * Initializes the AccessWorkoutSessions to be used in the test
     */
    @Before
    public void setUp()
    {
        dataAccess = new TemplateAccessWorkoutSessions();
    }

    /**
     * Tests that getting a workout session with given date works properly
     */
    @Test
    public void testGetWorkoutSession() {
        System.out.println("\nStarting testGetWorkoutSession");

        WorkoutSession workoutSession = dataAccess.getWorkoutSession(new LocalDate().minusWeeks(1).withDayOfWeek(DateTimeConstants.THURSDAY));
        assertNotNull(workoutSession);
        assertEquals("Welcome to the Gun Show", workoutSession.getName());
        assertEquals(new LocalDate().minusWeeks(1).withDayOfWeek(DateTimeConstants.THURSDAY), workoutSession.getDate());

        workoutSession = dataAccess.getWorkoutSession(new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY));
        assertNotNull(workoutSession);
        assertEquals("Never Skip Leg Day", workoutSession.getName());
        assertEquals(new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY), workoutSession.getDate());

        workoutSession = dataAccess.getWorkoutSession(new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY));
        assertNotNull(workoutSession);
        assertEquals("Work that Core, Get that Score!", workoutSession.getName());
        assertEquals(new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY), workoutSession.getDate());

        workoutSession = dataAccess.getWorkoutSession(new LocalDate().plusWeeks(1).withDayOfWeek(DateTimeConstants.TUESDAY));
        assertNotNull(workoutSession);
        assertEquals("Marathon Training Starts Here", workoutSession.getName());
        assertEquals(new LocalDate().plusWeeks(1).withDayOfWeek(DateTimeConstants.TUESDAY), workoutSession.getDate());

        workoutSession = dataAccess.getWorkoutSession(new LocalDate(2000, 01, 01));
        assertNull(workoutSession);

        System.out.println("Finishing testGetWorkoutSession\n");
    }


    /**
     * Tests getting workout sessions in a specified date range works properly
     */
    @Test
    public void testGetSessionsInDateRange() {
        System.out.println("\nStarting testGetSessionsInDateRange");

        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        List<WorkoutSession> workoutSessionList1 = new ArrayList<>();

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 15))
                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.FRIDAY),
                false));

        List<WorkoutSession> workoutSessionList2 = dataAccess
                .getSessionsInDateRange(LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY), LocalDate.now().withDayOfWeek(DateTimeConstants.SUNDAY));

        assertNotNull(workoutSessionList2);
        assertEquals(3, scheduleWeek.getNumSessionsInWeek(workoutSessionList2));
        assertEquals(workoutSessionList1.toString(), workoutSessionList2.toString());

        // First WorkoutSession in List
        workoutSessionList2 = dataAccess
                .getSessionsInDateRange(LocalDate.now().withDayOfWeek(DateTimeConstants.TUESDAY), LocalDate.now().withDayOfWeek(DateTimeConstants.TUESDAY));
        assertNotNull(workoutSessionList2);
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(workoutSessionList2));

        // Last WorkoutSession in List
        workoutSessionList2 = dataAccess
                .getSessionsInDateRange(LocalDate.now().withDayOfWeek(DateTimeConstants.WEDNESDAY), LocalDate.now().withDayOfWeek(DateTimeConstants.WEDNESDAY));
        assertNotNull(workoutSessionList2);
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(workoutSessionList2));

        // No WorkoutSession in range
        workoutSessionList2 = dataAccess
                .getSessionsInDateRange(LocalDate.now().withDayOfWeek(DateTimeConstants.THURSDAY), LocalDate.now().withDayOfWeek(DateTimeConstants.THURSDAY));
        assertNotNull(workoutSessionList2);

        assertEquals(0, scheduleWeek.getNumSessionsInWeek(workoutSessionList2));

        System.out.println("Finishing testGetSessionsInDateRange\n");
    }

    /**
     * Tests that getting current week sessions works properly
     */
    @Test
    public void testGetCurrentWeekSessions() {
        System.out.println("\nStarting testGetSessionsInDateRange");

        List<WorkoutSession> workoutSessionList1 = new ArrayList<>();

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false),
                                xpMediumIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE, false),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE, false),
                                xpHighIntensity, new ExerciseSets(2, 15))
            }),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false),
                                xpMediumIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.FRIDAY),
                false));

        assertNotNull(dataAccess.getCurrentWeekSessions());
        assertEquals(workoutSessionList1.toString(), dataAccess.getCurrentWeekSessions().toString());

        System.out.println("Finishing testGetCurrentWeekSessions\n");
    }

    /**
     * Tests that new scheduled week works properly
     */
    @Test
    public void testNewScheduledWeek() {
        System.out.println("\nStarting testNewScheduledWeek");
        ScheduleWeek scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertNotNull(scheduleWeek);
        assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));
        System.out.println("Finishing testNewScheduledWeek\n");
    }

    /**
     * Tests that inserting a workout sessions works properly
     */
    @Test
    public void testInsertWorkoutSession() {
        System.out.println("\nStarting testInsertWorkoutSession");

        // Insert new WorkoutSession
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        dataAccess.insertWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 15))
                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.THURSDAY),
                false));

        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(4, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        // Trying to insert new WorkoutSession on same day as a previously added WorkoutSession
        dataAccess.insertWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 15))
                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.THURSDAY),
                false));
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(4, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        System.out.println("Finishing testInsertWorkoutSession\n");
    }

    /**
     * Tests that removing a workout sessions works properly
     */
    @Test
    public void testRemoveWorkoutSession() {
        System.out.println("\nStarting testRemoveWorkoutSession");

        // Remove middle WorkoutSession from list
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));;

        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 15))
                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false));
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(2, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        // Trying to remove on a day where nothing exists
        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 15))
                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false));
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(2, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        // Remove First WorkoutSession in list
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(2, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        dataAccess.removeWorkoutSession(new WorkoutSession(
                        new Workout("Never Skip Leg Day", false, new WorkoutExercise[]{
                                new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false),
                                        xpMediumIntensity, new ExerciseSets(4, 15)),
                                new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false),
                                        xpLowIntensity, new ExerciseSets(3, 10))

                        }),
                        new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY),
                        false));
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        // Remove Last WorkoutSession in list
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));
        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false),
                                xpMediumIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.FRIDAY),
                false));
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        System.out.println("Finishing testRemoveWorkoutSession\n");
    }

    /**
     * Tests that toggling workout completed works properly
     */
    @Test
    public void testToggleWorkoutCompleted() {
        System.out.println("\nStarting testToggleWorkoutCompleted");

        LocalDate localDate = new LocalDate(LocalDate.now().withDayOfWeek(DateTimeConstants.TUESDAY));

        WorkoutSession workoutSession1 = dataAccess.getWorkoutSession(localDate);
        assertFalse(workoutSession1.isComplete());
        dataAccess.toggleWorkoutCompleted(workoutSession1);
        assertTrue(workoutSession1.isComplete());
        dataAccess.toggleWorkoutCompleted(workoutSession1);
        assertFalse(workoutSession1.isComplete());

        System.out.println("Finishing testToggleWorkoutCompleted\n");
    }

    /**
     * Tests that set to next week works properly
     */
    @Test
    public void testSetToNextWeek() {
        System.out.println("\nStarting testSetToNextWeek");
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        dataAccess.setToNextWeek(scheduleWeek);
        assertEquals(LocalDate.now().plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        System.out.println("Finishing testSetToNextWeek\n");
    }

    /**
     * Tests that set to current week works properly
     */
    @Test
    public void testSetToCurrentWeek() {

        System.out.println("\nStarting testSetToCurrentWeek");
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        dataAccess.setToNextWeek(scheduleWeek);
        assertEquals(LocalDate.now().plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));
        dataAccess.setToCurrentWeek(scheduleWeek);
        assertEquals(LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));
        assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));
        System.out.println("Finishing testSetToCurrentWeek\n");
    }

    /**
     * Tests that set to last week works properly
     */
    public void testSetToLastWeek() {
        System.out.println("\nStarting testSetToLastWeek");
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        dataAccess.setToLastWeek(scheduleWeek);
        assertEquals(LocalDate.now().minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        System.out.println("Finishing testSetToLastWeek\n");
    }

}

/**
 * A template WorkoutSession accessor that creates a template database stub for use in testing
 */
class TemplateAccessWorkoutSessions implements InterfaceAccessWorkoutSessions {
    TemplateDataAccessStub dataAccess;

    /**
     * The default constructor for the TemplateAccessWorkoutSessions
     */
    TemplateAccessWorkoutSessions() {
        dataAccess = new TemplateDataAccessStub("testDB");
        dataAccess.open();
    }

    /**
     * This method gets a workout session from the database with the given date
     *
     * @param dateOfSession the date of the workout session
     * @return a workout session from the database scheduled on the given date
     */
    @Override
    public WorkoutSession getWorkoutSession(LocalDate dateOfSession) {
        return dataAccess.getWorkoutSession(dateOfSession);
    }

    /**
     * A method that returns a list of workout sessions scheduled in a date range
     *
     * @param startDate the first date of the date range
     * @param endDate   the last date of the date range
     * @return a list of all workout sessions scheduled between startDate and endDate, inclusive
     */
    @Override
    public List<WorkoutSession> getSessionsInDateRange(LocalDate startDate, LocalDate endDate) {
        return dataAccess.getSessionsInDateRange(startDate, endDate);
    }

    /**
     * A method that returns a list of workout sessions scheduled in the current week
     * @return a list of all workout sessions scheduled in the current week
     */
    @Override
    public List<WorkoutSession> getCurrentWeekSessions() {
        LocalDate firstOfThisWeek = new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY);
        return dataAccess.getSessionsInDateRange(firstOfThisWeek, firstOfThisWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1));
    }

    /**
     * Adds a new workout session to the database
     *
     * @param workoutSession the workout session to be added to the database
     */
    @Override
    public void insertWorkoutSession(WorkoutSession workoutSession) {
        dataAccess.insertWorkoutSession(workoutSession);
    }

    /**
     * Removes a workout session from the database, if it exists
     *
     * @param workoutSession the workout session to be removed
     */
    @Override
    public void removeWorkoutSession(WorkoutSession workoutSession) {
        dataAccess.removeWorkoutSession(workoutSession);
    }

    /**
     * Toggles the completed state of a workout
     *
     * @param workoutSession the workout to change the state of
     */
    @Override
    public void toggleWorkoutCompleted(WorkoutSession workoutSession) {
        workoutSession.toggleCompleted();
        dataAccess.toggleWorkoutComplete(workoutSession);
    }


    /**
     * Log an exercise in a workout as complete
     *
     * @param workoutSession the workout which contains the exercise
     * @param exercise       the exercise to complete
     * @return a boolean representing whether the exercise was marked as completed or not
     * @throws IllegalArgumentException if passed a {@code null} parameter
     */
    @Override
    public boolean completeWorkoutExercise(WorkoutSession workoutSession, WorkoutSessionExercise exercise) throws IllegalArgumentException {
        return workoutSession.completeExercise(exercise) && dataAccess.toggleExerciseComplete(workoutSession, exercise);
    }

    /**
     * Adds a workout session to a given day of a scheduled week
     *
     * @param scheduleWeek  the week to add the workout to
     * @param workoutSession the workout session to add
     * @param dayOfWeek      the day of the week to add the workout session to
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     *                                  > DateTimeConstants.SUNDAY}
     */
    @Override
    public void addWorkoutSession(ScheduleWeek scheduleWeek, WorkoutSession workoutSession, int dayOfWeek) throws IllegalArgumentException {
        scheduleWeek.addWorkoutSession(workoutSession, dayOfWeek);
        dataAccess.addWorkoutSession(scheduleWeek, workoutSession, dayOfWeek);
    }

    /**
     * Removes a workout from a given day of a scheduled week
     *
     * @param scheduleWeek the week to remove the workout from
     * @param dayOfWeek     the day to remove the workout from
     * @return a boolean representing if a workout was removed
     * @throws IllegalArgumentException if {@code dayOfWeek < DateTimeConstants.MONDAY || dayOfWeek
     *                                  > DateTimeConstants.SUNDAY}
     */
    @Override
    public boolean removeWorkoutSession(ScheduleWeek scheduleWeek, int dayOfWeek) throws IllegalArgumentException {
        return scheduleWeek.removeWorkoutSession(dayOfWeek) && dataAccess.removeWorkoutSession(scheduleWeek, dayOfWeek);
    }

    /**
     * Creates a new ScheduleWeek based on the given date
     *
     * @param dayInWeek a day in the week to created a ScheduleWeek for
     * @return a ScheduleWeek, which contains all WorkoutSessions for the given week
     */
    @Override
    public ScheduleWeek newScheduledWeek(LocalDate dayInWeek) {
        LocalDate firstDayOfWeek = dayInWeek.withDayOfWeek(DateTimeConstants.MONDAY);
        return new ScheduleWeek(getSessionsInDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1)));
    }

    /**
     * Sets the manager to contain the scheduled workouts for the previous week
     *
     * @param scheduleWeek the week to change
     */
    @Override
    public void setToLastWeek(ScheduleWeek scheduleWeek) {
        LocalDate firstDayOfWeek;
        List<WorkoutSession> weekWorkouts;

        firstDayOfWeek = scheduleWeek.getWeekday(DateTimeConstants.MONDAY).minusWeeks(1);
        weekWorkouts = getSessionsInDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1));
        scheduleWeek.lastWeek(weekWorkouts);
    }

    /**
     * Sets the manager to contain the scheduled workouts for the following week
     *
     * @param scheduleWeek the week to change
     */
    @Override
    public void setToNextWeek(ScheduleWeek scheduleWeek) {
        LocalDate firstDayOfWeek;
        List<WorkoutSession> weekWorkouts;

        firstDayOfWeek = scheduleWeek.getWeekday(DateTimeConstants.MONDAY).plusWeeks(1);
        weekWorkouts = getSessionsInDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1));
        scheduleWeek.nextWeek(weekWorkouts);
    }

    /**
     * Sets the manager to contain the scheduled workouts for the current week
     * @param scheduleWeek the week to change
     */
    @Override
    public void setToCurrentWeek(ScheduleWeek scheduleWeek) {
        LocalDate firstDayOfWeek;
        List<WorkoutSession> weekWorkouts;

        firstDayOfWeek = LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY);
        weekWorkouts = getSessionsInDateRange(firstDayOfWeek, firstDayOfWeek.plusDays(DateTimeConstants.DAYS_PER_WEEK - 1));
        scheduleWeek.currentWeek(weekWorkouts);
    }
}
