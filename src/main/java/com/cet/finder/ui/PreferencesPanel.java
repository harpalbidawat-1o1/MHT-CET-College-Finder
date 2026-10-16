
package com.cet.finder.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PreferencesPanel extends JPanel {

    private DefaultTableModel model;
    private JTable table;

    public PreferencesPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("My College Preferences");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        add(heading, BorderLayout.NORTH);

        String[] columns = {"Priority", "College Name", "Branch"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addRow(new Object[]{1, "Sample Engineering College", "Computer Engineering"});
        model.addRow(new Object[]{2, "Example Institute of Technology", "Information Technology"});
        model.addRow(new Object[]{3, "Demo College of Engineering", "Mechanical Engineering"});

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton upButton = new JButton("Move Up");
        JButton downButton = new JButton("Move Down");
        JButton removeButton = new JButton("Remove");

        buttons.add(upButton);
        buttons.add(downButton);
        buttons.add(removeButton);

        add(buttons, BorderLayout.SOUTH);

        upButton.addActionListener(e -> moveRow(-1));
        downButton.addActionListener(e -> moveRow(1));

        removeButton.addActionListener(e -> {
            int row = table.getSelectedRow();

            if (row != -1) {
                model.removeRow(row);
                updatePriorities();
            } else {
                JOptionPane.showMessageDialog(this, "Select a college first.");
            }
        });
    }

    private void moveRow(int direction) {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a college first.");
            return;
        }

        int newRow = row + direction;

        if (newRow < 0 || newRow >= model.getRowCount()) {
            return;
        }

        Object[] data = new Object[model.getColumnCount()];

        for (int i = 0; i < model.getColumnCount(); i++) {
            data[i] = model.getValueAt(row, i);
        }

        model.removeRow(row);
        model.insertRow(newRow, data);
        table.setRowSelectionInterval(newRow, newRow);

        updatePriorities();
    }

    private void updatePriorities() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(i + 1, i, 0);
        }
    }
}
