package com.mycompany.parkingmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManagerDashboard extends JFrame {
    private DefaultTableModel vehicleModel;
    private DefaultTableModel historyModel;
    private DefaultTableModel ticketModel;
    private DefaultTableModel monthlyCardModel;
    private DefaultTableModel sercurityGuardModel;
    private JTextField licensePlateField;
    private JComboBox<String> vehicleTypeCombo;
    private JTextField entryTimeField;
    private JTextField entryDateField;
    private JTable vehicleTable;
    private JTable monthlyCardTable;
    private JComboBox<String> ticketTypeCombo;
    private JTextField monthlyCardInputField;
    private JLabel monthlyCardInputLabel;
    private JTextField monthlyCardIdField;
    private JTextField monthlyCardLicensePlateField;
    private JComboBox<String> monthlyCardTypeCombo;
    private JTextField monthlyCardStartDateField;
    private JTextField monthlyCardEndDateField;
    private JTextField monthlyCardFeeField;
    private ArrayList<Object[]> personsList;
    private ArrayList<Object[]> vehiclesList;
    private ArrayList<Object[]> historyList;
    private ArrayList<Object[]> ticketsList;
    private ArrayList<Object[]> monthlyCardsList;
    private ArrayList<Object[]> sercurityGuardsList;

    public ManagerDashboard(String username, String role) {
        setTitle("Hệ thống quản lý bãi đỗ xe");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        vehiclesList = new ArrayList<>();
        historyList = new ArrayList<>();
        ticketsList = new ArrayList<>();
        monthlyCardsList = new ArrayList<>();
        sercurityGuardsList = new ArrayList<>();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 165, 0));

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

        JTabbedPane tabs = new JTabbedPane();

        // Tab Quản lý xe
        JSplitPane vehicleTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        vehicleTab.setResizeWeight(0.5);

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
        vehiclePanel.add(new JLabel("Loại xe: *"), gbc);
        gbc.gridx = 1;
        vehicleTypeCombo = new JComboBox<>(new String[]{"Xe máy", "Ô tô","Xe đạp"});
        vehicleTypeCombo.setPreferredSize(fieldSize);
        vehiclePanel.add(vehicleTypeCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        vehiclePanel.add(new JLabel("Loại vé: *"), gbc);
        gbc.gridx = 1;
        ticketTypeCombo = new JComboBox<>(new String[]{"Vé thường", "Vé tháng"});
        ticketTypeCombo.setPreferredSize(fieldSize);
        vehiclePanel.add(ticketTypeCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        monthlyCardInputLabel = new JLabel("Mã vé tháng: *");
        vehiclePanel.add(monthlyCardInputLabel, gbc);
        gbc.gridx = 1;
        monthlyCardInputField = new JTextField(15);
        monthlyCardInputField.setPreferredSize(fieldSize);
        vehiclePanel.add(monthlyCardInputField, gbc);
        monthlyCardInputLabel.setVisible(false);
        monthlyCardInputField.setVisible(false);

        gbc.gridx = 0;
        gbc.gridy = 4;
        vehiclePanel.add(new JLabel("Giờ vào bến: *"), gbc);
        gbc.gridx = 1;
        entryTimeField = new JTextField(15);
        entryTimeField.setPreferredSize(fieldSize);
        entryTimeField.setEditable(false);
        vehiclePanel.add(entryTimeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        vehiclePanel.add(new JLabel("Ngày vào bến: *"), gbc);
        gbc.gridx = 1;
        entryDateField = new JTextField(15);
        entryDateField.setPreferredSize(fieldSize);
        entryDateField.setEditable(false);
        vehiclePanel.add(entryDateField, gbc);

        ticketTypeCombo.addActionListener(e -> {
            boolean isMonthlyCard = ticketTypeCombo.getSelectedItem().toString().equals("Vé tháng");
            monthlyCardInputLabel.setVisible(isMonthlyCard);
            monthlyCardInputField.setVisible(isMonthlyCard);
        });

        String[] vehicleColumns = {"Mã", "Biển số", "Loại xe", "Loại vé", "TG vào bến"};
        vehicleModel = new DefaultTableModel(vehicleColumns, 0);
        vehicleTable = new JTable(vehicleModel);
        JScrollPane vehicleTableScroll = new JScrollPane(vehicleTable);

        vehicleTab.setLeftComponent(vehiclePanel);
        vehicleTab.setRightComponent(vehicleTableScroll);

        // Tab Lịch sử gửi xe
        JPanel historyTab = new JPanel(new BorderLayout());
        String[] historyColumns = {"Mã", "Biển số", "Loại xe", "TG vào", "TG ra", "Phí", "Loại vé"};
        historyModel = new DefaultTableModel(historyColumns, 0);
        JTable historyTable = new JTable(historyModel);
        JScrollPane historyTableScroll = new JScrollPane(historyTable);
        historyTab.add(historyTableScroll, BorderLayout.CENTER);

        // Tab Vé thường
        JPanel ticketTab = new JPanel(new BorderLayout());
        String[] ticketColumns = {"Mã", "Biển số", "Loại xe", "Phí", "TG vào"};
        ticketModel = new DefaultTableModel(ticketColumns, 0);
        JTable ticketTable = new JTable(ticketModel);
        JScrollPane ticketTableScroll = new JScrollPane(ticketTable);
        ticketTab.add(ticketTableScroll, BorderLayout.CENTER);

        // Tab Vé tháng
        JSplitPane monthlyCardTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        monthlyCardTab.setResizeWeight(0.5);

        JPanel monthlyCardInputPanel = new JPanel(new GridBagLayout());
        monthlyCardInputPanel.setBorder(BorderFactory.createTitledBorder("Quản lý vé tháng"));
        GridBagConstraints mtGbc = new GridBagConstraints();
        mtGbc.insets = new Insets(5, 5, 5, 5);
        mtGbc.fill = GridBagConstraints.HORIZONTAL;

        mtGbc.gridx = 0;
        mtGbc.gridy = 0;
        monthlyCardInputPanel.add(new JLabel("Mã vé tháng: *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardIdField = new JTextField(15);
        monthlyCardIdField.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(monthlyCardIdField, mtGbc);

        mtGbc.gridx = 0;
        mtGbc.gridy = 1;
        monthlyCardInputPanel.add(new JLabel("Biển số xe: *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardLicensePlateField = new JTextField(15);
        monthlyCardLicensePlateField.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(monthlyCardLicensePlateField, mtGbc);

        mtGbc.gridx = 0;
        mtGbc.gridy = 2;
        monthlyCardInputPanel.add(new JLabel("Loại xe: *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardTypeCombo = new JComboBox<>(new String[]{"Xe máy", "Ô tô","Xe đạp"});
        monthlyCardTypeCombo.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(monthlyCardTypeCombo, mtGbc);

        mtGbc.gridx = 0;
        mtGbc.gridy = 3;
        monthlyCardInputPanel.add(new JLabel("Ngày bắt đầu: *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardStartDateField = new JTextField(15);
        monthlyCardStartDateField.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(monthlyCardStartDateField, mtGbc);

        mtGbc.gridx = 0;
        mtGbc.gridy = 4;
        monthlyCardInputPanel.add(new JLabel("Ngày hết hạn: *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardEndDateField = new JTextField(15);
        monthlyCardEndDateField.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(monthlyCardEndDateField, mtGbc);

        mtGbc.gridx = 0;
        mtGbc.gridy = 5;
        monthlyCardInputPanel.add(new JLabel("Phí vé (VNĐ): *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardFeeField = new JTextField(15);
        monthlyCardFeeField.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(monthlyCardFeeField, mtGbc);

        String[] monthlyCardColumns = {"Mã vé", "Biển số xe", "Loại xe", "Ngày bắt đầu", "Ngày kết thúc", "Phí vé"};
        monthlyCardModel = new DefaultTableModel(monthlyCardColumns, 0);
        monthlyCardTable = new JTable(monthlyCardModel);
        JScrollPane monthlyCardTableScroll = new JScrollPane(monthlyCardTable);

        monthlyCardTab.setLeftComponent(monthlyCardInputPanel);
        monthlyCardTab.setRightComponent(monthlyCardTableScroll);

        // Tab Nhân viên
        JPanel sercurityGuardTab = new JPanel(new BorderLayout());
        String[] sercurityGuardColumns = {"Mã NV", "Họ tên", "Chức vụ", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT"};
        sercurityGuardModel = new DefaultTableModel(sercurityGuardColumns, 0);
        JTable sercurityGuardTable = new JTable(sercurityGuardModel);
        JScrollPane sercurityGuardTableScroll = new JScrollPane(sercurityGuardTable);
        sercurityGuardTab.add(sercurityGuardTableScroll, BorderLayout.CENTER);

        tabs.addTab("Quản lý xe", vehicleTab);
        tabs.addTab("Lịch sử gửi xe", historyTab);
        tabs.addTab("Vé thường", ticketTab);
        tabs.addTab("Quản lý vé tháng", monthlyCardTab);
        tabs.addTab("Nhân viên", sercurityGuardTab);

        mainPanel.add(tabs, BorderLayout.CENTER);

        // Các panel nút riêng cho từng tab
        JPanel vehicleButtonPanel = new JPanel(new FlowLayout());
        JButton vehicleAddBtn = new JButton("Thêm");
        JButton vehicleEditBtn = new JButton("Sửa");
        JButton vehicleDeleteBtn = new JButton("Xóa");
        JButton vehicleSearchIdBtn = new JButton("Tìm kiếm theo mã");
        JButton vehicleSearchAllBtn = new JButton("Tìm kiếm xe");
        JButton vehicleConfirmExitBtn = new JButton("Xác nhận rời bãi");
        JButton vehicleRegisterMonthlyBtn = new JButton("Đăng ký vé tháng");
        vehicleButtonPanel.add(vehicleAddBtn);
        vehicleButtonPanel.add(vehicleEditBtn);
        vehicleButtonPanel.add(vehicleDeleteBtn);
        vehicleButtonPanel.add(vehicleSearchIdBtn);
        vehicleButtonPanel.add(vehicleSearchAllBtn);
        vehicleButtonPanel.add(vehicleConfirmExitBtn);
        vehicleButtonPanel.add(vehicleRegisterMonthlyBtn);

        JPanel historyButtonPanel = new JPanel(new FlowLayout());
        JButton historySearchIdBtn = new JButton("Tìm kiếm theo mã");
        JButton historySearchAllBtn = new JButton("Tìm kiếm xe");
        historyButtonPanel.add(historySearchIdBtn);
        historyButtonPanel.add(historySearchAllBtn);

        JPanel ticketButtonPanel = new JPanel(new FlowLayout());
        JButton ticketSearchIdBtn = new JButton("Tìm kiếm theo mã");
        JButton ticketSearchAllBtn = new JButton("Tìm kiếm xe");
        ticketButtonPanel.add(ticketSearchIdBtn);
        ticketButtonPanel.add(ticketSearchAllBtn);

        JPanel monthlyCardButtonPanel = new JPanel(new FlowLayout());
        JButton monthlyCardAddBtn = new JButton("Thêm");
        JButton monthlyCardEditBtn = new JButton("Sửa");
        JButton monthlyCardDeleteBtn = new JButton("Xóa");
        monthlyCardButtonPanel.add(monthlyCardAddBtn);
        monthlyCardButtonPanel.add(monthlyCardEditBtn);
        monthlyCardButtonPanel.add(monthlyCardDeleteBtn);

        JPanel sercurityGuardButtonPanel = new JPanel(new FlowLayout());
        JButton sercurityGuardSearchIdBtn = new JButton("Tìm kiếm theo mã");
        sercurityGuardButtonPanel.add(sercurityGuardSearchIdBtn);

        JButton logoutBtn = new JButton("Đăng xuất");

        JPanel currentButtonPanel = new JPanel(new CardLayout());
        currentButtonPanel.add(vehicleButtonPanel, "Vehicle");
        currentButtonPanel.add(historyButtonPanel, "History");
        currentButtonPanel.add(ticketButtonPanel, "Ticket");
        currentButtonPanel.add(monthlyCardButtonPanel, "MonthlyCard");
        currentButtonPanel.add(sercurityGuardButtonPanel, "SercurityGuard");

        JPanel commonButtonPanel = new JPanel(new FlowLayout());
        commonButtonPanel.add(logoutBtn);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(currentButtonPanel, BorderLayout.CENTER);
        southPanel.add(commonButtonPanel, BorderLayout.SOUTH);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        CardLayout cardLayout = (CardLayout) currentButtonPanel.getLayout();
        tabs.addChangeListener(e -> {
            int selectedTab = tabs.getSelectedIndex();
            switch (selectedTab) {
                case 0:
                    cardLayout.show(currentButtonPanel, "Vehicle");
                    break;
                case 1:
                    cardLayout.show(currentButtonPanel, "History");
                    break;
                case 2:
                    cardLayout.show(currentButtonPanel, "Ticket");
                    break;
                case 3:
                    cardLayout.show(currentButtonPanel, "MonthlyCard");
                    break;
                case 4:
                    cardLayout.show(currentButtonPanel, "SercurityGuard");
                    break;
            }
        });

        // Listener cho nút Thêm (Quản lý xe)
        vehicleAddBtn.addActionListener(e -> {
            String licensePlate = licensePlateField.getText().trim();
            String type = vehicleTypeCombo.getSelectedItem().toString();
            String ticketType = ticketTypeCombo.getSelectedItem().toString();

            
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
            String entryDate = now.format(dateFormatter);
            String entryTime = now.format(timeFormatter);
            entryDateField.setText(entryDate);
            entryTimeField.setText(entryTime);

            String id = "ID" + String.format("%04d", vehiclesList.size() + 1);
            String fullEntryTime = entryDate + " " + entryTime;

            vehiclesList.add(new Object[]{id, licensePlate, type, ticketType, fullEntryTime});
            vehicleModel.setRowCount(0);
            for (Object[] vehicle : vehiclesList) {
                vehicleModel.addRow(vehicle);
            }

            JOptionPane.showMessageDialog(this, "Thêm xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            licensePlateField.setText("");
            ticketTypeCombo.setSelectedIndex(0);
            monthlyCardInputField.setText("");
        });

        // Listener cho nút Thêm (Vé tháng)
        monthlyCardAddBtn.addActionListener(e -> {
            String ticketId = monthlyCardIdField.getText().trim();
            String licensePlate = monthlyCardLicensePlateField.getText().trim();
            String type = monthlyCardTypeCombo.getSelectedItem().toString();
            String startDate = monthlyCardStartDateField.getText().trim();
            String endDate = monthlyCardEndDateField.getText().trim();
            String fee = monthlyCardFeeField.getText().trim();

            if (ticketId.isEmpty() ||startDate.isEmpty() || endDate.isEmpty() || fee.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            monthlyCardsList.add(new Object[]{ticketId, licensePlate, type, startDate, endDate, fee});
            monthlyCardModel.setRowCount(0);
            for (Object[] card : monthlyCardsList) {
                monthlyCardModel.addRow(card);
            }

            JOptionPane.showMessageDialog(this, "Thêm vé tháng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            monthlyCardIdField.setText("");
            monthlyCardLicensePlateField.setText("");
            monthlyCardTypeCombo.setSelectedIndex(0);
            monthlyCardStartDateField.setText("");
            monthlyCardEndDateField.setText("");
            monthlyCardFeeField.setText("");
        });

     

        manageTab.addActionListener(e -> {
            setVisible(false);
            ParkingSercurityGuardManagement managementScreen = new ParkingSercurityGuardManagement(username, role);
            managementScreen.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    setVisible(true);
                }
            });
        });

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

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginScreen().setVisible(true);
        });

        add(mainPanel);
        setVisible(true);
    }
}