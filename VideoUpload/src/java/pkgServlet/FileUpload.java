package pkgServlet;

import com.oreilly.servlet.MultipartRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import pkgDB.DBQuery;
import pkgLogic.info;

public class FileUpload extends HttpServlet {
    
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 100;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
                String path = info.path;
            
                MultipartRequest m = new MultipartRequest(request, path);
                Enumeration e1 = m.getParameterNames();
                Enumeration e = m.getFileNames();
                
                String fileName = "",catg="";
                while(e1.hasMoreElements()){
                    String param = (String)e1.nextElement();
                    
                    if(param != null){
                        catg = m.getParameter("catg");
                    }    
                }
                while(e.hasMoreElements()){
                    String param = (String)e.nextElement();
                    
                    if(param != null){
                        fileName = m.getFilesystemName(param);
                    }    
                }
                
                System.out.println(fileName);
                System.out.println(catg);
                
                DBQuery db = new DBQuery();
                    int rw = db.regFile(fileName,catg);
                    
                    if(rw>0){
                        HttpSession session = request.getSession();
                        session.setAttribute("msg", "File uploaded successfully");
                        RequestDispatcher rd = request.getRequestDispatcher("FileUpload.jsp");
                        rd.forward(request, response);
                    }
        } 
        
        finally {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
