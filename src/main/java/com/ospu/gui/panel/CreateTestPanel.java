package com.ospu.gui.panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ospu.gui.JIntegerTextField;
import com.ospu.gui.tablemodel.CachingResultSetTableModel;
import com.ospu.gui.tablemodel.ResultSetTableModel;
import com.ospu.util.DatabaseConnection;
import org.apache.log4j.Logger;

/**
 * Panel for creating new tests.
 *
 * @author vkolodrevskiy
 */
public class CreateTestPanel extends JPanel {
    /*
    * log4j audit channel
    */
    private static final Logger logger = Logger.getLogger(CreateTestPanel.class);
    private JTable table;

    private JComponent panel;

    public CreateTestPanel(final Connection applicationDBconnection) {
        panel = this;

        setLayout(null);
        final JLabel lab1 = new JLabel("Создание нового теста:");
        lab1.setBounds(10, 10, 400, 25);
        add(lab1);

        final JLabel lab2 = new JLabel("Уже имеющиеся в базе тесты:");
        lab2.setBounds(10, 170, 400, 25);
        add(lab2);

        final JLabel jlTestName = new JLabel("Имя теста:");
        jlTestName.setBounds(10, 40, 130, 25);
        add(jlTestName);

        final JTextField jtfTestName = new JTextField();
        jtfTestName.setBounds(130, 40, 140, 25);
        add(jtfTestName);

        final JLabel jlLogFile = new JLabel("Файл логов:");
        jlLogFile.setBounds(10, 75, 130, 25);
        add(jlLogFile);

        final JTextField jtfLogFile = new JTextField();
        jtfLogFile.setBounds(130, 75, 260, 25);
        add(jtfLogFile);

        final JLabel jlTestQuantity = new JLabel("Число запросов:");
        jlTestQuantity.setBounds(10, 110, 130, 25);
        add(jlTestQuantity);

        final JTextField jtfTestQuantity = new JIntegerTextField();
        jtfTestQuantity.setBounds(130, 110, 140, 25);
        add(jtfTestQuantity);

        final JLabel jlTimeInterval = new JLabel("Интервал(мс):");
        jlTimeInterval.setBounds(280, 40, 130, 25);
        add(jlTimeInterval);

        final JTextField jtfTimeInterval = new JIntegerTextField();
        jtfTimeInterval.setBounds(400, 40, 140, 25);
        add(jtfTimeInterval);

        final JButton jbBrowseLogFile = new JButton("Обзор...");
        jbBrowseLogFile.setBounds(400, 75, 100, 25);
        jbBrowseLogFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(panel);
                if(chooser.getSelectedFile() != null)
                    jtfLogFile.setText(chooser.getSelectedFile().getPath());
            }
        });
        add(jbBrowseLogFile);

        final JButton jbCreateTemplates = new JButton("Добавить в базу");
        jbCreateTemplates.setBounds(10, 140, 160, 25);
        jbCreateTemplates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                // validation
                if(jtfTestName.getText().isEmpty()) {
                    JOptionPane.showConfirmDialog(panel, "Необходимо указать имя теста", "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(jtfLogFile.getText().isEmpty()) {
                    JOptionPane.showConfirmDialog(panel, "Необходимо указать файл логов", "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(jtfTestQuantity.getText().isEmpty()) {
                    JOptionPane.showConfirmDialog(panel, "Необходимо указать число запросов", "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(jtfTimeInterval.getText().isEmpty()) {
                    JOptionPane.showConfirmDialog(panel, "Необходимо указать временной интервал считывания логов", "", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // TODO:
                // read file

                // matrix, chances, templates

                // generate requests

                // save test to database
                List<String> l = new ArrayList<String>();
                l.add("bla bla");
                l.add("bla2 bla2 ");
                saveTest(jtfTestName.getText(), l);

                // clear text fields
                jtfTestName.setText("");
                jtfLogFile.setText("");
                jtfTestQuantity.setText("");
                jtfTimeInterval.setText("");
            }
        });
        add(jbCreateTemplates);

        final JButton jbUpdateTable = new JButton("Обновить");
        jbUpdateTable.setBounds(10, 200, 170, 25);
        jbUpdateTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final Statement stat;
                    final ResultSet rs;
                    final ResultSetTableModel model;
                    stat = applicationDBconnection.createStatement();
                    rs = stat.executeQuery("SELECT query AS \"запрос\", name AS \"имя теста\" FROM query\n" +
                            "JOIN test\n" + "ON test.id=query.test_id");
                    model = new CachingResultSetTableModel(rs);

                    table.setModel(model);
                    rs.close();
                    stat.close();
                } catch (SQLException e1) {
                    logger.error(e1.getMessage());
                }
            }
        });
        add(jbUpdateTable);

        try {
            Statement stat;
            ResultSet rs;
            ResultSetTableModel model;
            JScrollPane scrollPane;
            stat = applicationDBconnection.createStatement();
            rs = stat.executeQuery("SELECT query AS \"запрос\", name AS \"имя теста\" FROM query\n" +
                    "JOIN test\n" + "ON test.id=query.test_id");
            model = new CachingResultSetTableModel(rs);

            table = new JTable(model);

            scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(10, 230, 880, 385);

            add(scrollPane);

            rs.close();
            stat.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private void saveTest(String testName, List<String> queries) {
        // get database connection
        final Connection connection = DatabaseConnection.getConnectionToApplicationDatabase();
        Statement stat;

        try {
            stat = connection.createStatement();

            stat.executeUpdate("insert into public.test (name) values ('" + testName + "')");
            logger.debug("saved test to test table with name = " + testName);
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(panel, "Неудалось добавить тест. Возвожно тест с таким именем уже существует", "",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            logger.error(e.getMessage());
            return;
        }

        try {
            stat = connection.createStatement();

            ResultSet result = stat.executeQuery("select id from test where name='" + testName + "'");
            Long testId;
            result.next();
            testId = result.getLong(1);

            for(String query: queries) {
                stat.executeUpdate("insert into public.query (query, test_id) " +
                                   "values ('" + query + "', "+ testId + ")");
            }
            logger.debug("saved queries for test with name = " + testName);

            JOptionPane.showConfirmDialog(panel, "Новый тест успешно добавлен", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            stat.close();
            // close database connection
            connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
