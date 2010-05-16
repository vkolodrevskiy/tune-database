package com.ospu.util;

import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Creates database connection.
 *
 * FIXME: I think this is rather crappy, what about using some sort of connection pooling?
 *
 * @author vkolodrevskiy
 */
public class DatabaseConnection {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class);

    private static final String PROPERTY_FILE = "database.properties";

    /**
     * Returns database connection to testing database.
     *
     * @return database connection reference.
     */
    public static synchronized Connection getConnectionToTestingDatabase()  {
        Connection con = null;

        try {
            final Properties props = new Properties();
            final FileInputStream in = new FileInputStream(PROPERTY_FILE);
            props.load(in);
            in.close();

            final String drivers = props.getProperty("testing.jdbc.drivers");
            if(drivers != null)
                System.setProperty("testing.jdbc.drivers", drivers);
            final String url = props.getProperty("testing.jdbc.url");
            final String username = props.getProperty("testing.jdbc.username");
            final String password = props.getProperty("testing.jdbc.password");

            con = DriverManager.getConnection(url, username, password);

            logger.info("Connection to testing database established. url = " + url + "; username = " + username +
                "; password = " + password);
        } catch (final SQLException e) {
            logger.error(e.getMessage());
        } catch (final IOException e) {
            logger.error(e.getMessage());
        }

        return con;
    }

    /**
     * Returns database connection to application database.
     *
     * @return database connection reference.
     */
    public static synchronized Connection getConnectionToApplicationDatabase()  {
        Connection con = null;

        try {
            final Properties props = new Properties();
            final FileInputStream in = new FileInputStream(PROPERTY_FILE);
            props.load(in);
            in.close();

            final String drivers = props.getProperty("application.jdbc.drivers");
            if(drivers != null)
                System.setProperty("application.jdbc.drivers", drivers);
            final String url = props.getProperty("application.jdbc.url");
            final String username = props.getProperty("application.jdbc.username");
            final String password = props.getProperty("application.jdbc.password");

            con = DriverManager.getConnection(url, username, password);

            logger.info("Connection to application database established. url = " + url + "; username = " + username +
                "; password = " + password);
        } catch (final SQLException e) {
            logger.error(e.getMessage());
        } catch (final IOException e) {
            logger.error(e.getMessage());
        }

        return con;
    }
}
