/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.sikuutti.tietokantasovellus;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sikuutti
 */
public class PaivitaHarjoitus extends HttpServlet {

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

        String checkbox = request.getParameter("box");
        String id_p = request.getParameter("id_p");
        String kalori_p = request.getParameter("kalori_p");
        String laji_p= request.getParameter("laji_p");
                
        String tehtava = request.getParameter("tehtava");

       
        if(tehtava==null)
            tehtava="";
        if(tehtava.equals("poista") && tehtava!=null)
        {
           boolean t=SQLDao.poistaHarjoitus (id_p);
        }
        
        if(tehtava.equals("paivita")&& tehtava!=null)
        {
            
            boolean tt =SQLDao.paivitaHarjoitus(id_p, "",kalori_p );  
        } 
        
        if(tehtava.equals("lisaa")&& tehtava!=null)
        {
            
            boolean ttt =SQLDao.lisaaHarjoitus(laji_p,kalori_p);  
        } 
       
        LinkedList plista = new LinkedList();
        plista = SQLDao.haePaivitettavaHarjoitus(checkbox);
        System.out.println(plista + " plista");

        request.setAttribute("plista", plista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Paivita.jsp");
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
