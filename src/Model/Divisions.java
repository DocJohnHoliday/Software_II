package Model;

public class Divisions {

    private int divisionId;
    private String division;

    public Divisions(int divisionId, String division) {

        this.divisionId = divisionId;
        this.division = division;

    }


    public int getDivisionId() {
        return divisionId;
    }

    public String getDivision() {
        return division;
    }


    @Override
    public String toString() {
        return ("[" + divisionId + "] " + division);
    }

}
