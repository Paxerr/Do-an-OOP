package com.mycompany.parkingmanagementsystem;

import javax.swing.*;
import java.awt.*;

public class StatisticsScreen extends JFrame {

    public StatisticsScreen(String username, String role) {
        setTitle("Thống kê");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 165, 0)); // Màu nền cam

        // Panel tiêu đề và tab
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 165, 0));

        JLabel titleLabel = new JLabel("Chào " + username, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel roleLabel = new JLabel("Chức vụ: " + role, SwingConstants.CENTER);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(roleLabel, BorderLayout.CENTER);

        // Tab buttons
        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabPanel.setBackground(new Color(255, 165, 0));
        JButton manageTab = new JButton("Quản lý");
        JButton statsTab = new JButton("Thống kê");
        tabPanel.add(manageTab);
        tabPanel.add(statsTab);
        topPanel.add(tabPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel thống kê
        JPanel statsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Lấy dữ liệu từ DataManager
        int vehicleCount = DataManager.getInstance().getVehicles().size();
        int regularTicketCount = DataManager.getInstance().getTickets().size();
        int monthlyTicketCount = DataManager.getInstance().getMonthlyTickets().size();
        int staffCount = DataManager.getInstance().getStaffList().size();

        gbc.gridx = 0;
        gbc.gridy = 0;
        statsPanel.add(new JLabel("Tổng số xe hiện tại:"), gbc);
        gbc.gridx = 1;
        statsPanel.add(new JLabel(String.valueOf(vehicleCount)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        statsPanel.add(new JLabel("Tổng số vé thường:"), gbc);
        gbc.gridx = 1;
        statsPanel.add(new JLabel(String.valueOf(regularTicketCount)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        statsPanel.add(new JLabel("Tổng số vé tháng:"), gbc);
        gbc.gridx = 1;
        statsPanel.add(new JLabel(String.valueOf(monthlyTicketCount)), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        statsPanel.add(new JLabel("Tổng số nhân viên:"), gbc);
        gbc.gridx = 1;
        statsPanel.add(new JLabel(String.valueOf(staffCount)), gbc);

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton backBtn = new JButton("Quay lại");
        JButton logoutBtn = new JButton("Đăng xuất");
        buttonPanel.add(backBtn);
        buttonPanel.add(logoutBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Sự kiện cho các nút
        backBtn.addActionListener(e -> {
            dispose(); // Đóng giao diện hiện tại, giao diện trước đó sẽ tự động hiển thị lại nhờ WindowListener
        });

        manageTab.addActionListener(e -> {
            setVisible(false); // Ẩn giao diện hiện tại
            ParkingStaffManagement managementScreen = new ParkingStaffManagement(username, role);
            managementScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    setVisible(true); // Hiển thị lại khi giao diện mới đóng
                }
            });
        });

        statsTab.addActionListener(e -> {
            // Đã ở tab Thống kê, không cần làm gì
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginScreen().setVisible(true);
        });

        add(mainPanel);
        setVisible(true);
    }
}