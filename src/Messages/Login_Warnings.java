package Messages;

import javafx.scene.control.Alert;

import java.util.Locale;

public class Login_Warnings {

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

    private static void clearDialogOptionSelections() {
    }

}
