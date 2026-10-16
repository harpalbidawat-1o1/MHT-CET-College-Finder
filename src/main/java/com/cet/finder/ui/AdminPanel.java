
package com.cet.finder.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminPanel extends BasePanel {

    public AdminPanel() {
        JLabel heading = new JLabel("Admin - Manage Colleges");
        heading.setFont(new Font("Arial", Font.BOLD, 22));

        String[] columns = {
            "College Name", "City", "Branch", "Cutoff"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        model.addRow(new Object[]{
            "Sample Engineering College",
            "Pune",
            "Computer Engineering",
            85.0
        });

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("Add College");
        JButton removeButton = new JButton("Remove Selected");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttons.add(addButton);
        buttons.add(removeButton);

        addButton.addActionListener(e -> {
            JTextField name = new JTextField();
            JTextField city = new JTextField();
            JTextField branch = new JTextField();
            JTextField cutoff = new JTextField();

            Object[] fields = {
                "College Name:", name,
                "City:", city,
                "Branch:", branch,
                "Cutoff:", cutoff
            };

            int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Add College",
                JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {
                try {
                    if (name.getText().trim().isEmpty()
                            || city.getText().trim().isEmpty()
                            || branch.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(
                            this, "Please fill in all fields."
                        );
                        return;
                    }

                    double value = Double.parseDouble(
                        cutoff.getText().trim()
                    );

                    if (value < 0 || value > 100) {
                        JOptionPane.showMessageDialog(
                            this, "Cutoff must be between 0 and 100."
                        );
                        return;
                    }

                    model.addRow(new Object[]{
                        name.getText().trim(),
                        city.getText().trim(),
                        branch.getText().trim(),
                        value
                    });

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        this, "Enter a valid numeric cutoff."
                    );
                }
            }
        });

        removeButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow != -1) {
                model.removeRow(table.convertRowIndexToModel(selectedRow));
            } else {
                JOptionPane.showMessageDialog(
                    this, "Please select a college to remove."
                );
            }
        });

        add(heading, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }
}
