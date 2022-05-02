package Messages;

import DBAccess.DB_Customers;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**This class shows all warning messages for the Main_Form controller also known as customer controller.
 * All methods in this class use a lambda expression.
 * These lambda expression allow for condensed and more legible code. */
public class Main_Warnings {
    /**This method shows warning dialog for null fields in main form controller.
     * lambda expression show alert and allow button OK to confirm*/
    public static void fieldsNullWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Incomplete");
        alert.setContentText("All text fields must be filled");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for null fields in main form controller.
     * lambda expression show alert and allow button OK to confirm*/
    public static void nullUpdate() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a customer to update");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for null fields in main form controller.
     * lambda expression show alert and allow button OK to confirm*/
    public static void countryWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a country");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for null fields in main form controller.
     * lambda expression show alert and allow button OK to confirm*/
    public static void divisionWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a division");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for customer unable to be deleted in main form controller.
     * lambda expression show alert and allow button OK to confirm*/
    public static void cannotDeleteWarning(int customerID, String customerName) {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Customer cannot be deleted");
        alert.setContentText("Customer [" + customerID + "] " + customerName + " has an appointment. All associated appointments must be deleted first.");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for null fields in main form controller.
     * lambda expression show alert and allow button OK to confirm*/
    public static void selectionDeleteWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a customer to delete");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows information dialog for customer deleted in main form controller.
     * lambda expression show alert and allow button OK to confirm*/
    public static void customerDeleted(int unluckyCustomerID, String unluckyCustomerName) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DELETED");
        alert.setContentText("Customer [" + unluckyCustomerID + "] " + unluckyCustomerName + " has been deleted.");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows confirmation for deleting customer in main form controller.
     * If-else statement in lambda expression allows for confirmation of deleting customer, or cancel customer termination. */
    public static void deleteConfirmation(int unluckyCustomerID, String unluckyCustomerName) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete?");
        alert.setContentText("Are you sure you want to delete [" + unluckyCustomerID + "] " + unluckyCustomerName + "?");
        alert.showAndWait().ifPresent((btnType) -> {
            if (btnType == ButtonType.OK) {
                DB_Customers.delete(unluckyCustomerID);
                customerDeleted(unluckyCustomerID, unluckyCustomerName);

            } else if (btnType == ButtonType.CANCEL) {
                //
            }
        });
    }
    /**An empty method for dialog boxes. */
    private static void clearDialogOptionSelections() {
    }
}
