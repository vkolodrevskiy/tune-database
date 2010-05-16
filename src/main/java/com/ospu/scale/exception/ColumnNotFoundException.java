package com.ospu.scale.exception;

/**
 * This exception is thrown when a column with a given chance
 * was not found on the scale.
 *
 * @author vkolodrevskiy
 *         To change this template use File | Settings | File Templates.
 */
public class ColumnNotFoundException extends Exception {

    public ColumnNotFoundException(String message) {
        super(message);
    }
}
