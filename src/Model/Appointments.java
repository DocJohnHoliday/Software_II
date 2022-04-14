package Model;

import java.sql.Timestamp;

public class Appointments {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String contactName;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private int customerID;
    private int userID;

    public Appointments(int appointmentID, String title, String description, String location, String contactName,
                        String type, Timestamp start, Timestamp end, int customerID, int userID) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactName = contactName;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public String getContactName() {
        return contactName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getUserID() {
        return userID;
    }
}
