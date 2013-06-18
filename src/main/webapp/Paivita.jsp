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
        <%String id = (String) session.getAttribute("id");%>
        <%request.setAttribute("id", id);%>
        <h1>Päivittääksesi tai poistaaksesi lajin ohjelmasta valitse se alla olevasta taulukosta ja paina HAE TIEDOT-painiketta</h1>

        <form action="PaivitaHarjoitus" method="post">
            <table  border="1">
                <td>Valitse rivi</td>

                <td>Laji</td>
                <td>Kalorit tunnissa/kilo</td>
                <c:forEach var="alkio" items="${lista.harjoitukset}">
                    <c:set var="osa" value="${fn:split(alkio, ':')}" />                
                    <tr>


                    </tr>
                    <tr>
                        <td><input type="checkbox" name="box" value="${osa[0]}"</td>
                    <input type="hidden" name="id"  value="${osa[0]}">
                    <td><input type="text" name="laji" readonly value="${osa[1]}"></td>
                    <td><input type="text" name="kalori" readonly value="${osa[2]}"></td>

                    </tr>

                </c:forEach>





            </table>

            <input type="submit" value="HAE TIEDOT">     


        </form> 

        <form action="PaivitaHarjoitus" method="get">
            <h1>Tee muutos alla olevalle riville ja paina MUUTA-painiketta</h1>
            <h1>POISTA-painike poistaa valitun rivin </h1>
            <h1>Uuden lajin tiedot voit syöttää suoraan riveille ja tallettaa UUSI-painikkeesta </h1>
            <table>


                <td>Laji</td>
                <td>Kalorit tunnissa/kilo</td>

                <tr>                   

                <input type="hidden" name="id_p"  value="${plista[0]}">
                <td><input type="text" name="laji_p"  value="${plista[1]}"></td>
                <td><input type="text" name="kalori_p"  value="${plista[2]}"></td>

                </tr>

            </table>
            <input type="submit" name="p" value="POISTA"> 
            <input type="submit" name="p"value="MUUTA">
            <input type="submit" name="p"value="UUSI">
        </form>
        <h3>
            <p><c:out value="${viesti}"/></p>          
            <%request.setAttribute("id", id);%>

        </h3>
        <p> <a href="<%=request.getContextPath()%>/Paivakirja">Paluu<td> </p>
                    </body>
                    </html>
