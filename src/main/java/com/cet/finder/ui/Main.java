
package com.cet.finder.ui;

import javax.swing.*;

import com.cet.finder.model.College;

import java.awt.*;

public class Main {

    private static JFrame frame;
    private static JPanel content;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("MHT-CET College Finder");
            frame.setSize(1000, 650);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            JLabel title = new JLabel("MHT-CET COLLEGE FINDER");
            title.setFont(new Font("Arial", Font.BOLD, 24));
            title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            frame.add(title, BorderLayout.NORTH);

            JPanel menu = new JPanel(new GridLayout(5, 1, 5, 5));
            menu.setPreferredSize(new Dimension(200, 0));

            String[] options = {
                "Home",
                "Search Colleges",
                "My Preferences",
                "College Details",
                "Admin"
            };

            for (String option : options) {
                JButton button = new JButton(option);
                menu.add(button);
                button.addActionListener(e -> showScreen(option));
            }

            frame.add(menu, BorderLayout.WEST);

            content = new JPanel(new BorderLayout());
            frame.add(content, BorderLayout.CENTER);

            showScreen("Home");

            frame.setVisible(true);
        });
    }

    private static void showScreen(String screen) {
        content.removeAll();

        if (screen.equals("Search Colleges")) {
            content.add(new SearchPanel(), BorderLayout.CENTER);

        } else if (screen.equals("My Preferences")) {
            content.add(new PreferencesPanel(), BorderLayout.CENTER);

        } else if (screen.equals("College Details")) {
    College sampleCollege = new com.cet.finder.service.CollegeService()
        .createSampleCollege("Pune", "Computer Engineering");

    content.add(new CollegeDetailsPanel(sampleCollege), BorderLayout.CENTER);

        } else if (screen.equals("Admin")) {
            content.add(new AdminPanel(), BorderLayout.CENTER);

        } else {
            JLabel welcome = new JLabel(
                "Welcome! Find your best-fit engineering college.",
                SwingConstants.CENTER
            );

            welcome.setFont(new Font("Arial", Font.PLAIN, 20));
            content.add(welcome, BorderLayout.CENTER);
        }

        content.revalidate();
        content.repaint();
    }
}
