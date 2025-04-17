package com.nhom2.quanlibaiguixe;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class ManagerDashboard extends JFrame {
    private DefaultTableModel vehicleModel;
    private DefaultTableModel historyModel;
    private DefaultTableModel ticketModel;
    private DefaultTableModel monthlyTicketModel;
    private DefaultTableModel staffModel;
    private JTextField licensePlateField;
    private JTextField colorField;
    private JComboBox<String> vehicleTypeCombo;
    private JTextField vehicleNameField;
    private JTextField entryTimeField;
    private JTextField entryDateField;

    public ManagerDashboard(String username, String role) {
        // Khởi tạo giao diện
        setTitle("Hệ thống quản lý bãi đỗ xe");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 165, 0));

        // Phần tiêu đề
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 165, 0));

        JLabel titleLabel = new JLabel("Chào " + username, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel roleLabel = new JLabel("Chức vụ: " + role, SwingConstants.CENTER);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(roleLabel, BorderLayout.CENTER);

        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabPanel.setBackground(new Color(255, 165, 0));
        JButton manageTab = new JButton("Quản lý");
        JButton statsTab = new JButton("Thống kê");
        tabPanel.add(manageTab);
        tabPanel.add(statsTab);
        topPanel.add(tabPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Khởi tạo các tab
        JTabbedPane tabs = new JTabbedPane();

        // Tab Quản lý xe
        JSplitPane vehicleTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        vehicleTab.setResizeWeight(0.5); // Tỉ lệ 50-50

        JPanel vehiclePanel = new JPanel(new GridBagLayout());
        vehiclePanel.setBorder(BorderFactory.createTitledBorder("Quản lý xe"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(200, 25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        vehiclePanel.add(new JLabel("Biển số: *"), gbc);
        gbc.gridx = 1;
        licensePlateField = new JTextField(15);
        licensePlateField.setPreferredSize(fieldSize);
        vehiclePanel.add(licensePlateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        vehiclePanel.add(new JLabel("Màu xe: *"), gbc);
        gbc.gridx = 1;
        colorField = new JTextField(15);
        colorField.setPreferredSize(fieldSize);
        vehiclePanel.add(colorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        vehiclePanel.add(new JLabel("Loại xe: *"), gbc);
        gbc.gridx = 1;
        vehicleTypeCombo = new JComboBox<>(new String[]{"Xe máy", "Ô tô"});
        vehicleTypeCombo.setPreferredSize(fieldSize);
        vehiclePanel.add(vehicleTypeCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        vehiclePanel.add(new JLabel("Giờ vào bến: *"), gbc);
        gbc.gridx = 1;
        entryTimeField = new JTextField(15);
        entryTimeField.setPreferredSize(fieldSize);
        entryTimeField.setEditable(false);
        vehiclePanel.add(entryTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        vehiclePanel.add(new JLabel("Tên xe: *"), gbc);
        gbc.gridx = 1;
        vehicleNameField = new JTextField(15);
        vehicleNameField.setPreferredSize(fieldSize);
        vehiclePanel.add(vehicleNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        vehiclePanel.add(new JLabel("Ngày vào bến: *"), gbc);
        gbc.gridx = 1;
        entryDateField = new JTextField(15);
        entryDateField.setPreferredSize(fieldSize);
        entryDateField.setEditable(false);
        vehiclePanel.add(entryDateField, gbc);

        String[] vehicleColumns = {"mã số", "biển số", "loại xe", "Tên xe", "Màu xe", "Loại vé", "TG vào bến"};
        vehicleModel = new DefaultTableModel(vehicleColumns, 0);
        JTable vehicleTable = new JTable(vehicleModel);
        JScrollPane vehicleTableScroll = new JScrollPane(vehicleTable);

        loadVehicles();

        vehicleTab.setLeftComponent(vehiclePanel);
        vehicleTab.setRightComponent(vehicleTableScroll);

        // Tab Lịch sử gửi xe
        JPanel historyTab = new JPanel(new BorderLayout());
        String[] historyColumns = {"Mã xe", "Biển số", "Loại xe", "TG vào", "TG ra", "Phí", "Loại vé"};
        historyModel = new DefaultTableModel(historyColumns, 0);
        JTable historyTable = new JTable(historyModel);
        JScrollPane historyTableScroll = new JScrollPane(historyTable);
        historyTab.add(historyTableScroll, BorderLayout.CENTER);

        loadHistory();

        // Tab Vé thường
        JPanel ticketTab = new JPanel(new BorderLayout());
        String[] ticketColumns = {"Mã vé", "Biển số", "Loại xe", "Phí", "TG vào"};
        ticketModel = new DefaultTableModel(ticketColumns, 0);
        JTable ticketTable = new JTable(ticketModel);
        JScrollPane ticketTableScroll = new JScrollPane(ticketTable);
        ticketTab.add(ticketTableScroll, BorderLayout.CENTER);

        loadTickets();

        // Tab Vé tháng
        JPanel monthlyTicketTab = new JPanel(new BorderLayout());
        String[] monthlyTicketColumns = {"Mã vé", "Biển số", "Loại xe", "Ngày bắt đầu", "Ngày kết thúc", "Phí"};
        monthlyTicketModel = new DefaultTableModel(monthlyTicketColumns, 0);
        JTable monthlyTicketTable = new JTable(monthlyTicketModel);
        JScrollPane monthlyTicketTableScroll = new JScrollPane(monthlyTicketTable);
        monthlyTicketTab.add(monthlyTicketTableScroll, BorderLayout.CENTER);

        loadMonthlyTickets();

        // Tab Nhân viên
        JPanel staffTab = new JPanel(new BorderLayout());
        String[] staffColumns = {"Mã NV", "Họ tên", "Chức vụ", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT"};
        staffModel = new DefaultTableModel(staffColumns, 0);
        JTable staffTable = new JTable(staffModel);
        JScrollPane staffTableScroll = new JScrollPane(staffTable);
        staffTab.add(staffTableScroll, BorderLayout.CENTER);

        loadStaff();

        tabs.addTab("Quản lý xe", vehicleTab);
        tabs.addTab("Lịch sử gửi xe", historyTab);
        tabs.addTab("Vé thường", ticketTab);
        tabs.addTab("Vé tháng", monthlyTicketTab);
        tabs.addTab("Nhân viên", staffTab);

        mainPanel.add(tabs, BorderLayout.CENTER);

        // Các nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Thêm");
        JButton editBtn = new JButton("Sửa");
        JButton deleteBtn = new JButton("Xóa");
        JButton searchBtn = new JButton("Tìm kiếm theo mã");
        JButton searchAllBtn = new JButton("Tìm kiếm xe");
        JButton confirmExitBtn = new JButton("Xác nhận rời bãi");
        JButton registerMonthlyBtn = new JButton("Đăng ký vé tháng");
        JButton changeInfoBtn = new JButton("Thay đổi thông tin");
        JButton logoutBtn = new JButton("Đăng xuất");
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(searchAllBtn);
        buttonPanel.add(confirmExitBtn);
        buttonPanel.add(registerMonthlyBtn);
        buttonPanel.add(changeInfoBtn);
        buttonPanel.add(logoutBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Sự kiện nút "Thêm"
        addBtn.addActionListener(e -> {
            String licensePlate = licensePlateField.getText().trim();
            String color = colorField.getText().trim();
            String type = vehicleTypeCombo.getSelectedItem().toString();
            String name = vehicleNameField.getText().trim();

            if (licensePlate.isEmpty() || color.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean isDuplicate = DataManager.getInstance().getVehicles().stream()
                .anyMatch(v -> v.getLicensePlate().equalsIgnoreCase(licensePlate));
            if (isDuplicate) {
                JOptionPane.showMessageDialog(this, "Biển số xe đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
            String entryDate = now.format(dateFormatter);
            String entryTime = now.format(timeFormatter);
            entryDateField.setText(entryDate);
            entryTimeField.setText(entryTime);

            String vehicleId = "VE" + String.format("%04d", DataManager.getInstance().getVehicles().size() + 1);
            String fullEntryTime = entryDate + " " + entryTime;

            Vehicle newVehicle = new Vehicle(vehicleId, licensePlate, type, name, color, "Vé lượt", fullEntryTime);
            DataManager.getInstance().addVehicle(newVehicle);

            Ticket newTicket = new Ticket("T" + vehicleId.substring(2), licensePlate, type, "5000", fullEntryTime);
            DataManager.getInstance().addTicket(newTicket);

            loadVehicles();
            loadTickets();
            JOptionPane.showMessageDialog(this, "Thêm xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });

        // Sự kiện nút "Sửa"
        editBtn.addActionListener(e -> {
            int selectedTab = tabs.getSelectedIndex();
            if (selectedTab == 0) { // Tab Quản lý xe
                int selectedRow = vehicleTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String vehicleId = vehicleModel.getValueAt(selectedRow, 0).toString();
                    Vehicle vehicle = DataManager.getInstance().getVehicles().stream()
                        .filter(v -> v.getId().equals(vehicleId)).findFirst().orElse(null);
                    if (vehicle != null) {
                        licensePlateField.setText(vehicle.getLicensePlate());
                        colorField.setText(vehicle.getColor());
                        vehicleTypeCombo.setSelectedItem(vehicle.getType());
                        vehicleNameField.setText(vehicle.getName());
                        String[] timeParts = vehicle.getEntryTime().split(" ");
                        if (timeParts.length >= 3) {
                            entryTimeField.setText(timeParts[1] + " " + timeParts[2]);
                            entryDateField.setText(timeParts[0]);
                        }

                        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin xe này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            String newLicensePlate = licensePlateField.getText().trim();
                            if (newLicensePlate.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Biển số không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            boolean isDuplicate = DataManager.getInstance().getVehicles().stream()
                                .anyMatch(v -> !v.getId().equals(vehicleId) && v.getLicensePlate().equalsIgnoreCase(newLicensePlate));
                            if (isDuplicate) {
                                JOptionPane.showMessageDialog(this, "Biển số xe đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            vehicle.setLicensePlate(newLicensePlate);
                            vehicle.setColor(colorField.getText().trim());
                            vehicle.setType(vehicleTypeCombo.getSelectedItem().toString());
                            vehicle.setName(vehicleNameField.getText().trim());

                            Ticket ticket = DataManager.getInstance().getTickets().stream()
                                .filter(t -> t.getId().equals("T" + vehicleId.substring(2))).findFirst().orElse(null);
                            if (ticket != null) {
                                ticket.setVehiclePlate(newLicensePlate);
                                ticket.setVehicleType(vehicleTypeCombo.getSelectedItem().toString());
                            }

                            loadVehicles();
                            loadTickets();
                            JOptionPane.showMessageDialog(this, "Sửa thông tin xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedTab == 2) { // Tab Vé thường
                int selectedRow = ticketTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String ticketId = ticketModel.getValueAt(selectedRow, 0).toString();
                    Ticket ticket = DataManager.getInstance().getTickets().stream()
                        .filter(t -> t.getId().equals(ticketId)).findFirst().orElse(null);
                    if (ticket != null) {
                        licensePlateField.setText(ticket.getVehiclePlate());
                        vehicleTypeCombo.setSelectedItem(ticket.getVehicleType());

                        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin vé này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            String newLicensePlate = licensePlateField.getText().trim();
                            if (newLicensePlate.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Biển số không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            boolean isDuplicate = DataManager.getInstance().getVehicles().stream()
                                .anyMatch(v -> !v.getLicensePlate().equals(ticket.getVehiclePlate()) && v.getLicensePlate().equalsIgnoreCase(newLicensePlate));
                            if (isDuplicate) {
                                JOptionPane.showMessageDialog(this, "Biển số xe đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            ticket.setVehiclePlate(newLicensePlate);
                            ticket.setVehicleType(vehicleTypeCombo.getSelectedItem().toString());

                            Vehicle vehicle = DataManager.getInstance().getVehicles().stream()
                                .filter(v -> v.getLicensePlate().equals(newLicensePlate)).findFirst().orElse(null);
                            if (vehicle != null) {
                                vehicle.setLicensePlate(newLicensePlate);
                                vehicle.setType(vehicleTypeCombo.getSelectedItem().toString());
                            }

                            loadTickets();
                            loadVehicles();
                            JOptionPane.showMessageDialog(this, "Sửa thông tin vé thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một vé để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedTab == 3) { // Tab Vé tháng
                int selectedRow = monthlyTicketTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String monthlyTicketId = monthlyTicketModel.getValueAt(selectedRow, 0).toString();
                    MonthlyTicket monthlyTicket = DataManager.getInstance().getMonthlyTickets().stream()
                        .filter(mt -> mt.getId().equals(monthlyTicketId)).findFirst().orElse(null);
                    if (monthlyTicket != null) {
                        licensePlateField.setText(monthlyTicket.getVehiclePlate());
                        vehicleTypeCombo.setSelectedItem(monthlyTicket.getVehicleType());

                        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa thông tin vé tháng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            String newLicensePlate = licensePlateField.getText().trim();
                            if (newLicensePlate.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Biển số không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            // Không kiểm tra trùng biển số vì vé tháng có thể thay đổi xe
                            monthlyTicket.setVehiclePlate(newLicensePlate);
                            monthlyTicket.setVehicleType(vehicleTypeCombo.getSelectedItem().toString());

                            Vehicle vehicle = DataManager.getInstance().getVehicles().stream()
                                .filter(v -> v.getLicensePlate().equals(newLicensePlate)).findFirst().orElse(null);
                            if (vehicle != null) {
                                vehicle.setLicensePlate(newLicensePlate);
                                vehicle.setType(vehicleTypeCombo.getSelectedItem().toString());
                            }

                            loadMonthlyTickets();
                            loadVehicles();
                            JOptionPane.showMessageDialog(this, "Sửa thông tin vé tháng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một vé tháng để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedTab == 4) { // Tab Nhân viên
                int selectedRow = staffTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String staffId = staffModel.getValueAt(selectedRow, 0).toString();
                    ChangeInfoScreen changeInfoScreen = new ChangeInfoScreen(staffId, role);
                    changeInfoScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            loadStaff();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Sự kiện nút "Xóa"
        deleteBtn.addActionListener(e -> {
            int selectedTab = tabs.getSelectedIndex();
            if (selectedTab == 0) {
                int selectedRow = vehicleTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String vehicleId = vehicleModel.getValueAt(selectedRow, 0).toString();
                    DataManager.getInstance().removeVehicle(vehicleId);
                    DataManager.getInstance().removeTicket("T" + vehicleId.substring(2));
                    loadVehicles();
                    loadTickets();
                    JOptionPane.showMessageDialog(this, "Xóa xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedTab == 2) {
                int selectedRow = ticketTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String ticketId = ticketModel.getValueAt(selectedRow, 0).toString();
                    DataManager.getInstance().removeTicket(ticketId);
                    loadTickets();
                    JOptionPane.showMessageDialog(this, "Xóa vé thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một vé để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedTab == 3) {
                int selectedRow = monthlyTicketTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String monthlyTicketId = monthlyTicketModel.getValueAt(selectedRow, 0).toString();
                    DataManager.getInstance().removeMonthlyTicket(monthlyTicketId);
                    loadMonthlyTickets();
                    JOptionPane.showMessageDialog(this, "Xóa vé tháng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một vé tháng để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else if (selectedTab == 4) {
                int selectedRow = staffTable.getSelectedRow();
                if (selectedRow >= 0) {
                    String staffId = staffModel.getValueAt(selectedRow, 0).toString();
                    DataManager.getInstance().removeStaff(staffId);
                    loadStaff();
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Sự kiện nút "Tìm kiếm theo mã"
        searchBtn.addActionListener(e -> {
            int selectedTab = tabs.getSelectedIndex();
            if (selectedTab == 0) {
                String searchId = JOptionPane.showInputDialog(this, "Nhập mã số xe cần tìm:");
                if (searchId != null && !searchId.trim().isEmpty()) {
                    vehicleModel.setRowCount(0);
                    for (Vehicle v : DataManager.getInstance().getVehicles()) {
                        if (v.getId().equals(searchId)) {
                            vehicleModel.addRow(new Object[]{v.getId(), v.getLicensePlate(), v.getType(), v.getName(), v.getColor(), v.getTicketType(), v.getEntryTime()});
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Không tìm thấy xe với mã số: " + searchId, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadVehicles();
                }
            } else if (selectedTab == 2) {
                String searchId = JOptionPane.showInputDialog(this, "Nhập mã vé cần tìm:");
                if (searchId != null && !searchId.trim().isEmpty()) {
                    ticketModel.setRowCount(0);
                    for (Ticket t : DataManager.getInstance().getTickets()) {
                        if (t.getId().equals(searchId)) {
                            ticketModel.addRow(new Object[]{t.getId(), t.getVehiclePlate(), t.getVehicleType(), t.getFee(), t.getEntryTime()});
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Không tìm thấy vé với mã: " + searchId, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadTickets();
                }
            } else if (selectedTab == 3) {
                String searchId = JOptionPane.showInputDialog(this, "Nhập mã vé tháng cần tìm:");
                if (searchId != null && !searchId.trim().isEmpty()) {
                    monthlyTicketModel.setRowCount(0);
                    for (MonthlyTicket mt : DataManager.getInstance().getMonthlyTickets()) {
                        if (mt.getId().equals(searchId)) {
                            monthlyTicketModel.addRow(new Object[]{mt.getId(), mt.getVehiclePlate(), mt.getVehicleType(), mt.getStartDate(), mt.getEndDate(), mt.getFee()});
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Không tìm thấy vé tháng với mã: " + searchId, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadMonthlyTickets();
                }
            } else if (selectedTab == 4) {
                String searchId = JOptionPane.showInputDialog(this, "Nhập mã nhân viên cần tìm:");
                if (searchId != null && !searchId.trim().isEmpty()) {
                    staffModel.setRowCount(0);
                    for (Staff s : DataManager.getInstance().getStaffList()) {
                        if (s.getId().equals(searchId)) {
                            staffModel.addRow(new Object[]{s.getId(), s.getName(), s.getRole(), s.getBirthDate(), s.getGender(), s.getAddress(), s.getPhone()});
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã: " + searchId, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadStaff();
                }
            }
        });

        // Sự kiện nút "Tìm kiếm xe"
        searchAllBtn.addActionListener(e -> {
            String searchPlate = JOptionPane.showInputDialog(this, "Nhập biển số xe cần tìm (để trống để hiển thị tất cả):");
            vehicleModel.setRowCount(0);
            historyModel.setRowCount(0);
            ticketModel.setRowCount(0);
            monthlyTicketModel.setRowCount(0);

            if (searchPlate == null || searchPlate.trim().isEmpty()) {
                loadVehicles();
                loadHistory();
                loadTickets();
                loadMonthlyTickets();
            } else {
                for (Vehicle v : DataManager.getInstance().getVehicles()) {
                    if (v.getLicensePlate().toLowerCase().contains(searchPlate.toLowerCase())) {
                        vehicleModel.addRow(new Object[]{v.getId(), v.getLicensePlate(), v.getType(), v.getName(), v.getColor(), v.getTicketType(), v.getEntryTime()});
                    }
                }
                for (ParkingHistory ph : DataManager.getInstance().getParkingHistory()) {
                    if (ph.getLicensePlate().toLowerCase().contains(searchPlate.toLowerCase())) {
                        historyModel.addRow(new Object[]{ph.getVehicleId(), ph.getLicensePlate(), ph.getType(), ph.getEntryTime(), ph.getExitTime(), ph.getFee(), ph.getTicketType()});
                    }
                }
                for (Ticket t : DataManager.getInstance().getTickets()) {
                    if (t.getVehiclePlate().toLowerCase().contains(searchPlate.toLowerCase())) {
                        ticketModel.addRow(new Object[]{t.getId(), t.getVehiclePlate(), t.getVehicleType(), t.getFee(), t.getEntryTime()});
                    }
                }
                for (MonthlyTicket mt : DataManager.getInstance().getMonthlyTickets()) {
                    if (mt.getVehiclePlate().toLowerCase().contains(searchPlate.toLowerCase())) {
                        monthlyTicketModel.addRow(new Object[]{mt.getId(), mt.getVehiclePlate(), mt.getVehicleType(), mt.getStartDate(), mt.getEndDate(), mt.getFee()});
                    }
                }
                if (vehicleModel.getRowCount() == 0 && historyModel.getRowCount() == 0 && ticketModel.getRowCount() == 0 && monthlyTicketModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy xe với biển số: " + searchPlate, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadVehicles();
                    loadHistory();
                    loadTickets();
                    loadMonthlyTickets();
                }
            }
        });

        // Sự kiện nút "Xác nhận rời bãi"
        confirmExitBtn.addActionListener(e -> {
            int selectedRow = vehicleTable.getSelectedRow();
            if (selectedRow >= 0) {
                String vehicleId = vehicleModel.getValueAt(selectedRow, 0).toString();
                Vehicle vehicle = DataManager.getInstance().getVehicles().stream()
                    .filter(v -> v.getId().equals(vehicleId)).findFirst().orElse(null);
                if (vehicle != null) {
                    if (vehicle.getTicketType().equals("Vé tháng")) {
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a");
                        String exitTime = now.format(formatter);

                        ParkingHistory history = new ParkingHistory(
                            vehicleId, vehicle.getLicensePlate(), vehicle.getType(),
                            vehicle.getEntryTime(), exitTime, "0", "Vé tháng"
                        );
                        DataManager.getInstance().addParkingHistory(history);

                        DataManager.getInstance().removeVehicle(vehicleId);
                        loadVehicles();
                        loadHistory();
                        JOptionPane.showMessageDialog(this, "Xe vé tháng đã rời bãi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm a");
                    String exitTime = now.format(formatter);

                    try {
                        LocalDateTime entry = LocalDateTime.parse(vehicle.getEntryTime(), formatter);
                        long hours = ChronoUnit.HOURS.between(entry, now);
                        if (hours < 1) hours = 1;
                        double fee = vehicle.getType().equals("Ô tô") ? hours * 10000 : hours * 5000;

                        ParkingHistory history = new ParkingHistory(
                            vehicleId, vehicle.getLicensePlate(), vehicle.getType(),
                            vehicle.getEntryTime(), exitTime, String.valueOf(fee), "Vé lượt"
                        );
                        DataManager.getInstance().addParkingHistory(history);

                        DataManager.getInstance().removeVehicle(vehicleId);
                        DataManager.getInstance().removeTicket("T" + vehicleId.substring(2));

                        loadVehicles();
                        loadHistory();
                        loadTickets();
                        JOptionPane.showMessageDialog(this, "Xe đã rời bãi! Phí: " + fee + " VND", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(this, "Lỗi định dạng thời gian! Vui lòng kiểm tra lại dữ liệu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Lỗi xử lý thời gian!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để xác nhận rời bãi!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Sự kiện nút "Đăng ký vé tháng"
        registerMonthlyBtn.addActionListener(e -> {
            int selectedRow = vehicleTable.getSelectedRow();
            if (selectedRow >= 0) {
                String vehicleId = vehicleModel.getValueAt(selectedRow, 0).toString();
                Vehicle vehicle = DataManager.getInstance().getVehicles().stream()
                    .filter(v -> v.getId().equals(vehicleId)).findFirst().orElse(null);
                if (vehicle != null) {
                    vehicle.setTicketType("Vé tháng");
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                    String startDate = now.format(dateFormatter);
                    String endDate = now.plusDays(30).format(dateFormatter);
                    MonthlyTicket monthlyTicket = new MonthlyTicket(
                        "MT" + vehicleId.substring(2),
                        vehicle.getLicensePlate(),
                        vehicle.getType(),
                        startDate,
                        endDate,
                        "100000"
                    );
                    DataManager.getInstance().addMonthlyTicket(monthlyTicket);
                    DataManager.getInstance().removeTicket("T" + vehicleId.substring(2));
                    loadVehicles();
                    loadMonthlyTickets();
                    JOptionPane.showMessageDialog(this, "Đã đăng ký vé tháng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một xe để đăng ký vé tháng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Sự kiện nút "Thay đổi thông tin"
        changeInfoBtn.addActionListener(e -> {
            ChangeInfoScreen changeInfoScreen = new ChangeInfoScreen(username, role);
            changeInfoScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    loadStaff();
                }
            });
        });

        // Sự kiện nút "Quản lý"
        manageTab.addActionListener(e -> {
            setVisible(false);
            ParkingStaffManagement managementScreen = new ParkingStaffManagement(username, role);
            managementScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    setVisible(true);
                }
            });
        });

        // Sự kiện nút "Thống kê"
        statsTab.addActionListener(e -> {
            setVisible(false);
            StatisticsScreen statsScreen = new StatisticsScreen(username, role);
            statsScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    setVisible(true);
                }
            });
        });

        // Sự kiện nút "Đăng xuất"
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

    private void loadHistory() {
        historyModel.setRowCount(0);
        for (ParkingHistory ph : DataManager.getInstance().getParkingHistory()) {
            historyModel.addRow(new Object[]{ph.getVehicleId(), ph.getLicensePlate(), ph.getType(), ph.getEntryTime(), ph.getExitTime(), ph.getFee(), ph.getTicketType()});
        }
    }

    private void loadTickets() {
        ticketModel.setRowCount(0);
        for (Ticket t : DataManager.getInstance().getTickets()) {
            ticketModel.addRow(new Object[]{t.getId(), t.getVehiclePlate(), t.getVehicleType(), t.getFee(), t.getEntryTime()});
        }
    }

    private void loadMonthlyTickets() {
        monthlyTicketModel.setRowCount(0);
        for (MonthlyTicket mt : DataManager.getInstance().getMonthlyTickets()) {
            monthlyTicketModel.addRow(new Object[]{mt.getId(), mt.getVehiclePlate(), mt.getVehicleType(), mt.getStartDate(), mt.getEndDate(), mt.getFee()});
        }
    }

    private void loadStaff() {
        staffModel.setRowCount(0);
        for (Staff s : DataManager.getInstance().getStaffList()) {
            staffModel.addRow(new Object[]{s.getId(), s.getName(), s.getRole(), s.getBirthDate(), s.getGender(), s.getAddress(), s.getPhone()});
        }
    }
}