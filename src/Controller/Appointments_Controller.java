package Controller;

import DBAccess.DB_Appointments;
import DBAccess.DB_Contacts;
import DBAccess.DB_Customers;
import DBAccess.DB_Users;
import Messages.Appointment_Warnings;
import Messages.Main_Warnings;
import Model.*;
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
import java.sql.SQLException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
/**This class is used as a controller for the appointment page. */
public class Appointments_Controller implements Initializable {

    Stage stage;

    ZoneId label = ZoneId.systemDefault();

    public TableView<Appointments> AppointmentTable;
    public TableColumn<Appointments, Integer> IDCol;
    public TableColumn<Appointments, String> titleCol;
    public TableColumn<Appointments, String> descriptionCol;
    public TableColumn<Appointments, String> locationCol;
    public TableColumn<Appointments, String> contactCol;
    public TableColumn<Appointments, String> typeCol;
    public TableColumn<Appointments, ZonedDateTime> startCol;
    public TableColumn<Appointments, ZonedDateTime> endCol;
    public TableColumn<Appointments, Integer> customerIDCol;
    public TableColumn<Appointments, Integer> userIDCol;

    private static int index;
    public Button cancelAppointmentButton;
    public Button updateAppointmentButton;

    public TextField appointmentIDField;
    public TextField titleField;
    public TextField descriptionField;
    public TextField locationField;
    public TextField typeField;
    public Button clearButton;
    public TextArea messageCenter;

    public ComboBox<Contacts> contactCombo;
    public ComboBox<Customer> customerIDCombo;
    public ComboBox<User> userIDCombo;
    public ComboBox<Integer> startHourCombo;
    public ComboBox<Integer> endHourCombo;
    public ComboBox<Integer> startMinCombo;
    public ComboBox<Integer> endMinCombo;

    public DatePicker appointmentDate;

    public RadioButton byMonth;
    public ToggleGroup sortDate;
    public RadioButton byWeek;
    public RadioButton byAll;

    public Button saveButton;

/**In the initialize method the appointment table view and all combo boxes are set.
 * A for loop checks for any appointments that are scheduled within 15 minutes of login.
 * @param url
 * @param resourceBundle */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        sortDate = new ToggleGroup();
        byMonth.setToggleGroup(sortDate);
        byWeek.setToggleGroup(sortDate);
        byAll.setToggleGroup(sortDate);


        for(int i = 0; i < DB_Appointments.getAllAppointments().size(); i++) {
            Appointments a = DB_Appointments.getAllAppointments().get(i);
            LocalDateTime b = a.getStart().toLocalDateTime();
            LocalTime startTime = b.toLocalTime();
            LocalTime currentTime = LocalTime.now();
            long interval = ChronoUnit.MINUTES.between(currentTime, startTime);
            if(interval > 0 && interval <= 15) {
                Appointment_Warnings.upcomingAppointmentWarning(a.getStart(), a.getAppointmentID(), interval);
                messageCenter.setText("Appointment [" + a.getAppointmentID() +
                        "] is within " + interval + " minutes. \nStart time is " + a.getStart());
            } else {
                messageCenter.setText("No upcoming appointments");
            }
        }

        AppointmentTable.setItems(DB_Appointments.getAllAppointments());
        customerIDCombo.setItems(DB_Customers.getAllCustomers());
        contactCombo.setItems(DB_Contacts.getAllContacts());
        userIDCombo.setItems(DB_Users.getAllUser());

        ObservableList<Integer> hours = FXCollections.observableArrayList();
        ObservableList<Integer> minutes = FXCollections.observableArrayList();

        for(int j = 0; j < 60; j++) {
            minutes.add(j);
        }

        for(int i = 0; i <= 24; i++) {
            hours.add(i);
        }

        startHourCombo.setItems(hours);
        endHourCombo.setItems(hours);
        startMinCombo.setItems(minutes);
        endMinCombo.setItems(minutes);

        IDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

/**The updateAppointment method updates a previously created appointment.
 * The selected appointment from the table view is added to text fields and combo boxes.
 * @param actionEvent*/
    public void updateAppointment(ActionEvent actionEvent) {

        Appointments selectedAppointment = AppointmentTable.getSelectionModel().getSelectedItem();
        index = DB_Appointments.getAllAppointments().indexOf(selectedAppointment);

        if(selectedAppointment == null) {
            Main_Warnings.nullUpdate();
        } else {
            appointmentIDField.setText(String.valueOf(selectedAppointment.getAppointmentID()));
            titleField.setText(String.valueOf(selectedAppointment.getTitle()));
            descriptionField.setText(String.valueOf(selectedAppointment.getDescription()));
            locationField.setText(String.valueOf(selectedAppointment.getLocation()));
            typeField.setText(String.valueOf(selectedAppointment.getType()));

            LocalDate datePicker = selectedAppointment.getStart().toLocalDate();
            appointmentDate.setValue(datePicker);

            LocalTime startHour = selectedAppointment.getStart().toLocalTime();
            startHourCombo.setValue(startHour.getHour());
            LocalTime startMinute = selectedAppointment.getStart().toLocalTime();
            startMinCombo.setValue(startMinute.getMinute());

            LocalTime endHour = selectedAppointment.getEnd().toLocalTime();
            endHourCombo.setValue(endHour.getHour());
            LocalTime endMinute = selectedAppointment.getEnd().toLocalTime();
            endMinCombo.setValue(endMinute.getMinute());

            for(int i = 0; i < contactCombo.getItems().size(); i++) {
                Contacts c = contactCombo.getItems().get(i);
                if(c.getContactName().equals(selectedAppointment.getContactName())) {
                    contactCombo.setValue(c);
                    break;
                }
            }
            for(int i = 0; i < customerIDCombo.getItems().size(); i++) {
                Customer customer = customerIDCombo.getItems().get(i);
                if(customer.getId() == selectedAppointment.getCustomerID()) {
                    customerIDCombo.setValue(customer);
                    break;
                }
            }

            for(int i = 0; i < userIDCombo.getItems().size(); i++) {
                User u = userIDCombo.getItems().get(i);
                if(u.getId() == selectedAppointment.getUserID()) {
                    userIDCombo.setValue(u);
                    break;
                }
            }
        }
    }
/**The saveAppointment method adds either a new appointment or updates a current appointment.
 * If the appointment has been selected from table view and has an ID it will update the appointment otherwise it will create a new appointment.
 * @param actionEvent*/
    public void saveAppointment(ActionEvent actionEvent) {

            String saveID = appointmentIDField.getText();
            String saveTitle = titleField.getText();
            String saveDescription = descriptionField.getText();
            String saveLocation = locationField.getText();
            Contacts contacts = contactCombo.getValue();
            String saveType = typeField.getText();
            Customer customer = customerIDCombo.getValue();
            User user = userIDCombo.getValue();

            try {
                LocalDateTime today = LocalDateTime.now();
                //For selecting start and end time from combo boxes
                Integer startHours = startHourCombo.getValue();
                Integer startMinutes = startMinCombo.getValue();
                Integer endHours = endHourCombo.getValue();
                Integer endMinutes = endMinCombo.getValue();
                //Create LocalTime from combo box selection
                LocalTime start = LocalTime.of(startHours, startMinutes);
                LocalTime end = LocalTime.of(endHours, endMinutes);
                //Create LocalDateTime from date-picker and combo box selection
                LocalDateTime sdt = LocalDateTime.of(appointmentDate.getValue(), start);
                LocalDateTime edt = LocalDateTime.of(appointmentDate.getValue(), end);
                //Create ZonedDateTime from LocalDateTime
                ZonedDateTime zsd = ZonedDateTime.of(sdt, label);
                ZonedDateTime zed = ZonedDateTime.of(edt, label);
                //Make ZonedDateTime UTC for database
                ZonedDateTime s = zsd.withZoneSameInstant(ZoneOffset.UTC);
                ZonedDateTime e = zed.withZoneSameInstant(ZoneOffset.UTC);
                //For checking appointment time in EST
                ZonedDateTime convertStartEST = zsd.withZoneSameInstant(ZoneId.of("America/New_York"));
                ZonedDateTime convertEndEST = zed.withZoneSameInstant(ZoneId.of("America/New_York"));
                LocalTime startTimeCheck = convertStartEST.toLocalTime();
                LocalTime endTimeCheck = convertEndEST.toLocalTime();
                DayOfWeek startAppointmentDayToCheck = convertStartEST.toLocalDate().getDayOfWeek();
                DayOfWeek endAppointmentDayToCheck = convertEndEST.toLocalDate().getDayOfWeek();
                int startWeekend = startAppointmentDayToCheck.getValue();
                int endWeekend = endAppointmentDayToCheck.getValue();
                int workWeekStart = DayOfWeek.MONDAY.getValue();
                int workWeekEnd = DayOfWeek.FRIDAY.getValue();
                LocalTime estBusinessStart = LocalTime.of(8, 0, 0);
                LocalTime estBusinessEnd = LocalTime.of(22, 0, 0);


                        if (saveTitle == null || saveTitle.length() == 0 || saveDescription == null || saveDescription.length() == 0 ||
                                saveLocation == null || saveLocation.length() == 0 || saveType == null || saveType.length() == 0) {
                            Appointment_Warnings.fieldsNullWarning();
                        } else if (contacts == null) {
                            Appointment_Warnings.contactWarning();
                        } else if (customer == null) {
                            Appointment_Warnings.customerWarning();
                        } else if (user == null) {
                            Appointment_Warnings.userWarning();
                        } else if (startTimeCheck.isBefore(estBusinessStart) || startTimeCheck.isAfter(estBusinessEnd)) {
                            Appointment_Warnings.startAppointmentWarning();
                        } else if (endTimeCheck.isBefore(estBusinessStart) || endTimeCheck.isAfter(estBusinessEnd)) {
                            Appointment_Warnings.endAppointmentWarning();
                        } else if (startWeekend < workWeekStart || startWeekend > workWeekEnd ||
                                endWeekend < workWeekStart || endWeekend > workWeekEnd) {
                            Appointment_Warnings.weekendWarning();
                        } else if (sdt.isAfter(edt)) {
                            Appointment_Warnings.startAfterWarning();
                        } else if (sdt.isEqual(edt)) {
                            Appointment_Warnings.startEqualsEndWarning();
                        } else if (sdt.isBefore(today)){
                            Appointment_Warnings.pastWarning();
                        } else if (edt.isBefore(today)) {
                            Appointment_Warnings.pastWarning();
                        }else {
                            if (saveID.length() == 0) {
                                if(!DB_Appointments.checkOverlappingAppointment(customer.getId(), sdt)) {
                                    return;
                                }
                                DB_Appointments.createAppointment(saveTitle, saveDescription, saveLocation, contacts.getContactID(), saveType, customer.getId(),
                                        s, e, user.getId());
                            } else {
                                int id = Integer.parseInt(saveID);
                                if(!DB_Appointments.checkOverlapAptUpdate(id, customer.getId(), sdt)) {
                                    return;
                                } else {
                                    DB_Appointments.updateAppointment(id, saveTitle, saveDescription, saveLocation, contacts.getContactID(), saveType, customer.getId(),
                                            s, e, user.getId());
                                }
                            }
                        }
            } catch (NullPointerException e) {
                Appointment_Warnings.timeWarning();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        AppointmentTable.setItems(DB_Appointments.getAllAppointments());
    }
/**The clearAppointmentForm clears all info from the text fields and combo boxes.
 * Anything typed into the text fields and selected into combo boxes will be deleted.
 * @param actionEvent */
    public void clearAppointmentForm(ActionEvent actionEvent) {
        appointmentIDField.clear();
        titleField.clear();
        descriptionField.clear();
        locationField.clear();
        typeField.clear();
        contactCombo.getSelectionModel().clearSelection();
        customerIDCombo.getSelectionModel().clearSelection();
        userIDCombo.getSelectionModel().clearSelection();
        startHourCombo.getSelectionModel().clearSelection();
        startMinCombo.getSelectionModel().clearSelection();
        endHourCombo.getSelectionModel().clearSelection();
        endMinCombo.getSelectionModel().clearSelection();
        appointmentDate.setValue(null);
        AppointmentTable.getSelectionModel().clearSelection();
    }
/**The deleteAppointment method deletes a selected appointment.
 * The selected appointment will be deleted from the database and the table.
 * @param actionEvent */
    public void deleteAppointment(ActionEvent actionEvent) {

        Appointments selectedAppointment = AppointmentTable.getSelectionModel().getSelectedItem();
        index = DB_Appointments.getAllAppointments().indexOf(selectedAppointment);

        if(selectedAppointment == null) {
            Appointment_Warnings.selectionDeleteWarning();
        } else {
            Appointment_Warnings.deleteConfirmation(selectedAppointment.getAppointmentID(), selectedAppointment.getType());
            AppointmentTable.setItems(DB_Appointments.getAllAppointments());
            Appointment_Warnings.appointmentDeleted(selectedAppointment.getAppointmentID(), selectedAppointment.getType());
        }
    }
/**The toCustomers method sends to customer form.
 * The toCustomers method sends to customer form also known as Main_Form.
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
    /**The toReports method sends to report form.
     * The toReports method sends to report form.
     * @param actionEvent */
    public void toReports(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Reports.fxml"));
        loader.load();

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
/**The byMonthToggle adds only appointments that are held in the current month to the tableview.
 * Only appointments in the current month will be in view in the tableview.
 * @param actionEvent */
    public void byMonthToggle(ActionEvent actionEvent) {
        AppointmentTable.setItems(DB_Appointments.getMonthlyAppointments());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }
/**The byWeekToggle adds only appointments that are held in the current week to the tableview.
 * Only appointments in the current week will be in view in the tableview.
 * @param actionEvent */
    public void byWeekToggle(ActionEvent actionEvent) {

        AppointmentTable.setItems(DB_Appointments.getWeeklyAppointments());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }
/**The allAptToggle adds all appointments to the tableview.
 * All appointments in the database are shown in the tableview.
 * @param actionEvent */
    public void allAptToggle(ActionEvent actionEvent) {

        AppointmentTable.setItems(DB_Appointments.getAllAppointments());
        IDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }

}