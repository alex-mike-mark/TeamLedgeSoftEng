package ledge.muscleup.unit.persistence;

import junit.framework.TestCase;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.enums.DistanceUnit;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.exercise.enums.TimeUnit;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;

/**
 * Used for testing the InterfaceWorkoutSessionDataAccess persistence interface
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-29
 *
 */
public class WorkoutSessionDataAccessTest extends TestCase {
    static private final int XP_HIGH_INTENSITY = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    static private final int XP_MEDIUM_INTENSITY = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    static private final int XP_LOW_INTENSITY = (ExerciseIntensity.LOW.ordinal() + 1) * 15;

    private static TemplateDataAccessStub dataAccess;

    ScheduleWeek scheduleWeek;
    /**
     * Constructor for the WorkoutSessionDataAccessTest
     */
    public WorkoutSessionDataAccessTest(String arg0)
    {
        super(arg0);
    }

    /**
     * Initializes the WorkoutSessionDataAccess to be used in the test
     */
    @Before
    public void setUp()
    {
        dataAccess = new TemplateDataAccessStub("Test Workout Sessions");
        dataAccess.open("Test Workout Sessions");
    }

    /**
     * Closes the DataAccess connection
     */
    @After
    public void tearDown() {
        dataAccess.close();
    }

    /**
     * Tests that getting a workout session with given date works properly
     */
    @Test
    public static void testGetWorkoutSession() {
        System.out.println("\nStarting testGetWorkoutSession");

        WorkoutSession workoutSession = dataAccess.getWorkoutSession(new LocalDate(2017, 06, 21));
        assertNotNull(workoutSession);
        assertEquals("Welcome to the Gun Show", workoutSession.getName());
        assertEquals(new LocalDate(2017, 06, 21), workoutSession.getDate());

        workoutSession = dataAccess.getWorkoutSession(new LocalDate(2017, 06, 25));
        assertNotNull(workoutSession);
        assertEquals("Never Skip Leg Day", workoutSession.getName());
        assertEquals(new LocalDate(2017, 06, 25), workoutSession.getDate());

        workoutSession = dataAccess.getWorkoutSession(new LocalDate(2017, 06, 26));
        assertNotNull(workoutSession);
        assertEquals("Work that Core, Get that Score!", workoutSession.getName());
        assertEquals(new LocalDate(2017, 06, 26), workoutSession.getDate());

        workoutSession = dataAccess.getWorkoutSession(new LocalDate(2017, 07, 05));
        assertNotNull(workoutSession);
        assertEquals("Marathon Training Starts Here", workoutSession.getName());
        assertEquals(new LocalDate(2017, 07, 05), workoutSession.getDate());

        workoutSession = dataAccess.getWorkoutSession(new LocalDate(2000, 1, 1));
        assertNull(workoutSession);

        System.out.println("Finishing testGetWorkoutSession\n");
    }


