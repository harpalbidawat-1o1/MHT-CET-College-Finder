
package com.cet.finder.ui;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("MHT-CET College Finder");
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
                "Home", "Search Colleges", "My Preferences",
                "College Details", "Admin"
            };

            for (String option : options) {
                JButton button = new JButton(option);
                menu.add(button);
            }

            frame.add(menu, BorderLayout.WEST);

            JPanel content = new JPanel(new BorderLayout());
            JLabel welcome = new JLabel(
                "Welcome! Find your best-fit engineering college.",
                SwingConstants.CENTER
            );
            welcome.setFont(new Font("Arial", Font.PLAIN, 20));
            content.add(welcome, BorderLayout.CENTER);

            frame.add(content, BorderLayout.CENTER);

            for (Component component : menu.getComponents()) {
                if (component instanceof JButton button) {
                    button.addActionListener(e -> {
                        frame.remove(content);

                        for (Component c : frame.getContentPane().getComponents()) {
                            if (c != title && c != menu) {
                                frame.remove(c);
                            }
                        }

                        if (button.getText().equals("Search Colleges")) {
                            frame.add(new SearchPanel(), BorderLayout.CENTER);
                        } else if (button.getText().equals("My Preferences")) {
                            frame.add(new PreferencesPanel(), BorderLayout.CENTER);
                        } else {
                            frame.add(content, BorderLayout.CENTER);
                        }

                        frame.revalidate();
                        frame.repaint();
                    });
                }
            }

            frame.setVisible(true);
        });
    }
}
