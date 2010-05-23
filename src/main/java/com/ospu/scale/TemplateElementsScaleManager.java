package com.ospu.scale;

import com.ospu.chance.Column;
import com.ospu.chance.Table;
import com.ospu.template.QueryType;
import org.apache.log4j.Logger;
import java.util.*;
import static com.ospu.template.QueryType.*;

/**
 * Manager for working with a scale.
 *
 * @author vkolodrevskiy
 */
public class TemplateElementsScaleManager {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(TemplateElementsScaleManager.class);

    private Scale tableSelectScale;
    private Scale tableInsertScale;
    private Scale tableUpdateScale;
    private Scale tableDeleteScale;

    private Map<String, Scale> columnSelectScale;
    private Map<String, Scale> columnInsertScale;
    private Map<String, Scale> columnUpdateScale;
    private Map<String, Scale> columnDeleteScale;

    public TemplateElementsScaleManager(List<Table> tables) {
        tableSelectScale = createTableScale(SELECT, tables);
        tableInsertScale = createTableScale(INSERT, tables);
        tableUpdateScale = createTableScale(UPDATE, tables);
        tableDeleteScale = createTableScale(DELETE, tables);

        columnSelectScale = new HashMap<String, Scale>();
        columnInsertScale = new HashMap<String, Scale>();
        columnUpdateScale = new HashMap<String, Scale>();
        columnDeleteScale = new HashMap<String, Scale>();

        for(Table table: tables) {
            if(table.getColumns() != null) {
                columnSelectScale.put(table.getName(), createColumnScale(SELECT, table.getColumns()));
                columnUpdateScale.put(table.getName(), createColumnScale(UPDATE, table.getColumns()));
                columnDeleteScale.put(table.getName(), createColumnScale(DELETE, table.getColumns()));
                columnInsertScale.put(table.getName(), createColumnScale(INSERT, table.getColumns()));
            }
        }
    }

    public String getTable(QueryType queryType, double chance) {
        Random r = new Random();
        switch (queryType) {
            case SELECT: {
                for(ScaleElement element: tableSelectScale.getElements()) {
                    if(chance > element.getStart() && chance <= element.getEnd())
                        return element.getName();
                }
                logger.debug("Table with the given chance was not found in the scale; " +
                        "chance = " + chance + " query type = " + queryType + " Check table chances. Return random table.");
                // return random table
                return tableSelectScale.getElements().
                        get(r.nextInt(tableSelectScale.getElements().size())).getName();
            }
            case INSERT: {
                for(ScaleElement element: tableInsertScale.getElements()) {
                    if(chance > element.getStart() && chance <= element.getEnd())
                        return element.getName();
                }
                logger.debug("Table with the given chance was not found in the scale; " +
                        "chance = " + chance + " query type = " + queryType + " Check table chances. Return random table.");
                // return random table
                return tableInsertScale.getElements().
                        get(r.nextInt(tableInsertScale.getElements().size())).getName();
            }
            case UPDATE: {
                for(ScaleElement element: tableUpdateScale.getElements()) {
                    if(chance > element.getStart() && chance <= element.getEnd())
                        return element.getName();
                }
                logger.debug("Table with the given chance was not found in the scale; " +
                        "chance = " + chance + " query type = " + queryType + " Check table chances. Return random table.");
                // return random table
                return tableUpdateScale.getElements().
                        get(r.nextInt(tableUpdateScale.getElements().size())).getName();
            }
            case DELETE: {
                for(ScaleElement element: tableDeleteScale.getElements()) {
                    if(chance > element.getStart() && chance <= element.getEnd())
                        return element.getName();
                }
                logger.debug("Table with the given chance was not found in the scale; " +
                        "chance = " + chance + " query type = " + queryType + " Check table chances. Return random table.");
                // return random table
                return tableDeleteScale.getElements().
                        get(r.nextInt(tableDeleteScale.getElements().size())).getName();
            }

            default: return null; // this should never happen I guess :)
        }
    }

