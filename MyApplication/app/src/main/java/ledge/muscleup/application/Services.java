package ledge.muscleup.application;

import ledge.muscleup.persistence.DataAccessStub;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * A class containing static methods for creating, retrieving, and closing the DataAccessStub.
 * The methods in this class also ensure that only one DataAccessStub is created
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class Services {
    private static InterfaceDataAccess dataAccessService = null;

    /**
     * Creates and opens the stub database, if it hasn't already been created. Returns the stub
     * database
     * @param dbName the name of the database
     * @return the database
     */
    public static InterfaceDataAccess createDataAccess(String dbName) {
        if (dataAccessService == null) {
            dataAccessService = new DataAccessStub(dbName);
            dataAccessService.open(dbName);
        }
        return dataAccessService;
    }

    /**
     * Retrieves the database
     * @return the database, if it exists
     */
    public static InterfaceDataAccess getDataAccess() {
        if (dataAccessService == null) {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    /**
     * Closes the database connection, if the database exists. Sets the dataAccessService variable
     * to null
     */
    public static void closeDataAccess() {
        if (dataAccessService != null) {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}
