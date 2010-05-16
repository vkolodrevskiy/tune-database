package com.ospu.servermanager.exception;

/**
 * This exception is thrown when user password is invalid.
 *
 * @author vkolodrevskiy
 */
public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
