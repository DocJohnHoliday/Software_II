package Messages;

import Controller.Main_Controller;
import DBAccess.DB_Customers;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Main_Warnings {

    public static void fieldsNullWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Incomplete");
        alert.setContentText("All text fields must be filled");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void nullUpdate() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a customer to update");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void countryWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a country");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void divisionWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a division");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void cannotDeleteWarning(int customerID, String customerName, int appointmentID) {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Customer cannot be deleted");
        alert.setContentText("Customer [" + customerID + "] " + customerName + " has an appointment. " +
                "Appointment ID [" + appointmentID + "] must be deleted before customer can be deleted.");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void selectionDeleteWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a customer to delete");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void customerDeleted(int unluckyCustomerID, String unluckyCustomerName) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DELETED");
        alert.setContentText("Customer [" + unluckyCustomerID + "] " + unluckyCustomerName + " has been deleted.");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    public static void deleteConfirmation(int unluckyCustomerID, String unluckyCustomerName) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete?");
        alert.setContentText("Are you sure you want to delete [" + unluckyCustomerID + "] " + unluckyCustomerName + "?");
        alert.showAndWait().ifPresent((btnType) -> {
            if (btnType == ButtonType.OK) {
                DB_Customers.delete(unluckyCustomerID);

            } else if (btnType == ButtonType.CANCEL) {
                //
            }
        });
    }

    private static void clearDialogOptionSelections() {
    }
}
