<%-- 
    Document   : burger
    Created on : 18-nov-2021, 11:38:18
    Author     : Yannick Saelen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../opmaak.css">
        <title>Burger</title>
    </head>
    <body>
        <h1>Dag Burger</h1>
        <h3>Uw certificaten overzicht</h3>
        <form method="post" action="<c:url value='/BurgerServlet'/>">
            <input type="submit" name="submitknop" value="Toon mijn certificaten">
        </form>
        <h4>Testcertificaten</h4>
        <div class="line"></div>
        
        <table>
            <tr>
                <th>Tcid</th>
                <th>Date</th>
                <th>Resultaat</th>
            </tr>
            <c:forEach var="tests" items="${sessionScope.tests}">
                <tr>
                    <td><c:out value="${tests.getTcid()}"/></td>
                    <td><c:out value="${tests.getDtm()}"/></td>
                    <td><c:out value="${tests.getRes()}"/></td>
                </tr>
            </c:forEach>
        </table>
        
        <h4>Vaccincertificaten</h4>
        <div class="line"></div>
        <table>
            <tr>
                <th>Vcid</th>
                <th>Date</th>
                <th>Soort</th>
                <th>Dosis</th>
            </tr>
            <c:forEach var="vaccins" items="${sessionScope.vaccins}">
                <tr>
                    <td><c:out value="${vaccins.getVcid()}"/></td>
                    <td><c:out value="${vaccins.getDtm()}"/></td>
                    <td><c:out value="${vaccins.getSoort()}"/></td>
                    <td><c:out value="${vaccins.getNr()}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
    <%@ include file="../footer.jsp" %>
</html>
