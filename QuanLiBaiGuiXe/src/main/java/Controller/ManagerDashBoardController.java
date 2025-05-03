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
        this.MD = MD;
    }

    ParkingTicket Ticket;

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Thêm")) {

            String LicenseNumber = MD.vehiclePlateInputField.getText().trim();
            String VehicleType = MD.vehicleTypeCombo.getSelectedItem().toString();

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

            String entryDate = now.format(dateFormatter);
            String entryTime = now.format(timeFormatter);

            MD.entryDateField.setText(entryDate);
            MD.entryTimeField.setText(entryTime);

            String TicketID = "ID" + String.format("%04d", MD.vehiclesList.size() + 1);
            String EntryTime = entryDate + " " + entryTime;

            if (LicenseNumber.isEmpty() && (VehicleType.equals("Xe máy") || (VehicleType.equals("Ô tô")))) {
                JOptionPane.showMessageDialog(MD, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            String TicketType = ManagerDashBoardController.ThemXe(LicenseNumber, VehicleType, EntryTime);
            if (Ticket.getTicketType() == "error") {
                JOptionPane.showMessageDialog(MD, "Lỗi hệ thống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            ticketTypeField.setText(TicketType);

            vehiclesList.add(new Object[]{Ticket_id, LicenseNumber, VehicleType, TicketType, EntryTime});
            vehicleModel.setRowCount(0);
            for (Object[] vehicle : vehiclesList) {
                vehicleModel.addRow(vehicle);
            }

            JOptionPane.showMessageDialog(this, "Thêm xe thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            ManagerDashboard.CustomOptionPane.showMessage("Bạn có muốn in vé không?", "Thông báo", "In vé ngay");

            vehiclePlateInputField.setText("");

            monthlyCardInputField.setText("");
        }
            
    }

    public static String ThemXe(String LicenseNumber, String VehicleType, String EntryTime) {

    }
;

}
