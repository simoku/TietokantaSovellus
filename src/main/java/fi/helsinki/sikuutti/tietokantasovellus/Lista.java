/*
 * 
 * Hakee tiedot Lista.jsp-sivulle
 */
package fi.helsinki.sikuutti.tietokantasovellus;

import fi.helsinki.sikuutti.tietokantasovellus.SQLDao;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sikuutti
 */
public class Lista extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet responsee
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        
        String id = (String) session.getAttribute("id");
        String paino = (String) session.getAttribute("paino");
        String alku= request.getParameter("alku");
        String loppu= request.getParameter("loppu");
        
        LinkedList harkat= new LinkedList();
        harkat=SQLDao.haeHaeHarjoitukset(id,alku,loppu);
        
         request.setAttribute("alku", alku);
        request.setAttribute("loppu", loppu);
        request.setAttribute("harkat", harkat);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Lista.jsp");
       
        dispatcher.forward(request, response);
        
        
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