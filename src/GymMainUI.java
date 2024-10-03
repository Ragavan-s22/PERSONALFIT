import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GymMainUI extends JFrame {
    private Gym gym;
    private User user;
    private JProgressBar progressBar;

    public GymMainUI(Gym gym, User user) {
        this.gym = gym;
        this.user = user;
        setTitle("Gym Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getName());
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(welcomeLabel);

        // Daily Activity Panel
        JPanel activityPanel = new JPanel();
        activityPanel.setLayout(new GridLayout(7, 3, 10, 10));
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String day : days) {
            JLabel dayLabel = new JLabel(day);
            activityPanel.add(dayLabel);

            JComboBox<String> activityBox = new JComboBox<>(new String[]{"Select Activity", "Cardio", "Strength Training", "Rest"});
            activityPanel.add(activityBox);

            JCheckBox completedCheckBox = new JCheckBox();
            activityPanel.add(completedCheckBox);

            activityBox.addActionListener(e -> {
                String selectedActivity = (String) activityBox.getSelectedItem();
                if ("Rest".equals(selectedActivity)) {
                    completedCheckBox.setSelected(false);
                    completedCheckBox.setEnabled(false);
                } else {
                    completedCheckBox.setEnabled(true);
                }
            });

            completedCheckBox.addActionListener(e -> {
                user.setActivityCompleted(day, completedCheckBox.isSelected());
                gym.saveDataToFile();
                updateProgressBar();
            });
        }

        mainPanel.add(activityPanel);

        // Weekly Progress Panel
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new BorderLayout());

        JLabel progressLabel = new JLabel("Weekly Progress: ");
        progressPanel.add(progressLabel, BorderLayout.WEST);

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue((int) user.getWeeklyProgress());
        progressPanel.add(progressBar, BorderLayout.CENTER);

        mainPanel.add(progressPanel);

        // InBody Calculator Panel
        JPanel inBodyPanel = new JPanel();
        inBodyPanel.setLayout(new BorderLayout());

        JLabel inBodyLabel = new JLabel("InBody Calculator");
        inBodyPanel.add(inBodyLabel, BorderLayout.NORTH);

        JTextArea inBodyResults = new JTextArea(10, 30);
        inBodyPanel.add(new JScrollPane(inBodyResults), BorderLayout.CENTER);

        JButton calculateInBodyButton = new JButton("Calculate InBody");
        calculateInBodyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        inBodyPanel.add(calculateInBodyButton, BorderLayout.SOUTH);

        calculateInBodyButton.addActionListener(e -> {
            String gender = JOptionPane.showInputDialog("Enter gender (M/F):");
            if (gender != null && !gender.isEmpty()) {
                String results = InBodyCalculator.calculateInBodyResults(user.getWeight(), user.getHeight(), user.getAge(), gender);
                inBodyResults.setText(results);
            } else {
                JOptionPane.showMessageDialog(null, "Gender cannot be empty");
            }
        });

        mainPanel.add(inBodyPanel);

        // Add Change Password Button
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(changePasswordButton);

        changePasswordButton.addActionListener(e -> {
            String newPassword = JOptionPane.showInputDialog("Enter new password:");
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
                gym.saveDataToFile();
                JOptionPane.showMessageDialog(null, "Password changed successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Password cannot be empty");
            }
        });

        // Add scroll pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);

        // Update progress bar periodically
        Timer timer = new Timer(1000, e -> updateProgressBar());
        timer.start();
    }

    private void updateProgressBar() {
        int progress = (int) user.getWeeklyProgress();
        System.out.println("Updating progress bar to: " + progress);
        progressBar.setValue(progress);
    }
}

