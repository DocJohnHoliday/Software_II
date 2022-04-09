package DBAccess;

import Helper.JDBC;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                String start = rs.getString("Start");
                String end = rs.getString("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");

                Appointments D = new Appointments(appointmentID, title, description, location,
                        contactName, type, start, end, customerID, userID);
                aList.add(D);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  aList;
    }

    public static void createAppointment() {

    }

    public static void updateAppointment() {

    }

    public static void deleteAppointment() {

    }
}
