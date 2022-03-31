package Model;

public class Divisions {

    private int divisionId;
    private String division;
    private String country;

    public Divisions(int divisionId, String division, String country) {

        this.divisionId = divisionId;
        this.division = division;
        this.country = country;

    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivision() {
        return division;
    }

    public String getCountry() {
        return country;
    }

}
