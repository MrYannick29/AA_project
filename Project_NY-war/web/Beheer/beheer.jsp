<%-- 
    Document   : beheer
    Created on : 18-nov-2021, 11:14:47
    Author     : Yannick Saelen & Niels Serlet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../opmaak.css">
        <title>Beheer</title>
    </head>
    <body>
        <h1>Dag Beheerder</h1>
        <h3>Wat wilt u doen?</h3>
        <div class="line"></div>
        <%-- 
        ========================================================================================
        // Form to choose between adding a new certificate or change a certificate
        ========================================================================================        
        --%>
        
        <form method="post" action="<c:url value='/BeheerServlet'/>">
            
            <select name="actie" id="actie">
                <option value="1">Voeg nieuw certificaat toe</option>
                <option value="2">Pas certificaat aan</option>
                <input type="submit" name="submitknop" value="GA">
            </select>
    
        </form>
    </body>
    <%@ include file="../footer.jsp" %>
</html>

