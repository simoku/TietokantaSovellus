<%-- 
    Document   : Lista
    Created on : Apr 18, 2013, 9:48:33 AM
    Author     : sikuutti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="tyyli.css" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Harjoitus lista</title>
    </head>
    <body>
        
        <h1>Harjoitukset ajalta <%out.print(request.getParameter("alku"));%>-<%out.print(request.getParameter("loppu"));%></h1>
        <table>
            
            
            <tr><td>Laji</td>
                    <td>Kulutettu aika</td>
                    <td>Kulutetut kalorit</td>

                </tr>
            <c:forEach var="alkio" items="${harkat}">
                <c:set var="osa" value="${fn:split(alkio, ':')}" />                
                <tr><td>${osa[0]}</td>
                    <td>${osa[1]} tuntia</td>
                    <td>${osa[2]} kaloria</td>

                </tr>

            </c:forEach>
<td><a href="<%=request.getContextPath()%>/Paivakirja">Päiväkirjaan<td></td>
        </table>



    </body>
</html>