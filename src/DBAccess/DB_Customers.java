package DBAccess;

import Helper.JDBC;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This class controls communication between customers in the database and the controllers.*/
public class DB_Customers {
/**The getAllCustomers method returns all the customers.
 * This method returns all customers from the database.
 * @return cList*/
    public static ObservableList<Customer> getAllCustomers() {

        ObservableList<Customer> clist = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_code, Phone, Division, Country " +
                    "FROM customers, first_level_divisions, countries WHERE customers.Division_ID = " +
                    "first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID";
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
/**This method creates a customer.
 * The createCustomer method creates a customer in the database.
 * @param name Customer Name.
 * @param address Customer Address.
 * @param code Customer postal code.
 * @param division Customer first level division.
 * @param phone Customer Phone #. */
    public static void createCustomer(String name, String address, String code,
                                      String phone, int division) {

        try {
            String sqlti = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)";

            PreparedStatement psti = JDBC.getConnection().prepareStatement(sqlti);
            psti.setString(1, name);
            psti.setString(2, address);
            psti.setString(3, code);
            psti.setString(4, phone);
            psti.setInt(5, division);

            psti.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
/**This method updates a current customer.
 * The modifyCustomer method updates a Customer already in the database.
 * @param phone Customer phone #.
 * @param address Customer address.
 * @param name Customer name.
 * @param id Customer ID.
 * @param divisionID Customer division ID.
 * @param postalCode Customer postal code. */
    public static void modifyCustomer(int id, String name, String address, String postalCode, String phone,
                                      int divisionID){

        try {
            String sqlti = "UPDATE customers set Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlti);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phone);
            ps.setInt(5, divisionID);
            ps.setInt(6, id);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
/**Deletes customer.
 * The delete method  deletes a customer from database.
 * @param customerID Customer ID. */
    public static void delete(int customerID) {

        try {
            String sqlti = "DELETE from customers WHERE Customer_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sqlti);
            ps.setInt(1, customerID);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
