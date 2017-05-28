package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class userLoginPageController implements Initializable{

    @FXML
    private TextField TxtUserName;
    @FXML
    private Label isConnected;
    @FXML
    private PasswordField TxtPassword;


    public userLoginModel userLoginModel = new userLoginModel();

    @FXML
    private void userLogin(ActionEvent event) throws IOException {
        try {
            if(userLoginModel.isLogin(TxtUserName.getText(),TxtPassword.getText())){
                isConnected.setText("Username and password are correct.");
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("UserPage.fxml").openStream());
                primaryStage.setTitle("");
                primaryStage.setScene(new Scene(root));
                primaryStage.setResizable(false);
                primaryStage.show();
            }
            else{
                isConnected.setText("Username or password are not correct.");
            }
        } catch (SQLException e) {
            isConnected.setText("Username or password are not correct.");
            e.printStackTrace();
        }

    }
    @FXML
    private void switchAdmin(KeyEvent event) {
        TxtUserName.setOnKeyPressed(event1 ->{
          switch (event1.getCode()){
              case F9:
                  System.out.println("Admin Page Opened");

                  try {
                      Parent adminPageParent = FXMLLoader.load(getClass().getResource("adminLoginPage.fxml"));
                      Scene adminPageScene = new Scene(adminPageParent);
                      Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                      appStage.hide();
                      appStage.setScene(adminPageScene);
                      appStage.show();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
          }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(userLoginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else {
            isConnected.setText("Not Connected");
        }
    }


}
