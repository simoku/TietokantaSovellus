/*
 * Tuottaa tiedon päiväkirjasivulle
 * 
 */
package fi.helsinki.sikuutti.tietokantasovellus;

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
public class Paivakirja extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String id = (String) session.getAttribute("id");
        String nimi = (String) session.getAttribute("Nimi");
        String paino = (String) session.getAttribute("paino");

        if(id==null){
        request.setAttribute("viesti", "Kirjaudu uudelleen");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
            
        }
        
        System.out.println(id);

        String pvm = request.getParameter("pvm");
        String laji = request.getParameter("laji");
        String kesto = request.getParameter("kesto");
        String fiilis = request.getParameter("fiilis");

        // testataan keston numeerinen muoto
        boolean onkoNumero=onkoNumero(kesto);
        if(!onkoNumero(kesto)){
        request.setAttribute("viesti"," Harjoituksen kesto pitää olla numeerinen");
        
        
        }
        
        
        if (pvm == null || !onkoNumero ) {
        } else {
            SQLDao.paivitaHarjoitus(laji, pvm, kesto, fiilis, id, paino);
        }


        LinkedList harkat = new LinkedList();

        harkat = SQLDao.haePaivanHarjoitukset(id);
        System.out.println(harkat);
        request.setAttribute("harkat", harkat);
        request.setAttribute("Nimi", nimi);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Paivakirja.jsp");
        dispatcher.forward(request, response);
        

    }
public boolean onkoNumero( String kesto )  
{  
   try  
   {  
      Integer.parseInt( kesto );  
      return true;  
   }  
   catch( Exception e )  
   {  
      return false;  
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
