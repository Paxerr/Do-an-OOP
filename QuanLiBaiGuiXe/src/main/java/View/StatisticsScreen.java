package View;
import Controller.*;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class StatisticsScreen extends JFrame {
    private JLabel totalVehiclesLabel;
    private JLabel totalTicketsLabel;
    private JLabel totalMonthlyCardsLabel;
    private JLabel totalSercurityGuardLabel;
    private JComboBox<Integer> monthCombo;
    private JComboBox<Integer> yearCombo;

    public StatisticsScreen(String username, String role) {
        setTitle("Thống kê");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 255, 255));
        JLabel titleLabel = new JLabel("Thống kê", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(5, 2));
        statsPanel.setBackground(new Color(255, 255, 255));

        statsPanel.add(new JLabel("Tổng số xe hiện tại:"));
        totalVehiclesLabel = new JLabel("0");
        statsPanel.add(totalVehiclesLabel);

        statsPanel.add(new JLabel("Tổng số vé thường:"));
        totalTicketsLabel = new JLabel("0");
        statsPanel.add(totalTicketsLabel);

        statsPanel.add(new JLabel("Tổng số vé tháng:"));
        totalMonthlyCardsLabel = new JLabel("0");
        statsPanel.add(totalMonthlyCardsLabel);

        statsPanel.add(new JLabel("Tổng số nhân viên:"));
        totalSercurityGuardLabel = new JLabel("0");
        statsPanel.add(totalSercurityGuardLabel);

        statsPanel.add(new JLabel("Chọn tháng/năm:"));
        JPanel datePanel = new JPanel(new FlowLayout());
        Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        monthCombo = new JComboBox();
        datePanel.add(monthCombo);

        Integer[] years = new Integer[10];
        int currentYear = LocalDateTime.now().getYear();
        for (int i = 0; i < 10; i++) {
            years[i] = currentYear - i;
        }
        yearCombo = new JComboBox<>(years);
        datePanel.add(yearCombo);

        statsPanel.add(datePanel);

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton calcRevenueBtn = new JButton("Tính doanh thu");
        JButton backBtn = new JButton("Quay lại");
        buttonPanel.add(calcRevenueBtn);
        buttonPanel.add(backBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        calcRevenueBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Chức năng chưa được triển khai!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });

        backBtn.addActionListener(e -> dispose());

        add(mainPanel);
        setVisible(true);
    }
}