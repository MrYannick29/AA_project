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
            <input type="text" name="ID"  />
            <select name="CerType" id="CerType">
                <option value="test">Test</option>
                <option value="vaccin">Vaccin</option>
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
                
                    <tr>
                        <td><input type="text" name="id" value="<c:out value="${sessionScope.TestID}"/>"></td>
                        <td><input type="date" name="datum" value="<c:out value="${sessionScope.TestDate}"/>"></td>
                        <td><input type="text" name="result" value="<c:out value="${sessionScope.TestResult}"/>"></td>
                    </tr>
                    
               
            </table>
            <input type="submit" name="submitknop" value="Update Test"/> 
        </form>
        </p>
        <h4>Vaccin certificate</h4>
        <p>
        <form method="post" action="<c:url value='/BeheerServlet'/>">
            <table>
                    <tr>
                        <th>ID:</th>
                        <th>Datum:</th>
                        <th>Soort:</th>
                        <th>Dosis:</th>
                        
                    </tr>
                
                    <tr>
                        <td><input type="text" name="id" value="<c:out value="${sessionScope.VacID}"/>"></td>
                        <td><input type="date" name="datum" value="<c:out value="${sessionScope.VacDate}"/>"></td>
                        <td><select name="soort">
                                
                                <c:forEach var="Soorten" items="${sessionScope.VaccinSoorten}">
                                    
                                        <option value="${Soorten}" ${Soorten == sessionScope.VacSoort ? 'selected' : ''}>
                                            ${Soorten}
                                        </option>
                                    
                                </c:forEach>
                            
                        </select></td>
                                
                        <td><input type="text" name="dosis" value="<c:out value="${sessionScope.VacNr}"/>"></td>
                    </tr>                  
                
            </table>
            <input type="submit" name="submitknop" value="Update Vaccin"/> 
        </form>
        </p>
    </body>
    <%@ include file="../footer.jsp" %>
</html>
