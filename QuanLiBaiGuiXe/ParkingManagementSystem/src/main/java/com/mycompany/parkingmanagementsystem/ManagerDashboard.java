package com.mycompany.parkingmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManagerDashboard extends JFrame {

    private DefaultTableModel vehicleModel;
    private JTextField licensePlateField;
    private JTextField colorField;
    private JComboBox<String> vehicleTypeCombo;
    private JTextField vehicleNameField;
    private JTextField entryTimeField;
    private JTextField entryDateField;

    public ManagerDashboard(String username, String role) {
        // Thiết lập frame
        setTitle("Hệ thống quản lý bãi đỗ xe");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        // Giao diện Quản lý xe
        JPanel vehicleTab = new JPanel(new BorderLayout());
        JPanel vehiclePanel = new JPanel(new GridBagLayout());
        vehiclePanel.setBorder(BorderFactory.createTitledBorder("Quản lý xe"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(200, 25); // Kích thước đồng đều

        gbc.gridx = 0;
        gbc.gridy = 0;
        vehiclePanel.add(new JLabel("Biển số: *"), gbc);
        gbc.gridx = 1;
        licensePlateField = new JTextField(15);
        licensePlateField.setPreferredSize(fieldSize);
        licensePlateField.setMinimumSize(fieldSize);
        vehiclePanel.add(licensePlateField, gbc);

        gbc.gridx = 2;
        vehiclePanel.add(new JLabel("Màu xe: *"), gbc);
        gbc.gridx = 3;
        colorField = new JTextField(15);
        colorField.setPreferredSize(fieldSize);
        colorField.setMinimumSize(fieldSize);
        vehiclePanel.add(colorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        vehiclePanel.add(new JLabel("Loại xe: *"), gbc);
        gbc.gridx = 1;
        vehicleTypeCombo = new JComboBox<>(new String[]{"Xe máy", "Ô tô"});
        vehicleTypeCombo.setPreferredSize(fieldSize);
        vehicleTypeCombo.setMinimumSize(fieldSize);
        vehiclePanel.add(vehicleTypeCombo, gbc);

        gbc.gridx = 2;
        vehiclePanel.add(new JLabel("Giờ vào bến: *"), gbc);
        gbc.gridx = 3;
        entryTimeField = new JTextField(15);
        entryTimeField.setPreferredSize(fieldSize);
        entryTimeField.setMinimumSize(fieldSize);
        entryTimeField.setEditable(false);
        vehiclePanel.add(entryTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        vehiclePanel.add(new JLabel("Tên xe: *"), gbc);
        gbc.gridx = 1;
        vehicleNameField = new JTextField(15);
        vehicleNameField.setPreferredSize(fieldSize);
        vehicleNameField.setMinimumSize(fieldSize);
        vehiclePanel.add(vehicleNameField, gbc);

        gbc.gridx = 2;
        vehiclePanel.add(new JLabel("Ngày vào bến: *"), gbc);
        gbc.gridx = 3;
        entryDateField = new JTextField(15);
        entryDateField.setPreferredSize(fieldSize);
        entryDateField.setMinimumSize(fieldSize);
        entryDateField.setEditable(false);
        vehiclePanel.add(entryDateField, gbc);

        // Bảng danh sách xe
        String[] vehicleColumns = {"mã số", "biển số", "loại xe", "Tên xe", "Màu xe", "Loại vé", "TG vào bến"};
        vehicleModel = new DefaultTableModel(vehicleColumns, 0);
        JTable vehicleTable = new JTable(vehicleModel);
        JScrollPane vehicleTableScroll = new JScrollPane(vehicleTable);

        // Load dữ liệu từ DataManager
        loadVehicles();

        vehicleTab.add(vehiclePanel, BorderLayout.CENTER);
        vehicleTab.add(vehicleTableScroll, BorderLayout.EAST);

        mainPanel.add(vehicleTab, BorderLayout.CENTER);

        // Panel nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Thêm");
        JButton editBtn = new JButton("Sửa");
        JButton deleteBtn = new JButton("Xóa");
        JButton searchBtn = new JButton("Tìm kiếm theo mã");
        JButton exitBtn = new JButton("Xuất bến");
        JButton registerMonthlyBtn = new JButton("Đăng ký vé tháng");
        JButton changeInfoBtn = new JButton("Thay đổi thông tin");
        JButton logoutBtn = new JButton("Đăng xuất");
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(exitBtn);
        buttonPanel.add(registerMonthlyBtn);
        buttonPanel.add(changeInfoBtn);
        buttonPanel.add(logoutBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Sự kiện cho các nút
        addBtn.addActionListener(e -> {
            String licensePlate = licensePlateField.getText();
            String color = colorField.getText();
            String type = vehicleTypeCombo.getSelectedItem().toString();
            String name = vehicleNameField.getText();

            if (licensePlate.isEmpty() || color.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy thời gian thực tế
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm:ss a");
            String entryDate = now.format(dateFormatter);
            String entryTime = now.format(timeFormatter);
            entryDateField.setText(entryDate);
            entryTimeField.setText(entryTime);

            // Tạo mã xe mới
            String vehicleId = "VE" + (DataManager.getInstance().getVehicles().size() + 1);
            String fullEntryTime = entryDate + " " + entryTime;

            Vehicle newVehicle = new Vehicle(vehicleId, licensePlate, type, name, color, "Vé lượt", fullEntryTime);
            DataManager.getInstance().addVehicle(newVehicle);

            // Thêm vé thường tương ứng
            Ticket newTicket = new Ticket("T" + vehicleId.substring(2), licensePlate, type, "5000", fullEntryTime);
            DataManager.getInstance().addTicket(newTicket);

            loadVehicles();
            JOptionPane.showMessageDialog(this, "Thêm xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });

        editBtn.addActionListener(e -> {
            int selectedRow = vehicleTable.getSelectedRow();
            if (selectedRow >= 0) {
                String vehicleId = vehicleModel.getValueAt(selectedRow, 0).toString();
                Vehicle vehicle = DataManager.getInstance().getVehicles().stream()
                    .filter(v -> v.getId().equals(vehicleId)).findFirst().orElse(null);
                if (vehicle != null) {
                    vehicle.setLicensePlate(licensePlateField.getText());
                    vehicle.setColor(colorField.getText());
                    vehicle.setType(vehicleTypeCombo.getSelectedItem().toString());
                    vehicle.setName(vehicleNameField.getText());

                    // Cập nhật vé thường tương ứng
                    Ticket ticket = DataManager.getInstance().getTickets().stream()
                        .filter(t -> t.getId().equals("T" + vehicleId.substring(2))).findFirst().orElse(null);
                    if (ticket != null) {
                        ticket.setVehiclePlate(licensePlateField.getText());
                        ticket.setVehicleType(vehicleTypeCombo.getSelectedItem().toString());
                    }

                    loadVehicles();
                    JOptionPane.showMessageDialog(this, "Sửa thông tin xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = vehicleTable.getSelectedRow();
            if (selectedRow >= 0) {
                String vehicleId = vehicleModel.getValueAt(selectedRow, 0).toString();
                DataManager.getInstance().removeVehicle(vehicleId);
                DataManager.getInstance().removeTicket("T" + vehicleId.substring(2));
                loadVehicles();
                JOptionPane.showMessageDialog(this, "Xóa xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        searchBtn.addActionListener(e -> {
            String searchId = JOptionPane.showInputDialog(this, "Nhập mã số xe cần tìm:");
            if (searchId != null && !searchId.trim().isEmpty()) {
                vehicleModel.setRowCount(0); // Xóa bảng
                for (Vehicle v : DataManager.getInstance().getVehicles()) {
                    if (v.getId().equals(searchId)) {
                        vehicleModel.addRow(new Object[]{v.getId(), v.getLicensePlate(), v.getType(), v.getName(), v.getColor(), v.getTicketType(), v.getEntryTime()});
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Không tìm thấy xe với mã số: " + searchId, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadVehicles(); // Load lại toàn bộ danh sách nếu không tìm thấy
            }
        });

        exitBtn.addActionListener(e -> {
            int selectedRow = vehicleTable.getSelectedRow();
            if (selectedRow >= 0) {
                String vehicleId = vehicleModel.getValueAt(selectedRow, 0).toString();
                DataManager.getInstance().removeVehicle(vehicleId);
                DataManager.getInstance().removeTicket(vehicleId.replace("VE", "T")); // Xóa vé thường tương ứng
                loadVehicles();
                JOptionPane.showMessageDialog(this, "Xe đã xuất bến thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để xuất bến!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerMonthlyBtn.addActionListener(e -> {
            int selectedRow = vehicleTable.getSelectedRow();
            if (selectedRow >= 0) {
                String vehicleId = vehicleModel.getValueAt(selectedRow, 0).toString();
                Vehicle vehicle = DataManager.getInstance().getVehicles().stream()
                    .filter(v -> v.getId().equals(vehicleId)).findFirst().orElse(null);
                if (vehicle != null) {
                    vehicle.setTicketType("Vé tháng");
                    MonthlyTicket monthlyTicket = new MonthlyTicket(
                        "MT" + vehicleId.substring(2),
                        vehicle.getLicensePlate(),
                        vehicle.getType(),
                        "9/1/2017",
                        "9/30/2017",
                        "100000"
                    );
                    DataManager.getInstance().addMonthlyTicket(monthlyTicket);
                    DataManager.getInstance().removeTicket("T" + vehicleId.substring(2)); // Xóa vé thường
                    loadVehicles();
                    JOptionPane.showMessageDialog(this, "Đã đăng ký vé tháng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để đăng ký vé tháng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        changeInfoBtn.addActionListener(e -> {
            setVisible(false); // Ẩn giao diện hiện tại
            ChangeInfoScreen changeInfoScreen = new ChangeInfoScreen(username, role);
            changeInfoScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    setVisible(true); // Hiển thị lại khi giao diện mới đóng
                }
            });
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
            setVisible(false); // Ẩn giao diện hiện tại
            StatisticsScreen statsScreen = new StatisticsScreen(username, role);
            statsScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    setVisible(true); // Hiển thị lại khi giao diện mới đóng
                }
            });
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginScreen().setVisible(true);
        });

        add(mainPanel);
        setVisible(true);
    }

    private void loadVehicles() {
        vehicleModel.setRowCount(0);
        for (Vehicle v : DataManager.getInstance().getVehicles()) {
            vehicleModel.addRow(new Object[]{v.getId(), v.getLicensePlate(), v.getType(), v.getName(), v.getColor(), v.getTicketType(), v.getEntryTime()});
        }
    }
}