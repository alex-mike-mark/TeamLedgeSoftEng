package ledge.muscleup.persistence;

import java.sql.Statement;

/**
 * The interface for a component class, which is a part of database access
 *
 * @author Cole Kehler
 * @version 3.0
 * @since 2017-07-13
 */
public interface InterfaceDataAccessComponent {
    /**
     * Opens a data access class
     * @param statement the statement to use in data access queries
     */
    void open(Statement statement);

    /**
     * Closes a data access class
     */
    void close();
}
