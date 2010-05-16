package com.ospu.gui.panel;

import javax.swing.*;

/**
 * Edit database parameters panel.
 *
 * @author vkolodrevskiy
 */
public class EditDatabaseParametersPanel extends JPanel {
    public EditDatabaseParametersPanel() {
        setLayout(null);
        JLabel lab1 = new JLabel("Редактировать параметры БД: ");
        lab1.setBounds(10, 10, 250, 25);
        add(lab1);
    }
}
