package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminLoginPageController implements Initializable {

    @FXML
    private TextField adminUserName;
    @FXML
    private PasswordField adminPassword;
    @FXML
    private Label isConnected;


    adminLoginModel adminLoginModel = new adminLoginModel();


    @FXML
    private void adminLogin(ActionEvent event) throws IOException {
        try {
            if(adminLoginModel.isLogin(adminUserName.getText(),adminPassword.getText())){
                isConnected.setText("Username and password are correct.");
                System.out.println("Admin Connected");

                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                TabPane root = loader.load(getClass().getResource("adminPage.fxml").openStream());
                primaryStage.setTitle("");
                primaryStage.setScene(new Scene(root));
                primaryStage.setResizable(false);
                primaryStage.show();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
