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
            t1.setName("student");
            t1.setSelectChance(0.1);
            t1.setDeleteChance(0.0);
            t1.setInsertChance(0.1);
            t1.setUpdateChance(0.1);
        // setup columns
        List<Column> columns1 = new ArrayList<Column>();
        Column c1 = new Column();
            c1.setName("first_name");
            c1.setSelectChance(0.1);
            c1.setDeleteChance(0.1);
            c1.setInsertChance(0.1);
            c1.setUpdateChance(0.1);
        columns1.add(c1);
        Column c2 = new Column();
            c2.setName("last_name");
            c2.setSelectChance(0.5);
            c2.setDeleteChance(0.5);
            c2.setInsertChance(0.5);
            c2.setUpdateChance(0.5);
        columns1.add(c2);
        t1.setColumns(columns1);
        chances.add(t1);

        Table t2 = new Table();
            t2.setName("user");
            t2.setSelectChance(0.5);
            t2.setDeleteChance(0.0);
            t2.setInsertChance(0.1);
            t2.setUpdateChance(0.1);
        // setup columns
        List<Column> columns2 = new ArrayList<Column>();
        Column c3 = new Column();
            c3.setName("username");
            c3.setSelectChance(0.1);
            c3.setDeleteChance(0.1);
            c3.setInsertChance(0.1);
            c3.setUpdateChance(0.1);
        columns2.add(c3);
        Column c4 = new Column();
            c4.setName("password");
            c4.setSelectChance(0.2);
            c4.setDeleteChance(0.6);
            c4.setInsertChance(0.6);
            c4.setUpdateChance(0.6);
        columns2.add(c4);
        t2.setColumns(columns2);
        chances.add(t2);

        Table t3 = new Table();
            t3.setName("teacher");
            t3.setSelectChance(0.1);
            t3.setDeleteChance(0.0);
            t3.setInsertChance(0.1);
            t3.setUpdateChance(0.1);
        // setup columns
        List<Column> columns3 = new ArrayList<Column>();
        Column c5 = new Column();
            c5.setName("name");
            c5.setSelectChance(0.1);
            c5.setDeleteChance(0.1);
            c5.setInsertChance(0.1);
            c5.setUpdateChance(0.1);
        columns3.add(c5);
        Column c6 = new Column();
            c6.setName("subject");
            c6.setSelectChance(0.2);
            c6.setDeleteChance(0.6);
            c6.setInsertChance(0.6);
            c6.setUpdateChance(0.6);
        columns3.add(c6);
        t3.setColumns(columns3);
        chances.add(t3);

        Table t4 = new Table();
            t4.setName("car");
            t4.setSelectChance(0.2);
            t4.setDeleteChance(0.0);
            t4.setInsertChance(0.1);
            t4.setUpdateChance(0.1);
        // setup columns
        List<Column> columns4 = new ArrayList<Column>();
        Column c7 = new Column();
            c7.setName("model");
            c7.setSelectChance(0.1);
            c7.setDeleteChance(0.1);
            c7.setInsertChance(0.1);
            c7.setUpdateChance(0.1);
        columns4.add(c7);
        Column c8 = new Column();
            c8.setName("win");
            c8.setSelectChance(0.5);
            c8.setDeleteChance(0.5);
            c8.setInsertChance(0.5);
            c8.setUpdateChance(0.5);
        columns4.add(c8);
        t4.setColumns(columns4);
        chances.add(t4);

        Table t5 = new Table();
            t5.setName("house");
            t5.setSelectChance(0.1);
            t5.setDeleteChance(1);
            t5.setInsertChance(0.1);
            t5.setUpdateChance(0.1);
        // setup columns
        List<Column> columns5 = new ArrayList<Column>();
        Column c9 = new Column();
            c9.setName("address");
            c9.setSelectChance(0.1);
            c9.setDeleteChance(0.1);
            c9.setInsertChance(0.1);
            c9.setUpdateChance(0.1);
        columns5.add(c9);
        Column c10 = new Column();
            c10.setName("floor_number");
            c10.setSelectChance(0.2);
            c10.setDeleteChance(0.2);
            c10.setInsertChance(0.2);
            c10.setUpdateChance(0.2);
        columns5.add(c10);
        t5.setColumns(columns5);
        chances.add(t5);

        List<String> queries = new ArrayList<String>();
        queries.add("select column1, column2 from table1");
        queries.add("select column3 from table2");
        queries.add("delete from table2 where column1 = constant1");
        queries.add("INSERT INTO table1 (column1, column2, column3) VALUES (constant1, constant2, constant3)");


        /*QueryGenerator generator = new QueryGenerator(chances, queries);
        List<String> result = generator.generate();

        assert (result.size() == 4) : "there should be 4 templates";

        for (String s: result) {
            System.out.println(s);
        }*/

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
