<%-- 
    Document   : scannen
    Created on : 18-nov-2021, 11:40:04
    Author     : r0744479
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scannen</title>
    </head>
    <body>
        <h1>Hallo</h1>
        <h3>Geef de code die u wilt scannen</h3>
        <form method="post" action="<c:url value='/ScanServlet'/>">
            <label for="certificaatidid">CertificaatID: </label>
            <input type="text" name="certificaatid">
            <input type="submit" name="submitknop" value="scanid">
        </form>
    </body>
    <%@ include file="../footer.jsp" %>
</html>
