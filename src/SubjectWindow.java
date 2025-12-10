import javax.swing.*;
import java.awt.*;

public class SubjectWindow extends JFrame {

    private String subject;
    private String studentUsername;

    public SubjectWindow(String subjectName, String username) {

        this.subject = subjectName;
        this.studentUsername = username;

        setTitle(subjectName + " - Attendance");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel(subjectName + " - Attendance");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);

        JButton scanQRBtn = new JButton("Scan QR (Record Attendance)");
        JButton viewAttendanceBtn = new JButton("View Total Attendance");

        JPanel panel = new JPanel();
        panel.add(scanQRBtn);
        panel.add(viewAttendanceBtn);

        setLayout(new BorderLayout());
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        // ✔ FIXED: Correct button click → open QR scanner window
        scanQRBtn.addActionListener(e -> {
            new ScanQRWindow(studentUsername, subject).setVisible(true);
        });

        viewAttendanceBtn.addActionListener(e -> {
            new StudentTotalAttendance(studentUsername, subject).setVisible(true);
        });


    }
}
