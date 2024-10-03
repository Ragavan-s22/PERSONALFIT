import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupGUI extends JFrame {
    private Gym gym;

    public SignupGUI(Gym gym) {
        this.gym = gym;
        setTitle("Gym Application - Sign Up");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 150, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(200, 50, 150, 25);
        panel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 150, 25);
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(200, 100, 150, 25);
        panel.add(emailField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 150, 150, 25);
        panel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(200, 150, 150, 25);
        panel.add(ageField);

        JLabel heightLabel = new JLabel("Height (cm):");
        heightLabel.setBounds(50, 200, 150, 25);
        panel.add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setBounds(200, 200, 150, 25);
        panel.add(heightField);

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setBounds(50, 250, 150, 25);
        panel.add(weightLabel);

        JTextField weightField = new JTextField();
        weightField.setBounds(200, 250, 150, 25);
        panel.add(weightField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 300, 150, 25);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(200, 300, 150, 25);
        panel.add(passwordField);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(150, 350, 100, 25);
        panel.add(signupButton);

        signupButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            int age = Integer.parseInt(ageField.getText());
            int height = Integer.parseInt(heightField.getText());
            int weight = Integer.parseInt(weightField.getText());
            String password = new String(passwordField.getPassword());

            if (gym.getUserByEmail(email) != null) {
                JOptionPane.showMessageDialog(null, "Email already exists");
                return;
            }

            User user = new User(name, email, age, height, weight, password, "");
            gym.addUser(user);
            JOptionPane.showMessageDialog(null, "Sign Up Successful");
            new LoginGUI(gym).setVisible(true);
            dispose();
        });

        add(panel);
    }
}