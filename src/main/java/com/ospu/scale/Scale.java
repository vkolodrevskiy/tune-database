package com.ospu.scale;

import java.util.List;

/**
 * Scale, sample:
 * <p>Ptab1=0.1; Ptab2=0.4; Ptab3=0.5
 * <p>(0;0.1], (0.1;5], (0.5;1]
 *
 * @author vkolodrevskiy
 */
public class Scale {
    private List<ScaleElement> elements;

    // ------------------------------------------------------------------------
    public List<ScaleElement> getElements() {
        return elements;
    }

    public void setElements(final List<ScaleElement> elements) {
        this.elements = elements;
    }
}
