package com.ospu.servermanager.impl;

import com.ospu.servermanager.IPostgresServer;
import com.ospu.servermanager.exception.InvalidPasswordException;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * {@link com.ospu.servermanager.IPostgresServer} linux implementation.
 *
 * @author vkolodrevskiy
 */
public class LinuxServer implements IPostgresServer {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(LinuxServer.class);

    private final String userPassword;

    /**
     * Creates postgres lunux server implementation.
     * @param userPassword linux root password.
     * @throws com.ospu.servermanager.exception.InvalidPasswordException if root password is invalid.
     */
    public LinuxServer(String userPassword) throws InvalidPasswordException {
        this.userPassword = userPassword;

        // validate user password; e.g. try to perform "sudo ls"
        try {
            Runtime rtime = Runtime.getRuntime();
            Process child = rtime.exec("/bin/sh");
            BufferedWriter outCommand = new BufferedWriter(new OutputStreamWriter(child.getOutputStream()));
            outCommand.write("echo " + userPassword + " | sudo -S ls" + "\n");
            outCommand.write("exit" + "\n");
            outCommand.flush();
            int wait = child.waitFor();
            if(wait != 0)
                throw new InvalidPasswordException("Exit code = " + wait + "; Looks like user password is invalid.");
            logger.debug("Run \"sudo ls\" exit code: " + wait);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    //-------------------------------------------------------------------------
    /**
     * @inheritDoc
     * @see com.ospu.servermanager.IPostgresServer#start()
     */
    @Override
    public void start() {
        runCommand(Command.START);
    }

    /**
     * @inheritDoc
     * @see com.ospu.servermanager.IPostgresServer#stop()
     */
    @Override
    public void stop() {
        runCommand(Command.STOP);
    }

    /**
     * @inheritDoc
     * @see com.ospu.servermanager.IPostgresServer#restart()
     */
    @Override
    public void restart() {
        runCommand(Command.RESTART);
    }

    // ------------------------------------------------------------------------
    private void runCommand(Command c) {
        try {
            Runtime rtime = Runtime.getRuntime();

            //start unix shell
            Process child = rtime.exec("/bin/sh");
            //or "/bin/sh set -t" to auto-exit after 1 command

            BufferedWriter outCommand = new BufferedWriter(new OutputStreamWriter(child.getOutputStream()));

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(child.getInputStream()));

            //run command
            outCommand.write("echo " + userPassword + " | sudo -S /etc/init.d/postgresql-8.4 " + c.toString().toLowerCase() + "\n");
            //outCommand.flush();

            //exit the unix shell
            outCommand.write("exit" + "\n");
            outCommand.flush();

            int wait = child.waitFor();
            logger.debug("exit code: " + wait);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    private enum Command {
        START,
        STOP,
        RESTART
    }
}
