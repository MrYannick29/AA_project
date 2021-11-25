<%-- 
    Document   : burger
    Created on : 18-nov-2021, 11:38:18
    Author     : r0744479
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Burger</title>
    </head>
    <body>
        <h1>Dag Burger</h1>
        <h3>Uw certificaten overzicht</h3>
        <form method="post" action="<c:url value='/BurgerServlet'/>">
            <input type="submit" name="submitknop" value="Toon mijn certificaten">
        </form>
        <h4>Testcertificaten</h4>
        
        <table>
            <c:forEach var="tests" items="${sessionScope.tests}">
                <tr>
                    <td><c:out value="${tests.getTcid()}"/></td>
                    <td><c:out value="${tests.getDtm()}"/></td>
                    <td><c:out value="${tests.getRes()}"/></td>
                </tr>
            </c:forEach>
        </table>
        
        <h4>Vaccincertificaten</h4>
        <table>
            <c:forEach var="vaccins" items="${sessionScope.vaccins}">
                <tr>
                    <td><c:out value="${vaccins.getVcid()}"/></td>
                    <td><c:out value="${vaccins.getDtm()}"/></td>
                    <td><c:out value="${vaccins.getSoort()}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
