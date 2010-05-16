package com.ospu;

import com.ospu.template.QueryType;
import com.ospu.template.Template;
import com.ospu.template.TemplateParser;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests {@link com.ospu.template.TemplateParser TemplateParser}.
 *
 * @author vkolodrevskiy
 */
public class TemplateParserTest {

    @Test
    public void testParser() {
        List<String> queries = new ArrayList<String>();

        queries.add("select column1, table3.column2, column2 column1 from table1, table2, table1 " +
                    "where column3=constant1 and column1=constant2");
        queries.add("this is invalid template");
        queries.add("delete from table1 where column4 = constant22");
        queries.add("upDate table1 set column4=constant1 where column45=constant1");
        queries.add("INSERT INTO table1 (column1, column2, column3, column2) VALUES (constant1, constant2, constant3)");

        TemplateParser parser = new TemplateParser();
        List<Template> templates = parser.parse(queries);

        assert (templates.size() == 4) : "Expected 4 templates";

        assert (templates.get(0).getQueryType().equals(QueryType.SELECT)) : "Select template type expected";
        assert (templates.get(0).getTables().size() == 3) : "3 tables expected";
        assert (templates.get(0).getColumns().size() == 3) : "3 columns expected";
        assert (templates.get(0).getConstants().size() == 2) : "2 constant expected";

        assert (templates.get(1).getQueryType().equals(QueryType.DELETE)) : "Delete template type expected";
        assert (templates.get(1).getTables().size() == 1) : "1 table expected";
        assert (templates.get(1).getColumns().size() == 1) : "1 column expected";
        assert (templates.get(1).getConstants().size() == 1) : "1 constant expected";

        assert (templates.get(2).getQueryType().equals(QueryType.UPDATE)) : "Update template type expected";
        assert (templates.get(2).getTables().size() == 1) : "1 table expected";
        assert (templates.get(2).getColumns().size() == 2) : "2 columns expected";
        assert (templates.get(2).getConstants().size() == 1) : "1 constant expected";

        assert (templates.get(3).getQueryType().equals(QueryType.INSERT)) : "Insert template type expected";
        assert (templates.get(3).getTables().size() == 1) : "1 table expected";
        assert (templates.get(3).getColumns().size() == 3) : "3 columns expected";
        assert (templates.get(3).getConstants().size() == 3) : "3 constant expected";
    }
}
