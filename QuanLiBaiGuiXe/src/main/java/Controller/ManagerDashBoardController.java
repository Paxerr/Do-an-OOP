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
        if (cmd.equals("Thêm")) {

            String LicenseNumber = MD.vehiclePlateInputField.getText().trim();
            String VehicleType = MD.vehicleTypeCombo.getSelectedItem().toString();

            
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" HH:mm MM-dd-yyyy");                  
            Ticket.setEntryTime(now.format(formatter));
            Ticket.setTicketID("ID" + String.format("%04d", MD.vehiclesList.size() + 1));
            
            Ticket.setLicenseNumber(LicenseNumber);
            Ticket.setVehicleType(VehicleType);
            

            String rac = Ticket.getLicenseNumber();
            String rac1 = Ticket.getVehicleType();
            if (rac.isEmpty() && (rac1.equals("Xe máy") || (rac1.equals("Ô tô")))) {
                JOptionPane.showMessageDialog(MD, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Ticket.ParkTheVehicle();
            
            if ("error".equals(Ticket.getTicketType())) {
                JOptionPane.showMessageDialog(MD, "Lỗi hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            MD.ticketTypeField.setText(Ticket.getTicketType());

            MD.vehiclesList.add(new Object[]{Ticket.getTicketID(), Ticket.getLicenseNumber(), Ticket.getVehicleType(), Ticket.getTicketType(), Ticket.getEntryTime()});
            MD.vehicleModel.setRowCount(0);
            for (Object[] vehicle : MD.vehiclesList) {
                MD.vehicleModel.addRow(vehicle);
            }

            JOptionPane.showMessageDialog(MD, "Thêm xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            ManagerDashboard.CustomOptionPane.showMessage("Bạn có muốn in vé không?", "Thông báo", "In vé ngay");
            
            MD.vehiclePlateInputField.setText("");
            MD.monthlyCardInputField.setText("");

        }

    }
}
