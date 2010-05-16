package com.ospu;

import com.ospu.gui.panel.*;
import com.ospu.servermanager.PostgresServerManager;
import com.ospu.servermanager.exception.InvalidPasswordException;
import com.ospu.servermanager.impl.LinuxServer;
import com.ospu.util.DatabaseConnection;
import org.apache.log4j.Logger;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.*;

/**
 * Main application.
 *
 * @author vkolodrevskiy
 */
public class App {
    /*
     * log4j audit channel
     */
    private static final Logger logger = Logger.getLogger(App.class);

    private static Connection testingDBconnection;
    private static Connection applicationDBConnection;

    /**
     * Add some other components.
     *
     * @param pane where to add components.
     */
    public static void addComponentsToPane(Container pane) {

        testingDBconnection = DatabaseConnection.getConnectionToTestingDatabase();
        applicationDBConnection = DatabaseConnection.getConnectionToApplicationDatabase();

        JPanel pEditDatabaseParameters = new EditDatabaseParametersPanel();
        JPanel pDatabaseStructure = new DatabaseStructurePanel(testingDBconnection);
        JPanel pTestResults = new TestResultsPanel(applicationDBConnection);
        JPanel pCreateTest = new CreateTestPanel(applicationDBConnection);
        JPanel pRunTestPanel = new RunTestPanel(applicationDBConnection);

        JTabbedPane tabPane = new JTabbedPane();
        tabPane.setBounds(0, 10, 900, 650);

        tabPane.add("Параметры БД", pEditDatabaseParameters);
        tabPane.add("Структура БД", pDatabaseStructure);
        tabPane.add("Результаты", pTestResults);
        tabPane.add("Новый тест", pCreateTest);
        tabPane.add("Запуск тестов", pRunTestPanel);

        pane.add(tabPane);
    }

    /**
     * Creates frame menu.
     *
     * @param frame frame to which add menu.
     */
    public static void addMenu(final JFrame frame) {
        Action exitAction = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Exiting application.");
                try {
                    testingDBconnection.close();
                    applicationDBConnection.close();
                } catch (SQLException e1) {
                    logger.error("failed to close database testingDBconnection: " + e1.getMessage());
                }
                System.exit(0);
            }
        };

        // get root password.
        final String password = getPassword();

        Action stopAction = new AbstractAction("Stop") {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Stopping database server.");
                try {
                    PostgresServerManager manager = new PostgresServerManager(new LinuxServer(password));
                    if(System.getProperty("os.name").toUpperCase().startsWith("LINUX")) {
                        manager.stop();
                        JOptionPane.showConfirmDialog(frame, "Сервер остановлен", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }
                    else logger.error("Can not stop server; looks like you're not running LINUX");
                } catch (InvalidPasswordException exc) {
                    logger.error("Root password is invalid; password = " + password);
                }
            }
        };

        Action startAction = new AbstractAction("Start") {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Starting database server.");
                try {
                    PostgresServerManager manager = new PostgresServerManager(new LinuxServer(password));
                    if(System.getProperty("os.name").toUpperCase().startsWith("LINUX")) {
                        manager.start();
                        JOptionPane.showConfirmDialog(frame, "Сервер запущен", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }
                    else logger.error("Can not start server; looks like you're not running LINUX");
                } catch (InvalidPasswordException exc) {
                    logger.error("Root password is invalid; password = " + password);
                }
            }
        };

        Action restartAction = new AbstractAction("Restart") {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Restarting database server.");
                try {
                    PostgresServerManager manager = new PostgresServerManager(new LinuxServer(password));
                    if(System.getProperty("os.name").toUpperCase().startsWith("LINUX")) {
                        manager.restart();
                        JOptionPane.showConfirmDialog(frame, "Сервер перезагружен", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    }
                    else logger.error("Can not restart server; looks like you're not running LINUX");
                } catch (InvalidPasswordException exc) {
                    logger.error("Root password is invalid; password = " + password);
                }
            }
        };

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem exitItem = new JMenuItem(exitAction);
        fileMenu.add(exitItem);

        JMenu serverMenu = new JMenu("Server");
        menuBar.add(serverMenu);

        JMenuItem stopItem = new JMenuItem(stopAction);
        serverMenu.add(stopItem);

        JMenuItem startItem = new JMenuItem(startAction);
        serverMenu.add(startItem);

        JMenuItem restartItem = new JMenuItem(restartAction);
        serverMenu.add(restartItem);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame();

        frame.setLayout(null);
        frame.setTitle("Testing database application");
        frame.setResizable(false);
        frame.setBounds(230, 20, 908, 710);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setup the content pane.
        addComponentsToPane(frame.getContentPane());

        // setup menu
        addMenu(frame);

        //Display the window.
        frame.setVisible(true);
    }

    /**
     * Reads user password from application.properties file.
     * @return user password.
     */
    private static String getPassword() {
        try {
            final Properties props = new Properties();
            final FileInputStream in = new FileInputStream("application.properties");
            props.load(in);
            in.close();

            return props.getProperty("root.password");
        } catch (final IOException e) {
            logger.error("Can not read user password; " + e.getMessage());
        }

        return null;
    }

    // ------------------------------------------------------------------------
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
