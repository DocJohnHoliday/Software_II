package DBAccess;

import Helper.JDBC;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DBUsers {

    public static ObservableList<User> getAllUsers() throws SQLException {

        ObservableList<User> uList = FXCollections.observableArrayList();

        try{

            String sql = "SELECT * from users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String userPassword = rs.getString("Password");
                User u = new User(userId, userName, userPassword);
                uList.add(u);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return uList;
    }

}
