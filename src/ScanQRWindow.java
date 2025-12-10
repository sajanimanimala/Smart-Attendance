import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ScanQRWindow extends JFrame {

    private String studentUsername;
    private String subject;

    public ScanQRWindow(String studentUsername, String subject) {
        this.studentUsername = studentUsername;
        this.subject = subject;

        setTitle("Scan QR - " + subject);
        setSize(420, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton scanBtn = new JButton("Select QR Image to Scan");
        add(scanBtn);

        scanBtn.addActionListener(e -> handleScan());
    }

    private void handleScan() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));

            int result = chooser.showOpenDialog(this);
            if (result != JFileChooser.APPROVE_OPTION) return;

            File qrFile = chooser.getSelectedFile();

            // Decode QR Code to text
            String qrText = QRDecoder.decodeQR(qrFile.getAbsolutePath());

            if (qrText == null || qrText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Invalid QR Code!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            saveAttendanceToDB(qrText);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error while scanning QR!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveAttendanceToDB(String qrText) {
        try (Connection conn = DBConnection.getConnection()) {

            // Check for duplicate already scanned lecture
            String checkQuery =
                    "SELECT id FROM attendance WHERE studentUsername=? AND subject=? AND qr_text=?";
            PreparedStatement psCheck = conn.prepareStatement(checkQuery);
            psCheck.setString(1, studentUsername);
            psCheck.setString(2, subject);
            psCheck.setString(3, qrText);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this,
                        "Attendance already marked for this lecture!",
                        "Duplicate Entry",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Insert new attendance record
            String insertQuery =
                    "INSERT INTO attendance(studentUsername, subject, `timestamp`, qr_text) VALUES(?,?,NOW(),?)";

            PreparedStatement psInsert = conn.prepareStatement(insertQuery);
            psInsert.setString(1, studentUsername);
            psInsert.setString(2, subject);
            psInsert.setString(3, qrText);
            psInsert.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Attendance Recorded Successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            this.dispose(); // Close window after marking attendance

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Database Error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
