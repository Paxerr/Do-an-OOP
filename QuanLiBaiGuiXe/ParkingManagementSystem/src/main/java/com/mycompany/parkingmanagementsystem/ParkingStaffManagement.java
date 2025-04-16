package com.mycompany.parkingmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ParkingStaffManagement extends JFrame {

    private DefaultTableModel staffModel;
    private DefaultTableModel regularTicketModel;
    private DefaultTableModel monthlyTicketModel;

    public ParkingStaffManagement(String username, String role) {
        // Thiết lập frame
        setTitle("Hệ thống quản lý bãi đỗ xe");
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

        // JTabbedPane cho các tab Quản lý nhân viên, Quản lý vé thường, Quản lý vé tháng
        JTabbedPane manageTabs = new JTabbedPane();

        // Tab Quản lý nhân viên
        JPanel staffTab = new JPanel(new BorderLayout());

        // Panel chứa thông tin nhân viên
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Left panel for employee information form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(200, 25); // Kích thước đồng đều

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Mã nhân viên:"), gbc);
        gbc.gridx = 1;
        JTextField idField = new JTextField(15);
        idField.setText("admin2");
        idField.setPreferredSize(fieldSize);
        idField.setMinimumSize(fieldSize);
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Tên nhân viên:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(15);
        nameField.setPreferredSize(fieldSize);
        nameField.setMinimumSize(fieldSize);
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Ngày sinh:"), gbc);
        gbc.gridx = 1;
        JTextField dobField = new JTextField("9/22/2017", 15);
        dobField.setPreferredSize(fieldSize);
        dobField.setMinimumSize(fieldSize);
        formPanel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Giới tính:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> genderCombo = new JComboBox<>(new String[]{"Nam", "Nữ"});
        genderCombo.setPreferredSize(fieldSize);
        genderCombo.setMinimumSize(fieldSize);
        formPanel.add(genderCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Loại nhân viên:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Nhân viên thường", "Quản lý"});
        typeCombo.setSelectedItem("Nhân viên thường");
        typeCombo.setPreferredSize(fieldSize);
        typeCombo.setMinimumSize(fieldSize);
        formPanel.add(typeCombo, gbc);

        // Right panel for additional information
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5);
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        infoPanel.add(new JLabel("Địa chỉ:"), gbc2);
        gbc2.gridx = 1;
        JTextField addressField = new JTextField(15);
        addressField.setPreferredSize(fieldSize);
        addressField.setMinimumSize(fieldSize);
        infoPanel.add(addressField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        infoPanel.add(new JLabel("Số điện thoại:"), gbc2);
        gbc2.gridx = 1;
        JTextField phoneField = new JTextField(15);
        phoneField.setPreferredSize(fieldSize);
        phoneField.setMinimumSize(fieldSize);
        infoPanel.add(phoneField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        infoPanel.add(new JLabel("Mật khẩu:"), gbc2);
        gbc2.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(fieldSize);
        passwordField.setMinimumSize(fieldSize);
        infoPanel.add(passwordField, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 3;
        infoPanel.add(new JLabel("Nhập lại mật khẩu:"), gbc2);
        gbc2.gridx = 1;
        JPasswordField confirmPasswordField = new JPasswordField(15);
        confirmPasswordField.setPreferredSize(fieldSize);
        confirmPasswordField.setMinimumSize(fieldSize);
        infoPanel.add(confirmPasswordField, gbc2);

        // Add form and info panels to center panel
        centerPanel.add(formPanel);
        centerPanel.add(infoPanel);

        // Table panel
        String[] columns = {"Mã NV", "Họ và tên", "Loại nhân viên", "Ngày sinh", "Giới tính", "Địa chỉ", "Điện thoại", "Mật khẩu"};
        staffModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(staffModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));

        // Load dữ liệu nhân viên
        loadStaff();

        staffTab.add(centerPanel, BorderLayout.CENTER);
        staffTab.add(tableScroll, BorderLayout.EAST);

        // Tab Quản lý vé thường
        JPanel regularTicketTab = new JPanel(new BorderLayout());
        JPanel regularTicketPanel = new JPanel(new GridBagLayout());
        regularTicketPanel.setBorder(BorderFactory.createTitledBorder("Quản lý vé thường"));
        GridBagConstraints ticketGbc = new GridBagConstraints();
        ticketGbc.insets = new Insets(5, 5, 5, 5);
        ticketGbc.fill = GridBagConstraints.HORIZONTAL;

        ticketGbc.gridx = 0;
        ticketGbc.gridy = 0;
        regularTicketPanel.add(new JLabel("Mã vé:"), ticketGbc);
        ticketGbc.gridx = 1;
        JTextField regularTicketIdField = new JTextField(15);
        regularTicketIdField.setPreferredSize(fieldSize);
        regularTicketIdField.setMinimumSize(fieldSize);
        regularTicketPanel.add(regularTicketIdField, ticketGbc);

        ticketGbc.gridx = 0;
        ticketGbc.gridy = 1;
        regularTicketPanel.add(new JLabel("Biển số xe:"), ticketGbc);
        ticketGbc.gridx = 1;
        JTextField regularVehiclePlateField = new JTextField(15);
        regularVehiclePlateField.setPreferredSize(fieldSize);
        regularVehiclePlateField.setMinimumSize(fieldSize);
        regularTicketPanel.add(regularVehiclePlateField, ticketGbc);

        ticketGbc.gridx = 0;
        ticketGbc.gridy = 2;
        regularTicketPanel.add(new JLabel("Loại xe:"), ticketGbc);
        ticketGbc.gridx = 1;
        JComboBox<String> regularVehicleTypeCombo = new JComboBox<>(new String[]{"Xe máy", "Ô tô"});
        regularVehicleTypeCombo.setPreferredSize(fieldSize);
        regularVehicleTypeCombo.setMinimumSize(fieldSize);
        regularTicketPanel.add(regularVehicleTypeCombo, ticketGbc);

        ticketGbc.gridx = 0;
        ticketGbc.gridy = 3;
        regularTicketPanel.add(new JLabel("Phí vé (VND):"), ticketGbc);
        ticketGbc.gridx = 1;
        JTextField regularFeeField = new JTextField("5000", 15);
        regularFeeField.setPreferredSize(fieldSize);
        regularFeeField.setMinimumSize(fieldSize);
        regularTicketPanel.add(regularFeeField, ticketGbc);

        // Bảng danh sách vé thường
        String[] regularTicketColumns = {"Mã vé", "Biển số xe", "Loại xe", "Phí vé", "TG vào bến"};
        regularTicketModel = new DefaultTableModel(regularTicketColumns, 0);
        JTable regularTicketTable = new JTable(regularTicketModel);
        JScrollPane regularTicketTableScroll = new JScrollPane(regularTicketTable);

        // Load dữ liệu vé thường
        loadRegularTickets();

        regularTicketTab.add(regularTicketPanel, BorderLayout.CENTER);
        regularTicketTab.add(regularTicketTableScroll, BorderLayout.EAST);

        // Tab Quản lý vé tháng
        JPanel monthlyTicketTab = new JPanel(new BorderLayout());
        JPanel monthlyTicketPanel = new JPanel(new GridBagLayout());
        monthlyTicketPanel.setBorder(BorderFactory.createTitledBorder("Quản lý vé tháng"));
        GridBagConstraints monthlyGbc = new GridBagConstraints();
        monthlyGbc.insets = new Insets(5, 5, 5, 5);
        monthlyGbc.fill = GridBagConstraints.HORIZONTAL;

        monthlyGbc.gridx = 0;
        monthlyGbc.gridy = 0;
        monthlyTicketPanel.add(new JLabel("Mã vé tháng:"), monthlyGbc);
        monthlyGbc.gridx = 1;
        JTextField monthlyTicketIdField = new JTextField(15);
        monthlyTicketIdField.setPreferredSize(fieldSize);
        monthlyTicketIdField.setMinimumSize(fieldSize);
        monthlyTicketPanel.add(monthlyTicketIdField, monthlyGbc);

        monthlyGbc.gridx = 0;
        monthlyGbc.gridy = 1;
        monthlyTicketPanel.add(new JLabel("Biển số xe:"), monthlyGbc);
        monthlyGbc.gridx = 1;
        JTextField monthlyVehiclePlateField = new JTextField(15);
        monthlyVehiclePlateField.setPreferredSize(fieldSize);
        monthlyVehiclePlateField.setMinimumSize(fieldSize);
        monthlyTicketPanel.add(monthlyVehiclePlateField, monthlyGbc);

        monthlyGbc.gridx = 0;
        monthlyGbc.gridy = 2;
        monthlyTicketPanel.add(new JLabel("Loại xe:"), monthlyGbc);
        monthlyGbc.gridx = 1;
        JComboBox<String> monthlyVehicleTypeCombo = new JComboBox<>(new String[]{"Xe máy", "Ô tô"});
        monthlyVehicleTypeCombo.setPreferredSize(fieldSize);
        monthlyVehicleTypeCombo.setMinimumSize(fieldSize);
        monthlyTicketPanel.add(monthlyVehicleTypeCombo, monthlyGbc);

        monthlyGbc.gridx = 0;
        monthlyGbc.gridy = 3;
        monthlyTicketPanel.add(new JLabel("Ngày bắt đầu:"), monthlyGbc);
        monthlyGbc.gridx = 1;
        JTextField startDateField = new JTextField("9/1/2017", 15);
        startDateField.setPreferredSize(fieldSize);
        startDateField.setMinimumSize(fieldSize);
        monthlyTicketPanel.add(startDateField, monthlyGbc);

        monthlyGbc.gridx = 0;
        monthlyGbc.gridy = 4;
        monthlyTicketPanel.add(new JLabel("Ngày hết hạn:"), monthlyGbc);
        monthlyGbc.gridx = 1;
        JTextField endDateField = new JTextField("9/30/2017", 15);
        endDateField.setPreferredSize(fieldSize);
        endDateField.setMinimumSize(fieldSize);
        monthlyTicketPanel.add(endDateField, monthlyGbc);

        monthlyGbc.gridx = 0;
        monthlyGbc.gridy = 5;
        monthlyTicketPanel.add(new JLabel("Phí vé (VND):"), monthlyGbc);
        monthlyGbc.gridx = 1;
        JTextField monthlyFeeField = new JTextField("100000", 15);
        monthlyFeeField.setPreferredSize(fieldSize);
        monthlyFeeField.setMinimumSize(fieldSize);
        monthlyTicketPanel.add(monthlyFeeField, monthlyGbc);

        // Bảng danh sách vé tháng
        String[] monthlyTicketColumns = {"Mã vé", "Biển số xe", "Loại xe", "Ngày bắt đầu", "Ngày hết hạn", "Phí vé"};
        monthlyTicketModel = new DefaultTableModel(monthlyTicketColumns, 0);
        JTable monthlyTicketTable = new JTable(monthlyTicketModel);
        JScrollPane monthlyTicketTableScroll = new JScrollPane(monthlyTicketTable);

        // Load dữ liệu vé tháng
        loadMonthlyTickets();

        monthlyTicketTab.add(monthlyTicketPanel, BorderLayout.CENTER);
        monthlyTicketTab.add(monthlyTicketTableScroll, BorderLayout.EAST);

        // Thêm các tab vào JTabbedPane
        manageTabs.addTab("Quản lý nhân viên", staffTab);
        manageTabs.addTab("Quản lý vé thường", regularTicketTab);
        manageTabs.addTab("Quản lý vé tháng", monthlyTicketTab);

        mainPanel.add(manageTabs, BorderLayout.CENTER);

        // Buttons for form actions
        JPanel formButtonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Thêm");
        JButton deleteBtn = new JButton("Xóa");
        JButton updateBtn = new JButton("Thay đổi thông tin");
        JButton backBtn = new JButton("Quay lại");
        JButton logoutBtn = new JButton("Đăng xuất");
        formButtonPanel.add(addBtn);
        formButtonPanel.add(deleteBtn);
        formButtonPanel.add(updateBtn);
        formButtonPanel.add(backBtn);
        formButtonPanel.add(logoutBtn);

        // Kiểm tra vai trò để vô hiệu hóa nút "Thay đổi thông tin" nếu là Nhân viên thường
        if (role.equals("Nhân viên thường")) {
            updateBtn.setEnabled(false);
            addBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }

        mainPanel.add(formButtonPanel, BorderLayout.SOUTH); // Sửa lỗi "Border BMI" thành "BorderLayout.SOUTH"

        // Sự kiện cho các nút
        addBtn.addActionListener(e -> {
            int selectedTab = manageTabs.getSelectedIndex();
            if (selectedTab == 0) { // Quản lý nhân viên
                String id = idField.getText();
                String name = nameField.getText();
                String dob = dobField.getText();
                String gender = genderCombo.getSelectedItem().toString();
                String type = typeCombo.getSelectedItem().toString();
                String address = addressField.getText();
                String phone = phoneField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (id.isEmpty() || name.isEmpty() || dob.isEmpty() || address.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Staff newStaff = new Staff(id, name, type, dob, gender, address, phone, password);
                DataManager.getInstance().addStaff(newStaff);
                loadStaff();
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else if (selectedTab == 1) { // Quản lý vé thường
                String ticketId = regularTicketIdField.getText();
                String plate = regularVehiclePlateField.getText();
                String type = regularVehicleTypeCombo.getSelectedItem().toString();
                String fee = regularFeeField.getText();
                String entryTime = "9/22/2017 5:00 PM"; // Hard-coded for simplicity

                if (ticketId.isEmpty() || plate.isEmpty() || fee.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Ticket newTicket = new Ticket(ticketId, plate, type, fee, entryTime);
                DataManager.getInstance().addTicket(newTicket);
                loadRegularTickets();
                JOptionPane.showMessageDialog(this, "Thêm vé thường thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else if (selectedTab == 2) { // Quản lý vé tháng
                String ticketId = monthlyTicketIdField.getText();
                String plate = monthlyVehiclePlateField.getText();
                String type = monthlyVehicleTypeCombo.getSelectedItem().toString();
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                String fee = monthlyFeeField.getText();

                if (ticketId.isEmpty() || plate.isEmpty() || startDate.isEmpty() || endDate.isEmpty() || fee.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                MonthlyTicket newMonthlyTicket = new MonthlyTicket(ticketId, plate, type, startDate, endDate, fee);
                DataManager.getInstance().addMonthlyTicket(newMonthlyTicket);
                loadMonthlyTickets();
                JOptionPane.showMessageDialog(this, "Thêm vé tháng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedTab = manageTabs.getSelectedIndex();
            if (selectedTab == 0) { // Quản lý nhân viên
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String staffId = staffModel.getValueAt(selectedRow, 0).toString();
                    DataManager.getInstance().removeStaff(staffId);
                    loadStaff();
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedTab == 1) { // Quản lý vé thường
                int selectedRow = regularTicketTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String ticketId = regularTicketModel.getValueAt(selectedRow, 0).toString();
                    DataManager.getInstance().removeTicket(ticketId);
                    loadRegularTickets();
                    JOptionPane.showMessageDialog(this, "Xóa vé thường thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một vé để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedTab == 2) { // Quản lý vé tháng
                int selectedRow = monthlyTicketTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String ticketId = monthlyTicketModel.getValueAt(selectedRow, 0).toString();
                    DataManager.getInstance().removeMonthlyTicket(ticketId);
                    loadMonthlyTickets();
                    JOptionPane.showMessageDialog(this, "Xóa vé tháng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một vé để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateBtn.addActionListener(e -> {
            if (role.equals("Quản lý")) {
                setVisible(false); // Ẩn giao diện hiện tại
                ChangeInfoScreen changeInfoScreen = new ChangeInfoScreen(username, role);
                changeInfoScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        setVisible(true); // Hiển thị lại khi giao diện mới đóng
                    }
                });
            }
        });

        backBtn.addActionListener(e -> {
            dispose(); // Đóng giao diện hiện tại, giao diện trước đó sẽ tự động hiển thị lại nhờ WindowListener
        });

        statsTab.addActionListener(e -> {
            setVisible(false); // Ẩn giao diện hiện tại
            StatisticsScreen statsScreen = new StatisticsScreen(username, role);
            statsScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    setVisible(true); // Hiển thị lại khi giao diện mới đóng
                }
            });
        });

        manageTab.addActionListener(e -> {
            // Đã ở tab Quản lý, không cần làm gì
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginScreen().setVisible(true);
        });

        add(mainPanel);
        setVisible(true);
    }

    private void loadStaff() {
        staffModel.setRowCount(0);
        for (Staff s : DataManager.getInstance().getStaffList()) {
            staffModel.addRow(new Object[]{s.getId(), s.getName(), s.getRole(), s.getDob(), s.getGender(), s.getAddress(), s.getPhone(), "********"});
        }
    }

    private void loadRegularTickets() {
        regularTicketModel.setRowCount(0);
        for (Ticket t : DataManager.getInstance().getTickets()) {
            regularTicketModel.addRow(new Object[]{t.getId(), t.getVehiclePlate(), t.getVehicleType(), t.getFee(), t.getEntryTime()});
        }
    }

    private void loadMonthlyTickets() {
        monthlyTicketModel.setRowCount(0);
        for (MonthlyTicket mt : DataManager.getInstance().getMonthlyTickets()) {
            monthlyTicketModel.addRow(new Object[]{mt.getId(), mt.getVehiclePlate(), mt.getVehicleType(), mt.getStartDate(), mt.getEndDate(), mt.getFee()});
        }
    }
}