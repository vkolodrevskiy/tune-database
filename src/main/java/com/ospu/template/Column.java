package com.ospu.template;

/**
 * FIXME: add description.
 *
 * @author vkolodrevskiy
 */
public class Column {
    private String name;
    /* parent table name */
    private String table;
    /* column data type */
    private ColumnType type;// FIXME: delete this property?

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

    public ColumnType getType() {
        return type;
    }

    public void setType(final ColumnType type) {
        this.type = type;
    }
}
