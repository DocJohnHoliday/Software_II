package Controller;

import DBAccess.DBUsers;
import Model.User;
import Warnings.Login_Warnings;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;



public class LoginController implements Initializable {
    Stage stage;
    Parent scene;

    public Button logIn;

    public TextField Name;
    public TextField Password;
    public TextField Location;

    public Button loginButton;

    public void loginButton(ActionEvent actionEvent) throws SQLException {

        String textName = Name.getText();
        String textPassword = Password.getText();

                ObservableList<User> userList = DBUsers.getAllUsers();

                for (User U : userList) {
                   if(textName == null || textName.length() == 0 ||
                           textPassword == null || textPassword.length() == 0) {
                       Login_Warnings.nullLogin();
                   } else {

                       if (U.getName().equals(textName) && U.getPassword().equals(textPassword)) {
                           System.out.println("Y");
                       } else {
                           Login_Warnings.incorrectLogin();
                       }
                   }

                }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}