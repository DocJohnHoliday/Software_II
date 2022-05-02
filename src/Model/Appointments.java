package Model;

import java.time.ZonedDateTime;
/**This class creates an appointment object as well as getters. */
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
/**This method constructs an appointment object.
 * @param customerID Customer ID.
 * @param appointmentID Appointment ID.
 * @param title Appointment Title.
 * @param description Appointment Description.
 * @param end Appointment end time and date.
 * @param location Appointment location.
 *  @param start Appointment start time and date.
 *  @param type Appointment type.
 *  @param userID Appointment user ID.
 *  @param contactName Appointment contact name. */
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
    /**This method constructs an appointment object.
     * @param customerID Customer ID.
     * @param appointmentID Appointment ID.
     * @param title Appointment Title.
     * @param description Appointment Description.
     * @param end Appointment end time and date.
     * @param location Appointment location.
     *  @param start Appointment start time and date.
     *  @param type Appointment type.
     *  @param userID Appointment user ID. */
    public Appointments(int appointmentID, String title, String description, String location,
                        String type, ZonedDateTime start, ZonedDateTime end, int customerID, int userID) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
    }
    /**This method constructs an appointment object.
     *  @param type Appointment type. */
    public Appointments(String type) {
        this.type = type;
    }
    /**Getter for appointment ID.
     * Gets the appointment ID.
     * @return appointmentID.*/
    public int getAppointmentID() {
        return appointmentID;
    }
    /**Getter for appointment title.
     * Gets the appointment Title.
     * @return title.*/
    public String getTitle() {
        return title;
    }
    /**Getter for appointment Description.
     * Gets the appointment Description.
     * @return description.*/
    public String getDescription() {
        return description;
    }
    /**Getter for appointment Location.
     * Gets the appointment Location.
     * @return location.*/
    public String getLocation() {
        return location;
    }
    /**Getter for appointment Type.
     * Gets the appointment Type.
     * @return type.*/
    public String getType() {
        return type;
    }
    /**Getter for appointment Start time and date.
     * Gets the appointment Start time and date.
     * @return start.*/
    public ZonedDateTime getStart() {
        return start;
    }
    /**Getter for appointment End time and date.
     * Gets the appointment End time and date.
     * @return end.*/
    public ZonedDateTime getEnd() {
        return end;
    }
    /**Getter for appointment Contact Name.
     * Gets the appointment Contact Name.
     * @return contactName.*/
    public String getContactName() {
        return contactName;
    }
    /**Getter for appointment Customer ID.
     * Gets the appointment Customer ID.
     * @return customerID.*/
    public int getCustomerID() {
        return customerID;
    }
    /**Getter for appointment User ID.
     * Gets the appointment User ID.
     * @return userID.*/
    public int getUserID() {
        return userID;
    }
}
