import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentTotalAttendance extends JFrame {

    private String studentUsername;
    private String subject;

    public StudentTotalAttendance(String studentUsername, String subject) {
        this.studentUsername = studentUsername;
        this.subject = subject;

        setTitle("Attendance - " + subject);
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel rollLabel = new JLabel("Roll No : " + studentUsername, SwingConstants.CENTER);
        JLabel attendedLabel = new JLabel("", SwingConstants.CENTER);
        JLabel totalLabel = new JLabel("", SwingConstants.CENTER);
        JLabel percentLabel = new JLabel("", SwingConstants.CENTER);

        add(rollLabel);
        add(attendedLabel);
        add(totalLabel);
        add(percentLabel);

        loadStudentAttendance(attendedLabel, totalLabel, percentLabel);
    }

    private void loadStudentAttendance(JLabel attendedLabel, JLabel totalLabel, JLabel percentLabel) {
        try (Connection conn = DBConnection.getConnection()) {

            // Total classes conducted for subject
            String totalQuery = "SELECT COUNT(DISTINCT qr_text) AS total FROM attendance WHERE subject=?";
            PreparedStatement psTotal = conn.prepareStatement(totalQuery);
            psTotal.setString(1, subject);
            ResultSet rsTotal = psTotal.executeQuery();
            rsTotal.next();
            int totalClasses = rsTotal.getInt("total");

            // Attended by student
            String attendQuery = "SELECT COUNT(*) AS attended FROM attendance WHERE subject=? AND studentUsername=?";
            PreparedStatement psAttend = conn.prepareStatement(attendQuery);
            psAttend.setString(1, subject);
            psAttend.setString(2, studentUsername);
            ResultSet rsAttend = psAttend.executeQuery();
            rsAttend.next();
            int attended = rsAttend.getInt("attended");

            double percent = (totalClasses == 0) ? 0 : (attended * 100.0 / totalClasses);

            attendedLabel.setText("Attended Classes : " + attended);
            totalLabel.setText("Total Classes : " + totalClasses);
            percentLabel.setText("Attendance : " + String.format("%.2f", percent) + "%");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
