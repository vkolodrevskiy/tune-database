package com.ospu.gui.tablemodel;

import org.apache.log4j.Logger;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * {@link ResultSet} Table Model.
 *
 * @author vkolodrevskiy
 */
public abstract class ResultSetTableModel extends AbstractTableModel {
   /*
    * log4j audit channel
    */
    private static final Logger logger = Logger.getLogger(ResultSetTableModel.class);

    private ResultSet rs;
    private ResultSetMetaData rsmd;

    public ResultSetTableModel(ResultSet aResultSet) {
        rs = aResultSet;
        try {
            rsmd = rs.getMetaData();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public String getColumnName(int c) {
        try {
            return rsmd.getColumnName(c+1);
        } catch(SQLException e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return 0;
        }
    }

    protected ResultSet getResultSet() {
        return rs;
    }
}
