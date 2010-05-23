package com.ospu.validator;

import com.ospu.util.DatabaseConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Validates query.
 *
 * @author vkolodrevskiy
 */
public class QueryValidator {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(QueryValidator.class);

    /**
     * Walks through collection of queries.
     * Returns only those queries which do not throw exception while running.
     *
     * @param queriesToValidate <code>List</code> of queries to validate.
     * @return "good" queries.
     */
    public static List<String> validate(final List<String> queriesToValidate) {
        // get database connection
        final Connection connection = DatabaseConnection.getConnectionToTestingDatabase();

        final Statement stat;
        try {
            stat = connection.createStatement();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return queriesToValidate;
        }

        final List<String> result = new ArrayList<String>();
        for(final String query: queriesToValidate) {
            try {
                stat.execute(query);
                logger.debug("valid query: " + query);
                result.add(query);
            } catch (SQLException e) {
                logger.error("invalid query: " + query + "; " + e.getMessage());
            }
        }

        // close database connection
        try {
            stat.close();
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return result;
    }
}
