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

            String sql = "SELECT Division_ID, Division FROM first_level_divisions, countries WHERE first_level_divisions.Country_ID = countries.Country_ID";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");

                Divisions D = new Divisions(divisionID, division);
                dlist.add(D);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  dlist;
    }

    public static ObservableList<Divisions> getStates() {

        ObservableList<Divisions> dlist = FXCollections.observableArrayList();

        try {

            String sql = "select Division_ID, Division from first_level_divisions where Country_ID = 1";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");

                Divisions D = new Divisions(divisionID, division);
                dlist.add(D);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  dlist;
    }

    public static ObservableList<Divisions> getUnitedKingdom() {

        ObservableList<Divisions> dlist = FXCollections.observableArrayList();

        try {

            String sql = "select Division_ID, Division from first_level_divisions where Country_ID = 2";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");

                Divisions D = new Divisions(divisionID, division);
                dlist.add(D);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  dlist;
    }

    public static ObservableList<Divisions> getCanada() {

        ObservableList<Divisions> dlist = FXCollections.observableArrayList();

        try {

            String sql = "select Division_ID, Division from first_level_divisions where Country_ID = 3";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String division = rs.getString("Division");

                Divisions D = new Divisions(divisionID, division);
                dlist.add(D);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  dlist;
    }
}
