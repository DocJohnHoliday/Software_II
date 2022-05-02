package Messages;

import javafx.scene.control.Alert;
/**This class shows all warning messages for the report controller.
 * All methods in this class use a lambda expression.
 * These lambda expression allow for condensed and more legible code. */

public class Report_Warning {
    /**This method shows warning dialog for null fields in report controller.
     * lambda expression show alert and allow button OK to confirm*/
    public static void typeWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a Apt. Type");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**This method shows warning dialog for no daily appointments in report controller.
     * lambda expression show alert and allow button OK to confirm*/
    public static void noAptWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Appointments Today!");
        alert.setContentText("There are no Appointments today");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }
    /**An empty method for dialog boxes. */
    private static void clearDialogOptionSelections() {
    }
}
