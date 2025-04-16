package com.mycompany.parkingmanagementsystem;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        // Khởi tạo DataManager
        DataManager.getInstance();

        setTitle("Đăng nhập hệ thống");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("ĐĂNG NHẬP HỆ THỐNG", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Tên đăng nhập:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(15);
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loginBtn = new JButton("Đăng nhập");
        JButton exitBtn = new JButton("Thoát");
        buttonPanel.add(loginBtn);
        buttonPanel.add(exitBtn);
        mainPanel.add(buttonPanel, gbc);

        loginBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            Staff staff = DataManager.getInstance().getStaffById(username);
            if (staff != null && staff.getPassword().equals(password)) {
                dispose();
                if (staff.getRole().equals("Quản lý")) {
                    new ManagerDashboard(username, staff.getRole()).setVisible(true);
                } else {
                    new StaffDashboard(username, staff.getRole()).setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen());
    }
}