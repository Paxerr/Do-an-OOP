/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DataBase.JDBCUtil;
import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Moderator
 */
public class ManagerDashBoardController implements ActionListener {

    private ManagerDashboard MD;

    public ManagerDashBoardController(ManagerDashboard ctrl) {
        this.MD = ctrl;
    }

    ParkingTicket Ticket = new ParkingTicket();

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("Thêm xe")) {
            String LicenseNumber = MD.vehiclePlateInputField.getText().trim();
            String VehicleType = MD.vehicleTypeCombo.getSelectedItem().toString();

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" HH:mm MM-dd-yyyy");
            Ticket.setEntryTime(now.format(formatter));

            Ticket.setLicenseNumber(LicenseNumber);
            Ticket.setVehicleType(VehicleType);

            if (LicenseNumber.isEmpty() && (VehicleType.equals("Xe máy") || (VehicleType.equals("Ô tô")))) {
                JOptionPane.showMessageDialog(MD, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(VehicleType.equals("Xe máy")) Ticket.setCost(5000);
            if(VehicleType.equals("Ô tô")) Ticket.setCost(10000);
            if(VehicleType.equals("Xe đạp")) Ticket.setCost(2000);
            
            Ticket.ParkTheVehicle();


            if ("error".equals(Ticket.getTicketType())) {
                JOptionPane.showMessageDialog(MD, "Lỗi hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            List<ParkingTicket> Result = new ArrayList<>();
            Result = Ticket.SearchVehicle("Refesh");
            MD.vehicleModel.setRowCount(0);
            for (ParkingTicket t : Result) {
                Object[] row = new Object[]{
                    t.getTicketID(),
                    t.getLicenseNumber(),
                    t.getVehicleType(),
                    t.getTicketType(),
                    t.getEntryTime()
                };
                MD.vehicleModel.addRow(row);
            }

            JOptionPane.showMessageDialog(MD, "Thêm xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            if ("Vé Thường".equals(Ticket.getTicketType())) {
                ManagerDashboard.CustomOptionPane.showMessage("Vé đã được in", "Thông báo", "Ok!");
            }

            MD.vehiclePlateInputField.setText("");

        }

        if (cmd.equals("Tìm kiếm xe")) {

            List<ParkingTicket> Result = new ArrayList<>();
            String LicenseNumber = MD.vehiclePlateInputField.getText().trim();

            Ticket.setLicenseNumber(LicenseNumber);

            if(LicenseNumber.isEmpty())
                Result = Ticket.SearchVehicle("Refesh");
            else
                Result = Ticket.SearchVehicle(cmd);
            MD.vehicleModel.setRowCount(0);
            for (ParkingTicket t : Result) {
                Object[] row = new Object[]{
                    t.getTicketID(),
                    t.getLicenseNumber(),
                    t.getVehicleType(),
                    t.getTicketType(),
                    t.getEntryTime()
                };
                MD.vehicleModel.addRow(row);
            }
            MD.vehiclePlateInputField.setText("");
        }

        if (cmd.equals("Tìm kiếm lịch sử xe")) {

            List<ParkingTicket> Result = new ArrayList<>();
            String SearchLicenseNumber = JOptionPane.showInputDialog(MD, "Nhập biển số xe cần tìm (để trống để hiển thị tất cả):");
            Ticket.setLicenseNumber(SearchLicenseNumber);

            Result = Ticket.SearchHistoryVehicle();
            
            MD.historyModel.setRowCount(0);
            for (ParkingTicket t : Result) {
                Object[] row = new Object[]{
                    t.getTicketID(),
                    t.getLicenseNumber(),
                    t.getVehicleType(),
                    t.getTicketType(),
                    t.getEntryTime(),
                    t.getTimeOut(),
                    t.getCost()
                };
                MD.historyModel.addRow(row);
            }
        }
        
    }
}
