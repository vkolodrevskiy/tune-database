package com.ospu;

import com.ospu.chance.Table;
import com.ospu.scale.Scale;
import com.ospu.scale.ScaleManager;
import com.ospu.scale.exception.TableNotFoundException;
import com.ospu.template.QueryType;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for {@link ScaleManager}.
 *
 * @author vkolodrevskiy
 */
public class ScaleManagerTest {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(ScaleManagerTest.class);

    @Test
    public void testManager() {
        List<Table> tables = new ArrayList<Table>();

        Table t1 = new Table();
            t1.setName("table1");
            t1.setSelectChance(0.1);
        tables.add(t1);

        Table t2 = new Table();
            t2.setName("table2");
            t2.setSelectChance(0.2);
        tables.add(t2);

        Table t3 = new Table();
            t3.setName("table3");
            t3.setSelectChance(0.7);
        tables.add(t3);

        ScaleManager manager = new ScaleManager(tables);
        //Scale s1 = manager.createTableScaleForSelect(tables);

        //assert (s1.getElements().size() == 3) : "3 elements expected in the scale";
        //assert (s1.getElements().get(2).getEnd() == 1.0) : "start expected to be 0.3";

        String table2Find = null;
//        try {
            table2Find = manager.getTable(QueryType.SELECT, 0.25);
//        } catch (TableNotFoundException e) {
//            logger.error(e.getMessage());
//        }

        assert table2Find != null;
        assert (table2Find.equals("table2")) : "table should be table2";

        //try {
            table2Find = manager.getTable(QueryType.SELECT, 0.3);
        //} catch (TableNotFoundException e) {
        //    logger.error(e.getMessage());
        //}

        assert (table2Find.equals("table2")) : "table should be table2";

        //try {
            table2Find = manager.getTable(QueryType.SELECT, 0.5);
        //} catch (TableNotFoundException e) {
        //    logger.error(e.getMessage());
        //}

        assert (table2Find.equals("table3")) : "table should be table3";
        
        //try {
            table2Find = manager.getTable(QueryType.SELECT, 1);
        //} catch (TableNotFoundException e) {
        //    logger.error(e.getMessage());
        //}

        assert (table2Find.equals("table3")) : "table should be table3";

        table2Find = "table not found";
        //try {
            table2Find = manager.getTable(QueryType.SELECT, 0);
        //} catch (TableNotFoundException e) {
        //    logger.error(e.getMessage());
        //}

        assert (!table2Find.equals("table not found")) : "table should be found";

//        Math.random();
//        for(int i=0; i < 100; i++) {
//            logger.debug(Math.random());
//        }
        //System.out.println(table2Find);
    }
}
