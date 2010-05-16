package com.ospu.template;

import java.util.Set;

/**
 * Represents a template string.
 *
 * @author vkolodrevskiy
 */
public class Template {
    private String templateString;
    private QueryType queryType;

    // template columns
    private Set<String> tables;
    private Set<Column> columns;
    private Set<String> constants;

    // ------------------------------------------------------------------------
    public String getTemplateString() {
        return templateString;
    }

    public void setTemplateString(final String templateString) {
        this.templateString = templateString;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(final QueryType queryType) {
        this.queryType = queryType;
    }

    public Set<String> getTables() {
        return tables;
    }

    public void setTables(final Set<String> tables) {
        this.tables = tables;
    }

    public Set<Column> getColumns() {
        return columns;
    }

    public void setColumns(final Set<Column> columns) {
        this.columns = columns;
    }

    public Set<String> getConstants() {
        return constants;
    }

    public void setConstants(final Set<String> constants) {
        this.constants = constants;
    }
}
