/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.helsinki.sikuutti.tietokantasovellus;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kaiki tietokantahaut
 *
 * @author sikuutti
 */
public class SQLDao {

    static Connection currentCon = null;
    static ResultSet rs = null;
    static PreparedStatement preparedStatement = null;
    static String HAE_KAYTTAJA = "select * from Kayttajat where KayttajaTunnus=? AND SalaSana=?";
    static String LISAA_KAYTTAJA = "insert into Kayttajat (Nimi,KayttajaTunnus,Salasana,Osoite,Kaupunki,Postinumero,Paino,Ika)"
            + "values (?,?,?,?,?,?,?,?)";
    //static String LISAA_HARJOITUS = "insert into PaivaKirja (Paivamaara,Nimi,Harjoitus,Fiiilis,Kesto,KulutetutKalorit,HarjoitusID) "
    //        + "select ?,?,?,?,?,?,idHarjoitus from Harjoitus where Laji='Ratsastus'";
    static String HAE_KALORIT = " select KaloritPerKilo from Harjoitus where laji=?";
    static String HAE_LAJIT = "select Laji,KaloritPerKilo from Harjoitus ";
    static String HAE_HARJOITUKSET = "select Harjoitus ,sum(Kesto),sum(KulutetutKalorit)FROM tsoha.PaivaKirja where Nimi=? and Paivamaara between ? and ? group by Harjoitus";
    static String HAE_KAYTTAJA_TIEDOT = "select * from Kayttajat where id=?";
    static String HAE_HARJOITUS = "select Laji from Harjoitus";
    static String HAE_VIIKON_HARJOITUS="select dayname(Paivamaara) as paiva,Harjoitus,sum(Kesto),sum(KulutetutKalorit) FROM PaivaKirja WHERE WEEKOFYEAR(Paivamaara)=WEEKOFYEAR(NOW()) group by paiva,Harjoitus";
    
