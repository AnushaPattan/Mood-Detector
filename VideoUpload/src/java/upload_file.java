/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.oreilly.servlet.MultipartRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pkgDB.DBQuery;
import pkgLogic.info;

/**
 *
 * @author sumit
 */
public class upload_file extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String dirName =info.path;
                System.out.println("????????????????????????????????"+dirName);
		String paramname=null;
	        int fId=0;
		String usn="",fname="",catg="",branch="",sem="",sub="",sta="pending",ph="";
	HttpSession session=request.getSession();
        
                 
   		  File file1 = null;
   		  File file2 = null;
			
		  RequestDispatcher rd=null;
		  try {
			 
			MultipartRequest multi = new MultipartRequest(request, dirName,	10 * 1024 * 1024); // 10MB

			Enumeration params = multi.getParameterNames();
			while (params.hasMoreElements()) 
			{
				paramname = (String) params.nextElement();
				
				if(paramname.equalsIgnoreCase("catg"))
				{
					catg=multi.getParameter(paramname);
				}
                               
                                
                               
		        }
                       
                        
                        
                        int p=0,f=0;
                        String phPath="",filePath="";
                        DBQuery db=new DBQuery();
                      
                        
                     
                        Enumeration files = multi.getFileNames();
                     
	          while (files.hasMoreElements()) 
	          {
		paramname = (String) files.nextElement();
		if(paramname.equals("d1"))
		{
			paramname = null;
		}
		 if(paramname != null && paramname.equals("file"))
		{
			f = 1;
			filePath = multi.getFilesystemName(paramname);
			String fPath = dirName+filePath;
                        System.out.println(">>1>>"+filePath);
                        System.out.println(">>2>>"+fPath);
                   
                         db.regFile(filePath, catg);
                }
                  }
                  }catch(Exception e)
                  {
                  e.printStackTrace();
                  }
                  rd=request.getRequestDispatcher("FileUpload.jsp");
                  rd.forward(request, response);
                  
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
