<%-- 
    Document   : aanpassen
    Created on : 18-nov-2021, 11:15:15
    Author     : Yannick Saelen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aanpassen</title>
    </head>
    <body>
        <h1>Certificaat aanpassen</h1>
        <p>
        <form method="post" action="<c:url value='/BeheerServlet'/>">
            <input type="text" name="ID" value="ID"/>
            <select name="CerType" id="CerType">
                <option value="vaccin">Vaccin</option>
                <option value="test">Test</option>
            </select>
            <input type="submit" name="submitknop" value="Get Certificaat"/>
        </form>
        </p>
        <h4>Test certificate</h4>
        <p>
        <form method="post" action="<c:url value='/BeheerServlet'/>">
            <table>
                    <tr>
                        <th>ID:</th>
                        <th>Datum:</th>
                        <th>Result:</th>
                    </tr>
                <c:forEach var="tests" items="${sessionScope.Testcertificate}">
                    <tr>
                        <td><input type="text" name="id" <c:out value="${tests.getTcid()}"/>></td>
                        <td><input type="text" name="date" <c:out value="${tests.getDtm()}"/>></td>
                        <td><input type="text" name="result" <c:out value="${tests.getRes()}"/>></td>
                    </tr>
                    
                </c:forEach>
            </table>
            <input type="submit" name="submitknop" value="Update Test"/> 
        </form>
        </p>
        <h4>Test certificate</h4>
        <p>
        <form method="post" action="<c:url value='/BeheerServlet'/>">
            <table>
                    <tr>
                        <th>ID:</th>
                        <th>Datum:</th>
                        <th>Soort:</th>
                    </tr>
                <c:forEach var="tests" items="${sessionScope.Vaccincertificate}">
                    <tr>
                        <td><input type="text" name="id" <c:out value="${tests.getVcid()}"/>></td>
                        <td><input type="text" name="date" <c:out value="${tests.getDtm()}"/>></td>
                        <td><input type="text" name="result" <c:out value="${tests.getSoort()}"/>></td>
                    </tr>
                    
                </c:forEach>
            </table>
            <input type="submit" name="submitknop" value="Update Vaccin"/> 
        </form>
        </p>
    </body>
    <%@ include file="../footer.jsp" %>
</html>
