<%-- 
    Document   : Selaus
    Created on : Mar 15, 2013, 2:09:04 PM
    Author     : sikuutti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="tyyli.css" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Selaus</title>
   
    <h1>Kalorilaskuri</h1>   
    <form action="Selaus" methot="post">
        <table>

            <tr><td>Paino</td><td><input type="text" name="paino1" value="${paino1}"/> kg</td></tr>

            <tr><td>Aika</td><td><input type="text" name="aika"value="${aika}" />tuntia</td></tr>
            <tr><td>Laji</td><td><select name="laji">

                        <c:forEach var="alkio" items="${lista}">
                            <option value="${alkio}">${alkio}</option>
                        </c:forEach>
                    </select></td></tr>



            <td><input type="submit" value="Hae"></td>
            <td><a href="index.jsp">Kirjaantuminen<td></td>
                    </tr>
        </table>    

        <p><c:out value="${viesti}"/></p>





    </head>
    <body>






        </html>