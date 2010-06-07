package com.ospu.template;

/**
 * Represents table column.
 *
 * @author vkolodrevskiy
 */
public class Column {
    private String name;
    /* parent table name */
    private String table;
    /* column data type */

    // ------------------------------------------------------------------------
    // Setters and getters.
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public void setTable(final String table) {
        this.table = table;
    }
}
