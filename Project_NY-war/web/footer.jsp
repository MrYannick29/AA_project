<%-- 
    Document   : footer
    Created on : 3-dec-2021, 14:58:21
    Author     : Yannick Saelen

//======================================================================================================================================
//Footer, can be included in every page
//contains the "logout" and "home" button
//======================================================================================================================================
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<footer>
    <form method="post" action="<c:url value='/IndexServlet'/>">
        <input type="submit" name="submitknop" value="logout" >
        <input type="submit" name="submitknop" value="Home" >
        </form>
    </form>
</footer>

