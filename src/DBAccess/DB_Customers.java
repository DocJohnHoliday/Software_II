package DBAccess;

import Helper.JDBC;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Customers {

    public static ObservableList<Customer> getAllCustomers() {

        ObservableList<Customer> clist = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_code, Phone, Division, Country " +
                    "FROM customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostal = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                String division = rs.getString("Division");
                String country = rs.getString("Country");

                Customer C = new Customer(customerID, customerName, customerAddress, customerPostal, customerPhone, division, country);
                clist.add(C);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  clist;
    }

    public static void createCustomer(String name, String address, String postalCode, String phone, int divisionID, String division, String country) {

    }

    public static void modifyCustomer(){

    }
}
