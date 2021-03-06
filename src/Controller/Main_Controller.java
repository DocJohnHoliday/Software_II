package Controller;

import DBAccess.DB_Country;
import DBAccess.DB_Customers;
import DBAccess.DB_Divisions;
import DBAccess.DB_Appointments;
import Messages.Main_Warnings;
import Model.Country;
import Model.Customer;

import Model.Divisions;
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
import java.util.ResourceBundle;

/**This class controls the Customer page also known as the Main_Form.*/
public class Main_Controller implements Initializable{

    Stage stage;
    //Customer Table
    public TableView<Customer>CustomerTable;
    public TableColumn<Customer, Integer> IDCol;
    public TableColumn<Customer, String> NameCol;
    public TableColumn<Customer, String> AddressCol;
    public TableColumn<Customer, String> PostalCol;
    public TableColumn<Customer, String> PhoneCol;
    public TableColumn<Customer, Integer> DivCol;
    public TableColumn<Customer, String> CountryCol;
    //Buttons
    public Button deleteButton;
    public Button updateButton;
    //Text fields for add/update
    public TextField idField;
    public TextField codeField;
    public TextField addressField;
    public TextField phoneField;
    public TextField nameField;
    //Combo Fields
    public ComboBox<Country> countryCombo;
    public ComboBox<Divisions> divisionCombo;

/**The initialize method sets the tableview to show all customers.
 * All customer information is shown in the tableview.
 * @param url
 * @param resourceBundle */
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryCombo.setItems(DB_Country.getCountries());

        CustomerTable.setItems(DB_Customers.getAllCustomers());

        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        PostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        DivCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        CountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        
    }
/**The updateCustomer method updates a previously made customer.
 * The selected customer from the table view is added to text fields and combo boxes.
 * @param actionEvent */
    public void updateCustomer(ActionEvent actionEvent) {

        Customer selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();

        if(selectedCustomer == null) {
            Main_Warnings.nullUpdate();
        } else {
            idField.setText(String.valueOf(selectedCustomer.getId()));
            addressField.setText(String.valueOf(selectedCustomer.getAddress()));
            phoneField.setText(String.valueOf(selectedCustomer.getPhone()));
            codeField.setText(String.valueOf(selectedCustomer.getPostalCode()));
            nameField.setText(String.valueOf(selectedCustomer.getName()));

            for(int i = 0; i < countryCombo.getItems().size(); i++) {
                Country c = countryCombo.getItems().get(i);
                if(c.getCountryName().equals(selectedCustomer.getCountry())) {
                    countryCombo.setValue(c);
                    break;
                }
            }
            for(int i = 0; i < divisionCombo.getItems().size(); i++) {
                Divisions d = divisionCombo.getItems().get(i);
                if(d.getDivision().equals(selectedCustomer.getDivision())) {
                    divisionCombo.setValue(d);
                    break;
                }
            }
        }
    }
/**The saveCustomer method adds either a new customer or updates a current customer.
 * If the customer has been selected from table view and has an ID it will update the customer otherwise it will create a new customer.
 * @param actionEvent */
    public void saveCustomer(ActionEvent actionEvent) throws NumberFormatException {

        String saveID = idField.getText();
        String saveName = nameField.getText();
        String saveAddress = addressField.getText();
        String saveCode = codeField.getText();
        String savePhone = phoneField.getText();
        Divisions divisions = divisionCombo.getValue();
        Country country = countryCombo.getValue();

        if(saveName == null || saveName.length() == 0 || saveAddress == null || saveAddress.length() == 0 || saveCode == null || saveCode.length() == 0 ||
                savePhone == null || savePhone.length() == 0) {
            Main_Warnings.fieldsNullWarning();
        } else if (divisions == null) {
            Main_Warnings.divisionWarning();
        } else if (country == null) {
            Main_Warnings.countryWarning();
        } else {
            if(saveID == null || saveID.length() == 0) {
                DB_Customers.createCustomer(saveName, saveAddress, saveCode, savePhone, divisions.getDivisionId());
            } else {
                int id = Integer.parseInt(saveID);
                DB_Customers.modifyCustomer(id, saveName, saveAddress, saveCode, savePhone,
                        divisions.getDivisionId());
            }
        }
        CustomerTable.setItems(DB_Customers.getAllCustomers());
    }
/**The comboSelection sets the country and state combo boxes.
 * The divisionCombo box will be set according to what the countryCombo is set to.
 * @param actionEvent */
    public void comboSelection(ActionEvent actionEvent) {

        Country us = countryCombo.getItems().get(0);
        Country uk = countryCombo.getItems().get(1);
        Country c = countryCombo.getItems().get(2);
        if(countryCombo.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if(countryCombo.getSelectionModel().getSelectedItem().equals(us)) {
            divisionCombo.setItems(DB_Divisions.getStates());
        }
        if(countryCombo.getSelectionModel().getSelectedItem().equals(uk)) {
            divisionCombo.setItems(DB_Divisions.getUnitedKingdom());
        }
        if(countryCombo.getSelectionModel().getSelectedItem().equals(c)) {
            divisionCombo.setItems(DB_Divisions.getCanada());
        }
    }
/**The clearCustomerForm clears all info from the text fields and combo boxes.
 * Anything typed into the text fields and selected into combo boxes will be deleted.
 * @param actionEvent */
    public void clearCustomerForm(ActionEvent actionEvent) {
        idField.clear();
        nameField.clear();
        addressField.clear();
        phoneField.clear();
        codeField.clear();
        divisionCombo.getSelectionModel().clearSelection();
        countryCombo.getSelectionModel().clearSelection();
        CustomerTable.getSelectionModel().clearSelection();
    }
    /**The deleteCustomer method deletes a selected customer.
     * The selected customer will be deleted from the database and the table, if no appointments are associated with the selected customer.
     * @param actionEvent */
    public void deleteCustomer(ActionEvent actionEvent) throws SQLException {

        Customer selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();

        if(selectedCustomer == null) {
            Main_Warnings.selectionDeleteWarning();
        } else {
            DB_Appointments.getAllCustfromAppointments(selectedCustomer.getId(), selectedCustomer.getName());
            CustomerTable.setItems(DB_Customers.getAllCustomers());
        }
    }
/**The appointments method sends to appointments form.
 * The appointments method sends to appointment form.
 * @param actionEvent */
    public void appointments(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Appointments.fxml"));
        loader.load();

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
