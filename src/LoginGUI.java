import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private Gym gym;

    public LoginGUI(Gym gym) {
        this.gym = gym;
        setTitle("Gym Application - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 150, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(200, 50, 150, 25);
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 150, 25);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(200, 100, 150, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 150, 150, 25);
        panel.add(loginButton);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(200, 150, 150, 25);
        panel.add(signupButton);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            User user = gym.getUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                new GymMainUI(gym, user).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid email or password");
            }
        });

        signupButton.addActionListener(e -> {
            new SignupGUI(gym).setVisible(true);
            dispose();
        });

        add(panel);
    }

    public static void main(String[] args) {
        Gym gym = new Gym();
        SwingUtilities.invokeLater(() -> {
            new LoginGUI(gym).setVisible(true);
        });
    }
}
