package Controller;

import DBAccess.DB_Appointments;
import DBAccess.DB_Customers;
import Messages.Appointment_Warnings;
import Messages.Main_Warnings;
import Model.Appointments;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Appointments_Controller implements Initializable {


    public TableView<Appointments> AppointmentTable;
    public TableColumn<Appointments, Integer> IDCol;
    public TableColumn<Appointments, String> titleCol;
    public TableColumn<Appointments, String> descriptionCol;
    public TableColumn<Appointments, String> locationCol;
    public TableColumn<Appointments, String> contactCol;
    public TableColumn<Appointments, String> typeCol;
    public TableColumn<Appointments, String> startCol;
    public TableColumn<Appointments, String> endCol;
    public TableColumn<Appointments, Integer> customerIDCol;
    public TableColumn<Appointments, Integer> userIDCol;

    private static int index;

    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    public void deleteAppointment(ActionEvent actionEvent) {

        Appointments selectedAppointment = AppointmentTable.getSelectionModel().getSelectedItem();
        index = DB_Appointments.getAllAppointments().indexOf(selectedAppointment);

        if(selectedAppointment == null) {
            Appointment_Warnings.selectionDeleteWarning();
        } else {
            //Needs to be rewritten
            //Main_Warnings.deleteConfirmation(selectedAppointment.getAppointmentID());
            AppointmentTable.setItems(DB_Appointments.getAllAppointments());
            Appointment_Warnings.appointmentDeleted();
        }
    }

    public void updateAppointment(ActionEvent actionEvent) {
    }

    public void addAppointment(ActionEvent actionEvent) {
    }
}
