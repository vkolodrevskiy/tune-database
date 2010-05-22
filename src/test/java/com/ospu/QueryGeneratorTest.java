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

    @Test
    public void testManager() {

        List<Table> chances = new ArrayList<Table>();

        Table t1 = new Table();
            t1.setName("actor");
            t1.setSelectChance(0.1);
            t1.setDeleteChance(0.0);
            t1.setInsertChance(0.1);
            t1.setUpdateChance(0.1);
        // setup columns
        List<Column> t1_columns = new ArrayList<Column>();
        Column t1c1 = new Column();
            t1c1.setName("actor_id");
            t1c1.setSelectChance(0.1);
            t1c1.setDeleteChance(0.1);
            t1c1.setInsertChance(0.1);
            t1c1.setUpdateChance(0.1);
        t1_columns.add(t1c1);
        Column t1c2 = new Column();
            t1c2.setName("first_name");
            t1c2.setSelectChance(0.5);
            t1c2.setDeleteChance(0.5);
            t1c2.setInsertChance(0.5);
            t1c2.setUpdateChance(0.5);
        t1_columns.add(t1c2);
        Column t1c3 = new Column();
            t1c3.setName("last_name");
            t1c3.setSelectChance(0.2);
            t1c3.setDeleteChance(0.2);
            t1c3.setInsertChance(0.2);
            t1c3.setUpdateChance(0.2);
        t1_columns.add(t1c3);
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
        Column t2c1 = new Column();
            t2c1.setName("address");
            t2c1.setSelectChance(0.1);
            t2c1.setDeleteChance(0.1);
            t2c1.setInsertChance(0.1);
            t2c1.setUpdateChance(0.1);
        t2_columns.add(t2c1);
        Column t2c2 = new Column();
            t2c2.setName("district");
            t2c2.setSelectChance(0.2);
            t2c2.setDeleteChance(0.2);
            t2c2.setInsertChance(0.2);
            t2c2.setUpdateChance(0.2);
        t2_columns.add(t2c2);
        Column t2c3 = new Column();
            t2c3.setName("phone");
            t2c3.setSelectChance(0.2);
            t2c3.setDeleteChance(0.3);
            t2c3.setInsertChance(0.3);
            t2c3.setUpdateChance(0.3);
        t2_columns.add(t2c3);
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
        Column t3c1 = new Column();
            t3c1.setName("last_update");
            t3c1.setSelectChance(0.1);
            t3c1.setDeleteChance(0.1);
            t3c1.setInsertChance(0.1);
            t3c1.setUpdateChance(0.1);
        t3_columns.add(t3c1);
        Column t3c2 = new Column();
            t3c2.setName("city");
            t3c2.setSelectChance(0.2);
            t3c2.setDeleteChance(0.6);
            t3c2.setInsertChance(0.6);
            t3c2.setUpdateChance(0.6);
        t3_columns.add(t3c2);
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
        Column t4c1 = new Column();
            t4c1.setName("store_id");
            t4c1.setSelectChance(0.1);
            t4c1.setDeleteChance(0.1);
            t4c1.setInsertChance(0.1);
            t4c1.setUpdateChance(0.1);
        t4_columns.add(t4c1);
        Column t4c2 = new Column();
            t4c2.setName("first_name");
            t4c2.setSelectChance(0.5);
            t4c2.setDeleteChance(0.5);
            t4c2.setInsertChance(0.5);
            t4c2.setUpdateChance(0.5);
        t4_columns.add(t4c2);
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
        Column t5c1 = new Column();
            t5c1.setName("title");
            t5c1.setSelectChance(0.1);
            t5c1.setDeleteChance(0.1);
            t5c1.setInsertChance(0.1);
            t5c1.setUpdateChance(0.1);
        t5_columns.add(t5c1);
        Column t5c2 = new Column();
            t5c2.setName("description");
            t5c2.setSelectChance(0.3);
            t5c2.setDeleteChance(0.3);
            t5c2.setInsertChance(0.3);
            t5c2.setUpdateChance(0.3);
        t5_columns.add(t5c2);
        t5.setColumns(t5_columns);
        chances.add(t5);

        List<String> queries = new ArrayList<String>();
        queries.add("select column1, column2 from table1");
        queries.add("select column3 from table2");
        queries.add("delete from table2 where column1 = constant1");
        queries.add("INSERT INTO table1 (column1, column2, column3) VALUES (constant1, constant2, constant3)");

        QueryGenerator generator = new QueryGenerator(chances, queries);
        List<String> result = generator.generate(true);

        assert (result.size() == 4) : "there should be 4 templates";

        for (String s: result) {
            System.out.println(s);
        }
    }


//    public int someWork() {
//        List<Table> chances = new ArrayList<Table>();
//
//        Table t1 = new Table();
//        t1.setName("student");
//        t1.setSelectChance(0.1);
//
//        chances.add(t1);
//
//        Table t2 = new Table();
//        t2.setName("user");
//        t2.setSelectChance(0.5);
//        chances.add(t2);
//
//        Table t3 = new Table();
//        t3.setName("teacher");
//        t3.setSelectChance(0.1);
//        chances.add(t3);
//
//        Table t4 = new Table();
//        t4.setName("car");
//        t4.setSelectChance(0.2);
//        chances.add(t4);
//
//        Table t5 = new Table();
//        t5.setName("house");
//        t5.setSelectChance(0.1);
//        chances.add(t5);
//
//        List<String> queries = new ArrayList<String>();
//        queries.add("select column1, column2 from table1");
//        queries.add("select column3 from table2");
//        queries.add("delete from table2 where column1 = constant1");
//
//
//        QueryGenerator generator = new QueryGenerator(chances, queries);
//
//        List<String> result = generator.generate();
//
//        return result.size();
//    }
}
