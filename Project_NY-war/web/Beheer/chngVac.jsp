<%-- 
    Document   : chngVac
    Created on : 31-Dec-2021, 02:32:32
    Author     : Niels Serlet 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../opmaak.css">
        <title>Vaccinatie aanpassen</title>
    </head>
    <script>
        <%-- 
        ========================================================================================
        // Function to validate the input of the form, before it gets send to the server
        ========================================================================================        
        --%>
        function validateVacForm()
        {
            if(document.VacChng.soort.value!="<c:out value="${sessionScope.VacSoort }" />")
            {
                if(confirm("Verschil in vaccin ")!=true){
                   return false ;
               }
            }
            if(document.VacChng.soort.value=="Janssens" && document.Vacadd.dosis.value!="1" )
            {
                if(confirm("2de Janssens vaccinatie?")!=true){
                   return false ;
               }
            }
            if(document.VacChng.datum.value=="")
            {
                alert("Gelieve een datum in te vullen!");
                document.VacChng.datum.focus();
                return false;
            }
    
        }
        
        
    </script>
    <body>
        <%-- 
        ========================================================================================
        // Changing a vaccin certificate 
        ========================================================================================        
        --%>
        <h1>Vaccin certificate</h1>
        <div class="line"></div>
        <p>
        <form name="VacChng" method="post" action="<c:url value='/BeheerServlet'/>" onSubmit="return validateVacForm()">
            <table>
                    <tr>
                        <th>Burger ID:</th>
                        <th>ID:</th>
                        <th>Datum:</th>
                        <th>Soort:</th>
                        <th>Dosis:</th>
                        
                    </tr>
                
                    <tr>
                        <td><input type="text" name="bid" value="<c:out value="${sessionScope.VacBID}"/>"></td>
                        <td><input type="text" name="id" value="<c:out value="${sessionScope.VacID}"/>"></td>
                        <td><input type="date" name="datum" value="<c:out value="${sessionScope.VacDate}"/>"></td>
                        <td><select name="soort" id="soort" onchange="">
                                
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
        <div class="line"></div>
    </body>
</html>