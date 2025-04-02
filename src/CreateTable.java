import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CreateTable {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/quanliguixe?useSSL=false&serverTimezone=UTC";
            String userName = "root";
            String password = "quyet1110";

            try (Connection newConnection = DriverManager.getConnection(url, userName, password);
                 Statement statement = newConnection.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS vehicles (" +
                                       "license_number INT AUTO_INCREMENT PRIMARY KEY, " +
                                       "vehicle_type VARCHAR(20) NOT NULL)";
                statement.executeUpdate(createTableSQL);
                JOptionPane.showMessageDialog(null, "Bảng vehicles đã được tạo thành công!",
                    "System Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}