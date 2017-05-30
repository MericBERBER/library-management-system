package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnector {


    public static Connection Connect(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/library","username","password");
            System.out.println("Database connected");
            return con;
        }catch (Exception e){
            System.out.println("Database not connected");
            e.printStackTrace();
            return null;
        }
    }
}
