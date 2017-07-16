package ledge.muscleup.application;

/**
 * A class containing static methods for initializing the application and the database
 *
 * @author Cole Kehler
 * @version 2.0
 * @since 2017-06-28
 */
public class Main {
    public static final String dbName = "MU_DB";
    private static String dbPathName = "app/MU_DB";

    /**
     * Create the database
     */
    public static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    /**
     * Close the database
     */
    public static void shutDown()
    {
        Services.closeDataAccess();
    }

    /**
     * Get the path of the database file
     * @return the path of the database file
     */
    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    /**
     * Set the path of the database file
     * @param pathName the new path of the database file
     */
    public static void setDBPathName(String pathName) {
        dbPathName = pathName;
    }
}
