package Messages;

import javafx.scene.control.Alert;

public class Report_Warning {

    public static void typeWarning() {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Make Selection");
        alert.setContentText("You must select a Apt. Type");
        alert.showAndWait().ifPresent((btnType) -> {
            clearDialogOptionSelections();
        });
    }

    private static void clearDialogOptionSelections() {
    }
}
