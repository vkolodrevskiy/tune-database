package com.ospu.scale;

/**
 * Represents a scale element. This is an element of scale.
 *
 * @author vkolodrevskiy
 */
public class ScaleElement {
    private String name;
    private double start;
    private double end;

    // ------------------------------------------------------------------------
    // Setters and getters.
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getStart() {
        return start;
    }

    public void setStart(final double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(final double end) {
        this.end = end;
    }
}
