package com.mycompany.parkingmanagementsystem;

import javax.swing.*;
import java.awt.*;

public class StatisticsScreen extends JFrame {
    public StatisticsScreen(String username, String role) {
        setTitle("Thống kê");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 165, 0));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 165, 0));
        JLabel titleLabel = new JLabel("Thống kê", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(4, 2));
        statsPanel.setBackground(new Color(255, 165, 0));

        statsPanel.add(new JLabel("Tổng số xe hiện tại:"));
        JLabel totalVehiclesLabel = new JLabel(String.valueOf(DataManager.getInstance().getVehicles().size()));
        statsPanel.add(totalVehiclesLabel);

        statsPanel.add(new JLabel("Tổng số vé thường:"));
        JLabel totalTicketsLabel = new JLabel(String.valueOf(DataManager.getInstance().getTickets().size()));
        statsPanel.add(totalTicketsLabel);

        statsPanel.add(new JLabel("Tổng số vé tháng:"));
        JLabel totalMonthlyTicketsLabel = new JLabel(String.valueOf(DataManager.getInstance().getMonthlyTickets().size()));
        statsPanel.add(totalMonthlyTicketsLabel);

        statsPanel.add(new JLabel("Tổng số nhân viên:"));
        JLabel totalStaffLabel = new JLabel(String.valueOf(DataManager.getInstance().getStaffList().size()));
        statsPanel.add(totalStaffLabel);

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        JButton backBtn = new JButton("Quay lại");
        backBtn.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(backBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}