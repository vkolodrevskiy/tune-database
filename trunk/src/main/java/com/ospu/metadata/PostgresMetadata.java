package com.ospu.metadata;

import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Util class for getting database structure(schema, tables, columns).
 *
 * @author vkolodrevskiy.
 */
public class PostgresMetadata {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(PostgresMetadata.class);

    /**
     * Returns database structure.
     * @param con database connection.
     * @return <code>DatabaseStructure</code> object.
     */
    public static DatabaseStructure getDatabaseStructure(Connection con){
        if(con == null)
            return null;

        DatabaseStructure databaseStructure = new DatabaseStructure();

        try {
            DatabaseMetaData meta = con.getMetaData();
            databaseStructure.setDatabaseName(meta.getConnection().getCatalog());
            List<DatabaseSchema> schemas = new ArrayList<DatabaseSchema>();
            ResultSet mrss = meta.getSchemas();
            while(mrss.next()) {
                // pass postgres system schemas
                if(mrss.getString(1).equals("information_schema") || mrss.getString(1).startsWith("pg_"))
                    continue;

                DatabaseSchema databaseSchema = new DatabaseSchema();
                databaseSchema.setName(mrss.getString(1));

                ResultSet mrst = meta.getTables(null, databaseSchema.getName(), null, new String[] {"TABLE"} /*null*/);

                List<DatabaseTable> tables = new ArrayList<DatabaseTable>();
                while(mrst.next()) {
                    DatabaseTable databaseTable = new DatabaseTable();
                    databaseTable.setName(mrst.getString(3));

                    ResultSet mrsc = meta.getColumns(null, null, mrst.getString(3), null);
                    List<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();
                    while(mrsc.next()) {
                        DatabaseColumn databaseColumn = new DatabaseColumn();
                        databaseColumn.setName(mrsc.getString(4));
                        databaseColumn.setType(mrsc.getString(6));
                        columns.add(databaseColumn);
                    }
                    mrsc.close();
                    databaseTable.setColumns(columns);
                    tables.add(databaseTable);
                }
                mrst.close();

                databaseSchema.setTables(tables);
                schemas.add(databaseSchema);
            }

            databaseStructure.setSchemas(schemas);
            mrss.close();
            con.close();

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return databaseStructure;
    }
}

