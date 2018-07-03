package pkgDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {
    
    public int login(String email,String pass) throws ClassNotFoundException,SQLException{
        
        String qry = "select count(*) from login where user_id = '" + email + "' and pass = '" + pass + "'";
        System.out.println(qry);
        
        Connection con = DBCon.getCon();
        Statement stat = con.createStatement();
        ResultSet rset = stat.executeQuery(qry);
        
        int count = 0;
        
        while(rset.next()){
            count = rset.getInt("count(*)");
        }
        rset.close();
        return count;
    }
    
    public int user_login(String email,String pass) throws ClassNotFoundException,SQLException{
        
        String qry = "select count(*) from user_reg where email = '" + email + "' and pass = '" + pass + "'";
        System.out.println(qry);
        
        Connection con = DBCon.getCon();
        Statement stat = con.createStatement();
        ResultSet rset = stat.executeQuery(qry);
        
        int count = 0;
        
        while(rset.next()){
            count = rset.getInt("count(*)");
        }
        rset.close();
        return count;
    }
    
    
    public int getEmailCount(String email) throws ClassNotFoundException,SQLException{
        
        String qry = "select count(*) from user_reg where email = '" + email + "'";
        System.out.println(qry);
        
        Connection con = DBCon.getCon();
        Statement stat = con.createStatement();
        ResultSet rset = stat.executeQuery(qry);
        
        int count = 0;
        
        while(rset.next()){
            count = rset.getInt("count(*)");
        }
        rset.close();
        return count;
    }
    
    
    public int regUser(String name,String email,String pass) throws ClassNotFoundException, SQLException{
        
        String qry = "insert into user_reg values('" + name + "','" + email + "','" + pass + "')";
        String qry1 = "insert into login values('" + email + "','" + pass + "')";
        
        Connection con = DBCon.getCon();
        Statement stat = con.createStatement();
        
        if(getEmailCount(email) == 0){
            return stat.executeUpdate(qry);
        }
        
        else{
            return 0;
        }
    }
    
    
    public int regFile(String fileName,String catg) throws ClassNotFoundException, SQLException{
        
        String qry = "insert into file_info values('" + fileName + "','"+catg+"')";
        
        Connection con = DBCon.getCon();
        Statement stat = con.createStatement();
        
        return stat.executeUpdate(qry);
    }
     public String vid_catg(String fname) throws ClassNotFoundException,SQLException{
        String catg="";
        String qry = "select catg from file_info where file_name = '" + fname + "'";
        System.out.println(qry);
        
        Connection con = DBCon.getCon();
        Statement stat = con.createStatement();
        ResultSet rset = stat.executeQuery(qry);
        
        
        
        if(rset.next()){
            catg = rset.getString("catg");
        }
        rset.close();
        return catg;
    }
}
