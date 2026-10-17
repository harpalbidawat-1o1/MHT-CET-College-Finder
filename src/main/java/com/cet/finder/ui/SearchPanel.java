
package com.cet.finder.ui;

import com.cet.finder.model.College;
import com.cet.finder.service.CollegeService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchPanel extends BasePanel {
    private final CollegeService collegeService = new CollegeService();
    private final List<College> colleges = new ArrayList<>();

    public SearchPanel() {
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

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton detailsButton = new JButton("View Details");
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(detailsButton);
        add(bottomPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            model.setRowCount(0);
            colleges.clear();

            try {
                double score = Double.parseDouble(percentile.getText().trim());

                if (score < 0 || score > 100) {
                    JOptionPane.showMessageDialog(
                        this, "Enter a percentile between 0 and 100."
                    );
                    return;
                }

                String selectedCity = city.getSelectedItem().toString();
                String selectedBranch = branch.getSelectedItem().toString();

                if (selectedCity.equals("All Cities")) {
                    selectedCity = "Pune";
                }

                if (selectedBranch.equals("All Branches")) {
                    selectedBranch = "Computer Engineering";
                }

                College college = collegeService.createSampleCollege(
                    selectedCity, selectedBranch
                );

                colleges.add(college);

                model.addRow(new Object[]{
                    college.getName(),
                    college.getBranch(),
                    college.getCutoff(),
                    collegeService.getChance(score)
                });

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                    this, "Please enter a valid percentile."
                );
            }
        });

        detailsButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                    this, "Please select a college first."
                );
                return;
            }

            int modelRow = table.convertRowIndexToModel(selectedRow);
            College selectedCollege = colleges.get(modelRow);

            JOptionPane.showMessageDialog(
                this,
                "Selected college: " + selectedCollege.getName()
                    + "\nCity: " + selectedCollege.getCity()
                    + "\nBranch: " + selectedCollege.getBranch()
                    + "\nCutoff: " + selectedCollege.getCutoff(),
                "College Details",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
    }
}
