package com.nhom2.quanlibaiguixe;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ParkingStaffManagement extends JFrame {
    private DefaultTableModel staffModel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField roleField;
    private JTextField birthDateField;
    private JComboBox<String> genderCombo;
    private JTextField addressField;
    private JTextField phoneField;

    public ParkingStaffManagement(String username, String role) {
        setTitle("Quản lý nhân viên");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

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

        String[] staffColumns = {"Mã NV", "Họ tên", "Chức vụ", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT"};
        staffModel = new DefaultTableModel(staffColumns, 0);
        JTable staffTable = new JTable(staffModel);
        JScrollPane staffTableScroll = new JScrollPane(staffTable);

        loadStaff();

        mainPanel.add(inputPanel, BorderLayout.WEST);
        mainPanel.add(staffTableScroll, BorderLayout.CENTER);

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

            boolean isDuplicate = DataManager.getInstance().getStaffList().stream()
                .anyMatch(s -> s.getId().equalsIgnoreCase(id));
            if (isDuplicate) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Staff newStaff = new Staff(id, name, roleInput, birthDate, gender, address, phone);
            DataManager.getInstance().addStaff(newStaff);
            loadStaff();
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });

        editBtn.addActionListener(e -> {
            int selectedRow = staffTable.getSelectedRow();
            if (selectedRow >= 0) {
                String staffId = staffModel.getValueAt(selectedRow, 0).toString();
                Staff staff = DataManager.getInstance().getStaffList().stream()
                    .filter(s -> s.getId().equals(staffId)).findFirst().orElse(null);
                if (staff != null) {
                    idField.setText(staff.getId());
                    nameField.setText(staff.getName());
                    roleField.setText(staff.getRole());
                    birthDateField.setText(staff.getBirthDate());
                    genderCombo.setSelectedItem(staff.getGender());
                    addressField.setText(staff.getAddress());
                    phoneField.setText(staff.getPhone());

                    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        String newId = idField.getText().trim();
                        boolean isDuplicate = DataManager.getInstance().getStaffList().stream()
                            .anyMatch(s -> !s.getId().equals(staffId) && s.getId().equalsIgnoreCase(newId));
                        if (isDuplicate) {
                            JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        staff.setId(newId);
                        staff.setName(nameField.getText().trim());
                        staff.setRole(roleField.getText().trim());
                        staff.setBirthDate(birthDateField.getText().trim());
                        staff.setGender(genderCombo.getSelectedItem().toString());
                        staff.setAddress(addressField.getText().trim());
                        staff.setPhone(phoneField.getText().trim());

                        loadStaff();
                        JOptionPane.showMessageDialog(this, "Sửa thông tin nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = staffTable.getSelectedRow();
            if (selectedRow >= 0) {
                String staffId = staffModel.getValueAt(selectedRow, 0).toString();
                DataManager.getInstance().removeStaff(staffId);
                loadStaff();
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.addActionListener(e -> dispose());

        add(mainPanel);
        setVisible(true);
    }

    private void loadStaff() {
        staffModel.setRowCount(0);
        for (Staff s : DataManager.getInstance().getStaffList()) {
            staffModel.addRow(new Object[]{s.getId(), s.getName(), s.getRole(), s.getBirthDate(), s.getGender(), s.getAddress(), s.getPhone()});
        }
    }
}