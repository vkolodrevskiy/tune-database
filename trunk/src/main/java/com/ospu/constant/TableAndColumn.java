package com.ospu.constant;

/**
 * FIXME: add description.
 *
 * @author vkolodrevskiy
 *         To change this template use File | Settings | File Templates.
 */
public class TableAndColumn {

    private String table;
    private String column;

    public TableAndColumn(String table, String column) {
        this.table = table;
        this.column = column;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }
}
