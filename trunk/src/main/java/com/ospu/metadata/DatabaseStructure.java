package com.ospu.metadata;

import java.util.List;

/**
 * Represents database structure including: schema -> table -> column.
 *
 * @author vkolodrevskiy
 */
public class DatabaseStructure {
    private String databaseName;
    private List<DatabaseSchema> schemas;

    /**
     * Returns schema specified by its name.
     *
     * @param schemaName
     * @return
     */
    public DatabaseSchema getSchema(String schemaName) {
        for(DatabaseSchema s: schemas) {
            if(s.getName().equals(schemaName))
                return s;
        }

        return null;
    }

    // ------------------------------------------------------------------------
    // Setters and getters.
    public List<DatabaseSchema> getSchemas() {
        return schemas;
    }

    public void setSchemas(final List<DatabaseSchema> schemas) {
        this.schemas = schemas;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(final String databaseName) {
        this.databaseName = databaseName;
    }
}
