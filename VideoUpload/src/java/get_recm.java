/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pkgDB.DBQuery;
import pkgLogic.info;

/**
 *
 * @author sumit
 */
public class get_recm extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String gsr=request.getParameter("gsr");
            String temp=request.getParameter("temp");
            String humi=request.getParameter("humi");
            String motion=request.getParameter("motion");
            System.out.println("gsr="+gsr);
            System.out.println("temp="+temp);
            System.out.println("humi="+humi);
            System.out.println("motion="+motion);
            String mood="";
            System.out.println("....."+Integer.parseInt(gsr));
            if((Integer.parseInt(gsr)>300 && Integer.parseInt(gsr)<400) && (Integer.parseInt(temp)>90 && Integer.parseInt(temp)<93) && (Integer.parseInt(humi)<40 && Integer.parseInt(humi)>45))
            {
                System.out.println("happy");
                mood="Happy";
            }
              
            else if((Integer.parseInt(gsr)>400 && Integer.parseInt(gsr)<500) && (Integer.parseInt(temp)>93 && Integer.parseInt(temp)<95) && (Integer.parseInt(humi)<45 && Integer.parseInt(humi)>50))
            {
                System.out.println("sad");
                mood="Sad";
            }
//            else if(Integer.parseInt(gsr)>350 || Integer.parseInt(gsr)<389 && Integer.parseInt(temp)>90 || Integer.parseInt(temp)<93 && Integer.parseInt(humi)<42 || Integer.parseInt(humi)>37)
//            {
//                System.out.println("happy");
//                mood="Happy";
//            }
            else if((Integer.parseInt(gsr)>500 && Integer.parseInt(gsr)<600) && (Integer.parseInt(temp)>95 && Integer.parseInt(temp)<98) && (Integer.parseInt(humi)<50 && Integer.parseInt(humi)>55))
            {
                System.out.println("depression");
                mood="depression";
            }
             else  if((Integer.parseInt(gsr)>600 && Integer.parseInt(gsr)<700) && (Integer.parseInt(temp)>98 && Integer.parseInt(temp)<100) && (Integer.parseInt(humi)<56 && Integer.parseInt(humi)>60))
            {
                System.out.println("surprise");
                mood="surprise";
            }
            else if((Integer.parseInt(gsr)>700 && Integer.parseInt(gsr)<800) && (Integer.parseInt(temp)>90 && Integer.parseInt(temp)<93) && (Integer.parseInt(humi)<42 && Integer.parseInt(humi)>37))
            {
                System.out.println("angry");
                mood="Angry";
            }
            DBQuery db=new DBQuery();
             File f = new File(info.path);
                String[] arr = f.list();
                System.out.println(arr.length);
                String str = "";
                
                for(int i = 0; i<arr.length; i++){
                    
                    if(arr[i].endsWith(".mp4")){
                        String catg=db.vid_catg(arr[i]);
                        if(mood.equals(catg))
                        {
                        str += arr[i] + ":";
                        }
                    }
                }
                out.print(mood+"####"+str);
           
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(get_recm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(get_recm.class.getName()).log(Level.SEVERE, null, ex);
        }
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
