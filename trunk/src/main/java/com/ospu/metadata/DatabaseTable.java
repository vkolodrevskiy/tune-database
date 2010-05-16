package com.ospu.metadata;

import java.util.List;

/**
 * FIXME: add description.
 *
 * @author vkolodrevskiy
 *         To change this template use File | Settings | File Templates.
 */
public class DatabaseTable {
    private String name;
    private List<DatabaseColumn> columns;

    // ------------------------------------------------------------------------
    // Setters and getters.
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<DatabaseColumn> getColumns() {
        return columns;
    }

    public void setColumns(final List<DatabaseColumn> columns) {
        this.columns = columns;
    }
}
