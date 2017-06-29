package ledge.muscleup.model.business;

import junit.framework.TestCase;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ledge.muscleup.business.InterfaceAccessExercises;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.model.exercise.enums.*;
import ledge.muscleup.model.exercise.InterfaceExerciseQuantity;
import ledge.muscleup.model.exercise.WorkoutExercise;
import ledge.muscleup.model.exercise.WorkoutSessionExercise;
import ledge.muscleup.model.schedule.ScheduleWeek;
import ledge.muscleup.model.workout.Workout;
import ledge.muscleup.model.workout.WorkoutSession;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * AccessExercisesTest.java used to test AccessExercises.java
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-06-24
 */

public class AccessExercisesTest extends TestCase {
    InterfaceAccessExercises dataAccess;

    /**
     * Constructor for the AccessExercisesTest
     */
    public AccessExercisesTest(String arg0) {
        super(arg0);
    }

    /**
     * Initializes the AccessExercises to be used in the test
     */
    @Before
    public void setUp() {
        dataAccess = new TemplateAccessExercises();
    }

    /**
     * Tests that getting the list of exercises works properly
     */
    @Test
    public void testGetExercisesList() {
        System.out.println("\nStarting testGetExercisesList");

        // Exercises by object already in list
        List<Exercise> exerciseList1 = new ArrayList<>();
        exerciseList1.add(new Exercise("Bicep Curls", ExerciseIntensity.LOW, ExerciseType.ARM, false));
        exerciseList1.add(new Exercise("Crunches", ExerciseIntensity.LOW, ExerciseType.CORE, false));
        exerciseList1.add(new Exercise("Lunges", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false));
        exerciseList1.add(new Exercise("Push-Ups", ExerciseIntensity.HIGH, ExerciseType.ARM, false));
        exerciseList1.add(new Exercise("Running", ExerciseIntensity.HIGH, ExerciseType.CARDIO, false));
        exerciseList1.add(new Exercise("Bicycle Kicks", ExerciseIntensity.HIGH, ExerciseType.CORE,
                false));
        exerciseList1.add(new Exercise("Squats", ExerciseIntensity.MEDIUM, ExerciseType.LEG, false));
        exerciseList1.add(new Exercise("Exercise Bike", ExerciseIntensity.MEDIUM,
                ExerciseType.CARDIO, false));

        List<Exercise> exerciseList2 = dataAccess.getExercisesList();
        assertNotNull(exerciseList2);
        assertEquals(exerciseList1.toString(), exerciseList2.toString());

        System.out.println("Finishing testGetExercisesList\n");
    }
}

/**
 * A template Exercises accessor that creates a template database stub for use in testing
 */
class TemplateAccessExercises implements InterfaceAccessExercises {
    private TemplateDataAccessStub dataAccess;

    /**
     * The default constructor for the TemplateAccessExercises
     */
    TemplateAccessExercises() {
        dataAccess = new TemplateDataAccessStub("testDB");
        dataAccess.open("testDB");
    }

    /**
     * This method gets exercises stored in the database in the form of a list
     * @return a list of exercises in the database
     */
    @Override
    public List<Exercise> getExercisesList() {
        return dataAccess.getExercisesList();
    }
}

