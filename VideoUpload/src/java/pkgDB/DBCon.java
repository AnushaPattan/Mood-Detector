package pkgDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBCon {
    
    public static Connection getCon() throws ClassNotFoundException,SQLException{
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mood_detector", "root", "root");
       return con;
    }
}
