package View;
import Controller.*;
import Model.*;

import javax.swing.*;
import java.awt.*;

public class ChangeInfoScreen extends JFrame {
    private JTextField nameField;
    private JTextField birthDateField;
    private JComboBox<String> genderCombo;
    private JTextField addressField;
    private JTextField phoneField;

    public ChangeInfoScreen(String username, String role) {
        setTitle("Thay đổi thông tin");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(200, 25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Họ tên: *"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        nameField.setPreferredSize(fieldSize);
        nameField.setMinimumSize(fieldSize);
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Ngày sinh: *"), gbc);
        gbc.gridx = 1;
        birthDateField = new JTextField(15);
        birthDateField.setPreferredSize(fieldSize);
        birthDateField.setMinimumSize(fieldSize);
        panel.add(birthDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Giới tính: *"), gbc);
        gbc.gridx = 1;
        genderCombo = new JComboBox<>(new String[]{"Nam", "Nữ"});
        genderCombo.setPreferredSize(fieldSize);
        genderCombo.setMinimumSize(fieldSize);
        panel.add(genderCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Địa chỉ: *"), gbc);
        gbc.gridx = 1;
        addressField = new JTextField(15);
        addressField.setPreferredSize(fieldSize);
        addressField.setMinimumSize(fieldSize);
        panel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Số điện thoại: *"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(15);
        phoneField.setPreferredSize(fieldSize);
        phoneField.setMinimumSize(fieldSize);
        panel.add(phoneField, gbc);

        JButton saveBtn = new JButton("Lưu");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(saveBtn, gbc);

        saveBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String birthDate = birthDateField.getText().trim();
            String gender = genderCombo.getSelectedItem().toString();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || birthDate.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Cập nhật thông tin được nhấn!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        add(panel);
        setVisible(true);
    }
}