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
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class Appointments_Controller implements Initializable {

    Stage stage;

    public TableView<Appointments> AppointmentTable;
    public TableColumn<Appointments, Integer> IDCol;
    public TableColumn<Appointments, String> titleCol;
    public TableColumn<Appointments, String> descriptionCol;
    public TableColumn<Appointments, String> locationCol;
    public TableColumn<Appointments, String> contactCol;
    public TableColumn<Appointments, String> typeCol;
    public TableColumn<Appointments, Timestamp> startCol;
    public TableColumn<Appointments, Timestamp> endCol;
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
    public TextField startTimeField;
    public TextField endTimeField;
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

    public Button saveButton;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        AppointmentTable.setItems(DB_Appointments.getAllAppointments());

        customerIDCombo.setItems(DB_Customers.getAllCustomers());

        contactCombo.setItems(DB_Contacts.getAllContacts());

        userIDCombo.setItems(DB_Users.getAllUser());

        ObservableList<Integer> hours = FXCollections.observableArrayList();
        ObservableList<Integer> minutes = FXCollections.observableArrayList();

        for(int j = 1; j < 60; j++) {
            minutes.add(j);
        }

        for(int i = 8; i <= 22; i++) {
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

    public void saveAppointment(ActionEvent actionEvent) {

        String saveID = appointmentIDField.getText();
        String saveTitle = titleField.getText();
        String saveDescription = descriptionField.getText();
        String saveLocation = locationField.getText();
        Contacts contacts = contactCombo.getValue();
        String saveType = typeField.getText();
        Customer customer = customerIDCombo.getValue();
        User user = userIDCombo.getValue();

        Integer startHours = startHourCombo.getValue();
        Integer startMinutes = startMinCombo.getValue();
        Integer endHours = endHourCombo.getValue();
        Integer endMinutes = endMinCombo.getValue();

        LocalTime start = LocalTime.of(startHours, startMinutes);
        LocalTime end = LocalTime.of(endHours, endMinutes);

        LocalDateTime sdt = LocalDateTime.of(appointmentDate.getValue(), start);
        LocalDateTime edt = LocalDateTime.of(appointmentDate.getValue(), end);

        ZoneId label = ZoneId.systemDefault();

        ZonedDateTime zsd = ZonedDateTime.of(sdt, label);

        ZonedDateTime s = zsd.withZoneSameInstant(ZoneOffset.UTC);

        System.out.println(zsd);
        System.out.println(s);

//        if(saveTitle == null || saveTitle.length() == 0 || saveDescription == null || saveDescription.length() == 0 ||
//                saveLocation == null || saveLocation.length() == 0 || saveType == null || saveType.length() == 0 ) {
//            Appointment_Warnings.fieldsNullWarning();
//        } else if (contacts == null) {
//            Appointment_Warnings.contactWarning();
//        } else if (customer == null) {
//            Appointment_Warnings.customerWarning();
//        } else {
//            if(saveID == null || saveID.length() == 0) {
//                DB_Appointments.createAppointment(saveTitle, saveDescription, saveLocation, contacts.getContactID(), saveType, customer.getId(),
//                        sdt, edt, user.getId());
//            } else {
//                int id = Integer.parseInt(saveID);
//                DB_Appointments.updateAppointment(id, saveTitle, saveDescription, saveLocation, contacts.getContactID(), saveType, customer.getId(),
//                        sdt, edt, user.getId());
//            }
//        }
//
//        AppointmentTable.setItems(DB_Appointments.getAllAppointments());
    }

    public void clearAppointmentForm(ActionEvent actionEvent) {
        appointmentIDField.clear();
        titleField.clear();
        descriptionField.clear();
        locationField.clear();
        typeField.clear();
        contactCombo.getSelectionModel().clearSelection();
        customerIDCombo.getSelectionModel().clearSelection();
        userIDCombo.getSelectionModel().clearSelection();
        appointmentDate.getEditor().clear();
    }

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
