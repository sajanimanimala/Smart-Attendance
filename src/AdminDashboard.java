import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private JLabel qrLabel;
    private String username;

    public AdminDashboard(String username) {
        this.username = username;

        setTitle("FACULTY - Smart Attendance");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JButton generateQRBtn = new JButton("Generate QR Code");
        JButton viewAttendanceBtn = new JButton("View Attendance");
        JButton logoutBtn = new JButton("Logout");

        JPanel btnPanel = new JPanel();
        btnPanel.add(generateQRBtn);
        btnPanel.add(viewAttendanceBtn);
        btnPanel.add(logoutBtn);

        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.CENTER);

        generateQRBtn.addActionListener(e -> generateAndShowQR());

        viewAttendanceBtn.addActionListener(e -> {

            // Determine subject based on admin login
            String subject = null;

            if (username.equals("admin")) subject = "JAVA";
            else if (username.equals("admin1")) subject = "DBMS";
            else if (username.equals("admin2")) subject = "DV";

            new ViewAttendance(subject).setVisible(true);
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        qrLabel = new JLabel();
        qrLabel.setHorizontalAlignment(JLabel.CENTER);
        add(qrLabel, BorderLayout.SOUTH);

        // Auto-refresh QR every 5 minutes
        Timer timer = new Timer(5 * 60_000, e -> generateAndShowQR());
        timer.start();
    }

    private void generateAndShowQR() {
        try {
            String qrText = "ATTENDANCE:" + System.currentTimeMillis();
            String filename = "currentQR.png";

            QRgenerator.generateQRCode(qrText, filename);

            ImageIcon qrIcon = new ImageIcon(filename);
            Image scaledImage = qrIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            qrLabel.setIcon(new ImageIcon(scaledImage));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

