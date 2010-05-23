package com.ospu;

import com.ospu.matrix.MatrixItem;
import com.ospu.scale.MatrixElementsScaleManager;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link MatrixElementsScaleManager} tests.
 *
 * @author vkolodrevskiy
 */
public class MatrixElementsScaleManagerTest {

    @Test
    public void testManager() {
        MatrixElementsScaleManager matrix = new MatrixElementsScaleManager(getItems());
        List<String> result = matrix.getTemplates(100);

        assert (result.size() == 100) : "there should be 100 results";

        for(String s: result) {
            assert (!s.equals(MatrixElementsScaleManager.EMPTY_STATE)) : "there should not be empty state in result";
        }
    }

    // ------------------------------------------------------------------------
    /**
     * Test items.
     * @return collection of test items.
     */
    private List<MatrixItem> getItems() {
        List<MatrixItem> items = new ArrayList<MatrixItem>();

        MatrixItem item = new MatrixItem();
        item.setFrom("t1");
        item.setTo("t1");
        item.setChance(0.1);
        items.add(item);

        item = new MatrixItem();
        item.setFrom("t1");
        item.setTo("t2");
        item.setChance(0.5);
        items.add(item);

        item = new MatrixItem();
        item.setFrom("t1");
        item.setTo("t3");
        item.setChance(0.3);
        items.add(item);

        item = new MatrixItem();
        item.setFrom("t1");
        item.setTo(MatrixElementsScaleManager.EMPTY_STATE);
        item.setChance(0.1);
        items.add(item);

        //
        item = new MatrixItem();
        item.setFrom("t2");
        item.setTo("t1");
        item.setChance(0.2);
        items.add(item);

        item = new MatrixItem();
        item.setFrom("t2");
        item.setTo("t2");
        item.setChance(0.3);
        items.add(item);

        item = new MatrixItem();
        item.setFrom("t2");
        item.setTo("t3");
        item.setChance(0.4);
        items.add(item);

        item = new MatrixItem();
        item.setFrom("t2");
        item.setTo(MatrixElementsScaleManager.EMPTY_STATE);
        item.setChance(0.1);
        items.add(item);

        //
        item = new MatrixItem();
        item.setFrom("t3");
        item.setTo("t1");
        item.setChance(0.4);
        items.add(item);

        item = new MatrixItem();
        item.setFrom("t3");
        item.setTo("t2");
        item.setChance(0.0);
        items.add(item);

        item = new MatrixItem();
        item.setFrom("t3");
        item.setTo("t3");
        item.setChance(0.2);
        items.add(item);

        item = new MatrixItem();
        item.setFrom("t3");
        item.setTo(MatrixElementsScaleManager.EMPTY_STATE);
        item.setChance(0.4);
        items.add(item);

        //
        item = new MatrixItem();
        item.setFrom(MatrixElementsScaleManager.EMPTY_STATE);
        item.setTo("t1");
        item.setChance(0.3);
        items.add(item);

        item = new MatrixItem();
        item.setFrom(MatrixElementsScaleManager.EMPTY_STATE);
        item.setTo("t2");
        item.setChance(0.25);
        items.add(item);

        item = new MatrixItem();
        item.setFrom(MatrixElementsScaleManager.EMPTY_STATE);
        item.setTo("t3");
        item.setChance(0.35);
        items.add(item);

        item = new MatrixItem();
        item.setFrom(MatrixElementsScaleManager.EMPTY_STATE);
        item.setTo(MatrixElementsScaleManager.EMPTY_STATE);
        item.setChance(0.1);
        items.add(item);

        return items;
    }
}
