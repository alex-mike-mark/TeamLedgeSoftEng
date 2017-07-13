package ledge.muscleup.application;

import ledge.muscleup.persistence.DataAccess;
import ledge.muscleup.persistence.InterfaceDataAccess;

/**
 * A class containing static methods for creating, retrieving, and closing the DataAccess.
 * The methods in this class also ensure that only one DataAccess is created
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-05
 */
public class Services {
    private static InterfaceDataAccess dataAccessService = null;

    /**
     * Creates and opens the database, if it hasn't already been created. Returns the stub
     * database
     * @param dbName the name of the database
     * @return the database
     */
    public static InterfaceDataAccess createDataAccess(String dbName) {
        if (dataAccessService == null) {
            dataAccessService = new DataAccess(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    /**
     * Creates and opens the database, using an alternative database implementation
     * @param altDataAccessService the alternative database implementation
     * @return the database
     */
    public static InterfaceDataAccess createDataAccess(DataAccess altDataAccessService) {
        if (dataAccessService == null) {
            dataAccessService = altDataAccessService;
            dataAccessService.open(Main.getDBPathName());
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
            dataAccessService = null;
        }
    }
}
