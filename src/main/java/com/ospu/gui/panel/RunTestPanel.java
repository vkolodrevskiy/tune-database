package com.ospu.gui.panel;

import com.ospu.test.Runner;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Run test panel.
 *
 * @author vkolodrevskiy
 */
public class RunTestPanel extends JPanel {
    /*
    * log4j audit channel
    */
    private static final Logger logger = Logger.getLogger(RunTestPanel.class);

    private JComponent panel;

    public RunTestPanel(final Connection applicatioDBconnection) {
        panel = this;

        setLayout(null);
        JLabel lab1 = new JLabel("Запуск тестов(выбрать тест и число потоков):");
        lab1.setBounds(10, 10, 400, 25);
        add(lab1);

        JLabel lab2 = new JLabel("Имя теста:");
        lab2.setBounds(10, 40, 130, 25);
        add(lab2);

        final JComboBox jcbSelectTest = new JComboBox();
        jcbSelectTest.setBounds(130, 40, 140, 25);
        try {
            Statement stat;
            ResultSet rs;
            stat = applicatioDBconnection.createStatement();
            rs = stat.executeQuery("SELECT name FROM test");
            while(rs.next()) {
                jcbSelectTest.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        add(jcbSelectTest);

        final JButton jbUpdateTable = new JButton("Обновить");
        jbUpdateTable.setBounds(280, 40, 120, 25);
        jbUpdateTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                jcbSelectTest.removeAllItems();
                // add items
                try {
                    Statement stat;
                    ResultSet rs;
                    stat = applicatioDBconnection.createStatement();
                    rs = stat.executeQuery("SELECT name FROM test");
                    while(rs.next()) {
                        jcbSelectTest.addItem(rs.getString(1));
                    }
                } catch (SQLException exc) {
                    logger.error(exc.getMessage());
                }
            }
        });
        add(jbUpdateTable);

        final JComboBox jcbThreadNumber = new JComboBox();
        jcbThreadNumber.setBounds(130, 75, 140, 25);
        jcbThreadNumber.addItem("1");
        jcbThreadNumber.addItem("2");
        jcbThreadNumber.addItem("3");
        jcbThreadNumber.addItem("4");
        jcbThreadNumber.addItem("5");
        add(jcbThreadNumber);

        JLabel lab3 = new JLabel("Число потоков:");
        lab3.setBounds(10, 75, 130, 25);
        add(lab3);

        JButton jbBrowseLogFile = new JButton("Запуск теста");
        jbBrowseLogFile.setBounds(10, 120, 170, 25);
        jbBrowseLogFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // running test
                logger.info("Running test " + jcbSelectTest.getSelectedItem()
                        + "; number of threads " + jcbThreadNumber.getSelectedItem());

                Runner r = new Runner(Integer.parseInt(jcbThreadNumber.getSelectedItem().toString()), jcbSelectTest.getSelectedItem().toString());
                r.runTests();

                JOptionPane.showConfirmDialog(panel, "Тест завершился", "", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(jbBrowseLogFile);
    }
}
