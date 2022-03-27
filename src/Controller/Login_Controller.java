package Controller;

import Helper.JDBC;
import Messages.Login_Warnings;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class Login_Controller implements Initializable {
    Stage stage;
    Parent scene;

    public Button logIn;

    public TextField Name;
    public TextField Password;
    public Label Location;


    public void loginButton(ActionEvent actionEvent) throws SQLException, IOException {

        String textName = Name.getText();
        String textPassword = Password.getText();

        if (textName != null && textName.length() != 0 &&
                textPassword.length() != 0 && textPassword != null) {

            String sql = "SELECT * FROM users WHERE User_Name='" + textName + "' and Password='" + textPassword + "'";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
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
        } else {
            Login_Warnings.nullLogin();
        }
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}