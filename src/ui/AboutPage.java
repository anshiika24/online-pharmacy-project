//package ui;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class AboutPage extends JFrame {
//
//    public AboutPage() {
//
//        setTitle("About - Online Pharmacy System");
//        setSize(500, 400);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//        panel.setBackground(new Color(240, 245, 255));
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//
//        JLabel title = new JLabel("Online Pharmacy Management System");
//        title.setFont(new Font("Arial", Font.BOLD, 20));
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
//
//        JLabel info1 = new JLabel("Developed By: Anshika, Divya, Diya, Gayatri");
//        JLabel info2 = new JLabel("Course: Java Programming");
//        JLabel info3 = new JLabel("Project: Pharmacy Management");
//        JLabel info4 = new JLabel("Technologies: Java, Swing, MySQL");
//
//        Font f = new Font("Arial", Font.PLAIN, 16);
//        info1.setFont(f);
//        info2.setFont(f);
//        info3.setFont(f);
//        info4.setFont(f);
//
//        info1.setAlignmentX(Component.CENTER_ALIGNMENT);
//        info2.setAlignmentX(Component.CENTER_ALIGNMENT);
//        info3.setAlignmentX(Component.CENTER_ALIGNMENT);
//        info4.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        panel.add(title);
//        panel.add(info1);
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(info2);
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(info3);
//        panel.add(Box.createVerticalStrut(10));
//        panel.add(info4);
//
//        add(panel);
//
//        setVisible(true);
//    }
//}
//



package ui;

import javax.swing.*;
import java.awt.*;

public class AboutPage extends JFrame {

    public AboutPage() {

        setTitle("About - Online Pharmacy System");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // MAIN PANEL WITH GRADIENT
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Beautiful Blue Gradient
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0, 120, 215),
                        0, getHeight(), new Color(100, 180, 255)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Title
        JLabel title = new JLabel("Online Pharmacy Management System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        // Subtitle
        JLabel subtitle = new JLabel("Project Information");
        subtitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        subtitle.setForeground(Color.WHITE);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Info Labels
        Font infoFont = new Font("Segoe UI", Font.PLAIN, 16);
        Color infoColor = Color.WHITE;

        JLabel info1 = new JLabel("👩‍💻 Developed By: Anshika, Divya, Diya, Gayatri");
        JLabel info2 = new JLabel("📘 Course: Java Programming");
        JLabel info3 = new JLabel("💊 Project: Pharmacy Management System");
        JLabel info4 = new JLabel("🛠 Technologies: Java Swing, MySQL");

        JLabel[] labels = {info1, info2, info3, info4};
        for (JLabel lbl : labels) {
            lbl.setFont(infoFont);
            lbl.setForeground(infoColor);
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            lbl.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        }

        // Add components
        panel.add(title);
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(10));
        panel.add(info1);
        panel.add(info2);
        panel.add(info3);
        panel.add(info4);

        add(panel);

        setVisible(true);
    }
}
