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
public class HakuHarjoitus extends SQLDao {
    public LinkedList harjoitukset;

    public LinkedList getHarjoitukset() {
        
        return harjoitukset;
    }
    public HakuHarjoitus(){
    
    this.harjoitukset=SQLDao.haeKaikkiLajit();
        System.out.println(this.harjoitukset);
}
}
