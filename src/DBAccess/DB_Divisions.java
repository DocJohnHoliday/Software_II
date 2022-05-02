package DBAccess;

import Helper.JDBC;
import Model.Divisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This class communicates between First_Level_Divisions in the database and the controllers. */
public class DB_Divisions {
/**Gets all first level divisions from database.
 * The getDivision method gets all the first level divisions from the database.
 * @return dList. */
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
/**Gets all the USA's states.
 * The getStates method gets all the states from the database.
 * @return dlist*/
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
/**Gets areas from the UK.
 * The getUnitedKingdom gets all the areas from the UK.
 * @return dlist. */
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
/**Gets all territories and provinces from Canada.
 * The getCanada method gets all the first level division info for Canada.
 * @return dlist.*/
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
