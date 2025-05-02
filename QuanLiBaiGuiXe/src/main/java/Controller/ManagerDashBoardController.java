/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DataBase.JDBCUtil;
import Model.*;
import View.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
/**
 *
 * @author Moderator
 */
public class ManagerDashBoardController {
    public String ThemXe(String id, String LicenseNumber,String VehicleType,String entryDate,String entryTime){
        ResultSet KetQuaTruyVan = null;
        Connection tmp = null;
        PreparedStatement state = null;
        String ticketType;
        try {
            tmp = JDBCUtil.getConnection();
            String themVeXe = "\"INSERT INTO ParkingTicket (LicenseNumber, VehicleType, ticketType, entryDate,entryTime) VALUES (?, ?, ?)\"";
            String timLoaiVe = "SELECT * From MonthlyCard Where LicenseNumber = ?";
            state = tmp.prepareStatement(timLoaiVe);
            state.setString(1, LicenseNumber);
            KetQuaTruyVan = state.executeQuery();
            if (KetQuaTruyVan.next()) {
                if(KetQuaTruyVan != null) KetQuaTruyVan.close();
                if(state != null) state.close();
                if(tmp != null) tmp.close();
                 "Vé Tháng";
            } else {
                if(KetQuaTruyVan != null) KetQuaTruyVan.close();
                if(state != null) state.close();
                if(tmp != null) tmp.close();
                ticketType = "Vé Thường";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    };
}
// vehicleAddBtn.addActionListener(e -> {
//            String LicenseNumber = vehiclePlateInputField.getText().trim();
//            String Vehicle_Type = vehicleTypeCombo.getSelectedItem().toString();
//            String ticketType =" ";
//
//            
//            LocalDateTime now = LocalDateTime.now();
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
//            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
//            
//            String entryDate = now.format(dateFormatter);
//            String entryTime = now.format(timeFormatter);
//            
//            entryDateField.setText(entryDate);
//            entryTimeField.setText(entryTime);
//
//            String Ticket_id = "ID" + String.format("%04d", vehiclesList.size() + 1);
//            String EntryTime = entryDate + " " + entryTime;
//            
//            if (Liscense_number.isEmpty() &&(Vehicle_Type.equals("Xe máy")||(Vehicle_Type.equals("Ô tô")))) {
//                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            
//            vehiclesList.add(new Object[]{Ticket_id, Liscense_number, Vehicle_Type, ticketType, EntryTime});
//            vehicleModel.setRowCount(0);
//            for (Object[] vehicle : vehiclesList) {
//                vehicleModel.addRow(vehicle);
//            }
//
//            JOptionPane.showMessageDialog(this, "Thêm xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            
//            CustomOptionPane.showMessage("Bạn có muốn in vé không?", "Thông báo", "In vé ngay");
//
//            vehiclePlateInputField.setText("");
//            
//            monthlyCardInputField.setText("");
//        });