package View;

import Controller.*;
import Model.ParkingTicket;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManagerDashboard extends JFrame {
    public DefaultTableModel vehicleModel;
    public DefaultTableModel historyModel;
    public DefaultTableModel ticketModel;
    public DefaultTableModel monthlyCardModel;
    public DefaultTableModel LoginlogoutModel;
    public JTextField licensePlateField;
    public JComboBox<String> vehicleTypeCombo;
    public JTextField ticketTypeField;
    public JTextField entryTimeField;
    public JTextField entryDateField;
    public JTable vehicleTable;
    public JTable monthlyCardTable;
    public JComboBox<String> ticketTypeCombo;
    public JTextField monthlyCardInputField;
    public JLabel vehiclePlateInput;
    public JLabel vehiclePlateInputLabel;
    public JTextField vehiclePlateInputField;
    public JLabel monthlyCardInputLabel;
    public JTextField Card_IDField;
    public JTextField monthlyCardLicensePlateField;
    public JComboBox<String> monthlyCardTypeCombo;
    public JTextField monthlyCardStartDateField;
    public JTextField monthlyCardEndDateField;
    public JTextField monthlyCardFeeField;
    public ArrayList<Object[]> personsList;
    public ArrayList<Object[]> vehiclesList;    
    public ArrayList<Object[]> historyList;
    public ArrayList<Object[]> ticketsList;
    public ArrayList<Object[]> monthlyCardsList;
    public ArrayList<Object[]> LoginlogoutsList;
    
    ActionListener ctrl = new ManagerDashBoardController(this);
    
    public class CustomOptionPane {
    public static void showMessage(String message, String title, String buttonText) {
        JOptionPane.showOptionDialog(
            null,
            message,
            title,
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new Object[]{buttonText}, // NÃºt tÃ¹y chá»‰nh
            buttonText
        );
    }
}

    public ManagerDashboard(String username, String role) {
        setTitle("Há»‡ thá»‘ng quáº£n lÃ½ bÃ£i Ä‘á»— xe");
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

        JLabel titleLabel = new JLabel("ChÃ o ", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel roleLabel = new JLabel("Chá»©c vá»¥: " + role, SwingConstants.CENTER);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(roleLabel, BorderLayout.CENTER);

        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tabPanel.setBackground(new Color(255, 165, 0));
        JButton manageTab = new JButton("Quáº£n lÃ½");
        JButton statsTab = new JButton("Thá»‘ng kÃª");
        tabPanel.add(manageTab);
        tabPanel.add(statsTab);
        topPanel.add(tabPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();

        // Tab Quáº£n lÃ½ xe
        JSplitPane vehicleTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        vehicleTab.setResizeWeight(0.5);

        JPanel vehiclePanel = new JPanel(new GridBagLayout());
        vehiclePanel.setBorder(BorderFactory.createTitledBorder("Quáº£n lÃ½ xe"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Dimension fieldSize = new Dimension(200, 25);

        gbc.gridx = 0;
        gbc.gridy = 0;
        vehiclePlateInputLabel = new JLabel("biá»ƒn sá»‘: *");
        vehiclePanel.add(vehiclePlateInputLabel, gbc);
        gbc.gridx = 1;
        vehiclePlateInputField = new JTextField(15);
        vehiclePlateInputField.setPreferredSize(fieldSize);
        vehiclePanel.add(vehiclePlateInputField, gbc);
        vehiclePlateInputLabel.setVisible(true);
        vehiclePlateInputField.setVisible(true);

        gbc.gridx = 0;
        gbc.gridy = 1;
        vehiclePanel.add(new JLabel("Loáº¡i xe: *"), gbc);
        gbc.gridx = 1;
        vehicleTypeCombo = new JComboBox<>(new String[]{"Xe mÃ¡y", "Ã” tÃ´","Xe Ä‘áº¡p"});
        vehicleTypeCombo.setPreferredSize(fieldSize);
        vehiclePanel.add(vehicleTypeCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        vehiclePanel.add(new JLabel("Loáº¡i vÃ©: *"), gbc);
        gbc.gridx = 1;
        
        ticketTypeField = new JTextField(15);
        ticketTypeField.setPreferredSize(fieldSize);
        ticketTypeField.setEditable(false);
        vehiclePanel.add(ticketTypeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        monthlyCardInputLabel = new JLabel("MÃ£ vÃ© thÃ¡ng: *");
        vehiclePanel.add(monthlyCardInputLabel, gbc);
        gbc.gridx = 1;
        monthlyCardInputField = new JTextField(15);
        monthlyCardInputField.setPreferredSize(fieldSize);
        vehiclePanel.add(monthlyCardInputField, gbc);
        monthlyCardInputLabel.setVisible(false);
        monthlyCardInputField.setVisible(false);

        
//
//        vehicleTypeCombo.addActionListener(e -> {
//            boolean isvehicle = vehicleTypeCombo.getSelectedItem().toString().equals("Xe Ä‘áº¡p");
//            vehiclePlateInputLabel.setVisible(!isvehicle);
//            vehiclePlateInputField.setVisible(!isvehicle);
//        });

        String[] vehicleColumns = {"MÃ£", "Biá»ƒn sá»‘", "Loáº¡i xe", "Loáº¡i vÃ©", "TG vÃ o báº¿n"};
        vehicleModel = new DefaultTableModel(vehicleColumns, 0);
        vehicleTable = new JTable(vehicleModel);
        JScrollPane vehicleTableScroll = new JScrollPane(vehicleTable);

        vehicleTab.setLeftComponent(vehiclePanel);
        vehicleTab.setRightComponent(vehicleTableScroll);

        // Tab Lá»‹ch sá»­ gá»­i xe
        JPanel historyTab = new JPanel(new BorderLayout());
        String[] historyColumns = {"MÃ£", "Biá»ƒn sá»‘", "Loáº¡i xe", "Loáº¡i vÃ©", "TG vÃ o", "Tg ra", "PhÃ­"};
        historyModel = new DefaultTableModel(historyColumns, 0);
        JTable historyTable = new JTable(historyModel);
        JScrollPane historyTableScroll = new JScrollPane(historyTable);
        historyTab.add(historyTableScroll, BorderLayout.CENTER);

       

        // Tab VÃ© thÃ¡ng
        JSplitPane monthlyCardTab = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        monthlyCardTab.setResizeWeight(0.5);

        JPanel monthlyCardInputPanel = new JPanel(new GridBagLayout());
        monthlyCardInputPanel.setBorder(BorderFactory.createTitledBorder("Quáº£n lÃ½ vÃ© thÃ¡ng"));
        GridBagConstraints mtGbc = new GridBagConstraints();
        mtGbc.insets = new Insets(5, 5, 5, 5);
        mtGbc.fill = GridBagConstraints.HORIZONTAL;

        mtGbc.gridx = 0;
        mtGbc.gridy = 0;
        monthlyCardInputPanel.add(new JLabel("MÃ£ tháº»: *"), mtGbc);
        mtGbc.gridx = 1;
        Card_IDField = new JTextField(15);
        Card_IDField.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(Card_IDField, mtGbc);

        mtGbc.gridx = 0;
        mtGbc.gridy = 1;
        monthlyCardInputPanel.add(new JLabel("Biá»ƒn sá»‘ xe: *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardLicensePlateField = new JTextField(15);
        monthlyCardLicensePlateField.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(monthlyCardLicensePlateField, mtGbc);

        mtGbc.gridx = 0;
        mtGbc.gridy = 2;
        monthlyCardInputPanel.add(new JLabel("Loáº¡i xe: *"), mtGbc);
        mtGbc.gridx = 1;
        monthlyCardTypeCombo = new JComboBox<>(new String[]{"Xe mÃ¡y", "Ã” tÃ´","Xe Ä‘áº¡p"});
        monthlyCardTypeCombo.setPreferredSize(fieldSize);
        monthlyCardInputPanel.add(monthlyCardTypeCombo, mtGbc);

        

        
        
        vehicleTypeCombo.addActionListener(e -> {
            boolean isMonthlyCard = vehicleTypeCombo.getSelectedItem().toString().equals("VÃ© thÃ¡ng");
            monthlyCardInputLabel.setVisible(isMonthlyCard);
            monthlyCardInputField.setVisible(isMonthlyCard);
        });


        String[] monthlyCardColumns = {"MÃ£ vÃ©", "Biá»ƒn sá»‘ xe", "Loáº¡i xe", "Thá»i háº¡n", "PhÃ­ vÃ©"};
        monthlyCardModel = new DefaultTableModel(monthlyCardColumns, 0);
        monthlyCardTable = new JTable(monthlyCardModel);
        JScrollPane monthlyCardTableScroll = new JScrollPane(monthlyCardTable);

        monthlyCardTab.setLeftComponent(monthlyCardInputPanel);
        monthlyCardTab.setRightComponent(monthlyCardTableScroll);

        // Tab NhÃ¢n viÃªn
        JPanel LoginlogoutTab = new JPanel(new BorderLayout());
        String[] LoginlogoutColumns = {"STT","MÃ£ NV", "Há» tÃªn", "Chá»©c vá»¥", "Login", "Logout"};
        LoginlogoutModel = new DefaultTableModel(LoginlogoutColumns, 0);
        JTable LoginlogoutTable = new JTable(LoginlogoutModel);
        JScrollPane LoginlogoutTableScroll = new JScrollPane(LoginlogoutTable);
        LoginlogoutTab.add(LoginlogoutTableScroll, BorderLayout.CENTER);

        tabs.addTab("Quáº£n lÃ½ xe", vehicleTab);
        tabs.addTab("Lá»‹ch sá»­ gá»­i xe", historyTab);
        
        tabs.addTab("Quáº£n lÃ½ vÃ© thÃ¡ng", monthlyCardTab);
        tabs.addTab("Lá»‹ch sá»­ Ä‘Äƒng nháº­p", LoginlogoutTab);

        mainPanel.add(tabs, BorderLayout.CENTER);

        // CÃ¡c panel nÃºt riÃªng cho tá»«ng tab
        JPanel vehicleButtonPanel = new JPanel(new FlowLayout());
        JButton vehicleAddBtn = new JButton("ThÃªm xe");
        
        
        JButton vehicleSearchAllBtn = new JButton("TÃ¬m kiáº¿m xe");
        JButton vehicleConfirmExitBtn = new JButton("XÃ¡c nháº­n rá»i bÃ£i");
        JButton vehicleRegisterMonthlyBtn = new JButton("ÄÄƒng kÃ½ vÃ© thÃ¡ng");
        vehicleButtonPanel.add(vehicleAddBtn);
        
        
        vehicleButtonPanel.add(vehicleSearchAllBtn);
        vehicleButtonPanel.add(vehicleConfirmExitBtn);
        vehicleButtonPanel.add(vehicleRegisterMonthlyBtn);

        JPanel historyButtonPanel = new JPanel(new FlowLayout());
        
        JButton historySearchAllBtn = new JButton("TÃ¬m kiáº¿m lá»‹ch sá»­ xe");
        
        historyButtonPanel.add(historySearchAllBtn);
        


        JPanel monthlyCardButtonPanel = new JPanel(new FlowLayout());
        JButton monthlyCardSearchIdBtn = new JButton("TÃ¬m kiáº¿m vÃ© theo mÃ£");
        JButton monthlyCardSearchAllBtn = new JButton("TÃ¬m kiáº¿m vÃ© theo xe");
        
        JButton monthlyCardAddBtn = new JButton("ThÃªm vÃ©");
        JButton monthlyCardGiaHanBtn = new JButton("Gia háº¡n");
        monthlyCardButtonPanel.add(monthlyCardAddBtn);
        monthlyCardButtonPanel.add(monthlyCardGiaHanBtn);
        monthlyCardButtonPanel.add(monthlyCardSearchIdBtn);
        monthlyCardButtonPanel.add(monthlyCardSearchAllBtn);

        JPanel LoginlogoutButtonPanel = new JPanel(new FlowLayout());
        JButton LoginlogoutSearchIdBtn = new JButton("TÃ¬m kiáº¿m theo mÃ£ NV");
        LoginlogoutButtonPanel.add(LoginlogoutSearchIdBtn);

        JButton logoutBtn = new JButton("ÄÄƒng xuáº¥t");

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

        vehicleAddBtn.addActionListener(ctrl);
        
        LoginlogoutSearchIdBtn.addActionListener(e -> {
            String SearchloginNV = JOptionPane.showInputDialog(this, "Nháº­p mÃ£ NV cáº§n tÃ¬m (Ä‘á»ƒ trá»‘ng Ä‘á»ƒ hiá»ƒn thá»‹ táº¥t cáº£):");
            vehicleModel.setRowCount(0);
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
        historySearchAllBtn.addActionListener(ctrl);
        vehicleSearchAllBtn.addActionListener(ctrl);
        vehicleConfirmExitBtn.addActionListener(ctrl);
        vehicleRegisterMonthlyBtn.addActionListener(ctrl);
        monthlyCardAddBtn.addActionListener(ctrl);
        monthlyCardSearchIdBtn.addActionListener(ctrl);
        monthlyCardSearchAllBtn.addActionListener(ctrl);
        monthlyCardGiaHanBtn.addActionListener(ctrl);
    }
}
