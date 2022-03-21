package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField userPassword;
    public TextField name;
    public TextField area;
    public Button logIn;
    Stage stage;
    Parent scene;

    public TextField userName;
    public TextField password;
    public TextField location;

    public Button loginIn;

    public Button loginButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginButton(ActionEvent actionEvent) {
    }
}