package DBAccess;

import Helper.JDBC;
import Messages.Appointment_Warnings;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class DB_Appointments {

    public static ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> aList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Appointment_ID, title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID " +
                    "FROM appointments, contacts WHERE appointments.Contact_ID = contacts.Contact_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contactName = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");


                LocalDateTime startLDT = start.toLocalDateTime();
                ZonedDateTime startZDT = startLDT.atZone(ZoneId.of(ZoneId.systemDefault().toString()));

                LocalDateTime endLDT = end.toLocalDateTime();
                ZonedDateTime endZDT = endLDT.atZone(ZoneId.of(ZoneId.systemDefault().toString()));

                Appointments D = new Appointments(appointmentID, title, description, location,
                        contactName, type, startZDT, endZDT, customerID, userID);
                aList.add(D);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aList;
    }

    public static void createAppointment(String title, String description, String location, int contact, String type, int customer,
                                         ZonedDateTime start, ZonedDateTime end, int userID) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startString = start.format(formatter);
        String endString = end.format(formatter);

        try {
            String sqlti = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?, ?, ?)";

            PreparedStatement psti = JDBC.getConnection().prepareStatement(sqlti);
            psti.setString(1, title);
            psti.setString(2, description);
            psti.setString(3, location);
            psti.setString(4, type);
            psti.setString(5, startString);
            psti.setString(6, endString);
            psti.setInt(7, customer);
            psti.setInt(8, userID);
            psti.setInt(9, contact);

            psti.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateAppointment(int id, String title, String description, String location, int contact, String type, int customer,
                                         ZonedDateTime start, ZonedDateTime end, int userID) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startString = start.format(formatter);
        String endString = end.format(formatter);

        try {
            String sqlti = "UPDATE appointments set Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?," +
                    " Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlti);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setString(5, startString);
            ps.setString(6, endString);
            ps.setInt(7, customer);
            ps.setInt(8, userID);
            ps.setInt(9, contact);
            ps.setInt(10, id);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteAppointment(int appointmentID) {

        try {
            String sqlti = "DELETE from appointments WHERE Appointment_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlti);
            ps.setInt(1, appointmentID);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkOverlappingAppointment(int customer, LocalDateTime time) throws SQLException {

        ZonedDateTime ldtZoned = time.atZone(ZoneId.systemDefault());
        ZonedDateTime utcStart = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime utcEnd = utcStart.plusMinutes(1);
        LocalDateTime startConvert = utcStart.toLocalDateTime();
        LocalDateTime endConvert = utcEnd.toLocalDateTime();

        String sql = "SELECT * FROM appointments WHERE Customer_ID = " + customer + " AND ('" + startConvert +
                "' >= Start AND '" + startConvert + "' <= End) OR ( '" + endConvert + "' >= Start AND '" + endConvert + "' <= End)";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int overLapApt = 0;

        while (rs.next()) {
            overLapApt++;
        }

        if(overLapApt > 0) {
            Appointment_Warnings.overlapWarning();
            System.out.println("Here");
            System.out.println(customer);
            return false;
        } else {
            System.out.println("Or here");
            return true;
        }
    }
}
