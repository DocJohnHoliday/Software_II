package Messages;

import javafx.scene.control.Alert;

import java.util.Locale;
/**This class shows all warning messages for the login controller.
 * All methods in this class use a lambda expression.
 * These lambda expression allow for condensed and more legible code. */
public class Login_Warnings {

/**This method shows a message for incorrect login in login controller.
 * This method can be shown in either French or English depending on computer settings. */
    public static void incorrectLogin() {

        if (Locale.getDefault().getLanguage().equals("fr")) {

            Locale.setDefault(Locale.CANADA_FRENCH);
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("avertissement");
            alert.setContentText("L'identifiant ou le mot de passe est incorrect!");
            alert.showAndWait().ifPresent((btnType) -> {
                clearDialogOptionSelections();
            });
        } else {

            Locale.setDefault(Locale.ENGLISH);
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Username or Password is incorrect!");
            alert.showAndWait().ifPresent((btnType) -> {
                clearDialogOptionSelections();

            });
        }
    }
/**his method shows warning dialog for null fields in login controller. */
    public static void nullLogin() {

        if (Locale.getDefault().getLanguage().equals("fr")) {

            Locale.setDefault(Locale.CANADA_FRENCH);
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("avertissement");
            alert.setContentText("Le nom d'utilisateur et le mot de passe doivent être saisis!");
            alert.showAndWait().ifPresent((btnType) -> {
                clearDialogOptionSelections();
            });

        } else {

            Locale.setDefault(Locale.ENGLISH);
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Username and Password must be entered!");
            alert.showAndWait().ifPresent((btnType) -> {
                clearDialogOptionSelections();
            });
        }

    }
    /**An empty method for dialog boxes. */
    private static void clearDialogOptionSelections() {
    }

}