    public String getColumn(QueryType queryType, String tableName, double chance) {
        Random r = new Random();
        switch (queryType) {
            case SELECT: {
                for(ScaleElement element: columnSelectScale.get(tableName).getElements()) {
                    if(chance > element.getStart() && chance <= element.getEnd())
                        return element.getName();
                }
                logger.debug("Column with the given chance was not found in the scale; " +
                        "chance = " + chance + " query type = " + queryType + " Check column chances. Return random column.");
                // return random column
                return columnSelectScale.get(tableName).getElements().
                        get(r.nextInt(columnSelectScale.get(tableName).getElements().size())).getName();
            }
            case INSERT: {
                for(ScaleElement element: columnInsertScale.get(tableName).getElements()) {
                    if(chance > element.getStart() && chance <= element.getEnd())
                        return element.getName();
                }
                logger.debug("Column with the given chance was not found in the scale; " +
                        "chance = " + chance + " query type = " + queryType + " Check column chances. Return random column.");
                // return random column
                return columnInsertScale.get(tableName).getElements().
                        get(r.nextInt(columnInsertScale.get(tableName).getElements().size())).getName();
            }
            case UPDATE: {
                for(ScaleElement element: columnUpdateScale.get(tableName).getElements()) {
                    if(chance > element.getStart() && chance <= element.getEnd())
                        return element.getName();
                }
                logger.debug("Column with the given chance was not found in the scale; " +
                        "chance = " + chance + " query type = " + queryType + " Check column chances. Return random column.");
                // return random column
                return columnUpdateScale.get(tableName).getElements().
                        get(r.nextInt(columnUpdateScale.get(tableName).getElements().size())).getName();
            }
            case DELETE: {
                for(ScaleElement element: columnDeleteScale.get(tableName).getElements()) {
                    if(chance > element.getStart() && chance <= element.getEnd())
                        return element.getName();
                }
                logger.debug("Column with the given chance was not found in the scale; " +
                        "chance = " + chance + " query type = " + queryType + " Check column chances. Return random column.");
                // return random column
                return columnDeleteScale.get(tableName).getElements().
                        get(r.nextInt(columnDeleteScale.get(tableName).getElements().size())).getName();
            }

            default: return null; // this should never happen I guess :)
        }
    }

    // ------------------------------------------------------------------------
    /**
     * Creates scale for tables.
     * @param type   template type.
     * @param tables tables for which to create scale.
     * @return <code>Scale</code> object.
     */
    private Scale createTableScale(QueryType type, List<Table> tables) {
        Scale scale = new Scale();

        List<ScaleElement> scaleElements = new ArrayList<ScaleElement>();
        double next = 0;
        for(Table t: tables) {
            ScaleElement element = new ScaleElement();
            element.setName(t.getName());
            element.setStart(next);
            switch(type) {
                case SELECT: next = next + t.getSelectChance(); break;
                case INSERT: next = next + t.getInsertChance(); break;
                case UPDATE: next = next + t.getUpdateChance(); break;
                case DELETE: next = next + t.getDeleteChance(); break;
            }
            element.setEnd(next);

            scaleElements.add(element);
        }
        scale.setElements(scaleElements);

        return scale;
    }

    /**
     * Creates scale for tables.
     *
     * @param type    template type.
     * @param columns table columns for which to create scale.
     * @return <code>Scale</code> object.
     */
    private Scale createColumnScale(QueryType type, List<Column> columns) {
        Scale scale = new Scale();

        List<ScaleElement> scaleElements = new ArrayList<ScaleElement>();
        double next = 0;
        for(Column a: columns) {
            ScaleElement element = new ScaleElement();
            element.setName(a.getName());
            element.setStart(next);
            switch(type) {
                case SELECT: next = next + a.getSelectChance(); break;
                case INSERT: next = next + a.getInsertChance(); break;
                case UPDATE: next = next + a.getUpdateChance(); break;
                case DELETE: next = next + a.getDeleteChance(); break;
            }
            element.setEnd(next);

            scaleElements.add(element);
        }
        scale.setElements(scaleElements);

        return scale;
    }
}
