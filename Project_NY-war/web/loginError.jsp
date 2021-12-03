<%-- 
    Document   : loginError
    Created on : 4-nov-2021, 11:10:48
    Author     : r0744479
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Error</h1>
        <p>Geen Toegang</p>
        <form method="post" action="<c:url value='/IndexServlet'/>">
        <input type="submit" name="submitknop" value="logout" >
        </form>
    </body>
</html>

<!-- oplossing aan het zoeken voor een foute inlog, momenteel kan ik indexServlet niet aanpassen ( voor god weet welke reden, thanks GitHub) -->
