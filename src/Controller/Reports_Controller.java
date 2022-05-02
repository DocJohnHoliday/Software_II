package Controller;

import DBAccess.DB_Appointments;
import DBAccess.DB_Contacts;
import Helper.JDBC;
import Messages.Report_Warning;
import Model.Appointments;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**This class controls the reports page. */
public class Reports_Controller implements Initializable {

    //Table showing Schedule for Contacts
    public TableView<Appointments> scheduleTable;
    public ComboBox<Contacts> contactCombo;
    public TableColumn<Appointments, Integer> scheduleIDCol;
    public TableColumn<Appointments, String> scheduleTitleCol;
    public TableColumn<Appointments, String> scheduleTypeCol;
    public TableColumn<Appointments, String> scheduleDesCol;
    public TableColumn<Appointments, ZonedDateTime> scheduleStartCol;
    public TableColumn<Appointments, ZonedDateTime> scheduleEndCol;
    public TableColumn<Appointments, Integer> scheduleCustIDCol;
    //Total # of Apts
    public TextField totalTypeInput;
    public TextField monthInput;
    public TextField totalByType;
    public TextField totalByMonth;
    //Shows number of appointments for today
    public TableView<Appointments> currentDayTable;
    public TextField todayText;
    public TextField numOfAptsToday;
    public TableColumn<Appointments, Integer> todayIDCol;
    public TableColumn<Appointments, String> todayTitleCol;
    public TableColumn<Appointments, String> todayTypeCol;
    public TableColumn<Appointments, String> todayDesCol;
    public TableColumn<Appointments, ZonedDateTime> todayStartCol;
    public TableColumn<Appointments, ZonedDateTime> todayEndCol;
    public TableColumn<Appointments, Integer> todayCustIDCol;


    Stage stage;

/**The initialize method sets the comboBox for contacts and the users current ZonedID.
 * The current local time and date is set to users current Zone ID and current time and date.
 * @param url
 * @param resourceBundle */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ZoneId label = ZoneId.systemDefault();
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(ldt, label);
        todayText.setText(zdt.toString());

        contactCombo.setItems(DB_Contacts.getAllContacts());

    }
/**The findContactSchedule method sets the scheduleTable to the selected contacts schedule.
 * The tableview is set depending on the contact combobox's setting.
 * @param actionEvent */
    public void findContactSchedule(ActionEvent actionEvent) {

        Contacts selectedContact = contactCombo.getValue();

        if(selectedContact == null) {
            Report_Warning.typeWarning();
        } else {
            scheduleTable.setItems(DB_Appointments.getChosenContact(selectedContact.getContactID()));

            scheduleIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            scheduleTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            scheduleDesCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            scheduleTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            scheduleStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            scheduleEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
            scheduleCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        }
    }
/**The totalByTypeAction uses the database to find the total number of appointments by type.
 * The method counts the number of appointments with the entered type.
 * @param actionEvent */
    public void totalByTypeAction(ActionEvent actionEvent) throws SQLException {

        String selectedType = totalTypeInput.getText();
        String sql = "SELECT COUNT(Type) FROM appointments WHERE Type = '" + selectedType + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            String count = rs.getString("COUNT(Type)");
            totalByType.setText(count);
        }

    }
/**The totalByMonthAction method uses the database to find the total number of appointments by month.
 * The method counts the number of appointments with the entered month.
 * @param actionEvent */
    public void totalByMonthAction(ActionEvent actionEvent) throws SQLException {

        String selectedMonth = monthInput.getText();
        String sql = "SELECT COUNT(monthname(Start)) FROM appointments WHERE monthname(Start) = '" + selectedMonth + "'";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            String count = rs.getString("COUNT(monthname(Start))");
            totalByMonth.setText(count);
        }

    }
/**The checkForApts sets the tableview with the number of appointments for the current day.
 * The number of appointments for the current day are presented along with the current day and the tableview is populated with the information for those appointments.
 * @param actionEvent */
    public void checkForApts(ActionEvent actionEvent) {

        LocalDateTime today = LocalDateTime.now();
        ZonedDateTime zdt = today.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ObservableList<Appointments> aptsToday = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from appointments where '" + utczdt.format(formatter) + "' =  DATE(Start)";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            int i = 0;
            while (rs.next()) {
                i++;
                int id = rs.getInt("Appointment_ID");
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

                for(int j = 0; j < DB_Appointments.getAllAppointments().size(); j++) {
                    if(id == DB_Appointments.getAllAppointments().get(j).getAppointmentID()) {
                        Appointments a = new Appointments(id, title, description, location,
                                type, startZDT, endZDT, customerID, userID);
                        aptsToday.add(a);
                        currentDayTable.setItems(aptsToday);
                        todayIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
                        todayTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
                        todayDesCol.setCellValueFactory(new PropertyValueFactory<>("description"));
                        todayTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
                        todayStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
                        todayEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
                        todayCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
                    } else {
                        Report_Warning.noAptWarning();
                    }
                }

            }
            numOfAptsToday.setText(String.valueOf(i));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/**The toAppointments method sends to appointment page.
 * The toAppointments method sends to appointment page.
 * @param actionEvent */
    public void toAppointments(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Appointments.fxml"));
        loader.load();

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
/**The toCustomers method sends to customers page also known as Main_From.
 * The toCustomers method sends to customers page also known as Main_From.
 * @param actionEvent */
    public void toCustomers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Main_Form.fxml"));
        loader.load();

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
