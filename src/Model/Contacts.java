package Model;

public class Contacts {

    private int contactID;
    private String contactName;
    private String email;

    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    public int getContactID() {
        return contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return ("[" + getContactID() + "] " + getContactName());
    }
}
