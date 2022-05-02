package Model;
/**This class creates a Customer object as well as getters. */
public class Customer {

    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String division;
    private String country;
/**This method constructs a Customer object.
 * @param id Customer ID.
 * @param name Customer Name.
 * @param postalCode Customer Postal Code.
 * @param address Customer Address.
 * @param phone Customer Phone Number.
 * @param division Customer first level division.
 * @param country Customer country. */
    public Customer(int id, String name, String address, String postalCode, String phone, String division, String country) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.division = division;
        this.country = country;
    }
    /**Getter for Customer ID.
     * Gets the Customer ID.
     * @return id.*/
    public int getId() {
        return id;
    }
    /**Getter for Customer Name.
     * Gets the Customer Name.
     * @return name.*/
    public String getName() {
        return name;
    }
    /**Getter for Customer address.
     * Gets the Customer address.
     * @return address.*/
    public String getAddress() {
        return address;
    }
    /**Getter for Customer postal code.
     * Gets the Customer postal code.
     * @return postalCode.*/
    public String getPostalCode() {
        return postalCode;
    }
    /**Getter for Customer Phone number.
     * Gets the Customer Phone NUmber.
     * @return phone.*/
    public String getPhone() {
        return phone;
    }
    /**Getter for Customer Division.
     * Gets the Customer Division.
     * @return division.*/
    public String getDivision() {
        return division;
    }
    /**Getter for Customer Country.
     * Gets the Customer Country.
     * @return country.*/
    public String getCountry() {
        return country;
    }
    /**This method returns a string used for formatting the combo boxes.
     * @return String*/
    @Override
    public String toString() {
        return ("[" + getId() + "] " + getName());
    }
}
