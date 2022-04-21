package Model;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class Appointments {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String contactName;
    private String type;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private int customerID;
    private int userID;

    public Appointments(int appointmentID, String title, String description, String location, String contactName,
                        String type, ZonedDateTime start, ZonedDateTime end, int customerID, int userID) {

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

    public ZonedDateTime getStart() {
        return start;
    }

    public ZonedDateTime getEnd() {
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
