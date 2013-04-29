/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.sikuutti.beans;

import fi.helsinki.sikuutti.tietokantasovellus.SQLDao;
import java.util.LinkedList;
import java.util.List;

/**
 *Hakee lajitiedot
 * Palauttaa LinkedList
 * @author sikuutti
 */
public class HakuLista extends SQLDao {
   private LinkedList lista;
   
   
    /**
     *
     */
    public HakuLista(){
        
     
     
        
    }

    
    /**
     *
     * @return
     */
    public LinkedList getLista() {
        
          
    this.lista=SQLDao.haeLaji();
        return lista;
    }

    
    
}
