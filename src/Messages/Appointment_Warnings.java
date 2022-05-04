package Messages;

import DBAccess.DB_Appointments;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.ZonedDateTime;
/**This class shows all warning messages for the Appointments controller.
 * All methods in this class use a lambda expression.
 * These lambda expressions allow for condensed and more legible code. */
public class Appointment_Warnings {
/**This method shows warning dialog for null fields in Appointments controller.
 * lambda expression show alert and allow button OK to confirm*/
    public static void fieldsNullWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Incomplete");
        alert.setContentText("All text fields must be filled");
        //Lambda expression
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for null combo in Appointments controller. */
    public static void contactWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a contact");
        //Lambda expression
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for null fields in Appointments controller. */
    public static void userWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select user that has added appointment");
        //Lambda expression
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for null fields in Appointments controller. */
    public static void timeWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a start hour/start minute and end hour/end minute");
        //Lambda expression
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for null fields in Appointments controller. */
    public static void customerWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a customer");
        //Lambda expression
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for null fields in Appointments controller. */
    public static void selectionDeleteWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a appointment to delete");
        //Lambda expression
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for information on deleted appointment in Appointments controller. */
    public static void appointmentDeleted(int appointmentID, String type) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DELETED");
        alert.setContentText("Appointment ID [" + appointmentID + "] " + " TYPE [" + type + "] has been deleted.");
        //Lambda expression
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows confirmation for deleting an Appointment.
     * If-else statement in lambda expression allows for confirmation of deleting appointment, or cancel appointment termination. */
    public static void deleteConfirmation(int appointmentID, String type) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete?");
        alert.setContentText("Are you sure you want to delete appointment ID [" + appointmentID + "] " + " TYPE [" + type + "] ?");
        //Lambda expression #2, This lambda allowed for 42 lines of code to be replaced between DB_Appointments and Main_Controller
        alert.showAndWait().ifPresent((btnType) -> {
            if (btnType == ButtonType.OK) {
                DB_Appointments.deleteAppointment(appointmentID);

            } else if (btnType == ButtonType.CANCEL) {
                //
            }
        });
    }
    /**This method shows warning dialog for upcoming appointment in Appointments controller. */
    public static void upcomingAppointmentWarning(ZonedDateTime start, int id, long interval) {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Appointment coming up within 15 minutes");
        alert.setContentText("Appointment ID[" + id + " ] will start in " + interval + " minutes at " + start);
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for after business hours in Appointments controller. */
    public static void startAppointmentWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Before or after business hours");
        alert.setContentText("The appointment start time must be after 8:00AM EST and before 10:00PM EST");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for after business hours in Appointments controller. */
    public static void endAppointmentWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Before or after business hours");
        alert.setContentText("The appointment end time must be after 8:00AM EST and before 10:00PM EST");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for appointments that were tried to be scheduled on weekends in Appointments controller. */
    public static void weekendWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Before or after business hours");
        alert.setContentText("The appointment cannot be on weekends");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for appointments where user tried setting start time after end time in Appointments controller. */
    public static void startAfterWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Start time is after end time");
        alert.setContentText("The Apt start time must be before the Apt end time.");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for zero minute appointments in Appointments controller. */
    public static void startEqualsEndWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Start and end are same time");
        alert.setContentText("The Apt start time must be before the Apt end time. Apt can not begin and end at same time");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for overlapping appointments in Appointments controller. */
    public static void overlapWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Overlapping customer appointments");
        alert.setContentText("This is an overlapping appointments");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for no monthly appointments in Appointments controller. */
    public static void noMonthlyAptWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("There are no Apts");
        alert.setContentText("There are no appointments this month.");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for appointments scheduled in the past in Appointments controller. */
    public static void pastWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Apt is in the past");
        alert.setContentText("Appointments can not be made in the past.");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**An empty method for dialog boxes. */
    private static void clearDialogOptionSelections() {
    }
}
