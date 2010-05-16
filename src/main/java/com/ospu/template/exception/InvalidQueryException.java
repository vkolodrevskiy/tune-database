package com.ospu.template.exception;

/**
 * This exception is thrown when template is not valid.
 * e.g. does not begin with something like <code>insert</code>,
 * <code>update</code>, <code>delete</code> or <code>select</code>.
 *
 * @author vkolodrevskiy
 */
public class InvalidQueryException extends Exception {

    public InvalidQueryException(String message) {
        super(message);
    }
}
