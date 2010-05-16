package com.ospu.chance;

import java.util.List;

/**
 * Holds table and column chances.
 *
 * @author vkolodrevskiy
 */
public class Table {
    // FIXME: need to define if I need this
    private String name;

    private double selectChance;
    private double updateChance;
    private double deleteChance;
    private double insertChance;
    //private Map<String, Column> columns;
    private List<Column> columns; 

    // ------------------------------------------------------------------------
    // Setters and getters.
    public double getSelectChance() {
        return selectChance;
    }

    public void setSelectChance(final double selectChance) {
        this.selectChance = selectChance;
    }

    public double getUpdateChance() {
        return updateChance;
    }

    public void setUpdateChance(final double updateChance) {
        this.updateChance = updateChance;
    }

    public double getDeleteChance() {
        return deleteChance;
    }

    public void setDeleteChance(final double deleteChance) {
        this.deleteChance = deleteChance;
    }

    public double getInsertChance() {
        return insertChance;
    }

    public void setInsertChance(final double insertChance) {
        this.insertChance = insertChance;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(final List<Column> columns) {
        this.columns = columns;
    }
}
