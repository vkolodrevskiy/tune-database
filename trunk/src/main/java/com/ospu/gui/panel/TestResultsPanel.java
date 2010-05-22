package com.ospu.gui.panel;

import com.ospu.gui.tablemodel.CachingResultSetTableModel;
import com.ospu.gui.tablemodel.ResultSetTableModel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Displays test results.
 *
 * @author vkolodrevskiy
 */
public class TestResultsPanel extends JPanel {
    /*
    * log4j audit channel
    */
    private static final Logger logger = Logger.getLogger(TestResultsPanel.class);

    private JTable table;

    public TestResultsPanel(final Connection applicationDBConnection) {

        setLayout(null);
        JLabel lab1 = new JLabel("Результаты тестов:");
        lab1.setBounds(10, 10, 300, 25);
        add(lab1);

        JButton jbUpdateTable = new JButton("Обновить");
        jbUpdateTable.setBounds(10, 100, 170, 25);
        jbUpdateTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Statement stat;
                    ResultSet rs;
                    ResultSetTableModel model;
                    JScrollPane scrollPane;
                    stat = applicationDBConnection.createStatement();
                    rs = stat.executeQuery("SELECT name AS \"имя теста\", " +
                                           "duration AS \"общая длительность теста(мс)\", " +
                                           "average AS \"средняя длительность запроса(мс)\", " +
                                           "execution_date AS \"дата\" FROM result \n" +
                                           "JOIN test \n" +
                                           "ON test.id=result.test_id\n" +
                                           "ORDER BY execution_date DESC");
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
            stat = applicationDBConnection.createStatement();
            rs = stat.executeQuery("SELECT name AS \"имя теста\", " +
                    "duration AS \"общая длительность теста(мс)\", " +
                    "average AS \"средняя длительность запроса(мс)\", " +
                    "execution_date AS \"дата\" FROM result \n" +
                    "JOIN test \n" +
                    "ON test.id=result.test_id\n" +
                    "ORDER BY execution_date DESC");
            model = new CachingResultSetTableModel(rs);

            table = new JTable(model);

            scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(10, 130, 880, 485);

            add(scrollPane);

            rs.close();
            stat.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
