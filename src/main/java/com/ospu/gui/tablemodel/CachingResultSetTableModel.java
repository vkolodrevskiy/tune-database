package com.ospu.gui.tablemodel;

import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Caching {@link ResultSet} Table Model.
 *
 * @author vkolodrevskiy
 */
public class CachingResultSetTableModel extends ResultSetTableModel {
   /*
    * log4j audit channel
    */
    private static final Logger logger = Logger.getLogger(CachingResultSetTableModel.class);

    private ArrayList cache;

    public CachingResultSetTableModel(ResultSet aResultSet) {
        super(aResultSet);
        try {
            cache = new ArrayList();
            int cols = getColumnCount();
            ResultSet rs = getResultSet();

            while(rs.next()) {
                Object[] row = new Object[cols];
                for(int j=0; j< row.length; j++)
                    row[j] = rs.getObject(j+1);
                cache.add(row);
            }
        }
        catch(SQLException e) {
            logger.error("ERROR "+ e);
        }
    }

    @Override
    public Object getValueAt(int r, int c) {
        if(r < cache.size())
            return((Object[])cache.get(r))[c];
        else
            return null;
    }

    @Override
    public int getRowCount() {
        return cache.size();
    }
}
