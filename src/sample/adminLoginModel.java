package sample;

import POJOs.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class adminLoginModel {

    public static Admin admin;
    Connection connection;

    public adminLoginModel(){

        connection = MySqlConnector.Connect();
        if(connection==null) System.exit(1);

    }

    public ObservableList<Borrow> searchBorrow(String borrowDate,String returnDate,String lastDate,String SSN, String bookID) throws SQLException {

        Map<String, String> validParams = new HashMap<>();
        ObservableList list = FXCollections.observableArrayList();

        if(!borrowDate.equals("")) validParams.put("borrowDate",borrowDate);
        if(!returnDate.equals("")) validParams.put("returnDate",returnDate);
        if(!lastDate.equals("")) validParams.put("lastDate",lastDate);
        if(!SSN.equals("")) validParams.put("SSN",SSN);
        if(!bookID.equals("")) validParams.put("bookID",bookID);

        if(validParams.isEmpty()){

        }

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM borrow");
        boolean queryBuildStarted = false ;
        for(String key : validParams.keySet()){

            if(!queryBuildStarted){
                queryBuilder.append(" WHERE ");
                queryBuildStarted = true;
            }
            else{
                queryBuilder.append(" AND ");

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


        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            Borrow borrow = new Borrow();
            borrow.setBorrowDate(resultSet.getDate(1));
            borrow.setReturnDate(resultSet.getDate(2));
            borrow.setLastDate(resultSet.getDate(3));
            borrow.setSSN(resultSet.getString(4));
            borrow.setBookID(resultSet.getString(5));
            list.add(borrow);

        }
        return list;

    }

    public void updateBorrow(String borrowDate,String returnDate,String lastDate,String SSN, String bookID) throws SQLException {
        Map<String, String> validParams = new HashMap<>();
        if (!borrowDate.equals("")) validParams.put("borrowDate", borrowDate);
        if (!returnDate.equals("")) validParams.put("returnDate", returnDate);
        if (!lastDate.equals("")) validParams.put("lastDate", lastDate);
        if (!SSN.equals("")) validParams.put("SSN", SSN);
        if (!bookID.equals("")) validParams.put("bookID", bookID);
        if (validParams.isEmpty()) {
            return;
        }
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE borrow SET");

        Iterator entries = validParams.entrySet().iterator();

        Map.Entry<String,String> thisEntry = (Map.Entry<String, String>) entries.next();
        queryBuilder.append((" " +thisEntry.getKey() + "=" + "'"+thisEntry.getValue()+"'"));

        while (entries.hasNext()){
            Map.Entry<String,String> theEntry = (Map.Entry<String, String>) entries.next();
            queryBuilder.append(","+theEntry.getKey()+"="+"'"+theEntry.getValue()+"'");
        }
        queryBuilder.append(" WHERE SSN="+SSN+" AND bookID="+bookID);
        PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
        boolean i =statement.execute();
        if(i==true) System.out.println("UPDATED");

    }

    public boolean deleteBorrow(Date borrowDate, Date returnDate, Date lastDate, String SSN, String bookID) throws SQLException {
        PreparedStatement preparedStatement = null;
        String query ="DELETE FROM borrow WHERE borrowDate=? AND returnDate=? AND lastDate=? AND SSN=? AND bookID=?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1,borrowDate);
            preparedStatement.setDate(2,returnDate);
            preparedStatement.setDate(3,lastDate);
            preparedStatement.setString(4,SSN);
            preparedStatement.setString(5,bookID);
            preparedStatement.execute();
            System.out.println("Borrow Deleted");
            return true;
        }catch (Exception E){
            System.out.println("Borrow does not deleted.");
            return false;
        }

    }

    public boolean addBorrow( String borrowDate,String returnDate,String lastDate,String SSN, String bookID){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "INSERT INTO borrow values(?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,borrowDate);
            if(returnDate.equals("")){
                preparedStatement.setDate(2,null);
            }
            preparedStatement.setString(3,lastDate);
            preparedStatement.setString(4,SSN);
            preparedStatement.setString(5,bookID);
            preparedStatement.execute();
            System.out.println("Borrow ->SSN="+SSN+" "+"bookID="+bookID+" added.");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Borrow ->SSN="+SSN+" "+"bookID="+bookID+" does not added.");
            return false;
        }
    }

    public ObservableList<Borrow> getBorrow() throws SQLException {
        ObservableList<Borrow> list = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM borrow";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Borrow borrow = new Borrow();
               borrow.setBorrowDate(resultSet.getDate(1));
               borrow.setReturnDate(resultSet.getDate(2));
               borrow.setLastDate(resultSet.getDate(3));
                borrow.setSSN(resultSet.getString(4));
                borrow.setBookID(resultSet.getString(5));
                list.add(borrow);

            }
            System.out.println("Found "+ list.size()+" Borrows.");

        }catch (Exception e){

        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return list;
    }

    public void updateAuthor(String authorID,String firstName,String lastName,String birthDate,String city) throws SQLException {
        Map<String, String> validParams = new HashMap<>();

        if(!authorID.equals("")) validParams.put("authorID",authorID);
        if(!firstName.equals("")) validParams.put("firstName",firstName);
        if(!lastName.equals("")) validParams.put("lastName",lastName);
        if(!birthDate.equals("")) validParams.put("birthDate",lastName);
        if(!city.equals("")) validParams.put("city",city);
        if(validParams.isEmpty()) {
            return;
        }

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE author SET");
        // firstName = ?, lastName = ?, birthDate =?, streetName =?, zipCode = ?, city =?, userName =?, pass =? WHERE SSN = SSN;


        Iterator entries = validParams.entrySet().iterator();

        Map.Entry<String,String> thisEntry = (Map.Entry<String, String>) entries.next();
        queryBuilder.append((" " +thisEntry.getKey() + "=" + "'"+thisEntry.getValue()+"'"));

        while (entries.hasNext()){
            Map.Entry<String,String> theEntry = (Map.Entry<String, String>) entries.next();
            queryBuilder.append(","+theEntry.getKey()+"="+"'"+theEntry.getValue()+"'");
        }

        queryBuilder.append(" WHERE authorID="+authorID);
        PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
        statement.execute();
    }

    public boolean deleteAuthor(String authorID){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "DELETE FROM author WHERE authorID=?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,authorID);
            preparedStatement.execute();
            System.out.println("SSN = " +authorID +" deleted.");
            return true;
        }catch (Exception e){
            System.out.println("SSN = " +authorID +" not deleted.");
            return false;
        }

    }


    public ObservableList<Author> searchAuthor(String authorID,String firstName,String lastName,String birthDate,String city) throws SQLException {
        Map<String, String> validParams = new HashMap<>();
        ObservableList list = FXCollections.observableArrayList();
        Author author = new Author();

        if(!authorID.equals("")) validParams.put("authorID",authorID);
        if(!firstName.equals("")) validParams.put("firstName",firstName);
        if(!lastName.equals("")) validParams.put("lastName",lastName);
        if(!birthDate.equals("")) validParams.put("birthDate",lastName);
        if(!city.equals("")) validParams.put("city",city);
        if(validParams.isEmpty()) {
        }
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM author");
            boolean queryBuildStarted = false ;
            for(String key : validParams.keySet()){

                if(!queryBuildStarted){
                    queryBuilder.append(" WHERE ");
                    queryBuildStarted = true;
                }
                else{
                    queryBuilder.append(" AND ");

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


            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                author.setAuthorId(resultSet.getString(1));
                author.setFirstName(resultSet.getString(2));
                author.setLastName(resultSet.getString(3));
                author.setBirthDate(resultSet.getString(4));
                author.setCity(resultSet.getString(5));
                list.add(author);
            }
            System.out.println("Found "+list.size()+" Authors");
            return list;

        }


    public boolean addAuthor(String firstName,String lastName,String birthDate,String city){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "INSERT INTO author(firstName,lastName,birthDate,city) values(?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,birthDate);
            preparedStatement.setString(4,city);
            boolean result = preparedStatement.execute();
            System.out.println("Author= " + firstName +" "+ lastName+" added.");
            return true;
        }catch (Exception e){
            System.out.println("Author= " + firstName +" "+ lastName+" does not added.");
            return false;
        }
    }

    public ObservableList<Author> getAuthor() throws SQLException {
        ObservableList<Author> list = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM author";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Author author = new Author();
            author.setAuthorId(resultSet.getString(1));
            author.setFirstName(resultSet.getString(2));
            author.setLastName(resultSet.getString(3));
            author.setBirthDate(resultSet.getString(4));
            author.setCity(resultSet.getString(5));
            list.add(author);
            }
            System.out.println("Found " +list.size()+" Author");

        }catch (Exception e){

        }finally {
            resultSet.close();
            preparedStatement.close();
        }
        return list;
    }

    public ObservableList<Book> searchBook(String bookId,
                                           String title,
                                           String genre,
                                           String pageNumber,
                                           String publisher,
                                           String piece,
                                           String publishedDate,
                                           String authorId) throws SQLException {

        Map<String, String> validParams = new HashMap<>();
        ObservableList<Book> list = FXCollections.observableArrayList();
        if (!bookId.equals("")) validParams.put("bookID", bookId);
        if (!title.equals("")) validParams.put("title", title);
        if (!genre.equals("")) validParams.put("genre", genre);
        if (!pageNumber.equals("")) validParams.put("pageNumber", pageNumber);
        if (!publisher.equals("")) validParams.put("publisher", publisher);
        if (!piece.equals("")) validParams.put("piece", piece);
        if (!publishedDate.equals("")) validParams.put("publishedDate", publishedDate);
        if (!authorId.equals("")) validParams.put("authorID", authorId);
        if (validParams.isEmpty()) {

        }

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM book");
        boolean queryBuildStarted = false ;
        for(String key : validParams.keySet()){

            if(!queryBuildStarted){
                queryBuilder.append(" WHERE ");
                queryBuildStarted = true;
            }
            else{
                queryBuilder.append(" AND ");

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


        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            Book book = new Book();
            book.setBookId(resultSet.getString(1));
            book.setTitle(resultSet.getString(2));
            book.setGenre(resultSet.getString(3));
            book.setPageNumber(resultSet.getInt(4));
            book.setPublisher(resultSet.getString(5));
            book.setPiece(resultSet.getInt(6));
            book.setPublishDate(resultSet.getDate(7));
            book.setAuthor(resultSet.getString(8));
            list.add(book);
        }
        return list;
    }
    public boolean addBook(String title,
                           String genre,
                           int pageNumber,
                           String publisher,
                           String piece,
                           String publishedDate,
                           String authorId){

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "INSERT INTO book(title,genre,pageNumber,publisher,piece,publishedDate,authorID) values(?,?,?,?,?,?,?)";


        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,genre);
            preparedStatement.setInt(3,pageNumber);
            preparedStatement.setString(4,publisher);
            preparedStatement.setString(5,piece);
            preparedStatement.setString(6,publishedDate);
            preparedStatement.setString(7,authorId);

            preparedStatement.execute();
            System.out.println("Book Title = " + title +" added.");
            return true;

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Book Title = " + title +" does not added.");
            return false;
        }

    }
    public boolean deleteBook(String bookId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "DELETE FROM book WHERE bookID=?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,bookId);
            preparedStatement.execute();
            System.out.println("SSN = " +bookId +" deleted.");
            return true;
        }catch (Exception e){
            System.out.println("SSN = " +bookId +" not deleted.");
            return false;
        }

    }

    public ObservableList<Book> getBook() throws SQLException {

        ObservableList<Book> list = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM book ";

        try{
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Book book = new Book();
                book.setBookId(resultSet.getString(1));
                book.setTitle(resultSet.getString(2));
                book.setGenre(resultSet.getString(3));
                book.setPageNumber(resultSet.getInt(4));
                book.setPublisher(resultSet.getString(5));
                book.setPiece(resultSet.getInt(6));
                book.setPublishDate(resultSet.getDate(7));
                book.setAuthor(resultSet.getString(8));
                list.add(book);
            }
            System.out.println("Found "+list.size()+" Books.");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
       return list;
    }

    public void updateBook(String bookId,
                           String title,
                           String genre,
                           String pageNumber,
                           String publisher,
                           String piece,
                           String publishedDate,
                           String authorId) throws SQLException {

        Map<String, String> validParams = new HashMap<>();
        if (!bookId.equals("")) validParams.put("bookID", bookId);
        if (!title.equals("")) validParams.put("title", title);
        if (!genre.equals("")) validParams.put("genre", genre);
        if (!pageNumber.equals("")) validParams.put("pageNumber", pageNumber);
        if (!publisher.equals("")) validParams.put("publisher", publisher);
        if (!piece.equals("")) validParams.put("piece", piece);
        if (!publishedDate.equals("")) validParams.put("publishedDate", publishedDate);
        if (!authorId.equals("")) validParams.put("authorID", authorId);
        if (validParams.isEmpty()) {
            return;
        }
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE book SET");

        Iterator entries = validParams.entrySet().iterator();

        Map.Entry<String,String> thisEntry = (Map.Entry<String, String>) entries.next();
        queryBuilder.append((" " +thisEntry.getKey() + "=" + "'"+thisEntry.getValue()+"'"));

        while (entries.hasNext()){
            Map.Entry<String,String> theEntry = (Map.Entry<String, String>) entries.next();
            queryBuilder.append(","+theEntry.getKey()+"="+"'"+theEntry.getValue()+"'");
        }
        queryBuilder.append(" WHERE bookID="+bookId);
        PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
        statement.execute();
    }

    public void updateUser(    String SSN,
                               String firstName,
                               String lastName,
                               String birthDate,
                               String streetName,
                               String zipCode,
                               String city,
                               String userName,
                               String pass) throws SQLException {
        Map<String, String> validParams = new HashMap<>();

        if (!SSN.equals("")) validParams.put("SSN", SSN);
        if (!firstName.equals("")) validParams.put("firstName", firstName);
        if (!lastName.equals("")) validParams.put("lastName", lastName);
        if (!birthDate.equals("")) validParams.put("birthDate", birthDate);
        if (!streetName.equals("")) validParams.put("streetName", streetName);
        if (!zipCode.equals("")) validParams.put("zipCode", zipCode);
        if (!city.equals("")) validParams.put("city", city);
        if (!userName.equals("")) validParams.put("userName", userName);
        if (!pass.equals("")) validParams.put("pass", pass);
        if (validParams.isEmpty()) {
            return;
        }
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE users SET");
        // firstName = ?, lastName = ?, birthDate =?, streetName =?, zipCode = ?, city =?, userName =?, pass =? WHERE SSN = SSN;


        Iterator entries = validParams.entrySet().iterator();

        Map.Entry<String,String> thisEntry = (Map.Entry<String, String>) entries.next();
        queryBuilder.append((" " +thisEntry.getKey() + "=" + "'"+thisEntry.getValue()+"'"));

        while (entries.hasNext()){
            Map.Entry<String,String> theEntry = (Map.Entry<String, String>) entries.next();
            queryBuilder.append(","+theEntry.getKey()+"="+"'"+theEntry.getValue()+"'");
        }

        queryBuilder.append(" WHERE SSN="+SSN);
        PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());
        statement.execute();

        /*for (Map.Entry<String, String> entrySet : validParams.entrySet()) {
            queryBuilder.append(" " + entrySet.getKey() + "=" + entrySet.getValue() + ",");

        }*/
    }

    public ObservableList<User> searchUser( String SSN,
                                            String firstName,
                                            String lastName,
                                            String birthDate,
                                            String streetName,
                                            String zipCode,
                                            String city,
                                            String userName,
                                            String pass) throws SQLException {

        Map<String, String> validParams = new HashMap<>();
        ObservableList list = FXCollections.observableArrayList();

        if(!SSN.equals("")) validParams.put("SSN",SSN);
        if(!firstName.equals("")) validParams.put("firstName",firstName);
        if(!lastName.equals("")) validParams.put("lastName",lastName);
        if(!birthDate.equals("")) validParams.put("birthDate",birthDate);
        if(!streetName.equals("")) validParams.put("streetName",streetName);
        if(!zipCode.equals("")) validParams.put("zipCode",zipCode);
        if(!city.equals("")) validParams.put("city",city);
        if(!userName.equals("")) validParams.put("userName",userName);
        if(!pass.equals("")) validParams.put("pass",pass);
        if(validParams.isEmpty()){

        }

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM users");
        boolean queryBuildStarted = false ;
        for(String key : validParams.keySet()){

            if(!queryBuildStarted){
                queryBuilder.append(" WHERE ");
                queryBuildStarted = true;
            }
            else{
                queryBuilder.append(" AND ");

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


        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            User user = new User();
            user.setSsn(resultSet.getString(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setBirthDate(resultSet.getDate(4));
            user.setStreetName(resultSet.getString(5));
            user.setZipCode(resultSet.getString(6));
            user.setCity(resultSet.getString(7));
            user.setUserName(resultSet.getString(8));
            user.setPass(resultSet.getString(9));
            list.add(user);
        }

        return list;
    }


    public boolean deleteUser(String SSN) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "DELETE FROM users WHERE SSN=?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,SSN);
            preparedStatement.execute();
            System.out.println("SSN = " +SSN +" deleted.");
            return true;
        }catch (Exception e){
            System.out.println("SSN = " +SSN +" not deleted.");
            return false;
        }

    }

    public boolean addUser(String SSN,
                           String firstName,
                           String lastName,
                           String birthDate,
                           String streetName,
                           String zipCode,
                           String city,
                           String userName,
                           String pass) throws SQLException,NullPointerException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "INSERT INTO users values(?,?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,SSN);
            preparedStatement.setString(2,firstName);
            preparedStatement.setString(3,lastName);
            preparedStatement.setString(4,birthDate);
            preparedStatement.setString(5,streetName);
            preparedStatement.setString(6,zipCode);
            preparedStatement.setString(7,city);
            preparedStatement.setString(8,userName);
            preparedStatement.setString(9,pass);

            preparedStatement.execute();
            System.out.println("User SSN = " + SSN +" added.");
            return true;
        }catch (Exception e){
            System.out.println("User SSN = " + SSN +" doesn't added.");
            return false;
        }

    }

    public ObservableList<User> getUser() throws SQLException {

        ObservableList<User> list = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM users";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                User user = new User();
                user.setSsn(resultSet.getString(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setBirthDate(resultSet.getDate(4));
                user.setStreetName(resultSet.getString(5));
                user.setZipCode(resultSet.getString(6));
                user.setCity(resultSet.getString(7));
                user.setUserName(resultSet.getString(8));
                user.setPass(resultSet.getString(9));
                list.add(user);

            }
            System.out.println("Found " +list.size()+" Users");

        }catch (Exception e){

        }finally {
            resultSet.close();
            preparedStatement.close();
        }

        return list;

    }

    public boolean isLogin(String adminUserName, String adminPass) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM employee WHERE userName = ? and pass = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,adminUserName);
            preparedStatement.setString(2,adminPass);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                admin = new Admin();
                admin.setSSN(resultSet.getString(1));
                admin.setFirstName(resultSet.getString(2));
                admin.setLastName(resultSet.getString(3));
                admin.setBirthDate(resultSet.getDate(4));
                admin.setStreetName(resultSet.getString(5));
                admin.setZipCode(resultSet.getString(6));
                admin.setCity(resultSet.getString(7));
                admin.setAdminUserName(resultSet.getString(8));
                admin.setAdminPassword(resultSet.getString(9));
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
