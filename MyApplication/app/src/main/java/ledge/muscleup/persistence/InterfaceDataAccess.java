package ledge.muscleup.persistence;

/**
 * An interface for general database access, such as opening or closing the database
 *
 * @author Ryan Koop
 * @version 1.0
 * @since 2017-06-04
 */
public interface InterfaceDataAccess {

    /**
     * Opens the stub database and populates it with some default values
     */
    void open(String dbPath);

    /**
     * Close the stub database
     */
    void close();
}
