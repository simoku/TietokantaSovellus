/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.sikuutti.beans;

import fi.helsinki.sikuutti.tietokantasovellus.SQLDao;
import java.util.LinkedList;

/**
 *Hakee käyttäjätiedot ID:n mukaan
 * Palauttaa LinkedList
 * @author sikuutti 
 */
public class HakuKayttaja extends SQLDao{
    private LinkedList lista;
    
    /**
     *
     */
    public HakuKayttaja(){
        this.lista=lista;
        
    }

    /**
     *
     * @param id
     * @return
     */
    public LinkedList getLista(String id) {
       
        this.lista=SQLDao.haeKayttajatiedot(id);
        return lista;
    }
    
}
