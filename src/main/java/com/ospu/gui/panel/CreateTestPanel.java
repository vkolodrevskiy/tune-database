package com.ospu.gui.panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ospu.gui.tablemodel.CachingResultSetTableModel;
import com.ospu.gui.tablemodel.ResultSetTableModel;
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
        JLabel lab1 = new JLabel("Создание нового теста(набора шаблонов):");
        lab1.setBounds(10, 10, 400, 25);
        add(lab1);

        JLabel lab2 = new JLabel("Уже имеющиеся в базе шаблоны:");
        lab2.setBounds(10, 110, 400, 25);
        add(lab2);

        JLabel jlTestName = new JLabel("Имя теста:");
        jlTestName.setBounds(10, 40, 130, 25);
        add(jlTestName);

        JTextField jtfTestName = new JTextField();
        jtfTestName.setBounds(130, 40, 140, 25);
        add(jtfTestName);

        JLabel jlLogFile = new JLabel("Файл логов:");
        jlLogFile.setBounds(10, 75, 130, 25);
        add(jlLogFile);

        final JTextField jtfLogFile = new JTextField();
        jtfLogFile.setBounds(130, 75, 140, 25);
        add(jtfLogFile);

        JButton jbBrowseLogFile = new JButton("Обзор...");
        jbBrowseLogFile.setBounds(280, 75, 100, 25);
        jbBrowseLogFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(panel);
                if(chooser.getSelectedFile() != null)
                    jtfLogFile.setText(chooser.getSelectedFile().getPath());
            }
        });
        add(jbBrowseLogFile);

        JButton jbCreateTemplates = new JButton("Добавить в базу");
        jbCreateTemplates.setBounds(400, 75, 160, 25);
        jbCreateTemplates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // FIXME save test
                // FIXME save templates
            }
        });
        add(jbCreateTemplates);

        JButton jbUpdateTable = new JButton("Обновить");
        jbUpdateTable.setBounds(10, 140, 170, 25);
        jbUpdateTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Statement stat;
                    ResultSet rs;
                    ResultSetTableModel model;
                    stat = applicationDBconnection.createStatement();
                    rs = stat.executeQuery("SELECT template AS \"шаблон\", name AS \"имя теста\" FROM template\n" +
                                           "JOIN test\n" + "ON test.id=template.test_id");
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
            rs = stat.executeQuery("SELECT template AS \"шаблон\", name AS \"имя теста\" FROM template\n" +
                    "JOIN test\n" + "ON test.id=template.test_id");
            model = new CachingResultSetTableModel(rs);

            table = new JTable(model);

            scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            scrollPane.setBounds(10, 170, 880, 445);

            add(scrollPane);

            rs.close();
            stat.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
