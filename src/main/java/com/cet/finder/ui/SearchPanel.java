
package com.cet.finder.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchPanel extends JPanel {

    public SearchPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("Search Engineering Colleges");
        heading.setFont(new Font("Arial", Font.BOLD, 22));

        JPanel filters = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField percentile = new JTextField();

        JComboBox<String> category = new JComboBox<>(
            new String[]{"OPEN", "OBC", "SC", "ST", "EWS", "VJ/DT", "NT"}
        );

        JComboBox<String> city = new JComboBox<>(
            new String[]{"All Cities", "Mumbai", "Pune", "Nagpur", "Nashik"}
        );

        JComboBox<String> branch = new JComboBox<>(
            new String[]{"All Branches", "Computer Engineering",
                "Information Technology", "Mechanical Engineering",
                "Electrical Engineering", "Civil Engineering"}
        );

        filters.add(new JLabel("MHT-CET Percentile:"));
        filters.add(percentile);
        filters.add(new JLabel("Category:"));
        filters.add(category);
        filters.add(new JLabel("City:"));
        filters.add(city);
        filters.add(new JLabel("Engineering Branch:"));
        filters.add(branch);

        JButton searchButton = new JButton("Search Colleges");
        filters.add(new JLabel(""));
        filters.add(searchButton);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(heading, BorderLayout.NORTH);
        topPanel.add(filters, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"College", "Branch", "Cutoff", "Chance"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        searchButton.addActionListener(e -> {
            model.setRowCount(0);

            try {
                double score = Double.parseDouble(percentile.getText().trim());

                if (score < 0 || score > 100) {
                    JOptionPane.showMessageDialog(
                        this, "Enter a percentile between 0 and 100."
                    );
                    return;
                }

                String chance = score >= 87 ? "Safe"
                    : score >= 83 ? "Target" : "Dream";

                model.addRow(new Object[]{
                    "Sample Engineering College",
                    branch.getSelectedItem(),
                    85.0,
                    chance
                });

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this, "Please enter a valid percentile."
                );
            }
        });
    }
}
