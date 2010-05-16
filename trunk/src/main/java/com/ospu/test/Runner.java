package com.ospu.test;

import com.ospu.util.DatabaseConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runs tests.
 *
 * @author vkolodrevskiy
 */
public class Runner {
   /*
    * log4j audit channel
    */
    private static final Logger logger = Logger.getLogger(Runner.class);
    private List<String> templates;
    private String testName;
    private int numberOfThreads;

    public Runner(int numberOfThreads, String testName) {
        this.numberOfThreads = numberOfThreads;
        this.testName = testName;
        templates = new ArrayList<String>();

        // get templates from database.
        Connection connection = DatabaseConnection.getConnectionToApplicationDatabase();
        try {
            ResultSet rs;
            String templatesQuery = "SELECT template FROM template\n" +
                    "JOIN test ON test.id=template.test_id\n" +
                    "AND name = ?";

            PreparedStatement templatesQueryStat = connection.prepareStatement(templatesQuery);
            templatesQueryStat.setString(1, testName);

            rs = templatesQueryStat.executeQuery();

            while(rs.next()) {
                templates.add(rs.getString(1));
            }

            rs.close();
            templatesQueryStat.close();
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void runTests() {
        RunTestThread t1 = new RunTestThread(templates, testName);
        // create ExecutorService to manage threads
        ExecutorService threadExecutor = Executors.newFixedThreadPool(5);

        for(int i=0; i<numberOfThreads; i++){
            threadExecutor.submit(t1);
        }

        // shutdown worker threads
        threadExecutor.shutdown();

        // wait for the pool to shutdown
        while (!threadExecutor.isTerminated()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
