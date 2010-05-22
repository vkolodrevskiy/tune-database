package com.ospu.constant;

/**
 * Represents relation between column and table.
 * Example: <code>student.name</code>. <br><br>
 * <code>student</code> - <code>TableAndColumn.table</code> property.<br>
 * <code>name</code> - <code>TableAndColumn.name</code> property.
 *
 * @author vkolodrevskiy
 */
public class TableAndColumn {
    private String table;
    private String column;

    // ------------------------------------------------------------------------
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
