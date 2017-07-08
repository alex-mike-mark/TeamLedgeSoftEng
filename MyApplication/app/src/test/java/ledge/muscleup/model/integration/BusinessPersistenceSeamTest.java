package ledge.muscleup.model.integration;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import ledge.muscleup.application.Services;
import ledge.muscleup.application.Main;
import ledge.muscleup.business.AccessExercises;
import ledge.muscleup.model.exercise.Exercise;
import ledge.muscleup.persistence.InterfaceDataAccess;


/**
 * BusinessPersistenceSeamTest.java used to test seams between Business and Persistence layers
 *
 * @author Matthew Smidt
 * @version 1.0
 * @since 2017-07-08
 */

public class BusinessPersistenceSeamTest extends TestCase {

    private static String dbName = Main.dbName;
    InterfaceDataAccess dataAccess;

    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

    @Before
    public void setUp() {
        Services.closeDataAccess();
        Services.createDataAccess(dbName); // gets the default service of hsqldb, so the call Tests use the sql db instead
        dataAccess = Services.getDataAccess();
    }

    @After
    public void tearDown() {
        Services.closeDataAccess();
    }

    public void testAccessExercisesDBIntegration() {
        System.out.println("\nStarting testAccessExercisesDBIntegration");

        AccessExercises accessExercises;
        Exercise exercise;
        String result;

        accessExercises = new AccessExercises();

        System.out.println(accessExercises.getExercisesList());

        System.out.println("Finishing testAccessExercisesDBIntegration\n");
    }
}
