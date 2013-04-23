<%-- 
    Document   : Valikko
    Created on : Mar 15, 2013, 12:08:16 PM
    Author     : sikuutti
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="tyyli.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Päiväkirjasivu</title>
    </head>
    <jsp:useBean id="lista" class="fi.helsinki.sikuutti.beans.HakuLista" scope="request" />



    <%!        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String paiva = sdf.format(date);

    %>

    <%!
    %>
    <body>
        <h1>   
            <form action="Paivakirja" method="post">
                <h1> Lisää harjoitus</h1>
                <table>
                    <tr><td>Laji</td><td><select name="laji">

                                <%for (int a = 0; a < lista.getLista().size(); a++) {%> 
                                <%String arvo = (String) lista.getLista().get(a);%>


                                <option value="<%out.print(arvo);%>"><%out.print(arvo);%></option>
                                <%}%>         
                            </select></td></tr>
                    <tr><td>Päivämäärä</td><td><input type="text" name="pvm" value="<%out.print(paiva);%>"></td></tr>
                    <tr><td>Kesto</td><td><input type="text" name="kesto" /></td></tr>
                    <tr><td>Fiilis</td><td><select name="fiilis"><option value="Ihanaa">Ihanaa</option>
                                <option value="Loistavaa">Loistavaa</option>
                                <option value="Mahtavaa">Mahtavaa</option>
                            </select><input type="submit" value="Lähetä"></td></tr>



                    <td></td>

                    </tr>
                </table>    
            </form>
            <h1>Tämän päivän syötetyt harjoitukset</h1>
            <table border="1">
                <tr> 
                    <td id='o'>Laji</td>
                    <td id='o'>Kesto/h</td>
                    <td id='o' >Kulutus/kalori</td>
                    <td id='o'>Fiilis</td>

                </tr>
                <c:forEach var="alkio" items="${harkat}">
                    <c:set var="osa" value="${fn:split(alkio, ':')}" />                
                    <tr><td>${osa[0]}</td>
                        <td>${osa[1]}</td>
                        <td>${osa[2]}</td>
                        <td>${osa[3]}</td>                   
                    </tr>

                </c:forEach>


            </table>

            <form action="Lista" method="post">
                <h1>Harjoituslista</h1>
                <table>


                    <tr><td>Alku</td><td><input type="text" name="alku" /></td>
                    </tr>
                    <tr>   <td>Loppuaika</td><td><input type="text" name="loppu" /></td>
                    </tr>

                    <tr>
                        <td></td>   <td><input type="submit" value="Hae tiedot"></td></td> 

                    </tr>
                </table>
            </form>
            <h1>


                <p><a href="<%=request.getContextPath()%>/Logout">Kirjaa ulos käyttäjä <%out.print(request.getAttribute("Nimi"));%></td></p>
                <p>  <a href="Paivita.jsp">Päivitä lajitiedot<td></p>    

                            <p> <a href="Paivita_kayttaja.jsp">Päivitä omat tiedot<td> </p>       
                                        </h1>     

                                        </body>
                                        </html>