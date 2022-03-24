package Controller;

import DBAccess.DBUsers;
import Model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;




public class LoginController implements Initializable {
    Stage stage;
    Parent scene;

    public TextField password;
    public TextField name;
    public TextField area;
    public Button logIn;

    public Button loginButton;

    public void loginButton(ActionEvent actionEvent) throws SQLException {

        ObservableList<User> userList = DBUsers.getAllUsers();

        for(User U : userList) {
            System.out.println("User ID : " + U.getId());
            System.out.println("User Name : " + U.getName());
            System.out.println("User Password : " + U.getPassword());
        }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}