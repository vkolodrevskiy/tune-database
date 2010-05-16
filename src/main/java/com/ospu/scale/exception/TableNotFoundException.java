package com.ospu.scale.exception;

/**
 * This exception is thrown when a table with a given chance
 * was not found on the scale.
 *
 * @author vkolodrevskiy
 */
public class TableNotFoundException extends Exception {

    public TableNotFoundException(String message) {
        super(message);
    }
}
