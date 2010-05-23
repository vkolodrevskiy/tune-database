package com.ospu.matrix;

/**
 * Represents matrix element.
 *
 * @author vkolodrevskiy
 */
public class MatrixItem {
    private String from;
    private String to;
    private double chance;

    // ------------------------------------------------------------------------
    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(final double chance) {
        this.chance = chance;
    }
}
