package DBAccess;

import Helper.JDBC;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**This class controls communication between countries in the database and the controllers.*/
public class DB_Country {
/**The getCountries method returns all the countries from the database.
 * @return countryList*/
    public static ObservableList<Country> getCountries() {
        ObservableList<Country> countryList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Country_ID, Country FROM countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String country = rs.getString("Country");

                Country C = new Country(countryID, country);
                countryList.add(C);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  countryList;
    }

}
