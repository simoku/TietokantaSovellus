/*
 * Päivittää Kayttajat taulun
 */
package fi.helsinki.sikuutti.tietokantasovellus;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sikuutti
 */
public class PaivitaKayttaja extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet reques
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id= request.getParameter("id");
        String nimi = request.getParameter("Nimi");
        String kayttajatunnus = request.getParameter("KayttajaTunnus");
        String salasana = request.getParameter("SalaSana");
        String osoite = request.getParameter("Osoite");
        String kaupunki = request.getParameter("Kaupunki");
        String postnumero = request.getParameter("Postinumero");
        String paino = request.getParameter("Paino");

        String ika = request.getParameter("Ika");
        
        
      boolean t= SQLDao.paivitaKayttaja(id, nimi, kayttajatunnus, salasana, osoite, kaupunki, postnumero, paino, ika);
       
      if(t){
          request.setAttribute("viesti", "Päivitys onnistui");
      }
      else{
           request.setAttribute("viesti", "Päivitys epäonnistui");
      }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Paivita_kayttaja.jsp");
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
