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
    private static String dbPathName = "database/MU_DB";

    /**
     * Create the database
     */
    public static void startUp()
    {
        Services.createDataAccess(dbName);
    }

    public static void shutDown()
    {
        Services.closeDataAccess();
    }

    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    public static void setDBPathName(String pathName) {
        dbPathName = pathName;
    }
}
