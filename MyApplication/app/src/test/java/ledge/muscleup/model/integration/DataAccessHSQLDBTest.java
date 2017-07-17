package ledge.muscleup.model.integration;

import ledge.muscleup.application.Main;
import ledge.muscleup.application.Services;
import ledge.muscleup.persistence.InterfaceDataAccess;
import ledge.muscleup.persistence.InterfaceExerciseDataAccess;
import ledge.muscleup.persistence.InterfaceWorkoutDataAccess;
import ledge.muscleup.persistence.InterfaceWorkoutSessionDataAccess;

import junit.framework.TestCase;

import static ledge.muscleup.application.Services.getExerciseDataAccess;
import static ledge.muscleup.application.Services.getWorkoutDataAccess;
import static ledge.muscleup.application.Services.getWorkoutSessionDataAccess;

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
        dataAccess = getExerciseDataAccess();

        // TODO: Uncomment and run test
        //ExerciseDataAccessTest.testGetExercisesList();

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
        dataAccess = getWorkoutDataAccess();

        // TODO: Uncomment and run test
        //WorkoutDataAccessTest.testGetWorkout();
        //WorkoutDataAccessTest.testGetWorkoutsList();
        //WorkoutDataAccessTest.testGetWorkoutNamesList();

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
        dataAccess = getWorkoutSessionDataAccess();

        // TODO: Uncomment and run test
        //WorkoutSessionDataAccessTest.testGetWorkoutSession();
        //WorkoutSessionDataAccessTest.testGetSessionsInDateRange();
        //WorkoutSessionDataAccessTest.testInsertWorkoutSession();
        //WorkoutSessionDataAccessTest.testRemoveWorkoutSession();
        //WorkoutSessionDataAccessTest.testToggleWorkoutCompleted();

        Services.closeDataAccess();

        System.out.println("Finished Integration test WorkoutSessionDataAccess (using default DB)\n");
    }
}
