package ledge.muscleup.unit.business;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ledge.muscleup.business.AccessWorkouts;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.WorkoutExerciseDistance;
import ledge.muscleup.model.exercise.WorkoutExerciseDuration;
import ledge.muscleup.model.exercise.WorkoutExerciseSets;
import ledge.muscleup.model.exercise.WorkoutExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.enums.*;
import ledge.muscleup.business.InterfaceAccessWorkouts;

import ledge.muscleup.model.exercise.ExerciseDistance;
import ledge.muscleup.model.exercise.ExerciseDuration;
import ledge.muscleup.model.exercise.ExerciseSets;
import ledge.muscleup.model.exercise.ExerciseSetsAndWeight;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.persistence.InterfaceWorkoutDataAccess;

/**
 * AccessWorkoutsTest.java used to test AccessWorkouts.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-24
 */

public class AccessWorkoutsTest extends TestCase {
    private final int XP_HIGH_INTENSITY = (ExerciseIntensity.HIGH.ordinal() + 1) * 15;
    private final int XP_MEDIUM_INTENSITY = (ExerciseIntensity.MEDIUM.ordinal() + 1) * 15;
    private final int XP_LOW_INTENSITY = (ExerciseIntensity.LOW.ordinal() + 1) * 15;

    private InterfaceAccessWorkouts dataAccess;

    /**
     * Constructor for the AccessWorkoutsTest
     */
    public AccessWorkoutsTest(String arg0) {
        super(arg0);
    }

    /**
     * Initializes the AccessWorkouts to be used in the test
     */
    @Before
    public void setUp() {
        InterfaceWorkoutDataAccess templateDataAccess = new TemplateWorkoutDataAccess();

        templateDataAccess.open(null);
        dataAccess = new AccessWorkouts(templateDataAccess);
    }

    /**
     * Tests that getting a workout works properly
     */
    @Test
    public void testGetWorkout() {
        System.out.println("\nStarting testGetWorkout");

        // Workouts should already be in db
        Workout workout = dataAccess.getWorkout("Welcome to the Gun Show");
        assertNotNull(workout);
        assertEquals("Welcome to the Gun Show", workout.getName());
        assertEquals(2, workout.numExercises());

        workout = dataAccess.getWorkout("Never Skip Leg Day");
        assertNotNull(workout);
        assertEquals("Never Skip Leg Day", workout.getName());
        assertEquals(2, workout.numExercises());

        workout = dataAccess.getWorkout("Marathon Training Starts Here");
        assertNotNull(workout);
        assertEquals("Marathon Training Starts Here", workout.getName());
        assertEquals(2, workout.numExercises());

        workout = dataAccess.getWorkout("Work that Core, Get that Score!");
        assertNotNull(workout);
        assertEquals("Work that Core, Get that Score!", workout.getName());
        assertEquals(2, workout.numExercises());

        System.out.println("Finishing testGetWorkout\n");
    }

