package com.ospu;

import com.ospu.chance.Table;
import com.ospu.constant.ConstantGenerator;
import com.ospu.constant.TableAndColumn;
import com.ospu.scale.TemplateElementsScaleManager;
import com.ospu.template.Column;
import com.ospu.template.QueryType;
import com.ospu.template.Template;
import com.ospu.template.TemplateParser;

import java.util.*;

/**
 * Generates test queries using chances and query templates.
 *
 * @author vkolodrevskiy
 */
public class QueryGenerator {
    private List<Template> templates;
    private List<Table> chances;

    public QueryGenerator(final List<Table> chances, final List<String> templateStrings) {
        this.chances = chances;
        // parse templates
        TemplateParser parser = new TemplateParser();
        templates = parser.parse(templateStrings);
    }

    /**
     * Generates queries for the given collection of templates and template chances.
     * 
     * @param useDatabase defines whether database constants are used or not
     * @return collection of generated queries.
     */
    public List<String> generate(boolean useDatabase) {
        Random random = new Random();

        List<String> result = new ArrayList<String>();

        // create table scales
        TemplateElementsScaleManager templateElementsScaleManager = new TemplateElementsScaleManager(chances);

        //
        for(Template template: templates) {
            QueryType queryType = template.getQueryType();

            // replace tables
            List<String> tables = new ArrayList<String>();
            Map<String, String> replacedTablesMap = new HashMap<String, String>();
            for(String table: template.getTables()) {
                double randomTable = Math.random();
                String table2replace = templateElementsScaleManager.getTable(queryType, randomTable);
                template.setTemplateString(template.getTemplateString().replace(table, table2replace));
                replacedTablesMap.put(table, table2replace);

                tables.add(table2replace);
            }

            // replace columns
            List<TableAndColumn> tableAndColumns = new ArrayList<TableAndColumn>();
            for(Column column: template.getColumns()) {
                double randomColumn = Math.random();
                String column2replace = templateElementsScaleManager.getColumn(queryType, replacedTablesMap.get(column.getTable()), randomColumn);
                template.setTemplateString(template.getTemplateString().replace(column.getName(), column2replace));

                tableAndColumns.add(new TableAndColumn(tables.get(random.nextInt(tables.size())), column2replace));
            }

            ConstantGenerator gen = new ConstantGenerator();
            template.getConstants();
            for(String constant: template.getConstants()) {

                // suggest randomly which constant belongs to which column
                TableAndColumn c = tableAndColumns.get(random.nextInt(tableAndColumns.size()));

                if(gen.generate(c, useDatabase) == null) // if problem occured while constant generation
                    template.setTemplateString(template.getTemplateString().replace(constant, "null"));
                else {
                    String constant2replace = gen.generate(c, useDatabase).toString();
                    template.setTemplateString(template.getTemplateString().replace(constant, "\'" + constant2replace + "\'"));
                }
            }

            result.add(template.getTemplateString());
        }

        return result;
    }
}
