package com.nhom2.quanlibaiguixe;
import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Đăng nhập hệ thống quản lý bãi đỗ xe");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 165, 0));

        JLabel titleLabel = new JLabel("Đăng nhập", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(200, 25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Tên đăng nhập: *"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(15);
        usernameField.setPreferredSize(fieldSize);
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Mật khẩu: *"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(fieldSize);
        loginPanel.add(passwordField, gbc);

        JButton loginBtn = new JButton("Đăng nhập");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(loginBtn, gbc);

        mainPanel.add(loginPanel, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Staff staff = DataManager.getInstance().getStaffList().stream()
                .filter(s -> s.getId().equals(username) && s.getId().equals(password)).findFirst().orElse(null);

            if (staff == null) {
                JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            dispose();
            if (staff.getRole().equals("Quản lý")) {
                new ManagerDashboard(username, staff.getRole()).setVisible(true);
            } else {
                new StaffDashboard(username, staff.getRole()).setVisible(true);
            }
        });

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen());
    }
}