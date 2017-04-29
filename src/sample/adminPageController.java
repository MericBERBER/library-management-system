package sample;


import POJOs.Author;
import POJOs.Book;
import POJOs.Borrow;
import POJOs.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class adminPageController implements Initializable {
    @FXML
    private TableView<Borrow> borrowTableView;
    @FXML
    private TableView<Author> authorTableView;
    @FXML
    private TableView<User> userTableView;
    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private TableColumn borrowSSN;
    @FXML
    private TableColumn borrowID;
    @FXML
    private TableColumn borrowDate;
    @FXML
    private TableColumn lastDate;
    @FXML
    private TableColumn returnDate;
    @FXML
    private TableColumn A_idColumn;
    @FXML
    private TableColumn A_FirstNameColumn;
    @FXML
    private TableColumn A_LastNameColumn;
    @FXML
    private TableColumn A_dateColumn;
    @FXML
    private TableColumn A_cityColumn;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn genreColumn;
    @FXML
    private TableColumn pageColumn;
    @FXML
    private TableColumn publisherColumn;
    @FXML
    private TableColumn pieceColumn;
    @FXML
    private TableColumn pDateColumn;
    @FXML
    private TableColumn authorColumn;
    @FXML
    private TableColumn ssnColumn;
    @FXML
    private TableColumn firstColumn;
    @FXML
    private TableColumn lastColumn;
    @FXML
    private TableColumn birthColumn;
    @FXML
    private TableColumn streetColumn;
    @FXML
    private TableColumn zipColumn;
    @FXML
    private TableColumn cityColumn;
    @FXML
    private TableColumn userColumn;
    @FXML
    private TableColumn passColumn;
    @FXML
    private TextField b_ssn;
    @FXML
    private TextField b_id;
    @FXML
    private TextField b_bDate;
    @FXML
    private TextField b_rDate;
    @FXML
    private TextField b_lDate;
    @FXML
    private TextField a_id;
    @FXML
    private TextField a_firstName;
    @FXML
    private TextField a_lastName;
    @FXML
    private TextField a_birthDate;
    @FXML
    private TextField a_city;
    @FXML
    private TextField bookID;
    @FXML
    private TextField title;
    @FXML
    private TextField genre;
    @FXML
    private TextField pageNumber;
    @FXML
    private TextField publisher;
    @FXML
    private TextField piece;
    @FXML
    private TextField publishedDate;
    @FXML
    private TextField authorID;
    @FXML
    private TextField ssn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField birthDate;
    @FXML
    private TextField streetName;
    @FXML
    private TextField zipCode;
    @FXML
    private TextField city;
    @FXML
    private TextField userName;
    @FXML
    private TextField pass;



    adminLoginModel adminLoginModel = new adminLoginModel();

    @FXML
    private void updateBorrow() throws SQLException {
        Borrow borrow = borrowTableView.getSelectionModel().getSelectedItem();
        adminLoginModel.updateBorrow(b_bDate.getText(),
                b_rDate.getText(),
                b_lDate.getText(),
                borrow.getSSN(),
                borrow.getBookID());
        getBorrow();
    }
    @FXML
    private void addBorrow() throws SQLException {
        adminLoginModel.addBorrow(b_bDate.getText(),
                                b_rDate.getText(),
                                b_lDate.getText(),
                                b_ssn.getText(),
                                b_id.getText());
        getBorrow();
    }
    @FXML
    private void deleteBorrow() throws SQLException {
        Borrow borrow = borrowTableView.getSelectionModel().getSelectedItem();
        adminLoginModel.deleteBorrow(borrow.getBorrowDate(),borrow.getReturnDate(),borrow.getLastDate(),borrow.getSSN(),borrow.getBookID());
        getBorrow();
    }
    @FXML
    private void searchBorrow() throws SQLException {
        ObservableList<Borrow> list =adminLoginModel.searchBorrow(  b_bDate.getText(),
                                                                    b_rDate.getText(),
                                                                    b_lDate.getText(),
                                                                    b_ssn.getText(),
                                                                    b_id.getText());
        borrowTableView.setItems(list);
    }
    @FXML
    private void getBorrow() throws SQLException {
        ObservableList<Borrow> list = adminLoginModel.getBorrow();
        borrowTableView.setItems(list);
    }
    @FXML
    private void updateAuthor() throws SQLException {
        Author author = authorTableView.getSelectionModel().getSelectedItem();
        adminLoginModel.updateAuthor( author.getAuthorId(),
                a_firstName.getText(),
                a_lastName.getText(),
                a_birthDate.getText(),
                a_city.getText());

        getAuthor();
    }
    @FXML
    private void deleteAuthor() throws SQLException {
        Author author = authorTableView.getSelectionModel().getSelectedItem();
        adminLoginModel.deleteAuthor(author.getAuthorId());
        getAuthor();
    }

    @FXML
    private void searchAuthor() throws SQLException {

        ObservableList<Author> list = adminLoginModel.searchAuthor( a_id.getText(),
                                                                    a_firstName.getText(),
                                                                    a_lastName.getText(),
                                                                    a_birthDate.getText(),
                                                                    a_city.getText());

        authorTableView.setItems(list);
    }
    @FXML
    private void addAuthor() throws SQLException {
        adminLoginModel.addAuthor(  a_firstName.getText(),
                                    a_lastName.getText(),
                                    a_birthDate.getText(),
                                    a_city.getText());
        getAuthor();
    }

    @FXML
    private void getAuthor() throws SQLException {
        ObservableList<Author> list = adminLoginModel.getAuthor();
        authorTableView.setItems(list);
    }

    @FXML
    private void searchBook() throws SQLException {
       ObservableList<Book> list =adminLoginModel.searchBook(bookID.getText(),
                                    title.getText(),
                                    genre.getText(),
                                   pageNumber.getText(),
                                    publisher.getText(),
                                    piece.getText(),
                                    publishedDate.getText(),
                                    authorID.getText());
       bookTableView.setItems(list);
    }

    @FXML
    private void addBook() throws SQLException {
        adminLoginModel.addBook(title.getText(),
                                genre.getText(),
                                Integer.parseInt(pageNumber.getText()),
                                publisher.getText(),
                                piece.getText(),
                                publishedDate.getText(),
                                authorID.getText());
        getBooks();
    }

    @FXML
    private void updateBook() throws SQLException{
        Book book = bookTableView.getSelectionModel().getSelectedItem();
        adminLoginModel.updateBook(book.getBookId(),
                                    title.getText(),
                                    genre.getText(),
                                    pageNumber.getText(),
                                    publisher.getText(),
                                    piece.getText(),
                                    publishedDate.getText(),
                                    authorID.getText());
        getBooks();
    }

    @FXML
    private void deleteBook() throws SQLException{

        Book book = bookTableView.getSelectionModel().getSelectedItem();
        adminLoginModel.deleteBook(book.getBookId());
        getBooks();
    }

    @FXML
    private void getBooks() throws SQLException{
        ObservableList<Book> list = adminLoginModel.getBook();
        bookTableView.setItems(list);
    }
    @FXML
    private void getUsers() throws SQLException {
        ObservableList<User> list = adminLoginModel.getUser();
        userTableView.setItems(list);


    }
    @FXML
    private void updateUser() throws SQLException{
        User user = userTableView.getSelectionModel().getSelectedItem();
        adminLoginModel.updateUser( user.getSsn(),
                                    firstName.getText(),
                                    lastName.getText(),
                                    birthDate.getText(),
                                    streetName.getText(),
                                    zipCode.getText(),
                                    city.getText(),
                                    userName.getText(),
                                    pass.getText());

        getUsers();

    }
    @FXML
    private void searchUsers() throws SQLException{
        ObservableList<User> list = adminLoginModel.searchUser( ssn.getText(),
                                                                firstName.getText(),
                                                                lastName.getText(),
                                                                birthDate.getText(),
                                                                streetName.getText(),
                                                                zipCode.getText(),
                                                                city.getText(),
                                                                userName.getText(),
                                                                pass.getText());
        userTableView.setItems(list);
    }
    @FXML
    private void deleteUser() throws SQLException{
        User user = userTableView.getSelectionModel().getSelectedItem();
        System.out.println(user.getSsn());
        adminLoginModel.deleteUser(user.getSsn());
        getUsers();

    }
    @FXML
    private void addUser() throws SQLException {
        adminLoginModel.addUser(ssn.getText(),
                                firstName.getText(),
                                lastName.getText(),
                                birthDate.getText(),
                                streetName.getText(),
                                zipCode.getText(),
                                city.getText(),
                                userName.getText(),
                                pass.getText());
        getUsers();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borrowSSN.setCellValueFactory(new PropertyValueFactory<Borrow,String>("SSN"));
        borrowID.setCellValueFactory(new PropertyValueFactory<Borrow,String>("bookID"));
        borrowDate.setCellValueFactory(new PropertyValueFactory<Borrow,String>("borrowDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<Borrow,String>("returnDate"));
        lastDate.setCellValueFactory(new PropertyValueFactory<Borrow,String>("lastDate"));
        A_idColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("authorId"));
        A_FirstNameColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("firstName"));
        A_LastNameColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("lastName"));
        A_dateColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("birthDate"));
        A_cityColumn.setCellValueFactory(new PropertyValueFactory<Author,String>("city"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("bookId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Title"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Genre"));
        pageColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("PageNumber"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Publisher"));
        pieceColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("piece"));
        pDateColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("PublishDate"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Author"));
        ssnColumn.setCellValueFactory(new PropertyValueFactory<User,String>("ssn"));
        firstColumn.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
        lastColumn.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<User,String>("birthDate"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<User,String>("streetName"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<User,String>("zipCode"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<User,String>("city"));
        userColumn.setCellValueFactory(new PropertyValueFactory<User,String>("userName"));
        passColumn.setCellValueFactory(new PropertyValueFactory<User,String>("pass"));
        try {
            getBorrow();
            getAuthor();
            getUsers();
            getBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
