package Messages;

import DBAccess.DB_Appointments;
import DBAccess.DB_Customers;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.time.ZonedDateTime;

public class Appointment_Warnings {

    public static void fieldsNullWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Incomplete");
        alert.setContentText("All text fields must be filled");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void contactWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a contact");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void userWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select user that has added appointment");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void timeWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a start hour/start minute and end hour/end minute");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void customerWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a customer");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void selectionDeleteWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a appointment to delete");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void appointmentDeleted(int appointmentID, String type) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DELETED");
        alert.setContentText("Appointment ID [" + appointmentID + "] " + " TYPE [" + type + "] has been deleted.");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    public static void deleteConfirmation(int appointmentID, String type) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete?");
        alert.setContentText("Are you sure you want to delete appointment ID [" + appointmentID + "] " + " TYPE [" + type + "] ?");
        alert.showAndWait().ifPresent((btnType) -> {
            if (btnType == ButtonType.OK) {
                DB_Appointments.deleteAppointment(appointmentID);

            } else if (btnType == ButtonType.CANCEL) {
                //
            }
        });
    }

    public static void upcomingAppointmentWarning(ZonedDateTime start, int id) {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Appointment coming up within 15 minutes");
        alert.setContentText("Appointment ID[" + id + " ] will start at " + start);
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void startAppointmentWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Before or after business hours");
        alert.setContentText("The appointment start time must be after 8:00AM EST and before 10:00PM EST");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void endAppointmentWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Before or after business hours");
        alert.setContentText("The appointment end time must be after 8:00AM EST and before 10:00PM EST");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void weekendWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Before or after business hours");
        alert.setContentText("The appointment cannot be on weekends");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    private static void clearDialogOptionSelections() {
    }
}
