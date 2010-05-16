package com.ospu.template;

import com.ospu.template.exception.InvalidQueryException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses templates. Defines tables, columns(including parent table names)
 * and constants.
 *
 * @author vkolodrevskiy
 */
public class TemplateParser {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(TemplateParser.class);

    // REGEXes to find tables, columns and constants
    private static final String TABLE_PATTERN = "(\\btable)\\w+\\b";
    private static final String COLUMN_PATTERN = "(\\bcolumn)\\w+\\b";
    private static final String CONSTANT_PATTERN = "(\\bconstant)\\w+\\b";

    // ------------------------------------------------------------------------
    public List<Template> parse(List<String> templatesToParse) {
        List<Template> templates = new ArrayList<Template>();

        for(String s: templatesToParse) {
            Template t = new Template();
            t.setTemplateString(s);
            try {
                t.setQueryType(getQueryType(s));
            } catch (InvalidQueryException e) {
                continue;
            }

            t.setColumns(getColumns(s));
            t.setTables(getTables(s));
            t.setConstants(getConstants(s));

            templates.add(t);
        }

        return templates;
    }

    // ------------------------------------------------------------------------
    private QueryType getQueryType(String s) throws InvalidQueryException {
        // trim string
        s = s.trim();

        if(s.toUpperCase().startsWith("SELECT"))
            return QueryType.SELECT;
        if(s.toUpperCase().startsWith("INSERT"))
            return QueryType.INSERT;
        if(s.toUpperCase().startsWith("UPDATE"))
            return QueryType.UPDATE;
        if(s.toUpperCase().startsWith("DELETE"))
            return QueryType.DELETE;

        // else -- the string does not seem to be a valid template string
        throw new InvalidQueryException("Query is not valid: " + s);
    }

    private Set<String> getTables(String s) {
        // trim string
        s = s.trim();

        Set<String> result = new HashSet<String>();
        Pattern tablePattern = Pattern.compile(TABLE_PATTERN);
        Matcher m = tablePattern.matcher(s);
        while (m.find())
            result.add(m.group(0));

        return result;
    }

    private Set<Column> getColumns(String queryString) {
        // trim string
        queryString = queryString.trim();

        Set<Column> result = new HashSet<Column>();
        Pattern columnPattern = Pattern.compile(COLUMN_PATTERN);
        Matcher m = columnPattern.matcher(queryString);

        // FIXME: need to go back on this; it's a very crappy way to define  parent table,
        //        also table aliases are not checked
        while (m.find()) {
            Column c = new Column();
            c.setName(m.group(0));

            boolean found = false;
            for(Column col: result) {
                if(col.getName().equals(c.getName())) {
                    found = true;
                    break;
                }
            }
            if(!found)
                result.add(c);

            // set parent table for columns from INSERT, UPDATE, DELETE queries,
            // 'cause we know that these queries are performed on single table
            if(queryString.toUpperCase().startsWith("INSERT") || queryString.toUpperCase().startsWith("UPDATE")
                    || queryString.toUpperCase().startsWith("DELETE")) {
                // define table
                String tableName = null;
                Pattern tablePattern = Pattern.compile(TABLE_PATTERN);
                Matcher mt = tablePattern.matcher(queryString);
                if (mt.find())
                    tableName = mt.group(0);

                for(Column col: result) {
                    if(col.getName().equals(c.getName())) {
                        if(col.getTable() == null) {
                            col.setTable(tableName);
                        }
                        break;
                    }
                }

            } else {
                // else we got SELECT, several tables can be in SELECT query

                // lets count number of tables in the query
                Pattern tablePattern = Pattern.compile(TABLE_PATTERN);
                Matcher mt = tablePattern.matcher(queryString);

                Set<String> tables = new HashSet<String>();
                while (mt.find()) {
                    tables.add(mt.group(0));
                }
                mt.reset();
                int numberOfTables = tables.size();

                // if there's only one table, all columns parent table should be set to this table
                if(numberOfTables == 1) {
                    // get this table name
                    String tableName = null;
                    if (mt.find())
                        tableName = mt.group(0);
                    // setup this table to columns as parent
                    for(Column col: result) {
                        if(col.getName().equals(c.getName())) {
                            if(col.getTable() == null) {
                                col.setTable(tableName);
                            }
                            break;
                        }
                    }
                }

                if(numberOfTables > 1) {
                    // FIXME: randomly select table as parent; it's not correct. need to go back on this
                    List<String> t = new ArrayList<String>(tables);
                    Random r = new Random();
                    for(Column col: result) {
                        if(col.getName().equals(c.getName())) {
                            if(col.getTable() == null) {
                                col.setTable(t.get(r.nextInt(t.size())));
                                logger.debug("Setting random table = " + col.getTable() + " for column = " + col.getName());
                            }
                            break;
                        }
                    }
                }
            }

            ///// insert update delete легко найти
//            if(queryString.charAt(queryString.indexOf(m.group(0)) - 1) == '.') {
//                String sub = queryString.substring(0, queryString.indexOf(m.group(0)) - 1);
//                sub = sub.trim();
//
//                // lets see if we already have column with this name in result set.
//                // also check whether column has parent table already set.
//                for(Column col: result) {
//                    if(col.getName().equals(c.getName())) {
//                        if(col.getTable() == null) {
//                            col.setTable(sub.substring(sub.lastIndexOf(" ") + 1));
//                        }
//                        break;
//                    }
//                }
//            }
            /////

        }

        return result;
    }

    private Set<String> getConstants(String s) {
        Set<String> result = new HashSet<String>();
        Pattern constantPattern = Pattern.compile(CONSTANT_PATTERN);
        Matcher m = constantPattern.matcher(s);
        while (m.find())
            result.add(m.group(0));

        return result;
    }
}
