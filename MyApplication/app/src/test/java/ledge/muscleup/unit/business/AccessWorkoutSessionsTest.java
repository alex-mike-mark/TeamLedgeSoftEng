package ledge.muscleup.unit.business;

import junit.framework.TestCase;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.InterfaceAccessWorkoutSessions;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.model.exercise.enums.*;
import ledge.muscleup.persistence.InterfaceWorkoutSessionDataAccess;

/**
 * AccessWorkoutSessionsTest.java used to test AccessWorkoutSessions.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-24
 */

public class AccessWorkoutSessionsTest extends TestCase {
    private final int XP_HIGH_INTENSITY = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    private final int XP_MEDIUM_INTENSITY = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    private final int XP_LOW_INTENSITY = (ExerciseIntensity.LOW.ordinal() + 1) * 15;
    private final int weekStartDay = DateTimeConstants.MONDAY;

    private InterfaceAccessWorkoutSessions dataAccess;

    private ScheduleWeek scheduleWeek;

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
    public void setUp() {
        InterfaceWorkoutSessionDataAccess templateDataAccess = new TemplateWorkoutSessionsAccess();

        templateDataAccess.open(null);
        dataAccess = new AccessWorkoutSessions(templateDataAccess);
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

        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        List<WorkoutSession> workoutSessionList1 = new ArrayList<>();

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(3, 10))

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
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_MEDIUM_INTENSITY, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
            }),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_MEDIUM_INTENSITY, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.FRIDAY),
                false));

        assertNotNull(dataAccess.getCurrentWeekSessions(weekStartDay));
        assertEquals(workoutSessionList1.toString(), dataAccess.getCurrentWeekSessions(weekStartDay).toString());

        System.out.println("Finishing testGetCurrentWeekSessions\n");
    }

    /**
     * Tests that new scheduled week works properly
     */
    @Test
    public void testNewScheduledWeek() {
        System.out.println("\nStarting testNewScheduledWeek");
        ScheduleWeek scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
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
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        dataAccess.insertWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.THURSDAY),
                false));

        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        assertEquals(4, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        // Trying to insert new WorkoutSession on same day as a previously added WorkoutSession
        dataAccess.insertWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.THURSDAY),
                false));
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
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
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false));
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        assertEquals(2, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        // Trying to remove on a day where nothing exists
        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false));
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        assertEquals(2, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        // Remove First WorkoutSession in list
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        assertEquals(2, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        dataAccess.removeWorkoutSession(new WorkoutSession(
                        new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                                new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                        XP_MEDIUM_INTENSITY, new ExerciseSets(4, 15)),
                                new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                        XP_LOW_INTENSITY, new ExerciseSets(3, 10))

                        }),
                        new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY),
                        false));
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        // Remove Last WorkoutSession in list
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));
        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_MEDIUM_INTENSITY, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.FRIDAY),
                false));
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
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
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
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

        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
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
        scheduleWeek = dataAccess.newScheduledWeek(weekStartDay, LocalDate.now());
        dataAccess.setToLastWeek(scheduleWeek);
        assertEquals(LocalDate.now().minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        System.out.println("Finishing testSetToLastWeek\n");
    }

    /**
     * A template data access class for use in testing
     */
    private class TemplateWorkoutSessionsAccess implements InterfaceWorkoutSessionDataAccess {
        private Map<String, Exercise> exercisesByName;
        private Map<String, Workout> workoutsByName;
        private Map<LocalDate, WorkoutSession> workoutSessionsByDate;

        /**
         * Opens a data access class
         *
         * @param statement the statement to use in data access queries
         */
        @Override
        public void open(Statement statement) {
            Exercise exercise;
            WorkoutExercise workoutExercise;
            Workout workout;
            WorkoutSession workoutSession;

            exercisesByName = new HashMap<>();
            exercise = new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM);
            exercisesByName.put(exercise.getName(), exercise);
            exercise = new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM);
            exercisesByName.put(exercise.getName(), exercise);
            exercise = new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO);
            exercisesByName.put(exercise.getName(), exercise);
            exercise = new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                    ExerciseType.CARDIO);
            exercisesByName.put(exercise.getName(), exercise);
            exercise = new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE);
            exercisesByName.put(exercise.getName(), exercise);
            exercise = new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE);
            exercisesByName.put(exercise.getName(), exercise);
            exercise = new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG);
            exercisesByName.put(exercise.getName(), exercise);
            exercise = new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG);
            exercisesByName.put(exercise.getName(), exercise);

            workoutsByName = new HashMap<>();
            final int xpPerIntensityLevel = 15;

            workout = new Workout("Welcome to the Gun Show");
            workoutsByName.put(workout.getName(), workout);
            exercise = exercisesByName.get("Bicep Curls");
            int exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseSetsAndWeight(exercise, exerciseExperience, new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS));
            workout.addExercise(workoutExercise);
            exercise = exercisesByName.get("Push-Ups");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience, new ExerciseSets(2, 15));
            workout.addExercise(workoutExercise);

            workout = new Workout("Never Skip Leg Day");
            workoutsByName.put(workout.getName(), workout);
            exercise = exercisesByName.get("Squats");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience, new ExerciseSets(4, 15));
            workout.addExercise(workoutExercise);
            exercise = exercisesByName.get("Lunges");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience, new ExerciseSets(3, 10));
            workout.addExercise(workoutExercise);

            workout = new Workout("Marathon Training Starts Here");
            workoutsByName.put(workout.getName(), workout);
            exercise = exercisesByName.get("Running");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseDistance(exercise, exerciseExperience, new ExerciseDistance(2.5, DistanceUnit.MILES));
            workout.addExercise(workoutExercise);
            exercise = exercisesByName.get("Exercise Bike");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseDuration(exercise, exerciseExperience, new ExerciseDuration(45, TimeUnit.MINUTES));
            workout.addExercise(workoutExercise);

            workout = new Workout("Work that Core, Get that Score!");
            workoutsByName.put(workout.getName(), workout);
            exercise = exercisesByName.get("Crunches");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience, new ExerciseSets(2, 25));
            workout.addExercise(workoutExercise);
            exercise = exercisesByName.get("Bicycle Kicks");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience, new ExerciseSets(2, 15));
            workout.addExercise(workoutExercise);

            workoutSessionsByDate = new TreeMap<>();
            workoutSession = new WorkoutSession(
                    (workoutsByName.get("Welcome to the Gun Show")),
                    new LocalDate().minusWeeks(1).withDayOfWeek(DateTimeConstants.THURSDAY),
                    false);
            workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

            workoutSession = new WorkoutSession(
                    (workoutsByName.get("Never Skip Leg Day")),
                    new LocalDate().withDayOfWeek(DateTimeConstants.TUESDAY),
                    false);
            workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

            workoutSession = new WorkoutSession(
                    (workoutsByName.get("Work that Core, Get that Score!")),
                    new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                    false);
            workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

            workoutSession = new WorkoutSession(
                    (workoutsByName.get("Never Skip Leg Day")),
                    new LocalDate().withDayOfWeek(DateTimeConstants.FRIDAY),
                    false);
            workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);

            workoutSession = new WorkoutSession(
                    (workoutsByName.get("Marathon Training Starts Here")),
                    new LocalDate().plusWeeks(1).withDayOfWeek(DateTimeConstants.TUESDAY),
                    false);
            workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);
        }

        /**
         * Closes a data access class
         */
        @Override
        public void close() {
        }

        /**
         * Retrieves a workout session scheduled on the given date from the database, if it exists. If
         * no workout session is found for that date, returns null.
         *
         * @param dateOfSession the date to get the workout session for
         * @return the workout session scheduled on the given date
         */
        @Override
        public WorkoutSession getWorkoutSession(LocalDate dateOfSession) {
            return workoutSessionsByDate.get(dateOfSession);
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
            List<WorkoutSession> sessionsInDateRange = new ArrayList<>();

            LocalDate currDate = startDate;
            while (!currDate.isAfter(endDate)) {
                if (workoutSessionsByDate.containsKey(currDate)) {
                    sessionsInDateRange.add(workoutSessionsByDate.get(currDate));
                }
                currDate = currDate.plusDays(1);
            }

            return sessionsInDateRange;
        }

        /**
         * Inserts a new workout session into the database
         *
         * @param workoutSession the new workout session to insert into the database
         */
        @Override
        public void insertWorkoutSession(WorkoutSession workoutSession) {
            workoutSessionsByDate.put(workoutSession.getDate(), workoutSession);
        }

        /**
         * Removes a workout session from the database, if it exists
         *
         * @param workoutSession the workout session to remove from the database
         */
        @Override
        public void removeWorkoutSession(WorkoutSession workoutSession) {
            workoutSessionsByDate.remove(workoutSession.getDate());
        }

        /**
         * Toggles the completed state of a workout in the database
         *
         * @param workoutSession the workout to change the state of
         */
        @Override
        public void toggleWorkoutComplete(WorkoutSession workoutSession) {
            workoutSessionsByDate.get(workoutSession.getDate()).toggleCompleted();
        }
    }
}