    /**
     * Tests that getting the list of workouts works properly
     */
    @Test
    public void testGetWorkoutsList() {
        System.out.println("\nStarting testGetWorkoutsList");

        // Workouts by object already in list
        List<Workout> workoutList1 = new ArrayList<>();
        workoutList1.add(new Workout("Never Skip Leg Day", new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        XP_MEDIUM_INTENSITY, new ExerciseSets(4, 15)),
                new WorkoutExerciseSets(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG),
                        XP_MEDIUM_INTENSITY, new ExerciseSets(3, 10))
        }));
        workoutList1.add(new Workout("Marathon Training Starts Here", new WorkoutExercise[]{
                new WorkoutExerciseDistance(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO),
                        XP_HIGH_INTENSITY, new ExerciseDistance(2.5, DistanceUnit.MILES)),
                new WorkoutExerciseDuration(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM, ExerciseType.CARDIO),
                        XP_MEDIUM_INTENSITY, new ExerciseDuration(45, TimeUnit.MINUTES))
        }));
        workoutList1.add(new Workout("Welcome to the Gun Show", new WorkoutExercise[]{
                new WorkoutExerciseSetsAndWeight(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM),
                        XP_LOW_INTENSITY, new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS)),
                new WorkoutExerciseSets(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM),
                        XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
        }));
        workoutList1.add(new Workout("Work that Core, Get that Score!", new WorkoutExercise[]{
                new WorkoutExerciseSets(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE),
                        XP_LOW_INTENSITY, new ExerciseSets(2, 25)),
                new WorkoutExerciseSets(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE),
                        XP_HIGH_INTENSITY, new ExerciseSets(2, 15))
        }));

        List<Workout> workoutList2 = dataAccess.getWorkoutsList();
        assertNotNull(workoutList2);
        assertEquals(workoutList1.toString(), workoutList2.toString());

        System.out.println("Finishing testGetWorkoutsList\n");
    }

    /**
     * Tests that getting the list of workout names works properly
     */
    @Test
    public void testGetWorkoutNamesList() {
        System.out.println("\nStarting testGetWorkoutNamesList");

        // Workouts by name already in list
        List<String> namesList = new ArrayList<>();

        namesList.add("Never Skip Leg Day");
        namesList.add("Marathon Training Starts Here");
        namesList.add("Welcome to the Gun Show");
        namesList.add("Work that Core, Get that Score!");

        assertEquals(namesList, dataAccess.getWorkoutNamesList());

        System.out.println("Finishing testGetWorkoutNamesList\n");
    }

    /**
     * Tests that getting the recommended workout name works properly
     */
    @Test
    public void testGetRecommendedWorkout() {
        System.out.println("\nStarting testGetRecommendedWorkout");

        assertEquals("Never Skip Leg Day", dataAccess.getSuggestedWorkout());

        System.out.println("Finishing testGetRecommendedWorkout\n");
    }

    private class TemplateWorkoutDataAccess implements InterfaceWorkoutDataAccess {
        private Map<String, Exercise> exercisesByName;
        private Map<String, Workout> workoutsByName;

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
            workoutExercise = new WorkoutExerciseSetsAndWeight(exercise, exerciseExperience,new ExerciseSetsAndWeight(3, 10, 15, WeightUnit.LBS));
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
            workoutExercise = new WorkoutExerciseDistance(exercise, exerciseExperience,new ExerciseDistance(2.5, DistanceUnit.MILES));
            workout.addExercise(workoutExercise);
            exercise = exercisesByName.get("Exercise Bike");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseDuration(exercise, exerciseExperience, new ExerciseDuration(45, TimeUnit.MINUTES));
            workout.addExercise(workoutExercise);

            workout = new Workout("Work that Core, Get that Score!");
            workoutsByName.put(workout.getName(), workout);
            exercise = exercisesByName.get("Crunches");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience,new ExerciseSets(2, 25));
            workout.addExercise(workoutExercise);
            exercise = exercisesByName.get("Bicycle Kicks");
            exerciseExperience = (exercise.getIntensity().ordinal() + 1) * xpPerIntensityLevel;
            workoutExercise = new WorkoutExerciseSets(exercise, exerciseExperience,new ExerciseSets(2, 15));
            workout.addExercise(workoutExercise);
        }

        /**
         * Closes a data access class
         */
        @Override
        public void close() {

        }

        /**
         * Gets a list of all workouts in the database
         *
         * @return a list of all workouts in the database
         */
        @Override
        public List<Workout> getWorkoutsList() {
            return new ArrayList<>(workoutsByName.values());
        }

        /**
         * Gets a list of names of all exercises in the database
         *
         * @return a list of names of all workouts in the database
         */
        @Override
        public List<String> getWorkoutNamesList() {
            return new ArrayList<>(workoutsByName.keySet());
        }

        /**
         * Retrieves a workout from the database with the name given as parameter
         *
         * @param workoutName the name of the workout to retrieve from the database
         * @return The workout with name workoutName, or null if no workout exists with that name
         */
        @Override
        public Workout getWorkout(String workoutName) {
            return workoutsByName.get(workoutName);
        }

        /**
         * Retrieves the name of a the workout that has been completed the least amount of times
         *
         * @return the workout that has been completed the least amount of times
         */
        @Override
        public String getLeastCompletedWorkout() {
            return workoutsByName.get("Never Skip Leg Day").getName();
        }
}


}