<%-- 
    Document   : index
    Created on : 18-nov-2021, 10:51:15
    Author     : Yannick Saelen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="opmaak.css">
        <title>Welkom</title>
    </head>
    <body>
        <h1>Welkom bij CovidScan</h1>
        <h3>Wat wilt u doen?</h3>
        <div class="line"></div>
        <form method="post" action="<c:url value='/IndexServlet'/>">
            <input type="submit" name="submitknop" value="Aanpassen">
            <input type="submit" name="submitknop" value="Opvragen">
            <input type="submit" name="submitknop" value="Scannen">
        </form>
    </body>
    <%@ include file="footer.jsp" %>
</html>
