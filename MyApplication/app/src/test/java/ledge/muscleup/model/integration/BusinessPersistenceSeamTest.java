package ledge.muscleup.model.integration;

import junit.framework.TestCase;

import org.joda.time.DateTimeConstants;
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
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.enums.DistanceUnit;
import ledge.muscleup.model.exercise.enums.ExerciseIntensity;
import ledge.muscleup.model.exercise.enums.ExerciseType;
import ledge.muscleup.model.exercise.enums.TimeUnit;
import ledge.muscleup.model.exercise.enums.WeightUnit;
import ledge.muscleup.model.schedule.ScheduleWeek;
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

    final int xpHighIntensity = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    final int xpMediumIntensity = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    final int xpLowIntensity = (ExerciseIntensity.LOW.ordinal() + 1) * 15;

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

        // Exercises already in DB
        List<Exercise> exerciseList1 = new ArrayList<>();
        exerciseList1.add(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM, false));
        exerciseList1.add(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM, false));
        exerciseList1.add(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO, false));
        exerciseList1.add(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                ExerciseType.CARDIO, false));
        exerciseList1.add(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE, false));
        exerciseList1.add(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE,
                false));
        exerciseList1.add(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false));
        exerciseList1.add(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false));

        List<Exercise> exerciseList2 = accessExercises.getExercisesList();

        assertNotNull(exerciseList2);
        assertEquals(exerciseList1.toString(), exerciseList2.toString());

        System.out.println("Finishing Integration test of AccessExercises to persistence\n");
    }

    public void testAccessWorkouts() {
        System.out.println("\nStarting Integration test of AccessWorkouts to persistence");

        AccessWorkouts accessWorkouts = new AccessWorkouts();

        // Workouts in db
        Workout workout = accessWorkouts.getWorkout("Welcome to the Gun Show");
        assertNotNull(workout);
        assertEquals("Welcome to the Gun Show", workout.getName());
        assertEquals(2, workout.numExercises());
        assertEquals(false, workout.isFavourite());

        workout = accessWorkouts.getWorkout("Never Skip Leg Day");
        assertNotNull(workout);
        assertEquals("Never Skip Leg Day", workout.getName());
        assertEquals(2, workout.numExercises());
        assertEquals(false, workout.isFavourite());

        // Workouts by object already in db
        List<Workout> workoutList1 = new ArrayList<>();
        workoutList1.add(new Workout("Welcome to the Gun Show", false, new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM),
                        xpLowIntensity, new ExerciseSets(3, 15)),
                new WorkoutExerciseSets(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM),
                        xpHighIntensity, new ExerciseSets(2, 15))
        }));
        workoutList1.add(new Workout("Never Skip Leg Day", false, new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        xpMediumIntensity, new ExerciseSets(4, 15)),
                new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        xpMediumIntensity, new ExerciseSets(3, 10))
        }));
        workoutList1.add(new Workout("Marathon Training Starts Here", false, new WorkoutExercise[]{
                new WorkoutExerciseDistance(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO),
                        xpHighIntensity, new ExerciseDistance(2, DistanceUnit.MILES)),
                new WorkoutExerciseDuration(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM, ExerciseType.CARDIO),
                        xpMediumIntensity, new ExerciseDuration(45, TimeUnit.MINUTES))
        }));

        workoutList1.add(new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                        xpLowIntensity, new ExerciseSets(2, 25)),
                new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                        xpHighIntensity, new ExerciseSets(2, 25))
        }));

        List<Workout> workoutList2 = accessWorkouts.getWorkoutsList();
        assertNotNull(workoutList2);
        assertEquals(workoutList1.toString(), workoutList2.toString());

        // Workouts by name already in db
        List<String> namesList = new ArrayList<>();

        namesList.add("Welcome to the Gun Show");
        namesList.add("Never Skip Leg Day");
        namesList.add("Marathon Training Starts Here");
        namesList.add("Work that Core, Get that Score!");

        assertEquals(namesList, accessWorkouts.getWorkoutNamesList());

        System.out.println("Finishing Integration test of AccessWorkouts to persistence\n");
    }

    public void testAccessWorkoutSessions() {
        System.out.println("\nStarting Integration test of AccessWorkoutSessions to persistence");

        AccessWorkoutSessions accessWorkoutSessions = new AccessWorkoutSessions();
        ScheduleWeek scheduleWeek;

        // TODO: Test accessWorkoutSessions.getWorkoutSession(dateOfSession)
        // WorkoutSession getWorkoutSession(LocalDate dateOfSession)

        // TODO: Uncomment lines
       /* WorkoutSession workoutSession = accessWorkoutSessions.getWorkoutSession(new LocalDate().minusWeeks(1).withDayOfWeek(DateTimeConstants.THURSDAY));
        assertNotNull(workoutSession);
        assertEquals("Welcome to the Gun Show", workoutSession.getName());
        assertEquals(new LocalDate().minusWeeks(1).withDayOfWeek(DateTimeConstants.THURSDAY), workoutSession.getDate());

        workoutSession = accessWorkoutSessions.getWorkoutSession(new LocalDate(2000, 01, 01));
        assertNull(workoutSession);*/

       
        // TODO: Test accessWorkoutSessions.getWorkoutSession(startDate, endDate)
        // List<WorkoutSession> getSessionsInDateRange(LocalDate startDate, LocalDate endDate)

        // TODO: Uncomment line
        scheduleWeek = accessWorkoutSessions.newScheduledWeek(LocalDate.now());
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

        List<WorkoutSession> workoutSessionList2 = accessWorkoutSessions
                .getSessionsInDateRange(LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY), LocalDate.now().withDayOfWeek(DateTimeConstants.SUNDAY));

        // TODO: Uncomment lines
        //assertNotNull(workoutSessionList2);
        //assertEquals(3, scheduleWeek.getNumSessionsInWeek(workoutSessionList2));
        //assertEquals(workoutSessionList1.toString(), workoutSessionList2.toString());


        // TODO: Test accessWorkoutSessions.getCurrentWeekSessions()
        // List<WorkoutSession> getCurrentWeekSessions()

        // TODO: Uncomment lines
        //assertNotNull(accessWorkoutSessions.getCurrentWeekSessions());
        //assertEquals(workoutSessionList1.toString(), accessWorkoutSessions.getCurrentWeekSessions().toString());


        // TODO: Test accessWorkoutSessions.insertWorkoutSession(workoutSession)
        // void insertWorkoutSession(WorkoutSession workoutSession)

        // TODO: Uncomment lines
        //scheduleWeek = accessWorkoutSessions.newScheduledWeek(LocalDate.now());
        //assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));


        /*accessWorkoutSessions.insertWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 15))
                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.THURSDAY),
                false));*/

        //scheduleWeek = accessWorkoutSessions.newScheduledWeek(LocalDate.now());
        //assertEquals(4, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));


        // TODO: Test accessWorkoutSessions.removeWorkoutSession(workoutSession)
        // void removeWorkoutSession(WorkoutSession workoutSession)

        // TODO: Uncomment lines
        //scheduleWeek = accessWorkoutSessions.newScheduledWeek(LocalDate.now());
        //assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));;

        /*accessWorkoutSessions.removeWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", false, new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 15))
                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.WEDNESDAY),
                false));
        scheduleWeek = dataAccess.newScheduledWeek(LocalDate.now());
        assertEquals(2, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));*/


        // TODO: Test accessWorkoutSessions.toggleWorkoutCompleted(workoutSession)
        // void toggleWorkoutCompleted(WorkoutSession workoutSession)

        LocalDate localDate = new LocalDate(LocalDate.now().withDayOfWeek(DateTimeConstants.TUESDAY));

        // TODO: Uncomment lines
        /*WorkoutSession workoutSession1 = accessWorkoutSessions.getWorkoutSession(localDate);
        assertFalse(workoutSession1.isComplete());
        accessWorkoutSessions.toggleWorkoutCompleted(workoutSession1);
        assertTrue(workoutSession1.isComplete());
        accessWorkoutSessions.toggleWorkoutCompleted(workoutSession1);
        assertFalse(workoutSession1.isComplete());*/


        // TODO: Test accessWorkoutSessions.newScheduledWeek(dayInWeek)
        // ScheduleWeek newScheduledWeek(LocalDate dayInWeek)

        // TODO: Uncomment lines
        //scheduleWeek = accessWorkoutSessions.newScheduledWeek(LocalDate.now());
        //assertNotNull(scheduleWeek);
        //assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));


        // TODO: Test accessWorkoutSessions.setToLastWeek(scheduleWeek)
        // void setToLastWeek(ScheduleWeek scheduleWeek)

        // TODO: Uncomment lines
        //scheduleWeek = accessWorkoutSessions.newScheduledWeek(LocalDate.now());
        //accessWorkoutSessions.setToLastWeek(scheduleWeek);
        //assertEquals(LocalDate.now().minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));
        //assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));


        // TODO: Test accessWorkoutSessions.setToNextWeek(scheduleWeek)
        // void setToNextWeek(ScheduleWeek scheduleWeek)

        // TODO: Uncomment lines
        //scheduleWeek = accessWorkoutSessions.newScheduledWeek(LocalDate.now());
        //accessWorkoutSessions.setToNextWeek(scheduleWeek);
        //assertEquals(LocalDate.now().plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));
        //assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));


        // TODO: Test accessWorkoutSessions.setToCurrentWeek(scheduleWeek)
        // void setToCurrentWeek(ScheduleWeek scheduleWeek)

        // TODO: Uncomment lines
        /*scheduleWeek = accessWorkoutSessions.newScheduledWeek(LocalDate.now());
        accessWorkoutSessions.setToNextWeek(scheduleWeek);
        assertEquals(LocalDate.now().plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));
        accessWorkoutSessions.setToCurrentWeek(scheduleWeek);
        assertEquals(LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));
        assertEquals(3, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));*/

        System.out.println("Finishing Integration test of AccessWorkoutSessions to persistence\n");
    }
}
