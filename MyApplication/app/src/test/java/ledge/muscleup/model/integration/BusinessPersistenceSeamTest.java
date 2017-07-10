package ledge.muscleup.model.integration;

import junit.framework.TestCase;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.application.Main;
import ledge.muscleup.business.AccessExercises;
import ledge.muscleup.business.AccessWorkoutSessions;
import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;


/**
 * BusinessPersistenceSeamTest.java used to test seams between Business and Persistence layers
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-07-08
 */

public class BusinessPersistenceSeamTest extends TestCase {

    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

    @Before
    public void setUp() {
        Services.closeDataAccess();
        Services.createDataAccess(Main.dbName);
    }

    @After
    public void tearDown() {
        Services.closeDataAccess();
    }

    public void testAccessExercises() {
        System.out.println("\nStarting Integration test of AccessExercises to persistence");

        AccessExercises accessExercises = new AccessExercises();
        List<Exercise> exerciseList = new ArrayList<>();
        Exercise exercise;

        // TODO:
        // Test accessExercises.getExercisesList()
        // List<Exercise> getExercisesList()

        System.out.println("Finishing Integration test of AccessExercises to persistence\n");
    }

    public void testAccessWorkouts() {
        System.out.println("\nStarting Integration test of AccessWorkouts to persistence");

        AccessWorkouts accessWorkouts = new AccessWorkouts();
        List<Workout> workoutList = new ArrayList<>();
        Workout workout;

        // TODO:
        // Test accesWorkouts.getWorkout(workoutName)
        // Workout getWorkout(String workoutName)

        // TODO:
        // Test accessWorkouts.getWorkoutsList()
        // List<Workout> getWorkoutsList()

        // TODO:
        // Test accessWorkouts.getWorkoutNamesList()
        // List<String> getWorkoutNamesList()

        System.out.println("Finishing Integration test of AccessWorkouts to persistence\n");
    }

    public void testAccessWorkoutSessions() {
        System.out.println("\nStarting Integration test of AccessWorkoutSessions to persistence");

        AccessWorkoutSessions accessWorkoutSessions = new AccessWorkoutSessions();
        List<WorkoutSession> workoutSessionList = new ArrayList<>();
        WorkoutSession workoutSession;

        // TODO:
        // Test accessWorkoutSessions.getWorkoutSession(dateOfSession)
        // WorkoutSession getWorkoutSession(LocalDate dateOfSession)

        // TODO:
        // Test accessWorkoutSessions.getWorkoutSession(startDate, endDate)
        // List<WorkoutSession> getSessionsInDateRange(LocalDate startDate, LocalDate endDate)

        // TODO:
        // Test accessWorkoutSessions.getCurrentWeekSessions()
        // List<WorkoutSession> getCurrentWeekSessions()

        // TODO:
        // Test accessWorkoutSessions.insertWorkoutSession(workoutSession)
        // void insertWorkoutSession(WorkoutSession workoutSession)

        // TODO:
        // Test accessWorkoutSessions.removeWorkoutSession(workoutSession)
        // void removeWorkoutSession(WorkoutSession workoutSession)

        // TODO:
        // Test accessWorkoutSessions.toggleWorkoutCompleted(workoutSession)
        // void toggleWorkoutCompleted(WorkoutSession workoutSession)

        // TODO:
        // Test accessWorkoutSessions.newScheduledWeek(dayInWeek)
        // ScheduleWeek newScheduledWeek(LocalDate dayInWeek)

        // TODO:
        // Test accessWorkoutSessions.setToLastWeek(scheduleWeek)
        // void setToLastWeek(ScheduleWeek scheduleWeek)

        // TODO:
        // Test accessWorkoutSessions.setToNextWeek(scheduleWeek)
        // void setToNextWeek(ScheduleWeek scheduleWeek)

        // TODO:
        // Test accessWorkoutSessions.setToCurrentWeek(scheduleWeek)
        // void setToCurrentWeek(ScheduleWeek scheduleWeek)

        System.out.println("Finishing Integration test of AccessWorkoutSessions to persistence\n");
    }
}
