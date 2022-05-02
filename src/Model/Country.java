package Model;
/**This class creates a Country object as well as getters. */
public class Country {

    private int countryId;
    private String countryName;
/**This method constructs a Country object.
 * @param countryId Country ID.
 * @param countryName Country Name. */
    public Country(int countryId, String countryName) {

        this.countryId = countryId;
        this.countryName = countryName;
    }
    /**Getter for Country ID.
     * Gets the Country ID.
     * @return countryId.*/
    public int getCountryId() {
        return countryId;
    }
    /**Getter for Country Name.
     * Gets the Country Name.
     * @return countryName.*/
    public String getCountryName() {
        return countryName;
    }
    /**This method returns a string used for formatting the combo boxes.
     * @return String*/
    @Override
    public String toString() {
        return ("[" + countryId + "] " + countryName);
    }

}
