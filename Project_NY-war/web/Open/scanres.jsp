<%-- 
    Document   : scanres
    Created on : 2-dec-2021, 20:32:18
    Author     : Yannick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../opmaak.css">
        <title>ScanRes</title>
    </head>
    <body>
        <h1>Resultaat</h1>
        <c:if test="${!empty sessionScope.burgerID}">
            <h3>BurgerID: <c:out value="${sessionScope.burgerID}"/></h3>
            <h3>status: <c:out value="${sessionScope.status}"/></h3>
        </c:if>
        <form method="post" action="<c:url value='/ScanServlet'/>">
            <input type="submit" name="submitknop" value="scanopnieuw">
        </form>
    </body>
    <%@ include file="../footer.jsp" %>
</html>
