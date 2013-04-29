<%-- 
    Document   : PaivitaLaji
    Created on : Apr 22, 2013, 7:40:16 PM
    Author     : sikuutti
--%>

<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="tyyli.css" />
        <title>Päivitä harjoitus</title>
    </head>
    <body>
        <jsp:useBean id="lista" class="fi.helsinki.sikuutti.beans.HakuHarjoitus" scope="request" />

        <h1>Lisää, päivitä tai poista laji</h1>

        <form action="PaivitaHarjoitus" method="post">
            <table border="1">
                <td>Valitse poisto</td>
                <td>id</td>
                <td>Laji</td>
                <td>Kalorit tunnissa/kilo</td>
                <c:forEach var="alkio" items="${lista.harjoitukset}">
                    <c:set var="osa" value="${fn:split(alkio, ':')}" />                
                    <tr>


                    </tr>
                    <tr>
                        <td><input type="checkbox" name="box" value="${osa[0]}"</td>
                        <td><input type="hidden" name="id"  value="${osa[0]}"></td>
                        <td><input type="text" name="laji" readonly value="${osa[1]}"></td>
                        <td><input type="text" name="kalori" readonly value="${osa[2]}"></td>

                    </tr>

                </c:forEach>

                <input type="submit" value="submit">      



            </table>




        </form> 

        <form action="PaivitaHarjoitus" method="get">
            Päivitettävä rivi
            <table>

                <td>id</td>
                <td>Laji</td>
                <td>Kalorit tunnissa/kilo</td>

                <tr>                   

                    <td><input type="hidden" name="id_p"  value="${plista[0]}"></td>
                    <td><input type="text" name="laji_p"  value="${plista[1]}"></td>
                    <td><input type="text" name="kalori_p"  value="${plista[2]}"></td>
                    <td><input type="radio" name="tehtava"  value="poista">Poista</td>
                    <td><input type="radio" name="tehtava"  value="paivita">päivitä</td>
                    <td><input type="radio" name="tehtava"  value="lisaa" checked>lisaa</td>
                </tr>
                <input type="submit" value="submit">  
            </table>
        </form>
                    <p> <a href="Paivakirja.jsp">Paluu<td> </p>
    </body>
</html>
