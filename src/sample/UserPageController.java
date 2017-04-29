package sample;

import POJOs.Book;
import POJOs.Borrow;
import POJOs.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserPageController  implements Initializable{
    @FXML
    private TableView borrowTable;
    @FXML
    private TableColumn borrowTitleColumn;
    @FXML
    private TableColumn borrowDateColumn;
    @FXML
    private TableColumn returnDateColumn;
    @FXML
    private TableColumn lastDateColumn;
    @FXML
    private TextField author;
    @FXML
    private TextField title;
    @FXML
    private TableView bookTable;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn genreColumn;
    @FXML
    private TableColumn authorColumn;
    @FXML
    private TableColumn publisherColumn;
    @FXML
    private TableColumn pageColumn;
    @FXML
    private TableColumn dateColumn;
    @FXML
    private Label userSSN,userFirstName,userLastName,userBirthDate,userStreet,userZipCode,userUserName,userPassword,userCity;
    @FXML
    private TextField changePassword;
    @FXML
    private ComboBox<String> genreBox;





    userLoginModel ulm = new userLoginModel();

    @FXML
    private ObservableList<String> getGenreList(){
        ObservableList<Genre> list =ulm.getGenre();
        ObservableList<String> genreList = FXCollections.observableArrayList();
        genreList.add("");
        for(Genre genre : list){
            genreList.add(genre.getGenre());
        }

        return genreList;
    }

    @FXML
    private void searchRent(ActionEvent event) throws SQLException{
        String SSN[] = userSSN.getText().split(" : ");
        String argSNN = SSN[1];

        ObservableList list = ulm.searchBorrow(argSNN);
        System.out.println(list.size());
        borrowTable.setItems(list);
    }


    @FXML
    private void searchBook(ActionEvent event) throws SQLException {

        ObservableList list =  ulm.searchBook(title.getText(),author.getText(),genreBox.getSelectionModel().getSelectedItem().toString());
        System.out.println(list.size());

        bookTable.setItems(list);

    }

    @FXML
    private void SignOut(ActionEvent event){

       try {
           ((Node)event.getSource()).getScene().getWindow().hide();
           Stage primaryStage = new Stage();
           FXMLLoader loader = new FXMLLoader();
           Pane root = loader.load(getClass().getResource("userLoginPage.fxml").openStream());
           primaryStage.setTitle("");
           primaryStage.setScene(new Scene(root));
           primaryStage.setResizable(false);
           primaryStage.show();
       }catch (Exception e){
            e.printStackTrace();
       }
    }
    @FXML
    private void changePassword(ActionEvent event){
        //
        String SSN[] = userSSN.getText().split(" : ");
        int UserSSN = Integer.parseInt(SSN[1]);
        String password = changePassword.getText();
        System.out.println(SSN[1]);
        System.out.println(password);

        try {
            if(ulm.changePassword(UserSSN,password)){
                userPassword.setText("Password : "+changePassword.getText());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borrowTitleColumn.setCellValueFactory(new PropertyValueFactory<Borrow,String>("title"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<Borrow,String>("borrowDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<Borrow,String>("returnDate"));
        lastDateColumn.setCellValueFactory(new PropertyValueFactory<Borrow,String >("lastDate"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Genre"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Publisher"));
        pageColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("PageNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("PublishDate"));
        userSSN.setText(userSSN.getText()+" "+userLoginModel.user.getSsn());
        userFirstName.setText(userFirstName.getText()+" "+userLoginModel.user.getFirstName());
        userLastName.setText(userLastName.getText()+" "+userLoginModel.user.getLastName());
        userBirthDate.setText(userBirthDate.getText()+" "+userLoginModel.user.getBirthDate().toString());
        userStreet.setText(userStreet.getText()+" "+userLoginModel.user.getStreetName());
        userZipCode.setText(userZipCode.getText()+" "+userLoginModel.user.getZipCode());
        userCity.setText(userCity.getText()+" "+userLoginModel.user.getCity());
        userUserName.setText(userUserName.getText()+" "+userLoginModel.user.getUserName());
        userPassword.setText(userPassword.getText()+" "+userLoginModel.user.getPass());
        genreBox.setItems(getGenreList());

        genreBox.getSelectionModel().selectFirst();


    }
}
