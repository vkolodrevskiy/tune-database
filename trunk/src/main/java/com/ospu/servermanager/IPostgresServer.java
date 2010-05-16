package com.ospu.servermanager;

/**
 * Managing postgresql server.
 *
 * @author vkolodrevskiy
 */
public interface IPostgresServer {
    /**
     * Starts postgresql server.
     */
    void start();

    /**
     * Stops postgresql server.
     */
    void stop();

    /**
     * Restarts postgresql server.
     */
    void restart();
}
