package Model;
/**This class creates a Division object as well as getters.*/
public class Divisions {

    private int divisionId;
    private String division;
/**This method constructs a division object.
 * @param division Division name.
 * @param divisionId Division ID. */
    public Divisions(int divisionId, String division) {

        this.divisionId = divisionId;
        this.division = division;

    }

    /**Getter for Division ID.
     * Gets the Division ID.
     * @return divisionId.*/
    public int getDivisionId() {
        return divisionId;
    }
    /**Getter for Division Name.
     * Gets the Division Name.
     * @return division.*/
    public String getDivision() {
        return division;
    }

    /**This method returns a string used for formatting the combo boxes.
     * @return String*/
    @Override
    public String toString() {
        return ("[" + divisionId + "] " + division);
    }

}
