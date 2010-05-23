package com.ospu.scale;

import com.ospu.matrix.MatrixItem;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Working with chance scales for matrix elements.
 *
 * @author vkolodrevskiy
 */
public class MatrixElementsScaleManager {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(MatrixElementsScaleManager.class);

    public static final String EMPTY_STATE = "empty state";

    private List<MatrixItem> items;
    private Map<String, Scale> elements;

    public MatrixElementsScaleManager(List<MatrixItem> items) {
        this.items = items;
        elements = new HashMap<String, Scale>();

        for(MatrixItem item: items) {
            elements.put(item.getFrom(), createScale(item.getFrom()));
        }
        System.out.println("");
    }

    /**
     * Generates collection of templates of the specified size,
     * based on the chance matrix.
     *
     * @param quantity amount of items to generate.
     * @return <code>List</code> of templates.
     */
    public List<String> getTemplates(int quantity) {
        List<String> result = new ArrayList<String>();

        // randomly select element to start with
        Random r = new Random();
        String first = items.get(r.nextInt(items.size())).getFrom();
        // ensure that first element is not EMPTY_STATE
        while(first.equals(EMPTY_STATE))
            first = items.get(r.nextInt(items.size())).getFrom();

        result.add(first);

        for(int i=0; i<quantity-1; i++) {
            result.add(getNext(result.get(result.size()-1)));
        }

        return result;
    }

    // ------------------------------------------------------------------------
    private String getNext(String from) {
        String element2return = EMPTY_STATE;
        Scale scale = elements.get(from);

        while(element2return.equals(EMPTY_STATE)) {
            double chance = Math.random();
            for(ScaleElement element: scale.getElements()) {
                if(chance > element.getStart() && chance <= element.getEnd()) {
                    element2return = element.getName();
                }
            }
        }

        return element2return;
    }

    private Scale createScale(String state) {
        Scale scale = new Scale();
        List<ScaleElement> scaleElements = new ArrayList<ScaleElement>();

        double next = 0;
        for(MatrixItem item: items) {
            if(item.getFrom().equals(state)) {
                ScaleElement scaleElement = new ScaleElement();
                scaleElement.setName(item.getTo());
                scaleElement.setStart(next);
                // increase scale
                next = next + item.getChance();
                scaleElement.setEnd(next);

                scaleElements.add(scaleElement);
            }
        }

        scale.setElements(scaleElements);
        return scale;
    }
}
