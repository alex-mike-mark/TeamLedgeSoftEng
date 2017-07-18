package ledge.muscleup.integration;

import ledge.muscleup.application.Main;
import ledge.muscleup.application.Services;

import ledge.muscleup.persistence.InterfaceExerciseDataAccess;
import ledge.muscleup.persistence.InterfaceWorkoutDataAccess;
import ledge.muscleup.persistence.InterfaceWorkoutSessionDataAccess;
import ledge.muscleup.unit.persistence.ExerciseDataAccessTest;
import ledge.muscleup.unit.persistence.WorkoutDataAccessTest;
import ledge.muscleup.unit.persistence.WorkoutSessionDataAccessTest;


import junit.framework.TestCase;


/**
 * DataAccessHSQLDBTest.java used to test
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-07-08
 */

public class DataAccessHSQLDBTest extends TestCase {

    private static String dbName = Main.dbName;

    public DataAccessHSQLDBTest(String arg0)
    {
        super(arg0);
    }

    public void testExerciseDataAccess()
    {
        InterfaceExerciseDataAccess dataAccess;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test ExerciseDataAccess (using default DB)");

        // Use the following two statements to run with the real database
        Services.createDataAccess(dbName);
        dataAccess = Services.getExerciseDataAccess();

        ExerciseDataAccessTest.testGetExercisesList();

        Services.closeDataAccess();

        System.out.println("Finished Integration test ExerciseDataAccess (using default DB)\n");
    }

    public void testWorkoutDataAccess()
    {
        InterfaceWorkoutDataAccess dataAccess;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test WorkoutDataAccess (using default DB)");

        // Use the following two statements to run with the real database
        Services.createDataAccess(dbName);
        dataAccess = Services.getWorkoutDataAccess();

        WorkoutDataAccessTest.testGetWorkout();
        WorkoutDataAccessTest.testGetWorkoutsList();
        WorkoutDataAccessTest.testGetWorkoutNamesList();

        Services.closeDataAccess();

        System.out.println("Finished Integration test WorkoutDataAccess (using default DB)\n");
    }

    public void testWorkoutSessionDataAccess()
    {
        InterfaceWorkoutSessionDataAccess dataAccess;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test WorkoutSessionDataAccess (using default DB)");

        // Use the following two statements to run with the real database
        Services.createDataAccess(dbName);
        dataAccess = Services.getWorkoutSessionDataAccess();

        WorkoutSessionDataAccessTest.testGetWorkoutSession();
        WorkoutSessionDataAccessTest.testGetSessionsInDateRange();
        WorkoutSessionDataAccessTest.testInsertWorkoutSession();
        WorkoutSessionDataAccessTest.testRemoveWorkoutSession();
        WorkoutSessionDataAccessTest.testToggleWorkoutCompleted();

        Services.closeDataAccess();

        System.out.println("Finished Integration test WorkoutSessionDataAccess (using default DB)\n");
    }
}
