package Model;
/**This class creates a contact object as well as getters. */
public class Contacts {

    private int contactID;
    private String contactName;
    private String email;
/**This method constructs a contact object
 * @param contactName Contact Name.
 * @param contactID Contact ID.
 * @param email Contact email address. */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }
    /**Getter for Contact ID.
     * Gets the Contact ID.
     * @return contactID.*/
    public int getContactID() {
        return contactID;
    }
    /**Getter for Contact Name.
     * Gets the Contact Name.
     * @return contactName.*/
    public String getContactName() {
        return contactName;
    }
    /**Getter for Contact Email.
     * Gets the Contact Email.
     * @return email.*/
    public String getEmail() {
        return email;
    }
/**This method returns a string used for formatting the combo boxes.
 * @return String*/
    @Override
    public String toString() {
        return ("[" + getContactID() + "] " + getContactName());
    }
}
