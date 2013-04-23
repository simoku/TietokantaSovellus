<%-- 
    Document   : Paivita_kayttaja
    Created on : Apr 23, 2013, 10:06:12 AM
    Author     : sikuutti
--%>

<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="tyyli.css" />
        <title>Käyttäjän päivitys</title>
    </head>
    <body>
        <h1>Päivitä käyttäjä</h1>
        <form action="PaivitaKayttaja" method="post">

            <jsp:useBean id="lista" class="fi.helsinki.sikuutti.beans.HakuKayttaja" scope="request" />
            <%LinkedList li = new LinkedList();%>
            <%li = lista.getLista("2");%>

            <table>
                <tr><td>ID</td><td><input type="text" readonly name="id" value="<%out.print(li.get(0));%>"/></td></tr>
                <tr><td>Nimi</td><td><input type="text" name="Nimi" value="<%out.print(li.get(1));%>"/></td></tr>
                <tr><td>Käyttäjätunnus</td><td><input type="text" name="KayttajaTunnus" value="<%out.print(li.get(2));%>"/></td></tr>
                <tr><td>Salasana</td><td><input type="password" name="SalaSana" value="<%out.print(li.get(3));%>"/></td></tr>
                <tr><td>Osoite</td><td><input type="text" name="Osoite" value="<%out.print(li.get(4));%>"/></td></tr>
                <tr><td>Kaupunki</td><td><input type="text" name="Kaupunki" value="<%out.print(li.get(5));%>" /></td></tr>
                <tr><td>Postinumero</td><td><input type="text" name="Postinumero" value="<%out.print(li.get(6));%>"/></td></tr>
                <tr><td>Alkupaino</td><td><input type="text" name="Paino" value="<%out.print(li.get(7));%>"/></td></tr>
                <tr><td>Ikä</td><td><input type="text" name="Ika" value="<%out.print(li.get(8));%>"/></td></tr>


                <td></td>
                <td><input type="submit" value="Päivitä"></td>
                </tr>
            </table>    

        </form>

<p> <a href="Paivakirja.jsp">Paluu<td> </p>
    </body>
</html>
