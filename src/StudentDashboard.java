import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentDashboard extends JFrame {
    public StudentDashboard(String username) {
        setTitle("Student Dashboard - Smart Attendance");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Student Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JButton Subject1btn = new JButton("JAVA");
        JButton Subject2btn = new JButton("DBMS");
        JButton Subject3btn = new JButton("DV");
        JButton logoutBtn = new JButton("logout");

        JPanel btnPanel = new JPanel();
        btnPanel.add(Subject1btn);
        btnPanel.add(Subject2btn);
        btnPanel.add(Subject3btn);
        btnPanel.add(logoutBtn);

        JPanel btnPanel1 = new JPanel();
        btnPanel1.add(btnPanel);

        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.CENTER);

        Subject1btn.addActionListener(e -> {
            new SubjectWindow("JAVA", username).setVisible(true);
        });

        Subject2btn.addActionListener(e -> {
            new SubjectWindow("DBMS", username).setVisible(true);
        });

        Subject3btn.addActionListener(e -> {
            new SubjectWindow("DV", username).setVisible(true);
        });


        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });



    }
}

