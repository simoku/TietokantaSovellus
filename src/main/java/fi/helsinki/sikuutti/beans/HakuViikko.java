/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.sikuutti.beans;

import fi.helsinki.sikuutti.tietokantasovellus.SQLDao;
import java.util.LinkedList;

/**
 *
 * @author kuuttsim
 */
public class HakuViikko extends SQLDao{
    public LinkedList harjoitukset;

    /**
     *
     * @return
     */
    public LinkedList getHarjoitukset() {
        
        return harjoitukset;
    }
    /**
     *
     */
    public HakuViikko(){
    
    this.harjoitukset=SQLDao.haeViikonHarjoitukset();
        System.out.println(this.harjoitukset);
}
}