    /**
     * Tests getting workout sessions in a specified date range works properly
     */
    @Test
    public static void testGetSessionsInDateRange() {
        System.out.println("\nStarting testGetSessionsInDateRange");

        List<WorkoutSession> workoutSessionList1 = new ArrayList<>();

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(3, 10))

                }),
                new LocalDate(2017, 06, 25),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
                }),
                new LocalDate(2017, 06, 26),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(3, 10))

                }),
                new LocalDate(2017, 06, 29),
                false));

        List<WorkoutSession> workoutSessionList2 = dataAccess
                .getSessionsInDateRange(new LocalDate(2017, 06, 24), new LocalDate(2017, 06, 30));

        assertNotNull(workoutSessionList2);
        assertEquals(3, workoutSessionList2.size());
        assertEquals(workoutSessionList1.toString(), workoutSessionList2.toString());

        // First WorkoutSession in List
        workoutSessionList2 = dataAccess
                .getSessionsInDateRange(new LocalDate(2017, 06, 25), new LocalDate(2017, 06, 25));
        assertNotNull(workoutSessionList2);
        assertEquals(1, workoutSessionList2.size());

        // Last WorkoutSession in List
        workoutSessionList2 = dataAccess
                .getSessionsInDateRange(new LocalDate(2017, 06, 29), new LocalDate(2017, 06, 29));
        assertNotNull(workoutSessionList2);
        assertEquals(1, workoutSessionList2.size());

        // No WorkoutSession in range
        workoutSessionList2 = dataAccess
                .getSessionsInDateRange(new LocalDate(2017, 06, 27), new LocalDate(2017, 06, 27));
        assertNotNull(workoutSessionList2);

        assertEquals(0, workoutSessionList2.size());

        System.out.println("Finishing testGetSessionsInDateRange\n");
    }


    /**
     * Tests that inserting a workout session works properly
     */
    @Test
    public static void testInsertWorkoutSession() {
        System.out.println("\nStarting testInsertWorkoutSession");

        // Insert new WorkoutSession
        assertEquals(5, dataAccess.getWorkoutSessionsList().size());

        dataAccess.insertWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.THURSDAY),
                false));

        assertEquals(6, dataAccess.getWorkoutSessionsList().size());

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

        assertEquals(6, dataAccess.getWorkoutSessionsList().size());

        System.out.println("Finishing testInsertWorkoutSession\n");
    }

    /**
     * Tests that removing a workout session works properly
     */
    @Test
    public static void testRemoveWorkoutSession() {
        System.out.println("\nStarting testRemoveWorkoutSession");

        // Remove middle WorkoutSession from list
        assertEquals(5, dataAccess.getWorkoutSessionsList().size());

        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
                }),
                new LocalDate(2017, 06, 29),
                false));

        assertEquals(4, dataAccess.getWorkoutSessionsList().size());

        // Trying to remove on a day where nothing exists
        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
                }),
                new LocalDate(2017, 06, 29),
                false));

        assertEquals(4, dataAccess.getWorkoutSessionsList().size());

        // Remove First WorkoutSession in list
        assertEquals(4, dataAccess.getWorkoutSessionsList().size());

        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_MEDIUM_INTENSITY, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                XP_LOW_INTENSITY, new ExerciseSets(3, 10))

                }),
                new LocalDate(2017, 06, 25),
                false));

        assertEquals(3, dataAccess.getWorkoutSessionsList().size());

        // Remove Last WorkoutSession in list
        assertEquals(3, dataAccess.getWorkoutSessionsList().size());
        dataAccess.removeWorkoutSession(new WorkoutSession(
                new Workout("Marathon Training Starts Here", new WorkoutExercise[]{
                        new WorkoutExerciseDistance(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO),
                                XP_HIGH_INTENSITY, new ExerciseDistance(2.5, DistanceUnit.MILES)),
                        new WorkoutExerciseDuration(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM, ExerciseType.CARDIO),
                                XP_MEDIUM_INTENSITY, new ExerciseDuration(45, TimeUnit.MINUTES))

                }),
                new LocalDate(2017, 07, 05),
                false));

        assertEquals(2, dataAccess.getWorkoutSessionsList().size());

        System.out.println("Finishing testRemoveWorkoutSession\n");
    }

    /**
     * Tests that toggling workout completed works properly
     */
    @Test
    public static void testToggleWorkoutCompleted() {
        System.out.println("\nStarting testToggleWorkoutCompleted");

        LocalDate localDate = new LocalDate(2017, 06, 21);

        WorkoutSession workoutSession1 = dataAccess.getWorkoutSession(localDate);
        assertFalse(workoutSession1.isComplete());
        dataAccess.toggleWorkoutComplete(workoutSession1);
        assertTrue(workoutSession1.isComplete());
        dataAccess.toggleWorkoutComplete(workoutSession1);
        assertFalse(workoutSession1.isComplete());

        System.out.println("Finishing testToggleWorkoutCompleted\n");
    }
}
