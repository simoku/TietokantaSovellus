<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css" href="tyyli.css" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256"> 
        <title>Sisäänkirjaantuminen</title>
    </head> 
    <body> 
        <form action="Login" method="post">

            <table>
                <tr>
                    <td>Käyttäjätunnus</td>
                    <td><input type="text" name="user"/></td>
                </tr>
                <tr>
                    <td>Salasana</td>
                    <td><input type="password" name="pw"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>

                <tr>

                    <td></td>
                    <td><input type="submit" value="submit"></td>
                </tr>

                <tr><td><a href="UusiKayttaja.jsp">uusi käyttäjä<td><a href="<%=request.getContextPath()%>/Selaus">selaus</td></tr>
            </table></form>
        
        <p><c:out value="${viesti}"/></p>
</body>
</html>