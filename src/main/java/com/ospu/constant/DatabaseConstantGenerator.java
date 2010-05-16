package com.ospu.constant;

import com.ospu.util.DatabaseConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gets random value from database.
 *
 * @author vkolodrevskiy
 */
public class DatabaseConstantGenerator {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(DatabaseConstantGenerator.class);
    private static final Integer LIMIT = 100;

    private Connection connection;
    private Random random;

    public DatabaseConstantGenerator(Connection connection) {
        this.connection = connection;
        this.random = new Random();
    }

    // ------------------------------------------------------------------------
    public Object generate(TableAndColumn tableAndColumn) {
        // FIXME: load all data in constructor once;

        String selectQuery = "SELECT " + tableAndColumn.getColumn() + " FROM "
                + tableAndColumn.getTable() +" LIMIT " + LIMIT;

        try {
            Statement stat = connection.createStatement();
            ResultSet result = stat.executeQuery(selectQuery);

            List<Object> objects = new ArrayList<Object>();
            while (result.next()) {
                objects.add(result.getObject(1));
            }

            stat.close();
            result.close();

            if(objects.size() == 0)
                return null;
            else
                return objects.get(random.nextInt(objects.size()));

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return null;
    }
}
