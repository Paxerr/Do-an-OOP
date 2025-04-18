package com.mycompany.parkingmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ParkingSercurityGuardManagement extends JFrame {
    private DefaultTableModel sercurityGuardModel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField roleField;
    private JTextField birthDateField;
    private JComboBox<String> genderCombo;
    private JTextField addressField;
    private JTextField phoneField;
    private ArrayList<Object[]> sercurityGuardsList;

    public ParkingSercurityGuardManagement(String username, String role) {
        setTitle("Quản lý nhân viên");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        sercurityGuardsList = new ArrayList<>();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 165, 0));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 165, 0));
        JLabel titleLabel = new JLabel("Quản lý nhân viên", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(200, 25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Mã NV: *"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(15);
        idField.setPreferredSize(fieldSize);
        inputPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Họ tên: *"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        nameField.setPreferredSize(fieldSize);
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Chức vụ: *"), gbc);
        gbc.gridx = 1;
        roleField = new JTextField(15);
        roleField.setPreferredSize(fieldSize);
        inputPanel.add(roleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Ngày sinh: *"), gbc);
        gbc.gridx = 1;
        birthDateField = new JTextField(15);
        birthDateField.setPreferredSize(fieldSize);
        inputPanel.add(birthDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Giới tính: *"), gbc);
        gbc.gridx = 1;
        genderCombo = new JComboBox<>(new String[]{"Nam", "Nữ"});
        genderCombo.setPreferredSize(fieldSize);
        inputPanel.add(genderCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Địa chỉ: *"), gbc);
        gbc.gridx = 1;
        addressField = new JTextField(15);
        addressField.setPreferredSize(fieldSize);
        inputPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Số điện thoại: *"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(15);
        phoneField.setPreferredSize(fieldSize);
        inputPanel.add(phoneField, gbc);

        String[] sercurityGuardColumns = {"Mã NV", "Họ tên", "Chức vụ", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT"};
        sercurityGuardModel = new DefaultTableModel(sercurityGuardColumns, 0);
        JTable sercurityGuardTable = new JTable(sercurityGuardModel);
        JScrollPane sercurityGuardTableScroll = new JScrollPane(sercurityGuardTable);

        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(sercurityGuardTableScroll, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Thêm");
        JButton editBtn = new JButton("Sửa");
        JButton deleteBtn = new JButton("Xóa");
        JButton backBtn = new JButton("Quay lại");
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String roleInput = roleField.getText().trim();
            String birthDate = birthDateField.getText().trim();
            String gender = genderCombo.getSelectedItem().toString();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || roleInput.isEmpty() || birthDate.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            sercurityGuardsList.add(new Object[]{id, name, roleInput, birthDate, gender, address, phone});
            sercurityGuardModel.setRowCount(0);
            for (Object[] guard : sercurityGuardsList) {
                sercurityGuardModel.addRow(guard);
            }

            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            idField.setText("");
            nameField.setText("");
            roleField.setText("");
            birthDateField.setText("");
            genderCombo.setSelectedIndex(0);
            addressField.setText("");
            phoneField.setText("");
        });

        editBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Chức năng chưa được triển khai!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });

        deleteBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Chức năng chưa được triển khai!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });

        backBtn.addActionListener(e -> dispose());

        add(mainPanel);
        setVisible(true);
    }
}