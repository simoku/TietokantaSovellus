/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.sikuutti.beans;

import fi.helsinki.sikuutti.tietokantasovellus.SQLDao;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sikuutti
 */
public class HakuLista extends SQLDao {
   private LinkedList lista;
   
   
    public HakuLista(){
        
     
     
        
    }

    
    public LinkedList getLista() {
        
          
    this.lista=SQLDao.haeLaji();
        return lista;
    }

    
    
}
