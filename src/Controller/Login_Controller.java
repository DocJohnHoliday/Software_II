package Controller;

import DBAccess.DBUsers;
import Model.User;
import Warnings.Login_Warnings;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class Login_Controller implements Initializable {
    Stage stage;
    Parent scene;

    public Button logIn;

    public TextField Name;
    public TextField Password;
    public TextField Location;


    public void loginButton(ActionEvent actionEvent) throws SQLException, IOException {

        String textName = Name.getText();
        String textPassword = Password.getText();

                ObservableList<User> userList = DBUsers.getAllUsers();

                for (User U : userList) {
                   if(textName == null || textName.length() == 0 ||
                           textPassword == null || textPassword.length() == 0) {
                       Login_Warnings.nullLogin();
                   } else {

                       if (U.getName().equals(textName) && U.getPassword().equals(textPassword)) {

                           FXMLLoader loader = new FXMLLoader();
                           loader.setLocation(getClass().getResource("/view/Main_Form.fxml"));
                           loader.load();

                           stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                           Parent scene = loader.getRoot();
                           stage.setScene(new Scene(scene));
                           stage.show();

                       } else {
                           Login_Warnings.incorrectLogin();
                       }
                   }

                }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}