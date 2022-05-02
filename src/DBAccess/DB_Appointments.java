package DBAccess;

import Helper.JDBC;
import Messages.Appointment_Warnings;
import Messages.Main_Warnings;
import Model.Appointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

/**This class holds communication between the database and the controllers for appointments.
 * The methods in this class are used to communicate info between the database and controllers.*/
public class DB_Appointments {
/**The getAllAppointments method makes an observable list.
 * This method makes an observable list with all the info from the database on the appointments.
 * @return aList*/
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
/**The createAppointment method takes the user input and creates a new appointment in the database.
 * This method creates a new appointment from the user info collected from the appointments controller.
 * @param title Appointment title.
 * @param contact Appointment contact.
 * @param customer Appointment Customer ID.
 * @param description Appointment description.
 * @param end Appointment end time and date.
 * @param location Appointment location.
 * @param start Appointment start time and date.
 * @param type Appointment type.
 * @param userID Appointment user ID. */
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
/**The updateAppointment method takes the user input and updates an existing appointment in the database.
 * This method updates an existing appointment from the user info collected from the appointments controller.
 * @param userID Appointment user ID.
 * @param type Appointment type.
 * @param start Appointment start time and date.
 * @param location Appointment location.
 * @param end Appointment end time and date.
 * @param description Appointment description.
 * @param customer Appointment customer ID.
 * @param contact Appointment contact ID.
 * @param id Appointment ID.
 * @param title Appointment title. */
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
/**The deleteAppointment deletes a selected appointment from the appointment controller.
 * The selected appointment in the appointment controller is deleted in the database with this method using the appointment ID.
 * @param appointmentID Appointment ID. */
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
/**The checkOverlappingAppointment method checks for overlapping appointments.
 * One customer cannot have more than one appointment at anytime.
 * @param customer customer ID.
 * @param time Appointment time. */
    public static boolean checkOverlappingAppointment(int customer, LocalDateTime time) throws SQLException {

        ZonedDateTime ldtZoned = time.atZone(ZoneId.systemDefault());
        ZonedDateTime utcStart = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
        ZonedDateTime utcEnd = utcStart.plusMinutes(1);
        LocalDateTime startConvert = utcStart.toLocalDateTime();
        LocalDateTime endConvert = utcEnd.toLocalDateTime();

        String sql = "SELECT * FROM appointments WHERE Customer_ID = " + customer + " AND (('" + startConvert +
                "' >= Start AND '" + startConvert + "' <= End) OR ( '" + endConvert + "' >= Start AND '" + endConvert + "' <= End))";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int overLapApt = 0;

        while (rs.next()) {
            overLapApt++;
        }

        if(overLapApt > 0) {
            Appointment_Warnings.overlapWarning();
            return false;
        } else {
            return true;
        }
    }
/**The getChosenContact method gets the chosen contacts schedule.
 * All appointments with the chosen contact is sent to the reports page.
 * @param id Contact ID.
 * @return chosenList*/
    public static ObservableList<Appointments> getChosenContact(int id) {

        ObservableList<Appointments> chosenList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM appointments WHERE Contact_ID = " + id;
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");


                LocalDateTime startLDT = start.toLocalDateTime();
                ZonedDateTime startZDT = startLDT.atZone(ZoneId.of(ZoneId.systemDefault().toString()));

                LocalDateTime endLDT = end.toLocalDateTime();
                ZonedDateTime endZDT = endLDT.atZone(ZoneId.of(ZoneId.systemDefault().toString()));

                Appointments D = new Appointments(appointmentID, title, description, location, type, startZDT, endZDT, customerID, userID);
                chosenList.add(D);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chosenList;
    }
/**The getMonthlyAppointments method sends all appointments in a chosen month.
 * The getMonthlyAppointments method sends all appointments in a chosen month to the reports page.
 * @return aList*/
    public static ObservableList<Appointments> getMonthlyAppointments() {

        ObservableList<Appointments> aList = FXCollections.observableArrayList();

        try {

            LocalDateTime today = LocalDateTime.now();
            ZonedDateTime zdt = today.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
            ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
            Month month = utczdt.getMonth();

            String sql = "SELECT Appointment_ID, title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID" +
                    " from appointments, contacts where MONTHNAME(Start) = '" + month + "' AND appointments.Contact_ID = contacts.Contact_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            int i = 0;
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

                i++;
            }
            if(i == 0) {
                Appointment_Warnings.noMonthlyAptWarning();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aList;
    }
/**The getWeeklyAppointments method returns all appointments in a given week.
 * The getWeeklyAppointments method returns all appointments in a given week.
 * @return aList*/
    public static ObservableList<Appointments> getWeeklyAppointments() {

        ObservableList<Appointments> aList = FXCollections.observableArrayList();

        try {

            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            int week = calendar.get(Calendar.WEEK_OF_YEAR);
            int weekAdjusted = week - 1;

            String sql = "SELECT Appointment_ID, title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID" +
                    " from appointments, contacts where WEEK(Start) = '" + weekAdjusted + "' AND appointments.Contact_ID = contacts.Contact_ID";
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
/**The getAllCustfromAppointments makes sure that a customer does not have any appointments before being deleted.
 * The getAllCustfromAppointments makes sure that a customer does not have any appointments before being deleted.
 * @param customer Customer ID.
 * @param name Customer name. */
    public static void getAllCustfromAppointments(int customer, String name) throws SQLException{

            String sql = "SELECT * FROM appointments WHERE Customer_ID = " + customer;
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            int cust = 0;
            while (rs.next()) {
                cust++;
            }
            if(cust > 0) {
                Main_Warnings.cannotDeleteWarning(customer, name);
            } else {
                Main_Warnings.deleteConfirmation(customer, name);
            }
    }
}
