package com.ospu.gui.panel;

import com.ospu.metadata.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.Connection;

/**
 * This panel shows database structure.
 *
 * @author vkolodrevskiy
 */
public class DatabaseStructurePanel extends JPanel {

    public DatabaseStructurePanel(final Connection testingDBconnnection) {
        setLayout(null);
        JLabel lab1 = new JLabel("Структура исследуемой базы данных:");
        lab1.setBounds(10, 10, 300, 25);
        add(lab1);

        JLabel lDbName = new JLabel("Имя СУБД:");
        lDbName.setBounds(10, 45, 75, 25);
        add(lDbName);

        DatabaseStructure structure = PostgresMetadata.getDatabaseStructure(testingDBconnnection);
        if(structure == null) {
            JLabel lDbNameText = new JLabel("не удалось подключиться к БД");
            lDbNameText.setBounds(100, 45, 300, 25);
            add(lDbNameText);
            return;
        }

        JLabel lDbNameText = new JLabel(structure.getDatabaseName());
        lDbNameText.setBounds(100, 45, 200, 25);
        add(lDbNameText);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(structure.getDatabaseName());

        for(DatabaseSchema databaseSchema: structure.getSchemas()) {
            DefaultMutableTreeNode schema = new DefaultMutableTreeNode(databaseSchema.getName());
            for(DatabaseTable databaseTable: databaseSchema.getTables()) {
                DefaultMutableTreeNode table = new DefaultMutableTreeNode(databaseTable.getName());
                for(DatabaseColumn databaseColumn: databaseTable.getColumns()) {
                    DefaultMutableTreeNode column = new DefaultMutableTreeNode(databaseColumn.getName() +
                            " (" + databaseColumn.getType() + ")");
                    table.add(column);
                }
                schema.add(table);
            }
            root.add(schema);
        }

        JTree tree = new JTree(root);

        JScrollPane scrollPane = new JScrollPane(tree, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10, 80, 880, 535);
        add(scrollPane);
    }
}
