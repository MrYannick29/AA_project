<%-- 
    Document   : aanpassen
    Created on : 18-nov-2021, 11:15:15
    Author     : Niels Serlet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" type="text/css" href="../opmaak.css">
        <title>Aanpassen</title>
    </head>
    <script>
        <%-- 
        ========================================================================================
        // Functions to insure a certificate isn't deleted by accident 
        ========================================================================================        
        --%>
        function confrmDelete(){
            if(confirm("Bent u zeker dat u dit certificaat wilt verwijderen?")!=true){
                   return false ;
               }
        }
    </script>
    <body>
        <h1>Certificaat aanpassen</h1>
        <%-- 
        ========================================================================================
        // Input for the civilian ID
        ========================================================================================        
        --%>
        <p>
            <form method="post" action="<c:url value='/BeheerServlet'/>">

                <label for="burgerid">BurgerID: </label>
                <input type="text" name="burgerid">            
                <input type="submit" name="submitknop" value="Toon Certificaten om aan te passen">

            </form>
        
        </p>
        <%-- 
        ========================================================================================
        // Showing all certificates 
        ========================================================================================        
        --%>
        <h2>Overzicht van certificaten</h2>
        <h4>Testcertificaten</h4>
        <div class="line"></div>
        
        <table>
            <tr>
                <th>Tcid</th>
                <th>Date</th>
                <th>Resultaat</th>
                <th>Aanpassen?</th>
                <th>Delete?</th>
            </tr>
            <%-- 
            ========================================================================================
            // Showing Test certificates with 2 buttons: change and delete
            ========================================================================================        
            --%>
            <c:forEach var="tests" items="${sessionScope.tests}">
                <tr>
                    <td><c:out value="${tests.getTcid()}"/></td>
                    <td><c:out value="${tests.getDtm()}"/></td>
                    <td><c:out value="${tests.getRes()}"/></td>
                    
                    <td>
                        <form method="post" action="<c:url value='/BeheerServlet'/>">
                            <input type="hidden" name="CerType" value="test">
                            <input type="hidden" name="ID" value="<c:out value="${tests.getTcid()}"/>" />
                            <input type="submit" name="submitknop" value="Aanpassen"/>
                        </form>
                    </td>
                     <td>
                        <form method="post" action="<c:url value='/BeheerServlet'/>"onSubmit="return confrmDelete()">
                            <input type="hidden"name="CerType" value="test">
                            <input type="hidden" name="TID" value="<c:out value="${tests.getTcid()}"/>" >
                            <input type="submit" name="submitknop" value="Delete">
                        </form>
                    </td>
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
                <th>Aanpassen?</th>
                <th>Delete?</th>
            </tr>
            <%-- 
            ========================================================================================
            // Showing Vaccin certificates with 2 buttons: change and delete
            ========================================================================================        
            --%>
            <c:forEach var="vaccins" items="${sessionScope.vaccins}">
                <tr>
                    <td><c:out value="${vaccins.getVcid()}"/></td>
                    <td><c:out value="${vaccins.getDtm()}"/></td>
                    <td><c:out value="${vaccins.getSoort()}"/></td>
                    <td><c:out value="${vaccins.getNr()}"/></td>
                    <td>
                        <form method="post" action="<c:url value='/BeheerServlet'/>">
                            <input type="hidden" name="CerType" value="vaccin">
                            <input type="hidden" name="ID" value="<c:out value="${vaccins.getVcid()}"/>" />
                            <input type="submit" name="submitknop" value="Aanpassen"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="<c:url value='/BeheerServlet'/>"onSubmit="return confrmDelete()">
                            <input type="hidden" name="CerType" value="vaccin">
                            <input type="hidden" name="VID" value="<c:out value="${vaccins.getVcid()}"/>" />
                            <input type="submit" name="submitknop" value="Delete"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
                
        <div class="line"></div>    
        
        
        
        
    </body>
    <%@ include file="../footer.jsp" %>
</html>
