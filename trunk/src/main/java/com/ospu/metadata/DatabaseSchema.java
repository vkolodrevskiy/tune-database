package com.ospu.metadata;

import java.util.List;

/**
 * Represents database schema.
 *
 * @author vkolodrevskiy
 */
public class DatabaseSchema {
    private String name;
    private List<DatabaseTable> tables;

    // ------------------------------------------------------------------------
    // Setters and getters.
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<DatabaseTable> getTables() {
        return tables;
    }

    public void setTables(final List<DatabaseTable> tables) {
        this.tables = tables;
    }
}
