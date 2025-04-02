    import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class QuanLyBaiXe {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuanLyBaiXe::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Quản Lý Bãi Xe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Bảng hiển thị danh sách xe
        String[] columnNames = {"Biển Số", "Loại Xe", "Chủ Xe", "Thời Gian Gửi"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Thêm dữ liệu mẫu
        tableModel.addRow(new Object[]{"51A-12345", "Ô tô", "Nguyễn Văn A", "08:00 AM"});
        tableModel.addRow(new Object[]{"59D-67890", "Xe máy", "Trần Thị B", "09:30 AM"});

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel();
        JButton btnPositionManagement = new JButton("Quản Lý Vị Trí Gửi Xe");
        JButton btnManageParking = new JButton("Quản Lý Lượt Gửi Xe");
        JButton btnReports = new JButton("Báo Cáo");

        buttonPanel.add(btnPositionManagement);
        buttonPanel.add(btnManageParking);
        buttonPanel.add(btnReports);

        // Cửa sổ con cho Quản Lý Vị Trí Gửi Xe
        btnPositionManagement.addActionListener(e -> showPositionManagement());

        // Cửa sổ con cho Quản Lý Lượt Gửi Xe
        btnManageParking.addActionListener(e -> showManageParking());

        // Cửa sổ con cho Báo Cáo
        btnReports.addActionListener(e -> showReports());

        // Thêm các thành phần vào frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void showPositionManagement() {
        JFrame frame = new JFrame("Quản Lý Vị Trí Gửi Xe");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Bảng hiển thị vị trí gửi xe
        String[] columnNames = {"Mã Số Vị Trí", "Trạng Thái", "Loại Xe"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Thêm dữ liệu mẫu
        tableModel.addRow(new Object[]{"P1", "Trống", "Ô tô"});
        tableModel.addRow(new Object[]{"P2", "Đang sử dụng", "Xe máy"});

        // Button để cập nhật trạng thái
        JPanel panel = new JPanel();
        JButton btnUpdate = new JButton("Cập Nhật Trạng Thái");
        panel.add(btnUpdate);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void showManageParking() {
        JFrame frame = new JFrame("Quản Lý Lượt Gửi Xe");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Bảng hiển thị danh sách xe
        String[] columnNames = {"Biển Số", "Loại Xe", "Thời Gian Vào", "Thời Gian Ra", "Phí"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Thêm dữ liệu mẫu
        tableModel.addRow(new Object[]{"51A-12345", "Ô tô", "08:00 AM", "10:00 AM", "50,000 VND"});
        tableModel.addRow(new Object[]{"59D-67890", "Xe máy", "09:00 AM", "10:30 AM", "20,000 VND"});

        // Button để thêm xe vào bãi
        JPanel panel = new JPanel();
        JButton btnAddParking = new JButton("Thêm Lượt Gửi Xe");
        panel.add(btnAddParking);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static void showReports() {
        JFrame frame = new JFrame("Báo Cáo");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Bảng báo cáo
        String[] columnNames = {"Ngày", "Số Lượt Gửi", "Doanh Thu"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Thêm dữ liệu mẫu
        tableModel.addRow(new Object[]{"2025-04-01", "10", "500,000 VND"});
        tableModel.addRow(new Object[]{"2025-04-02", "12", "600,000 VND"});

        // Button để tính toán báo cáo
        JPanel panel = new JPanel();
        JButton btnCalculate = new JButton("Tính Toán Báo Cáo");
        panel.add(btnCalculate);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
