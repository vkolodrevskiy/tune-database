package com.ospu;

import com.ospu.chance.Column;
import com.ospu.chance.Table;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests for {@link com.ospu.QueryGenerator}.
 *
 * @author vkolodrevskiy
 */
public class QueryGeneratorTest {

    /**
     * main test method.
     */
    @Test
    public void testManager() {
        List<String> queries = new ArrayList<String>();
        queries.add("select column1, column2 from table1;");
        queries.add("select column3 from table2;");
        queries.add("delete from table2 where column1 = constant1;");
        queries.add("INSERT INTO table1 (column1, column2, column3) VALUES (constant1, constant2, constant3);");
        queries.add("UPDATE table1 SET column1=constant1, column2=constant3 WHERE column2=constant2;");
        queries.add("update table1 set column1 = (select column1 from table1 where column2 = constant1) " +
                    "column3 = (select column3 from table1 where column2 = constant1) where column2 = constant2;");

        QueryGenerator generator = new QueryGenerator(getChances(), queries);
        List<String> result = generator.generate(true);

        assert (result.size() == 6) : "there should be 6 templates";

        for (String s: result) {
            System.out.println(s);
        }
    }


    // ------------------------------------------------------------------------
    private List<Table> getChances() {
                List<Table> chances = new ArrayList<Table>();

        Table t1 = new Table();
            t1.setName("actor");
            t1.setSelectChance(0.1);
            t1.setDeleteChance(0.0);
            t1.setInsertChance(0.1);
            t1.setUpdateChance(0.1);
        // setup columns
        List<Column> t1_columns = new ArrayList<Column>();
        Column column = new Column();
            column.setName("actor_id");
            column.setSelectChance(0.4);
            column.setDeleteChance(0.4);
            column.setInsertChance(0.4);
            column.setUpdateChance(0.4);
        t1_columns.add(column);
        column = new Column();
            column.setName("first_name");
            column.setSelectChance(0.3);
            column.setDeleteChance(0.3);
            column.setInsertChance(0.3);
            column.setUpdateChance(0.3);
        t1_columns.add(column);
        column = new Column();
            column.setName("last_name");
            column.setSelectChance(0.2);
            column.setDeleteChance(0.2);
            column.setInsertChance(0.2);
            column.setUpdateChance(0.2);
        t1_columns.add(column);
        t1.setColumns(t1_columns);
        chances.add(t1);

        Table t2 = new Table();
            t2.setName("address");
            t2.setSelectChance(0.2);
            t2.setDeleteChance(0.0);
            t2.setInsertChance(0.1);
            t2.setUpdateChance(0.1);
        // setup columns
        List<Column> t2_columns = new ArrayList<Column>();
        column = new Column();
            column.setName("address");
            column.setSelectChance(0.1);
            column.setDeleteChance(0.1);
            column.setInsertChance(0.1);
            column.setUpdateChance(0.1);
        t2_columns.add(column);
        column = new Column();
            column.setName("district");
            column.setSelectChance(0.2);
            column.setDeleteChance(0.2);
            column.setInsertChance(0.2);
            column.setUpdateChance(0.2);
        t2_columns.add(column);
        column = new Column();
            column.setName("phone");
            column.setSelectChance(0.2);
            column.setDeleteChance(0.3);
            column.setInsertChance(0.3);
            column.setUpdateChance(0.3);
        t2_columns.add(column);
        t2.setColumns(t2_columns);
        chances.add(t2);

        Table t3 = new Table();
            t3.setName("city");
            t3.setSelectChance(0.1);
            t3.setDeleteChance(0.0);
            t3.setInsertChance(0.1);
            t3.setUpdateChance(0.1);
        // setup columns
        List<Column> t3_columns = new ArrayList<Column>();
        column = new Column();
            column.setName("last_update");
            column.setSelectChance(0.1);
            column.setDeleteChance(0.1);
            column.setInsertChance(0.1);
            column.setUpdateChance(0.1);
        t3_columns.add(column);
        column = new Column();
            column.setName("city");
            column.setSelectChance(0.2);
            column.setDeleteChance(0.6);
            column.setInsertChance(0.6);
            column.setUpdateChance(0.6);
        t3_columns.add(column);
        column = new Column();
            column.setName("city_id");
            column.setSelectChance(0.1);
            column.setDeleteChance(0.1);
            column.setInsertChance(0.1);
            column.setUpdateChance(0.1);
        t3_columns.add(column);
        t3.setColumns(t3_columns);
        chances.add(t3);

        Table t4 = new Table();
            t4.setName("customer");
            t4.setSelectChance(0.2);
            t4.setDeleteChance(0.0);
            t4.setInsertChance(0.1);
            t4.setUpdateChance(0.1);
        // setup columns
        List<Column> t4_columns = new ArrayList<Column>();
        column = new Column();
            column.setName("store_id");
            column.setSelectChance(0.1);
            column.setDeleteChance(0.1);
            column.setInsertChance(0.1);
            column.setUpdateChance(0.1);
        t4_columns.add(column);
        column = new Column();
            column.setName("first_name");
            column.setSelectChance(0.5);
            column.setDeleteChance(0.5);
            column.setInsertChance(0.5);
            column.setUpdateChance(0.5);
        t4_columns.add(column);
        column = new Column();
            column.setName("last_name");
            column.setSelectChance(0.3);
            column.setDeleteChance(0.3);
            column.setInsertChance(0.3);
            column.setUpdateChance(0.3);
        t4_columns.add(column);
        t4.setColumns(t4_columns);
        chances.add(t4);

        Table t5 = new Table();
            t5.setName("film");
            t5.setSelectChance(0.1);
            t5.setDeleteChance(1);
            t5.setInsertChance(0.1);
            t5.setUpdateChance(0.1);
        // setup columns
        List<Column> t5_columns = new ArrayList<Column>();
        column = new Column();
            column.setName("title");
            column.setSelectChance(0.1);
            column.setDeleteChance(0.1);
            column.setInsertChance(0.1);
            column.setUpdateChance(0.1);
        t5_columns.add(column);
        column = new Column();
            column.setName("description");
            column.setSelectChance(0.3);
            column.setDeleteChance(0.3);
            column.setInsertChance(0.3);
            column.setUpdateChance(0.3);
        t5_columns.add(column);
        column = new Column();
            column.setName("length");
            column.setSelectChance(0.3);
            column.setDeleteChance(0.3);
            column.setInsertChance(0.3);
            column.setUpdateChance(0.3);
        t5_columns.add(column);
        t5.setColumns(t5_columns);
        chances.add(t5);

        return chances;
    }
}
