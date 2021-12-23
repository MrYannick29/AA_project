<%-- 
    Document   : toevoegen
    Created on : 18-nov-2021, 11:15:03
    Author     : Yannick Saelen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../opmaak.css">
        <title>Toevoegen</title>
    </head>
    <body>
        <h1>Certificaat toevoegen</h1>
        <h3>VaccinCertificaat</h3>
        <div class="line"></div>
        
        <form method="post" action="<c:url value='/BeheerServlet'/>">
            <table>
                <tr>
                    <td><label for="datum">Datum: </label></td>
                    <td><input type="date" name="datum"><br></td>
                </tr>
                <tr>
                    <td><label for="soort">Soort:  </label></td>
                    <td><select name="soort">
                            <option value="Pfizer">Pfizer</option>
                            <option value="Moderna">Moderna</option>
                            <option value="AstraZeneca">AstraZeneca</option>
                            <option value="Janssens">Janssens</option>
                        </select></td>
                </tr>
                <tr>
                    <td><label for="dosis">Dosis: </label></td>
                    <td><input type="number" name="dosis"><br></td>
                </tr>
            </table>
            <input type="submit" name="submitknop" value="Vaccin Toevoegen">
        </form>
            
        <h3>TestCertificaat</h3>
        <div class="line"></div>
        
        <form method="post" action="<c:url value='/BeheerServlet'/>">
            <table>
                <tr>
                    <td><label for="datum">Datum: </label></td>
                    <td><input type="date" name="datum"><br></td>
                </tr>
                <tr>
                    <td><label for="res">Resultaat:  </label></td>
                    <td><select name="res">
                            <option value="0">Negatief</option>
                            <option value="1">Positief</option>
                        </select></td>
                </tr>
            </table>
            <input type="submit" name="submitknop" value="Test Toevoegen">
        </form>
    </body>
    <%@ include file="../footer.jsp" %>
</html>
