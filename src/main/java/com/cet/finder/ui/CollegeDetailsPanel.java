
package com.cet.finder.ui;

import com.cet.finder.model.College;
import com.cet.finder.service.CollegeService;
import javax.swing.*;
import java.awt.*;

public class CollegeDetailsPanel extends BasePanel {

    public CollegeDetailsPanel(College college) {
        JLabel heading = new JLabel("College Details");
        heading.setFont(new Font("Arial", Font.BOLD, 22));

        CollegeService collegeService = new CollegeService();

        JPanel card = new JPanel(new GridLayout(5, 2, 10, 15));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        card.add(new JLabel("College Name:"));
        card.add(new JLabel(college.getName()));

        card.add(new JLabel("City:"));
        card.add(new JLabel(college.getCity()));

        card.add(new JLabel("Engineering Branch:"));
        card.add(new JLabel(college.getBranch()));

        card.add(new JLabel("Cutoff Percentile:"));
        card.add(new JLabel(String.valueOf(college.getCutoff())));

        card.add(new JLabel("Admission Chance:"));
        card.add(new JLabel(
            collegeService.getChance(college.getCutoff())
        ));

        JPanel container = new JPanel(new BorderLayout(10, 15));
        container.add(heading, BorderLayout.NORTH);
        container.add(card, BorderLayout.CENTER);

        add(container, BorderLayout.NORTH);
    }
}
