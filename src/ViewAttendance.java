import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.io.*;

public class ViewAttendance extends JFrame {

    private JTable table;
    private String subject;

    public ViewAttendance(String subject) {
        this.subject = subject;

        setTitle("Attendance Report - " + subject);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JButton exportBtn = new JButton("Export to CSV");
        exportBtn.addActionListener(e -> exportToCSV());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(exportBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        String[] columns = {"Roll No", "Subject", "Attended", "Total Classes", "Percentage"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadAttendanceData(model);
    }

    private void loadAttendanceData(DefaultTableModel model) {
        try (Connection conn = DBConnection.getConnection()) {

            // Total classes conducted for the subject
            String totalQuery = "SELECT COUNT(DISTINCT qr_text) AS total FROM attendance WHERE subject=?";
            PreparedStatement psTotal = conn.prepareStatement(totalQuery);
            psTotal.setString(1, subject);
            ResultSet rsTotal = psTotal.executeQuery();
            rsTotal.next();
            int totalClasses = rsTotal.getInt("total");

            // Attendance for each student
            String attendQuery = "SELECT studentUsername, COUNT(*) AS attended FROM attendance WHERE subject=? GROUP BY studentUsername";
            PreparedStatement psAttend = conn.prepareStatement(attendQuery);
            psAttend.setString(1, subject);
            ResultSet rs = psAttend.executeQuery();

            while (rs.next()) {
                String studentUsername = rs.getString("studentUsername");
                int attended = rs.getInt("attended");
                double percentage = (totalClasses == 0) ? 0 : (attended * 100.0 / totalClasses);

                model.addRow(new Object[]{
                        studentUsername,
                        subject,
                        attended,
                        totalClasses,
                        String.format("%.2f", percentage) + "%"
                });
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportToCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Attendance Data");
        fileChooser.setSelectedFile(new File("attendance_report.csv"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection != JFileChooser.APPROVE_OPTION) return;

        File fileToSave = fileChooser.getSelectedFile();

        try (FileWriter fw = new FileWriter(fileToSave)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int colCount = model.getColumnCount();
            int rowCount = model.getRowCount();

            // Write headers
            for (int i = 0; i < colCount; i++) {
                fw.write(model.getColumnName(i));
                if (i < colCount - 1) fw.write(",");
            }
            fw.write("\n");

            // Write row data
            for (int r = 0; r < rowCount; r++) {
                for (int c = 0; c < colCount; c++) {
                    String value = model.getValueAt(r, c).toString();

// If first column (Roll No), wrap in ="rollno"
                    if (c == 0) {
                        value = "=\"" + value + "\"";  // tells Excel to treat as text
                    }

                    fw.write(value);

                    if (c < colCount - 1) fw.write(",");
                }
                fw.write("\n");
            }

            fw.flush();
            JOptionPane.showMessageDialog(this, "Export Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Export Failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

