package Controller;

import DBAccess.DB_Customers;
import DBAccess.DB_Divisions;
import Model.Country;
import Model.Customer;

import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;

import java.util.ResourceBundle;



public class Main_Controller implements Initializable{

    //Customer Table
    public TableView CustomerTable;
    public TableColumn IDCol;
    public TableColumn NameCol;
    public TableColumn AddressCol;
    public TableColumn PostalCol;
    public TableColumn PhoneCol;
    public TableColumn DivCol;
    public TableColumn CountryCol;

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

    private Customer customerToModify = null;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        CustomerTable.setItems(DB_Customers.getAllCustomers());

        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        PostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        PhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        DivCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        CountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        
    }

    public void deleteCustomer(ActionEvent actionEvent) {
    }

    public void updateCustomer(ActionEvent actionEvent) {
    }

    public void appointments(ActionEvent actionEvent) {
    }

    public void saveCustomer(ActionEvent actionEvent) {

        String saveName = nameField.getText();
        String saveAddress = addressField.getText();
        String saveCode = codeField.getText();
        String savePhone = phoneField.getText();
        Divisions divisions = divisionCombo.getValue();
        Country country = countryCombo.getValue();

        if(divisions == null || country == null) {
            return;
        }

        if(customerToModify == null) {
            DB_Customers.createCustomer(saveName, saveAddress, saveCode, savePhone, divisions.getDivisionId(), divisions.getDivision(), country.getCountryName());
        } else {
            DB_Customers.modifyCustomer();
        }

        CustomerTable.setItems(DB_Customers.getAllCustomers());
    }

    public void clearCustomerForm(ActionEvent actionEvent) {
    }
}