    /**
     *
     * @param @return
     */
    public static ArrayList login(String kayttis, String salasana) {
     

        ArrayList ar = new ArrayList();
        try {

            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement(HAE_KAYTTAJA);

            preparedStatement.setString(1, kayttis);
            preparedStatement.setString(2, salasana);

            rs = preparedStatement.executeQuery();
            boolean userExists = rs.next();

            if (!userExists) {
                ar = null;
                return ar;
            } else if (userExists) {
                ar.add(rs.getString("ID"));
                ar.add(rs.getString("Paino"));
                ar.add(rs.getString("Nimi"));

            }

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ar;
    }

    /**
     *
     * @param bean
     * @return
     */
    public static boolean UusiKayttaja(String nimi, String kayttajatunnus, String salasana, String osoite, String kaupunki, String postnumero, String paino, String ika) {
        boolean paluu = false;
        try {

            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement(LISAA_KAYTTAJA);

            preparedStatement.setString(1, nimi);
            preparedStatement.setString(2, kayttajatunnus);
            preparedStatement.setString(3, salasana);
            preparedStatement.setString(4, osoite);
            preparedStatement.setString(5, kaupunki);
            preparedStatement.setString(6, postnumero);
            preparedStatement.setString(7, paino);
            preparedStatement.setString(8, ika);
            System.out.println(LISAA_KAYTTAJA);
            preparedStatement.executeUpdate();


            paluu = true;

        } catch (Exception x) {
            System.out.println(x);

        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return paluu;



    }

    public static LinkedList haeViikonHarjoitukset() {

        LinkedList lista = new LinkedList();
        try {

            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement(HAE_VIIKON_HARJOITUS);



            rs = preparedStatement.executeQuery();
            String a = "";
            String a1="";
            while (rs.next()) {
             
                a1=muutaVkp(rs.getString(1));
                
                
                
                a = a1 + ":" + rs.getString(2) + ":" + rs.getString(3)+ ":" + rs.getString(4);
                lista.add(a);
                a = "";
            }

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }
    
    public static LinkedList haeKaikkiLajit() {

        LinkedList lista = new LinkedList();
        try {

            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement("select * from Harjoitus");



            rs = preparedStatement.executeQuery();
            String a = "";
            while (rs.next()) {

                a = rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3);
                lista.add(a);
                a = "";
            }

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }

    public static Map<String, String> haeLajit() {

        Map<String, String> lajit = new LinkedHashMap<String, String>();

        try {

            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement(HAE_LAJIT);



            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                lajit.put(rs.getString(1), rs.getString(2));
            }

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lajit;




    }

    public static boolean paivitaHarjoitus(String laji, String pvm, String kesto, String fiilis, String ID, String paino) {
        boolean paluu = false;
        String kalorit = "";
        String LISAA_HARJOITUS = "insert into PaivaKirja (Paivamaara,Nimi,Harjoitus,Fiiilis,Kesto,KulutetutKalorit,HarjoitusID) "
                + "select ?,?,?,?,?,?,idHarjoitus from Harjoitus where Laji=?";



        try {
            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement(HAE_KALORIT);

            preparedStatement.setString(1, laji);

            rs = preparedStatement.executeQuery();






            while (rs.next()) {
                kalorit = rs.getString(1);

            }

            try {
                int kalorit_i = Integer.parseInt(kalorit);
                int paino_i = Integer.parseInt(paino);
                int kesto_i = Integer.parseInt(kesto);

                int kulutus = paino_i * kalorit_i * kesto_i;
                kalorit = Integer.toString(kulutus);
            } catch (Exception E) {
                System.out.println(E + " EEE");
                kalorit = "0";
            }


            preparedStatement = currentCon.prepareStatement(LISAA_HARJOITUS);
            System.out.println("lisaa harjoitus " + preparedStatement);
            preparedStatement.setString(1, aikaParse(pvm));
            preparedStatement.setString(2, ID);

            preparedStatement.setString(3, laji);
            preparedStatement.setString(4, fiilis);
            preparedStatement.setString(5, kesto);
            preparedStatement.setString(6, kalorit);
            preparedStatement.setString(7, laji);
            preparedStatement.executeUpdate();


            paluu = true;

        } catch (Exception x) {
            System.out.println(x);

        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                System.out.println("sasas");
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return paluu;



    }

    public static LinkedList haePaivanHarjoitukset(String ID) {
        LinkedList lista = new LinkedList();


        try {

            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement("SELECT Harjoitus,Kesto,KulutetutKalorit,Fiiilis FROM tsoha.PaivaKirja where Paivamaara = curdate() and nimi=?");


            preparedStatement.setString(1, ID);
            rs = preparedStatement.executeQuery();
            String harkat = "";
            while (rs.next()) {

                harkat = rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3) + ":" + rs.getString(4);

                lista.add(harkat);
                harkat = "";

            }

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;




    }

    public static LinkedList haeHaeHarjoitukset(String ID, String alku, String loppu) {
        LinkedList lista = new LinkedList();




        try {

            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement(HAE_HARJOITUKSET);

            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, aikaParse(alku));
            preparedStatement.setString(3, aikaParse(loppu));

            rs = preparedStatement.executeQuery();
            String a = "";
            while (rs.next()) {

                a = rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3);

                lista.add(a);
                a = "";

            }

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;




    }

    public static LinkedList haeLaji() {
        LinkedList lista = new LinkedList();

        try {

            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement("Select Laji from Harjoitus");


            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {



                lista.add(rs.getString(1));


            }

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;




    }

    public static String muokkaaHarkatListaa(String harkat) {

        harkat = harkat.replaceAll(":", "         ");


        return harkat;
    }

    public static String aikaParse(String aika) throws ParseException {


        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        date = sdf.parse(aika);
        System.out.println(date);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd");


        aika = sdf1.format(date);



        return aika;
    }

    public static LinkedList haeKayttajatiedot(String ID) {
        LinkedList lista = new LinkedList();




        try {
            int a = Integer.parseInt(ID);
            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement(HAE_KAYTTAJA_TIEDOT);

            preparedStatement.setInt(1, a);

            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                lista.add(rs.getString(1));
                lista.add(rs.getString(2));
                lista.add(rs.getString(3));
                lista.add(rs.getString(4));
                lista.add(rs.getString(5));
                lista.add(rs.getString(6));
                lista.add(rs.getString(7));
                lista.add(rs.getString(8));
                lista.add(rs.getString(9));

            }

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;




    }

    public static boolean paivitaHarjoitus(String id, String laji, String kalori) {

        boolean paluu = false;
        String kalorit = "";
        try {
            currentCon = ConnectionMan.getConnection();
            String paivita = "update Harjoitus set KaloritPerKilo=? where idHArjoitus=? ";

            String paivita_paivakirja="update PaivaKirja set KulutetutKalorit = Kesto*? where HarjoitusID=?";

            preparedStatement = currentCon.prepareStatement(paivita);

            preparedStatement.setString(1, kalori);
            preparedStatement.setInt(2, Integer.parseInt(id));

            System.out.println(preparedStatement + " pavtys");
            preparedStatement.executeUpdate();

            preparedStatement = currentCon.prepareStatement(paivita_paivakirja);

            preparedStatement.setString(1, kalori);
            preparedStatement.setInt(2, Integer.parseInt(id));

            System.out.println(preparedStatement + " paivakirjan päivitys");
            preparedStatement.executeUpdate();

            paluu = true;

        } catch (Exception x) {
            System.out.println(x);

        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {

                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return paluu;



    }

    public static boolean paivitaKayttaja(String ID, String Nimi, String Tunnus, String Salasana, String Osoite, String Kaupunki, String Posti, String Paino, String Ika) {

        boolean paluu = false;
        String kalorit = "";
        try {
            currentCon = ConnectionMan.getConnection();
            String paivita = "update Kayttajat set Nimi=?,"
                    + " KayttajaTunnus=? ,SalaSana=? ,Osoite=?,Kaupunki=? "
                    + ",Postinumero=?, Paino=? ,Ika=? where ID=? ";



            preparedStatement = currentCon.prepareStatement(paivita);

            preparedStatement.setString(1, Nimi);
            preparedStatement.setString(2, Tunnus);
            preparedStatement.setString(3, Salasana);
            preparedStatement.setString(4, Osoite);
            preparedStatement.setString(5, Kaupunki);
            preparedStatement.setString(6, Posti);
            preparedStatement.setString(7, Paino);
            preparedStatement.setString(8, Ika);
            preparedStatement.setInt(9, Integer.parseInt(ID));

            System.out.println(preparedStatement + " pavtys");
            preparedStatement.executeUpdate();


            paluu = true;

        } catch (Exception x) {
            System.out.println(x);

        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {

                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return paluu;



    }

    public static boolean lisaaHarjoitus(String laji, String kalori) {

        boolean paluu = false;

        try {
            currentCon = ConnectionMan.getConnection();
            String paivita = "insert into Harjoitus values(null,?,?)";



            preparedStatement = currentCon.prepareStatement(paivita);


            preparedStatement.setString(1, laji);
            preparedStatement.setString(2, kalori);

            preparedStatement.executeUpdate();


            paluu = true;

        } catch (Exception x) {
            System.out.println(x);

        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                System.out.println("sasas");
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return paluu;


    }

    public static boolean poistaHarjoitus(String id) {

        boolean paluu = false;

        try {
            currentCon = ConnectionMan.getConnection();
            String paivita = "delete from Harjoitus where idHArjoitus=? ";



            preparedStatement = currentCon.prepareStatement(paivita);


            preparedStatement.setInt(1, Integer.parseInt(id));


            preparedStatement.executeUpdate();


            paluu = true;

        } catch (Exception x) {
            System.out.println(x);
             paluu = false;

        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                System.out.println("sasas");
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return paluu;



    }

    public static LinkedList haePaivitettavaHarjoitus(String id) {

        LinkedList lista = new LinkedList();


        try {
            int a = Integer.parseInt(id);
            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement("select * from Harjoitus where idHarjoitus=?");

            preparedStatement.setInt(1, a);

            System.out.println(preparedStatement);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {

                lista.add(rs.getString(1));
                lista.add(rs.getString(2));
                lista.add(rs.getString(3));


            }

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;


    }

    public static int haeHarkkaID(String laji) {


        int paluu = 0;

        try {

            currentCon = ConnectionMan.getConnection();
            preparedStatement = currentCon.prepareStatement("SELECT idHarjoitus FROM Harjoitus where Laji=?");

            preparedStatement.setString(1, laji);
            rs = preparedStatement.executeQuery();
            System.out.println(preparedStatement);
            while (rs.next()) {
            }
            paluu = rs.getInt(1);

        } catch (Exception x) {
        } finally {
            try {
                currentCon.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return paluu;


    }

    private static String muutaVkp(String string) {
        
        if(string.startsWith("Mon")){
            string="Maanantai";
        }
        else if (string.startsWith("Tue")){
            string="Tiistai";
        }
        else if (string.startsWith("Wed")){
            string="Keskiviikko";
        }
        else if (string.startsWith("Thu")){
            string="Maanantai";
        }
        else if (string.startsWith("Fri")){
            string="Perjantai";
        }
        
        else if (string.startsWith("Sa")){
            string="Lauantai";
        }
        
        else if (string.startsWith("Su")){
            string="Sunnuntai";
        }
        
        
        return string;
    }
}