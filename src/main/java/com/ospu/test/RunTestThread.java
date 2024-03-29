package com.ospu.test;

import com.ospu.util.DatabaseConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * Runs collection of queries in a thread.
 *
 * @author vkolodrevskiy
 */
public class RunTestThread extends Thread {
   /*
    * log4j audit channel
    */
    private static final Logger logger = Logger.getLogger(RunTestThread.class);
    private List<String> queries;
    private String testName;
    /* how long queries were run */
    private long duration;

    public long getDuration() {
        return duration;
    }

    public RunTestThread(List<String> queries, String testName) {
        this.queries = queries;
        this.testName = testName;
    }

    // ------------------------------------------------------------------------
    @Override
    public void run() {
        Connection connection = DatabaseConnection.getConnectionToTestingDatabase();

        long start = System.currentTimeMillis();

        Statement stat;
        try {
            stat = connection.createStatement();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return;
        }

        for(String q: queries) {
            try {
                stat.execute(q);
                logger.debug("Run successfully query = " + q);
            } catch (SQLException e) {
                logger.error("Error running query = " + q + "; " + e.getMessage());
            }
        }

        try {
            stat.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        long end = System.currentTimeMillis();

        duration = end - start;

        logger.debug("duration = " + duration);

        // close db connection
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        // average time of each query
        long average = duration/ queries.size();

        saveResults(testName, duration, average);
    }

    /**
     * Save test results to 'result' table.
     *
     * @param testName test name.
     * @param average average time of each query.
     * @param duration test duration time.
     */
    public void saveResults(final String testName, final long duration, final long average) {
        Connection connection = DatabaseConnection.getConnectionToApplicationDatabase();

        try {
            String templatesQuery = "INSERT INTO result(test_id, duration, average, execution_date) " +
                                    "VALUES ((SELECT id FROM test WHERE test.name = ?), ?, ?, ?)";

            PreparedStatement templatesQueryStat = connection.prepareStatement(templatesQuery);
            templatesQueryStat.setString(1, testName);
            templatesQueryStat.setLong(2, duration);
            templatesQueryStat.setLong(3, average);
            templatesQueryStat.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

            templatesQueryStat.executeUpdate();
            templatesQueryStat.close();
            connection.close();

            logger.info("Saved results for test " + testName);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
