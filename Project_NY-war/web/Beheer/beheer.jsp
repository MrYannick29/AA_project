<%-- 
    Document   : beheer
    Created on : 18-nov-2021, 11:14:47
    Author     : r0744479
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Beheer</title>
    </head>
    <body>
        <h1>Dag Beheerder</h1>
        <h3>Wat wilt u doen?</h3>
        
        <form method="post" action="<c:url value='/BeheerServlet'/>">
            <label for="burgerid">BurgerID: </label>
            <input type="text" name="burgerid">
            <select name="actie" id="actie">
                <option value="1">Voeg nieuw certificaat toe</option>
                <option value="2">Pas certificaat aan</option>
                <input type="submit" name="submitknop" value="GA">
            </select>
    
        </form>
    </body>
</html>
