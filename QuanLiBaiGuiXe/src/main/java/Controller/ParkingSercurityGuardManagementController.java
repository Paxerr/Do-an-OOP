package Controller;

import DataBase.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingSercurityGuardManagementController {
    private Connection c;

    public ParkingSercurityGuardManagementController() {
        c = JDBCUtil.getConnection();
    }

    
    public boolean addPerson(String identifier, String fullName, String address, int phoneNumber , String Gender) {
        if (c == null) return false;

        String sql = "INSERT INTO Person (identifier, FullName, Address, PhoneNumber, Gender) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, identifier);
            pstmt.setString(2, fullName);
            pstmt.setString(3, address);
            pstmt.setInt(4, phoneNumber);
            pstmt.setString(5, Gender);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    public boolean deletePerson(String identifier) {
        if (c == null) return false;

        String sql = "DELETE FROM Person WHERE identifier = ?";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, identifier);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public List<Object[]> searchPerson(String cccd) {
        List<Object[]> result = new ArrayList<>();
        if (c == null) return result;

        String sql = "SELECT identifier, FullName, Address, PhoneNumber, Gender FROM Person WHERE identifier = ?";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, cccd);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getString("identifier"),
                    rs.getString("FullName"),
                    rs.getString("Address"),
                    rs.getInt("PhoneNumber"),
                    rs.getString("Gender")
                };
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updatePerson(String identifier, String fullName, String address, int phoneNumber, String gender) {
        if (c == null) return false;

        String sql = "UPDATE Person SET FullName = ?, Address = ?, PhoneNumber = ?, Gender = ? WHERE identifier = ?";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setString(2, address);
            pstmt.setInt(3, phoneNumber);
            pstmt.setString(4, gender);
            pstmt.setString(5, identifier);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Object[]> getAllPersons() {
        List<Object[]> result = new ArrayList<>();
        if (c == null) {
            System.out.println("Connection is null when fetching all persons!");
            return result;
        }

        String sql = "SELECT identifier, FullName, Address, PhoneNumber, Gender FROM Person";
        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            int index = 1;
            while (rs.next()) {
                Object[] row = {
                    "ID" + String.format("%04d", index++), 
                    rs.getString("identifier"),
                    rs.getString("FullName"),
                    "Nhân viên", 
                    "", 
                    rs.getString("Gender"),
                    rs.getString("Address"),
                    rs.getInt("PhoneNumber")
                };
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("SQLException when fetching all persons: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    public void closeConnection() {
        JDBCUtil.closeConnection(c);
    }
}
