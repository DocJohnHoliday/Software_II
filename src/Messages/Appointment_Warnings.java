package Messages;

import DBAccess.DB_Customers;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
                DB_Customers.delete(appointmentID);

            } else if (btnType == ButtonType.CANCEL) {
                //
            }
        });
    }

    private static void clearDialogOptionSelections() {
    }
}
