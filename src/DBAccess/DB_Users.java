package DBAccess;

import Helper.JDBC;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DB_Users {

    public static boolean loginCheck(String userName, String userPassword) {

        try {
            String sql = "SELECT * FROM users WHERE User_Name='" + userName + "' and Password='" + userPassword + "'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ObservableList<User> getAllUser() {

        ObservableList<User> ulist = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM users";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");

                User U = new User(userID, userName, password);
                ulist.add(U);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  ulist;
    }
}