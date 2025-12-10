import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginFrame extends JFrame {
    public LoginFrame(){
        setTitle("Smart Attendance-Login");
        setSize(550,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");

        JPanel panel = new JPanel();
        panel.add(userLabel);
        panel.add(userField);
        System.out.println();
        panel.add(passwordLabel);
        panel.add(passField);
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(e->{
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if(user.equals("admin") && pass.equals("1234")){
                dispose();
                new AdminDashboard(user).setVisible(true);
            }
            else if(user.equals("admin1") && pass.equals("1234")){
                dispose();
                new AdminDashboard(user).setVisible(true);
            }
            else if(user.equals("admin2") && pass.equals("1234")){
                dispose();
                new AdminDashboard(user).setVisible(true);
            }
            else if(user.equals("student") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748001") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748002") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748003") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748004") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748005") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748006") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748007") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748008") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748009") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else if(user.equals("160624748010") && pass.equals("1234")){
                dispose();
                new StudentDashboard(user).setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        });
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() ->{
            new LoginFrame().setVisible(true);
        });
    }
}
