package com.ospu.metadata;

/**
 * Represents database table column
 * (table attribute).
 *
 * @author vkolodrevskiy
 */
public class DatabaseColumn {
    private String name;
    private String type;

    // ------------------------------------------------------------------------
    // Setters and getters.
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}
