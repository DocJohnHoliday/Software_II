package DBAccess;

import Helper.JDBC;
import Model.Customer;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Divisions {

    public static ObservableList<Divisions> getDivision() {

        ObservableList<Divisions> dlist = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Division_ID, Division, Country FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                String country = rs.getString("Country");

                Divisions D = new Divisions(divisionID, division, country);
                dlist.add(D);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  dlist;
    }
}
