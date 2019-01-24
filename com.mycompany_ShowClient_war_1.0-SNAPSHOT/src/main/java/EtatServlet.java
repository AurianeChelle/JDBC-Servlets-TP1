/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.*;

/**
 *
 * @author achelle
 */
@WebServlet(urlPatterns = {"/EtatServlet"})
public class EtatServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws simplejdbc.DAOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String state = request.getParameter("state");
        DAO dao = new DAO(DataSourceFactory.getDataSource());
     
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EtatServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EtatServlet at " + request.getContextPath() + "</h1>");
                      
            List<CustomerEntity> customers = new ArrayList<>();
            try {
                customers = dao.customersInState(state);
                out.println("<table border='2'>");
                out.println("<tr>");
                   out.println("<th> ID </th>");
                   out.println("<th> Name </th>");
                   out.println("<th> Adresse </th>");
                out.println("/<tr>");
                for (int i = 0; i<=customers.size();i++){
                out.println("<tr>");
                   out.println("<th>"+customers.get(i).getCustomerId()+"</th>");
                   out.println("<th>"+customers.get(i).getName()+"</th>");
                   out.println("<th>"+customers.get(i).getAddressLine1()+"</th>");
                out.println("/<tr>");
                }
            out.println("</table>");
            } catch (DAOException ex) {
                Logger.getLogger(EtatServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
