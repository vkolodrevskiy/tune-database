package com.ospu.servermanager;

/**
 * Managing postgresql server.
 *
 * @author vkolodrevskiy
 */
public class PostgresServerManager {
    private IPostgresServer postgresServer;

    public PostgresServerManager(IPostgresServer postgresServer) {
        this.postgresServer = postgresServer;
    }

    public void setPostgresServer(IPostgresServer postgresServer) {
        this.postgresServer = postgresServer;
    }

    public void start() {
        postgresServer.start();
    }

    public void stop() {
        postgresServer.stop();
    }

    public void restart() {
        postgresServer.restart();
    }
}
