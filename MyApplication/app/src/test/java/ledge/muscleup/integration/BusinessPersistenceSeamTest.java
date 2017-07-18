package ledge.muscleup.integration;

import junit.framework.TestCase;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import ledge.muscleup.application.Services;
import ledge.muscleup.application.Main;
import ledge.muscleup.business.AccessExercises;
import ledge.muscleup.business.AccessExperience;
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
import ledge.muscleup.model.experience.CompletedWorkoutRecord;
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
    private int firstDayOfWeek;
    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

    @Before
    public void setUp() {
        Services.closeDataAccess();
        Services.createDataAccess(Main.dbName);
        firstDayOfWeek = DateTimeConstants.MONDAY;
    }

    @After
    public void tearDown() throws IOException{
        Services.closeDataAccess();
    /*    File sourceFile = new File("/app/src/main/assets/db/MU_DB.script");
        File destFile = new File("/app/MU_DB.script");
            if(!destFile.exists()) {
                destFile.createNewFile();
            }

            FileChannel source = null;
            FileChannel destination = null;

            try {
                source = new FileInputStream(sourceFile).getChannel();
                destination = new FileOutputStream(destFile).getChannel();
                destination.transferFrom(source, 0, source.size());
            }
            finally {
                if (source != null) {
                    source.close();
                }
                if (destination != null) {
                    destination.close();
                }
            } */
    }

    public void testAccessExercises() {
        System.out.println("\nStarting Integration test of AccessExercises to persistence");

        AccessExercises accessExercises = new AccessExercises();

        // Exercises already in DB
        List<Exercise> exerciseList1 = new ArrayList<>();
        exerciseList1.add(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM));
        exerciseList1.add(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM));
        exerciseList1.add(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO));
        exerciseList1.add(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                ExerciseType.CARDIO));
        exerciseList1.add(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE));
        exerciseList1.add(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE));
        exerciseList1.add(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG));
        exerciseList1.add(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG));

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

        workout = accessWorkouts.getWorkout("Never Skip Leg Day");
        assertNotNull(workout);
        assertEquals("Never Skip Leg Day", workout.getName());
        assertEquals(2, workout.numExercises());

        // Workouts by object already in db
        List<Workout> workoutList1 = new ArrayList<>();
        workoutList1.add(new Workout("Welcome to the Gun Show", new WorkoutExercise[]{
                new WorkoutExerciseSetsAndWeight(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM),
                        xpLowIntensity, new ExerciseSetsAndWeight(3, 15, 15.0, WeightUnit.LBS)),
                new WorkoutExerciseSets(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM),
                        xpHighIntensity, new ExerciseSets(2, 15))
        }));
        workoutList1.add(new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        xpMediumIntensity, new ExerciseSets(4, 15)),
                new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        xpMediumIntensity, new ExerciseSets(3, 10))
        }));
        workoutList1.add(new Workout("Marathon Training Starts Here", new WorkoutExercise[]{
                new WorkoutExerciseDistance(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO),
                        xpHighIntensity, new ExerciseDistance(2, DistanceUnit.MILES)),
                new WorkoutExerciseDuration(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM, ExerciseType.CARDIO),
                        xpMediumIntensity, new ExerciseDuration(45, TimeUnit.MINUTES))
        }));

        workoutList1.add(new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
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

        WorkoutSession workoutSession = accessWorkoutSessions.getWorkoutSession(new LocalDate(2017, 6, 29));
        assertNotNull(workoutSession);
        assertEquals("Never Skip Leg Day", workoutSession.getName());
        assertEquals(new LocalDate(2017, 6, 29), workoutSession.getDate());

        workoutSession = accessWorkoutSessions.getWorkoutSession(new LocalDate(2000, 01, 01));
        assertNull(workoutSession);


        scheduleWeek = accessWorkoutSessions.newScheduledWeek(1, LocalDate.now());
        List<WorkoutSession> workoutSessionList1 = new ArrayList<>();

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate(2017, 6, 25),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 25))
                }),
                new LocalDate(2017, 6, 26),
                false));

        workoutSessionList1.add(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate(2017, 6, 29),
                false));

        List<WorkoutSession> workoutSessionList2 = accessWorkoutSessions
                .getSessionsInDateRange(new LocalDate(2017, 6, 25), new LocalDate(2017, 6, 29));

        assertNotNull(workoutSessionList2);
        assertEquals(3, scheduleWeek.getNumSessionsInWeek(workoutSessionList2));
        assertEquals(workoutSessionList1.toString(), workoutSessionList2.toString());


        assertNotNull(accessWorkoutSessions.getCurrentWeekSessions(1));

        scheduleWeek = accessWorkoutSessions.newScheduledWeek(1, LocalDate.now());
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        accessWorkoutSessions.insertWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY),
                false));

        scheduleWeek = accessWorkoutSessions.newScheduledWeek(1, LocalDate.now());
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));


        scheduleWeek = accessWorkoutSessions.newScheduledWeek(1, LocalDate.now());
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));;

        accessWorkoutSessions.removeWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY),
                false));
        scheduleWeek = accessWorkoutSessions.newScheduledWeek(1, LocalDate.now());
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));


        LocalDate localDate = new LocalDate(2017, 6, 25);

        // Should return true both times because it is already true and user cannot set complete workout to incomplete
        WorkoutSession workoutSession1 = accessWorkoutSessions.getWorkoutSession(localDate);
        assertTrue(workoutSession1.isComplete());
        accessWorkoutSessions.toggleWorkoutCompleted(workoutSession1);
        workoutSession1 = accessWorkoutSessions.getWorkoutSession(localDate);
        assertTrue(workoutSession1.isComplete());


        scheduleWeek = accessWorkoutSessions.newScheduledWeek(1, new LocalDate());
        assertNotNull(scheduleWeek);
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));


        scheduleWeek = accessWorkoutSessions.newScheduledWeek(firstDayOfWeek, LocalDate.now());
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        accessWorkoutSessions.insertWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                LocalDate.now().plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY),
                false));

        accessWorkoutSessions.setToNextWeek(scheduleWeek);
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));
        assertEquals(new LocalDate().plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));

        accessWorkoutSessions.removeWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate().plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY),
                false));
        scheduleWeek = accessWorkoutSessions.newScheduledWeek(firstDayOfWeek, LocalDate.now().plusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY));
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        scheduleWeek = accessWorkoutSessions.newScheduledWeek(firstDayOfWeek, LocalDate.now());
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        accessWorkoutSessions.insertWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                LocalDate.now().minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY),
                false));

        accessWorkoutSessions.setToLastWeek(scheduleWeek);
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));
        assertEquals(new LocalDate().minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getWeekday(DateTimeConstants.MONDAY));

        accessWorkoutSessions.removeWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate().minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY),
                false));
        scheduleWeek = accessWorkoutSessions.newScheduledWeek(firstDayOfWeek, LocalDate.now().minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY));
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        scheduleWeek = accessWorkoutSessions.newScheduledWeek(firstDayOfWeek, LocalDate.now());
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));
        accessWorkoutSessions.setToNextWeek(scheduleWeek);

        accessWorkoutSessions.insertWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY),
                false));

        accessWorkoutSessions.setToCurrentWeek(scheduleWeek);
        assertEquals(LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY), scheduleWeek.getFirstDayOfWeek());
        assertEquals(1, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        accessWorkoutSessions.setToLastWeek(scheduleWeek);
        accessWorkoutSessions.removeWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY),
                false));
        accessWorkoutSessions.setToCurrentWeek(scheduleWeek);
        assertEquals(0, scheduleWeek.getNumSessionsInWeek(scheduleWeek.getWorkoutSessionList()));

        System.out.println("Finishing Integration test of AccessWorkoutSessions to persistence\n");
    }

    public void testAccessExperience() {
        System.out.println("\nStarting Integration test of AccessExperience to persistence");

        AccessExperience accessExperience = new AccessExperience();
        AccessWorkoutSessions accessWorkoutSessions = new AccessWorkoutSessions(); //needed in order to complete workouts

        assertEquals(0, accessExperience.getCompletedWorkouts().size());
        assertNull(accessExperience.getMostRecentCompletedWorkout());

        accessWorkoutSessions.insertWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY),
                false)
        );

        accessWorkoutSessions.toggleWorkoutCompleted(
                accessWorkoutSessions.getWorkoutSession(LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY)));
        assertEquals(1, accessExperience.getCompletedWorkouts().size());
        assertNotNull(accessExperience.getCompletedWorkouts().get(0));
        assertEquals("Never Skip Leg Day", accessExperience.getCompletedWorkouts().get(0).getWorkoutName());

        assertEquals(accessExperience.getMostRecentCompletedWorkout().getWorkoutName(), "Never Skip Leg Day");
        assertEquals(accessExperience.getMostRecentCompletedWorkout().getExperienceGained(),
                accessWorkoutSessions.getWorkoutSession(LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY))
                .getExperienceValue());

        accessWorkoutSessions.insertWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 25))
                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.TUESDAY),
                false)
        );
        accessWorkoutSessions.toggleWorkoutCompleted(
                accessWorkoutSessions.getWorkoutSession(LocalDate.now().withDayOfWeek(DateTimeConstants.TUESDAY)));
        assertEquals(2, accessExperience.getCompletedWorkouts().size());
        assertEquals("Never Skip Leg Day", accessExperience.getCompletedWorkouts().get(0).getWorkoutName());
        assertEquals("Work that Core, Get that Score!", accessExperience.getCompletedWorkouts().get(1).getWorkoutName());
        assertTrue(accessExperience.getCompletedWorkouts().get(0).getExperienceAfterCompletion() >
                accessExperience.getCompletedWorkouts().get(1).getExperienceAfterCompletion());

        assertEquals("Work that Core, Get that Score!",
                accessExperience.getMostRecentCompletedWorkout().getWorkoutName());
        assertEquals(accessExperience.getMostRecentCompletedWorkout().getExperienceGained(),
                accessWorkoutSessions.getWorkoutSession(LocalDate.now().withDayOfWeek(DateTimeConstants.TUESDAY))
                .getExperienceValue());

        accessWorkoutSessions.removeWorkoutSession(new WorkoutSession(
                new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(4, 15)),
                        new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                                xpLowIntensity, new ExerciseSets(3, 10))

                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY),
                false)
        );

        accessWorkoutSessions.removeWorkoutSession(new WorkoutSession(
                new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                        new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                                xpLowIntensity, new ExerciseSets(2, 25)),
                        new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                                xpHighIntensity, new ExerciseSets(2, 25))
                }),
                LocalDate.now().withDayOfWeek(DateTimeConstants.TUESDAY),
                false)
        );

        System.out.println("Finishing Integration test of AccessExperience to persistence\n");
    }
}
