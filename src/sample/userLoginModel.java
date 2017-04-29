package sample;

import POJOs.Book;
import POJOs.Borrow;
import POJOs.Genre;
import POJOs.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class userLoginModel {
    Connection connection;
    public static User user;
    public userLoginModel(){

        connection = MySqlConnector.Connect();
        if(connection==null) System.exit(1);
    }

    public boolean isDbConnected()  {
        try {
            return  !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public ObservableList<Genre> getGenre(){
        ObservableList<Genre> list = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT distinct genre from book";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Genre genre = new Genre(resultSet.getString(1));
                list.add(genre);
            }

        }catch (Exception e){

        }
        return list;
    }

    public ObservableList<Borrow>  searchBorrow(String SSN) throws SQLException {

        ObservableList list = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT T.title, T.borrowDate, T.lastDate, T.returnDate \n" +
                "FROM(SELECT book.title, borrow.borrowDate, borrow.lastDate, borrow.returnDate, borrow.SSN\n" +
                "FROM book\n" +
                "INNER JOIN borrow\n" +
                "ON book.bookID = borrow.bookID ) as T\n" +
                "Where T.SSN = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,SSN);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Borrow borrow = new Borrow();
                borrow.setTitle(resultSet.getString(1));
                borrow.setBorrowDate(resultSet.getDate(2));
                borrow.setLastDate(resultSet.getDate(3));
                borrow.setReturnDate(resultSet.getDate(4));
                list.add(borrow);
            }
            System.out.println("Found "+list.size()+" Rents.");
            preparedStatement.close();
            resultSet.close();
            return list;
    }
    public ObservableList<Book> searchBook(String title, String author, String genre) throws SQLException {

        Map<String, String> validParams = new HashMap<>();
        ObservableList list = FXCollections.observableArrayList();

        //Check values.
        if (!title.equals("")) {

            validParams.put("title", title);
        }
        if (!author.equals("")) {

            validParams.put("author", author);
        }
        if (!genre.equals("") && !genre.equals(null)) {

            validParams.put("genre", genre);
        }
        if(validParams.isEmpty()){
            // if no value is correct, return


        }
        //Build suitable query.
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("Select * FROM\n" +
                "(SELECT book.title as Title, book.genre as Genre, book.pageNumber as PageNumber, book.publisher as Publisher,book.publishedDate as PublishDate,CONCAT(author.firstName, ' ' , author.lastName) AS Author\n" +
                "FROM book\n" +
                "INNER JOIN\n" +
                "author\n" +
                "ON book.authorID = author.authorID) as T");
        boolean queryBuildStarted = false ;

        for(String key : validParams.keySet()){

            if(!queryBuildStarted){
                queryBuilder.append(" WHERE ");
                queryBuildStarted = true;
            }
            else{
                queryBuilder.append(" AND T.");

            }


            queryBuilder.append(key+" = ?");
        }


        PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
        //Construct preparedStatement
        int index = 1;

        for(String key : validParams.keySet()){

            statement.setString(index, validParams.get(key));
            index ++;
        }


        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            Book book = new Book();
            book.setTitle(rs.getString(1));
            book.setGenre(rs.getString(2));
            book.setPageNumber(rs.getInt(3));
            book.setPublisher(rs.getString(4));
            book.setPublishDate(rs.getDate(5));
            book.setAuthor(rs.getString(6));
            list.add(book);

        }
        System.out.println("Found "+list.size()+" Books.");
        statement.close();
        rs.close();
        return list;
    }


    public boolean changePassword(int SSN,String pass) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query = "UPDATE users SET pass = ? where SSN = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,pass);
            preparedStatement.setInt(2,SSN);
            if(pass.length()==0){
                System.out.println("Password can not be empty");
            }
            boolean i = preparedStatement.execute();
            if(!i){
                System.out.println("Password changed : "+ pass);
                return  true;
            }
            else {
                System.out.println("Password does not changed.");
                return false;

            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Password does not changed.");
            preparedStatement.close();
            return false;
        }

    }

    public boolean isLogin(String userName, String pass) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM users WHERE userName = ? and pass = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,pass);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User(resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDate(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getString(8),
                                resultSet.getString(9));
                System.out.println(user.toString());
                return  true;
            }
            else return false;
        }catch (Exception e){
            return false;
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
}
