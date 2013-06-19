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
    <jsp:useBean id="viikko" class="fi.helsinki.sikuutti.beans.HakuViikko" scope="request" />


    <%!        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String paiva = sdf.format(date);

    %>

    <%!
    %>
    <body>
        <h2>   
            <form action="Paivakirja" method="post">
                <h2> Lisää harjoitus</h2>
                <table>
                    <tr><td>Laji</td><td><select name="laji">

                                <%for (int a = 0; a < lista.getLista().size(); a++) {%> 
                                <%String arvo = (String) lista.getLista().get(a);%>


                                <option value="<%out.print(arvo);%>"><%out.print(arvo);%></option>
                                <%}%>         
                            </select></td></tr>
                    <tr><td>Päivämäärä (pp.kk.vvvv)</td><td><input type="text" name="pvm" value="<%out.print(paiva);%>"></td></tr>
                    <tr><td>Kesto (tunti)</td><td><input type="text" name="kesto" /></td></tr>
                    <tr><td>Fiilis</td><td><select name="fiilis"><option value="Ihanaa">Ihanaa</option>
                                <option value="Loistavaa">Loistavaa</option>
                                <option value="Mahtavaa">Mahtavaa</option>
                            </select><input type="submit" value="Lähetä"></td></tr>



                    <td></td>

                    </tr>
                </table>    
            </form>

            <h3>
                <p color="red"><c:out value="${viesti}"/></p> 

            </h3>

                <h2>Tämän päivän harjoitukset<h2>
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
                <h2> Viikon harjoitukset<h2>
                <table border="1">
                <tr> 
                    <td id='o'>Päivä</td>
                    <td id='o'>Laji/h</td>
                    <td id='o' >Kesto h</td>
                    <td id='o'>Kulutetut kalorit</td>

                </tr>
                <c:forEach var="alkio" items="${vko_harkat}">
                    <c:set var="osa" value="${fn:split(alkio, ':')}" />                
                    <tr><td>${osa[0]}</td>
                        <td>${osa[1]}</td>
                        <td>${osa[2]}</td>
                        <td>${osa[3]}</td>                   
                    </tr>

                </c:forEach>


            </table>
            <form action="Lista" method="post">
                <h2>Harjoituslista</h2>
                <table>


                    <tr><td>Alku (pp.kk.vvvv)</td><td><input type="text" name="alku" /></td>
                    </tr>
                    <tr>   <td>Loppuaika (pp.kk.vvvv)</td><td><input type="text" name="loppu" /></td>
                    </tr>

                    <tr>
                        <td></td>   <td><input type="submit" value="Hae tiedot"></td></td> 

                    </tr>
                </table>
            </form>

            <h2>
                <p><a href="<%=request.getContextPath()%>/Logout">Kirjaa ulos käyttäjä <%out.print(request.getAttribute("Nimi"));%></td></p>
                <p>  <a href="Paivita.jsp">Päivitä lajitiedot<td></p>    

                            <p> <a href="Paivita_kayttaja.jsp">Päivitä omat tiedot<td> </p>       
                                        </h2>     

                                        </body>
                                        </html>