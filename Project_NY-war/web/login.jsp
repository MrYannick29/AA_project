<%-- 
    Document   : loginError
    Created on : 4-nov-2021, 11:10:48
    Author     : Yannick Saelen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../opmaak.css">
        <title>login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="post" action="j_security_check">
            <input type="text" name="j_username">
            <input type="password" name="j_password">
            <input type="submit" name="knop">
        </form>
    </body>
    <%@ include file="footer.jsp" %>
</html>

