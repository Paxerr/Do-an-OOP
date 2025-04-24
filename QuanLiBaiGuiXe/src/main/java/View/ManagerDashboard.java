package View;

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
    private DefaultTableModel LoginlogoutModel;
    private JTextField licensePlateField;
    private JComboBox<String> vehicleTypeCombo;
    private JTextField ticketTypeField;
    private JTextField entryTimeField;
    private JTextField entryDateField;
    private JTable vehicleTable;
    private JTable monthlyCardTable;
    private JComboBox<String> ticketTypeCombo;
    private JTextField monthlyCardInputField;
    private JLabel vehiclePlateInput;
    private JLabel vehiclePlateInputLabel;
    private JTextField vehiclePlateInputField;
    private JLabel monthlyCardInputLabel;
    private JTextField Card_IDField;
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
    private ArrayList<Object[]> LoginlogoutsList;
    
    public class CustomOptionPane {
    public static void showMessage(String message, String title, String buttonText) {
        JOptionPane.showOptionDialog(
            null,
            message,
            title,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[]{buttonText}, // Nút tùy chỉnh
            buttonText
        );
    }
}

    public ManagerDashboard(String username, String role) {
        setTitle("Hệ thống quản lý bãi đỗ xe");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        vehiclesList = new ArrayList<>();
        historyList = new ArrayList<>();
        ticketsList = new ArrayList<>();
        monthlyCardsList = new ArrayList<>();
        LoginlogoutsList = new ArrayList<>();

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
        vehiclePlateInputLabel = new JLabel("biển số: *");
        vehiclePanel.add(vehiclePlateInputLabel, gbc);
        gbc.gridx = 1;
        vehiclePlateInputField = new JTextField(15);
        vehiclePlateInputField.setPreferredSize(fieldSize);
        vehiclePanel.add(vehiclePlateInputField, gbc);
        vehiclePlateInputLabel.setVisible(true);
        vehiclePlateInputField.setVisible(true);

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
        
        ticketTypeField = new JTextField(15);
        ticketTypeField.setPreferredSize(fieldSize);
        ticketTypeField.setEditable(false);
        vehiclePanel.add(ticketTypeField, gbc);

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

        vehicleTypeCombo.addActionListener(e -> {
            boolean isvehicle = vehicleTypeCombo.getSelectedItem().toString().equals("Xe đạp");
            vehiclePlateInputLabel.setVisible(!isvehicle);
            vehiclePlateInputField.setVisible(!isvehicle);
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
        Card_IDField = new JTextField(15);
        Card_IDField.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(Card_IDField, mtGbc);

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
        monthlyCardStartDateField.setEditable(false);
        monthlyCardInputPanel.add(monthlyCardStartDateField, mtGbc);

        mtGbc.gridx = 0;
        mtGbc.gridy = 4;
        monthlyCardInputPanel.add(new JLabel("Ngày hết hạn: *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardEndDateField = new JTextField(15);
        monthlyCardEndDateField.setPreferredSize(fieldSize);
        monthlyCardEndDateField.setEditable(false);
        monthlyCardInputPanel.add(monthlyCardEndDateField, mtGbc);

        mtGbc.gridx = 0;
        mtGbc.gridy = 5;
        monthlyCardInputPanel.add(new JLabel("Phí vé (VNĐ): *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardFeeField = new JTextField(15);
        monthlyCardFeeField.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(monthlyCardFeeField, mtGbc);
        
        vehicleTypeCombo.addActionListener(e -> {
            boolean isMonthlyCard = vehicleTypeCombo.getSelectedItem().toString().equals("Vé tháng");
            monthlyCardInputLabel.setVisible(isMonthlyCard);
            monthlyCardInputField.setVisible(isMonthlyCard);
        });

        String[] monthlyCardColumns = {"Mã vé", "Biển số xe", "Loại xe", "Ngày bắt đầu", "Ngày kết thúc", "Phí vé"};
        monthlyCardModel = new DefaultTableModel(monthlyCardColumns, 0);
        monthlyCardTable = new JTable(monthlyCardModel);
        JScrollPane monthlyCardTableScroll = new JScrollPane(monthlyCardTable);

        monthlyCardTab.setLeftComponent(monthlyCardInputPanel);
        monthlyCardTab.setRightComponent(monthlyCardTableScroll);

        // Tab Nhân viên
        JPanel LoginlogoutTab = new JPanel(new BorderLayout());
        String[] LoginlogoutColumns = {"Mã NV", "Họ tên", "Chức vụ", "Login", "Logout"};
        LoginlogoutModel = new DefaultTableModel(LoginlogoutColumns, 0);
        JTable LoginlogoutTable = new JTable(LoginlogoutModel);
        JScrollPane LoginlogoutTableScroll = new JScrollPane(LoginlogoutTable);
        LoginlogoutTab.add(LoginlogoutTableScroll, BorderLayout.CENTER);

        tabs.addTab("Quản lý xe", vehicleTab);
        tabs.addTab("Lịch sử gửi xe", historyTab);
        
        tabs.addTab("Quản lý vé tháng", monthlyCardTab);
        tabs.addTab("Lịch sử đăng nhập", LoginlogoutTab);

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
        JButton vehiclePrintBtn = new JButton("In Vé");
        vehicleButtonPanel.add(vehicleAddBtn);
        vehicleButtonPanel.add(vehicleEditBtn);
        vehicleButtonPanel.add(vehicleDeleteBtn);
        vehicleButtonPanel.add(vehicleSearchIdBtn);
        vehicleButtonPanel.add(vehicleSearchAllBtn);
        vehicleButtonPanel.add(vehicleConfirmExitBtn);
        vehicleButtonPanel.add(vehicleRegisterMonthlyBtn);
        vehicleButtonPanel.add(vehiclePrintBtn);

        JPanel historyButtonPanel = new JPanel(new FlowLayout());
        JButton historySearchIdBtn = new JButton("Tìm kiếm theo mã");
        JButton historySearchAllBtn = new JButton("Tìm kiếm xe");
        historyButtonPanel.add(historySearchIdBtn);
        historyButtonPanel.add(historySearchAllBtn);

        

        JPanel monthlyCardButtonPanel = new JPanel(new FlowLayout());
        JButton monthlyCardSearchIdBtn = new JButton("Tìm kiếm theo mã");
        JButton monthlyCardSearchAllBtn = new JButton("Tìm kiếm xe");
        
        JButton monthlyCardAddBtn = new JButton("Thêm");
        JButton monthlyCardEditBtn = new JButton("Sửa");
        JButton monthlyCardDeleteBtn = new JButton("Xóa");
        JButton monthlyCardGiaHanBtn = new JButton("Gia Hạn");
        monthlyCardButtonPanel.add(monthlyCardAddBtn);
        monthlyCardButtonPanel.add(monthlyCardEditBtn);
        monthlyCardButtonPanel.add(monthlyCardDeleteBtn);
        monthlyCardButtonPanel.add(monthlyCardGiaHanBtn);
        monthlyCardButtonPanel.add(monthlyCardSearchIdBtn);
        monthlyCardButtonPanel.add(monthlyCardSearchAllBtn);

        JPanel LoginlogoutButtonPanel = new JPanel(new FlowLayout());
        JButton LoginlogoutSearchIdBtn = new JButton("Tìm kiếm theo mã");
        LoginlogoutButtonPanel.add(LoginlogoutSearchIdBtn);

        JButton logoutBtn = new JButton("Đăng xuất");

        JPanel currentButtonPanel = new JPanel(new CardLayout());
        currentButtonPanel.add(vehicleButtonPanel, "Vehicle");
        currentButtonPanel.add(historyButtonPanel, "History");
        
        currentButtonPanel.add(monthlyCardButtonPanel, "MonthlyCard");
        currentButtonPanel.add(LoginlogoutButtonPanel, "Loginlogout");

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
                    cardLayout.show(currentButtonPanel, "MonthlyCard");
                    break;
                case 3:
                    cardLayout.show(currentButtonPanel, "Loginlogout");
                    break;
            }
        });

        // Listener (Quản lý xe)
        vehicleAddBtn.addActionListener(e -> {
            String Liscense_number = vehiclePlateInputField.getText().trim();
            String Vehicle_Type = vehicleTypeCombo.getSelectedItem().toString();
            String ticketType =" ";

            
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
            
            String entryDate = now.format(dateFormatter);
            String entryTime = now.format(timeFormatter);
            
            entryDateField.setText(entryDate);
            entryTimeField.setText(entryTime);

            String Ticket_id = "ID" + String.format("%04d", vehiclesList.size() + 1);
            String EntryTime = entryDate + " " + entryTime;
            
            if (Liscense_number.isEmpty() &&(Vehicle_Type.equals("Xe máy")||(Vehicle_Type.equals("Ô tô")))) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            vehiclesList.add(new Object[]{Ticket_id, Liscense_number, Vehicle_Type, ticketType, EntryTime});
            vehicleModel.setRowCount(0);
            for (Object[] vehicle : vehiclesList) {
                vehicleModel.addRow(vehicle);
            }

            JOptionPane.showMessageDialog(this, "Thêm xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            
            CustomOptionPane.showMessage("Bạn có muốn in vé không?", "Thông báo", "In vé ngay");

            vehiclePlateInputField.setText("");
            
            monthlyCardInputField.setText("");
        });

        // Listener (Vé tháng)
        monthlyCardAddBtn.addActionListener(e -> {
            String Card_ID = Card_IDField.getText().trim();
            String Liscense_number = monthlyCardLicensePlateField.getText().trim();
            String Vehicle_type = monthlyCardTypeCombo.getSelectedItem().toString();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime start = now;
            LocalDateTime end = start.plusMonths(1);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/yyyy");

            String startDate = start.format(dateFormatter);
            String Expire_Date = end.format(dateFormatter);
            String Cost = monthlyCardFeeField.getText().trim();

            if (Card_ID.isEmpty() ||Cost.isEmpty()||(Liscense_number.isEmpty() &&(Vehicle_type.equals("Xe máy")||(Vehicle_type.equals("Ô tô"))))) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            monthlyCardsList.add(new Object[]{Card_ID, Liscense_number, Vehicle_type, startDate, Expire_Date, Cost});
            monthlyCardModel.setRowCount(0);
            for (Object[] card : monthlyCardsList) {
                monthlyCardModel.addRow(card);
            }

            JOptionPane.showMessageDialog(this, "Thêm vé tháng thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            Card_IDField.setText("");
            monthlyCardLicensePlateField.setText("");
            monthlyCardTypeCombo.setSelectedIndex(0);
            
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