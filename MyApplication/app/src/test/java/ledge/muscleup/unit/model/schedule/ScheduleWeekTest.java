package ledge.muscleup.unit.model.schedule;

import junit.framework.TestCase;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Assert;
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
import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.enums.*;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;

import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.persistence.InterfaceWorkoutSessionDataAccess;

/**
 * Tests for the ScheduleWeek
 *
 * @author Cole Kehler
 * @version 1.0
 * @since 2017-06-06
 */
public class ScheduleWeekTest extends TestCase {
    private ScheduleWeek scheduleWeek;
    private int weekStartDay;
    private InterfaceAccessWorkoutSessions dataAccess;

    /**
     * Constructor for the ScheduleWeekTest
     */
    public ScheduleWeekTest() { super(); }

    /**
     * Initializes the ScheduleWeek to be used in the test
     */
    @Before
    public void testInit(){
        TemplateWorkoutSessionDataAccess templateDataAccess = new TemplateWorkoutSessionDataAccess();

        templateDataAccess.open(null);
        dataAccess = new AccessWorkoutSessions(templateDataAccess);
        weekStartDay = DateTimeConstants.MONDAY;
        scheduleWeek = new ScheduleWeek(weekStartDay, dataAccess.getCurrentWeekSessions(weekStartDay));
    }

    /**
     * Tests that getFirstDayOfWeek works properly
     */
    @Test
    public void getFirstDayOfWeekTest() {
        LocalDate firstDayOfWeek = LocalDate.now().withDayOfWeek(weekStartDay);
        if (firstDayOfWeek.isAfter(LocalDate.now())){
            firstDayOfWeek = firstDayOfWeek.minusWeeks(1);
        }
        Assert.assertTrue("Returned unexpected first day of week",
                scheduleWeek.getFirstDayOfWeek().isEqual(firstDayOfWeek));

        weekStartDay = DateTimeConstants.SUNDAY;
        firstDayOfWeek = LocalDate.now().withDayOfWeek(weekStartDay);
        scheduleWeek = new ScheduleWeek(weekStartDay, dataAccess.getCurrentWeekSessions(weekStartDay));
        if (firstDayOfWeek.isAfter(LocalDate.now())){
            firstDayOfWeek = firstDayOfWeek.minusWeeks(1);
        }
        Assert.assertTrue("Returned unexpected first day of week",
                scheduleWeek.getFirstDayOfWeek().isEqual(firstDayOfWeek));

        weekStartDay = DateTimeConstants.WEDNESDAY;
        firstDayOfWeek = LocalDate.now().withDayOfWeek(weekStartDay);
        scheduleWeek = new ScheduleWeek(weekStartDay, dataAccess.getCurrentWeekSessions(weekStartDay));
        if (firstDayOfWeek.isAfter(LocalDate.now())){
            firstDayOfWeek = firstDayOfWeek.minusWeeks(1);
        }
        Assert.assertTrue("Returned unexpected first day of week",
                scheduleWeek.getFirstDayOfWeek().isEqual(firstDayOfWeek));
    }

    /**
     * Tests that getting a day of the current week works properly
     */
    @Test
    public void getWeekdayTest(){
        Assert.assertTrue("Returned unexpected weekday value",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY).isEqual(new LocalDate().withDayOfWeek(DateTimeConstants.MONDAY)));
        Assert.assertTrue("Returned unexpected weekday value",
                scheduleWeek.getWeekday(DateTimeConstants.SUNDAY).isEqual(new LocalDate().withDayOfWeek(DateTimeConstants.SUNDAY)));
    }

    /**
     * Tests that checking if a workout exists on a day and getting a workout on a day of the
     * current week works properly
     */
    @Test
    public void scheduledWorkoutsTest(){
        Assert.assertFalse("Returned that day is empty when it isn't",
                scheduleWeek.isDayEmpty(DateTimeConstants.TUESDAY));
        Assert.assertTrue("Returned that day isn't empty when it is",
                scheduleWeek.isDayEmpty(DateTimeConstants.THURSDAY));

        Assert.assertEquals("Returned a workout for a day where there shouldn't be one",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(), null);
        Assert.assertEquals("Didn't return the workout scheduled for a day",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.TUESDAY).getName(),
                "Never Skip Leg Day");
    }

    /**
     * Tests that changing the current week to the next and previous weeks works properly
     */
    @Test
    public void changeWeekTest(){
        LocalDate currWeekStart = scheduleWeek.getWeekday(DateTimeConstants.MONDAY);

        dataAccess.setToNextWeek(scheduleWeek);

        Assert.assertEquals("Improperly incremented week",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY), currWeekStart.plusWeeks(1));
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.TUESDAY).getName(),
                "Marathon Training Starts Here");

        dataAccess.setToCurrentWeek(scheduleWeek);
        Assert.assertEquals("Improperly Set to Current week",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY), LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY));
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.TUESDAY).getName(), "Never Skip Leg Day");
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.WEDNESDAY).getName(), "Work that Core, Get that Score!");
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.FRIDAY).getName(), "Never Skip Leg Day");


        dataAccess.setToLastWeek(scheduleWeek);
        Assert.assertEquals("Improperly decremented week",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY), LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY).minusWeeks(1));
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.THURSDAY).getName(),
                "Welcome to the Gun Show");


        dataAccess.setToLastWeek(scheduleWeek);

        Assert.assertEquals("Improperly decremented week",
                scheduleWeek.getWeekday(DateTimeConstants.MONDAY), LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY).minusWeeks(2));
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.MONDAY).getName(), null);
        Assert.assertEquals("Returned unexpected workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.SUNDAY).getName(), null);
    }

    /**
     * Tests that a workout can be added to and removed from the week
     */
    @Test
    public void addRemoveWorkoutTest(){
        Assert.assertFalse("Removed a workout when there isn't one",
                scheduleWeek.removeWorkoutSession(DateTimeConstants.THURSDAY));
        Assert.assertTrue("Didn't remove workout",
                scheduleWeek.removeWorkoutSession(DateTimeConstants.WEDNESDAY));

        Assert.assertEquals("Improperly deleted a workout",
                scheduleWeek.getScheduledWorkout(DateTimeConstants.WEDNESDAY), null);
    }

    /**
     * A template workout session access class for use in testing ScheduleWeek
     *
     * @author Cole Kehler
     * @version 3.0
     * @since 2017-07-13
     */
    class TemplateWorkoutSessionDataAccess implements InterfaceWorkoutSessionDataAccess {
        private Map<String, Workout> workoutsByName;
        private Map<String, Exercise> exercisesByName;
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
        public void close() { }

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