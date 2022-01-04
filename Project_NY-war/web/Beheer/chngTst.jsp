<%-- 
    Document   : chngTst
    Created on : 31-Dec-2021, 02:33:16
    Author     : Niels
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../opmaak.css">
        <title>Test aanpassen</title>
    </head>
    <script>
        function validateTestFrom(){
            if(document.Testchng.datum.value=="")
            {
                alert("Gelieve een datum in te vullen!");
                document.Testchng.datum.focus();
                return false;
            }
        }
    </script>
    <body>
    <h1>Test certificate</h1>
    <div class="line"></div>
        <p>
        <form name="Testchng" method="post" action="<c:url value='/BeheerServlet'/>" onSubmit="return validateTestFrom()">
            <table>
                    <tr>
                        <th>Burger ID:</th>
                        <th>ID:</th>
                        <th>Datum:</th>
                        <th>Result:</th>
                    </tr>
                
                    <tr>
                        <td><input type="text" name="bid" value="<c:out value="${sessionScope.TestBID}"/>"></td>
                        <td><input type="text" name="id" value="<c:out value="${sessionScope.TestID}"/>"></td>
                        <td><input type="date" name="datum" value="<c:out value="${sessionScope.TestDate}"/>"></td>
                        <td><input type="text" name="result" value="<c:out value="${sessionScope.TestResult}"/>"></td>
                    </tr>
                    
               
            </table>
            <input type="submit" name="submitknop" value="Update Test"/> 
        </form>
        </p>
        <div class="line"></div>
    </body>
</html>
