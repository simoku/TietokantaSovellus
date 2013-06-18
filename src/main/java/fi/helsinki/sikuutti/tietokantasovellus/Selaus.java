/*
 * Ilman kirjaatumista toimiva kalorilaskurin tidot
 * 
 */
package fi.helsinki.sikuutti.tietokantasovellus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sikuutti
 */
public class Selaus extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Map<String, String> lista;
    List<String> keyList;
    private String ika = "";
    private String paino1 = "";
    private String aika = "";
    private String laji = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        paino1 = request.getParameter("paino1");
        aika = request.getParameter("aika");
        laji = request.getParameter("laji");
        System.out.println(ika);
        lista = new LinkedHashMap<String, String>();
        lista = SQLDao.haeLajit();
        keyList = new ArrayList();

        for (String key : lista.keySet()) {
            keyList.add(key);

        }







        request.setAttribute("lista", this.keyList);
        request.setAttribute("ika", ika);
        request.setAttribute("paino1", paino1);
        request.setAttribute("aika", aika);
        if(laji!=null)
        request.setAttribute("viesti", laji+" kuluttaa kaloreita  " + laskeKalorit(lista.get(laji), paino1, aika));
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Selaus.jsp");
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

    private int laskeKalorit(String get, String paino, String aika) {

        int paluu = 0;

        try {



            paluu = Integer.parseInt(paino) * Integer.parseInt(aika) * Integer.parseInt(get);
        } catch (Exception e) {

            return 0;
        }


        return paluu;
    }
}