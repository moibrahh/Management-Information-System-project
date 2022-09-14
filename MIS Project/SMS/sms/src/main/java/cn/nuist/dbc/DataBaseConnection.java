package cn.nuist.dbc;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Administrator
 */
public class DataBaseConnection {
    public Connection createConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");   // 
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sms",
                    "root", "Thelicious9");  //
            System.out.println("Connected to database!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
