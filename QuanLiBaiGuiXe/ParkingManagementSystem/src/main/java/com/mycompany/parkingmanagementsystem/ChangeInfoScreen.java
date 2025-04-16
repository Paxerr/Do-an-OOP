package com.mycompany.parkingmanagementsystem;

import javax.swing.*;
import java.awt.*;

public class ChangeInfoScreen extends JFrame {

    public ChangeInfoScreen(String username, String role) {
        setTitle("Cập nhật thông tin");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Lấy thông tin nhân viên hiện tại
        Staff currentStaff = DataManager.getInstance().getStaffById(username);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(200, 25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Mã nhân viên:"), gbc);
        gbc.gridx = 1;
        JTextField idField = new JTextField(currentStaff.getId(), 15);
        idField.setPreferredSize(fieldSize);
        idField.setMinimumSize(fieldSize);
        idField.setEditable(false);
        mainPanel.add(idField, gbc);

        gbc.gridx = 2;
        mainPanel.add(new JLabel("Địa chỉ:"), gbc);
        gbc.gridx = 3;
        JTextField addressField = new JTextField(currentStaff.getAddress(), 15);
        addressField.setPreferredSize(fieldSize);
        addressField.setMinimumSize(fieldSize);
        mainPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Tên nhân viên:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(currentStaff.getName(), 15);
        nameField.setPreferredSize(fieldSize);
        nameField.setMinimumSize(fieldSize);
        mainPanel.add(nameField, gbc);

        gbc.gridx = 2;
        mainPanel.add(new JLabel("Số điện thoại:"), gbc);
        gbc.gridx = 3;
        JTextField phoneField = new JTextField(currentStaff.getPhone(), 15);
        phoneField.setPreferredSize(fieldSize);
        phoneField.setMinimumSize(fieldSize);
        mainPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Ngày sinh:"), gbc);
        gbc.gridx = 1;
        JTextField dobField = new JTextField(currentStaff.getDob(), 15);
        dobField.setPreferredSize(fieldSize);
        dobField.setMinimumSize(fieldSize);
        mainPanel.add(dobField, gbc);

        gbc.gridx = 2;
        mainPanel.add(new JLabel("Mật khẩu:"), gbc);
        gbc.gridx = 3;
        JPasswordField passwordField = new JPasswordField(currentStaff.getPassword(), 15);
        passwordField.setPreferredSize(fieldSize);
        passwordField.setMinimumSize(fieldSize);
        mainPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(new JLabel("Giới tính:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Nam", "Nữ"});
        genderCombo.setSelectedItem(currentStaff.getGender());
        genderCombo.setPreferredSize(fieldSize);
        genderCombo.setMinimumSize(fieldSize);
        mainPanel.add(genderCombo, gbc);

        gbc.gridx = 2;
        mainPanel.add(new JLabel("Nhập lại mật khẩu:"), gbc);
        gbc.gridx = 3;
        JPasswordField confirmPasswordField = new JPasswordField(currentStaff.getPassword(), 15);
        confirmPasswordField.setPreferredSize(fieldSize);
        confirmPasswordField.setMinimumSize(fieldSize);
        mainPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(new JLabel("Loại nhân viên:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> roleCombo = new JComboBox<>(new String[]{"Nhân viên thường", "Quản lý"});
        roleCombo.setSelectedItem(currentStaff.getRole());
        roleCombo.setPreferredSize(fieldSize);
        roleCombo.setMinimumSize(fieldSize);
        if (!role.equals("Quản lý")) {
            roleCombo.setEnabled(false); // Chỉ Quản lý mới được thay đổi loại nhân viên
        }
        mainPanel.add(roleCombo, gbc);

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton updateBtn = new JButton("Sửa");
        JButton cancelBtn = new JButton("Quay lại");
        buttonPanel.add(updateBtn);
        buttonPanel.add(cancelBtn);

        // Sự kiện cho nút Sửa
        updateBtn.addActionListener(e -> {
            String id = idField.getText();
            String name = nameField.getText();
            String dob = dobField.getText();
            String gender = genderCombo.getSelectedItem().toString();
            String type = roleCombo.getSelectedItem().toString();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (name.isEmpty() || dob.isEmpty() || address.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật thông tin nhân viên
            Staff updatedStaff = new Staff(id, name, type, dob, gender, address, phone, password);
            DataManager.getInstance().updateStaff(updatedStaff);
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        // Sự kiện cho nút Quay lại
        cancelBtn.addActionListener(e -> {
            dispose(); // Đóng giao diện hiện tại, giao diện trước đó sẽ tự động hiển thị lại nhờ WindowListener
        });

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}