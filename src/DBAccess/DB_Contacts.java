package DBAccess;

import Helper.JDBC;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Contacts {

    public static ObservableList<Contacts> getAllContacts() {

        ObservableList<Contacts> clist = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");

                Contacts C = new Contacts(contactID, contactName, email);
                clist.add(C);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  clist;
    }
}
