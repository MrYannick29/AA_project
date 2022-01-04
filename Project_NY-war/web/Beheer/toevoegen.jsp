<%-- 
    Document   : toevoegen
    Created on : 18-nov-2021, 11:15:03
    Author     : Yannick Saelen & Niels Serlet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>

<html>
    <script>
        <%-- 
        ========================================================================================
        // Functions to validate the input of the forms, before they get send to the server
        ========================================================================================        
        --%>
        function validateVacForm()
        {
            if(document.Vacadd.soort.value!="<c:out value="${sessionScope.VacSoort }" />")
            {
                if(confirm("Verschil in vaccin ")!=true){
                   return false ;
               }
            }
            if(document.Vacadd.soort.value=="Janssens" && document.Vacadd.dosis.value!="1" )
            {
                if(confirm("2de Janssens vaccinatie?")!=true){
                   return false ;
               }
            }
            if(document.Vacadd.datum.value=="")
            {
                alert("Gelieve een datum in te vullen!");
                document.Vacadd.datum.focus();
                return false;
            }
    
        }
        function validateTestFrom(){
            if(document.Testadd.datum.value=="")
            {
                alert("Gelieve een datum in te vullen!");
                document.Testadd.datum.focus();
                return false;
            }
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../opmaak.css">
        <title>Toevoegen</title>
    </head>
    <body>
        <h1>Certificaat toevoegen</h1>
        <%-- 
        ========================================================================================
        // Input for the civilian ID
        ========================================================================================        
        --%>
        <form method="post" action="<c:url value='/BeheerServlet'/>">
        
            <label for="burgerid">BurgerID: </label>
            <input type="text" name="burgerid">            
            <input type="submit" name="submitknop" value="Toon Certificaten">

        </form>
        <%-- 
        ========================================================================================
        // Showing previous vaccinations 
        ========================================================================================        
        --%>
        <h3>VaccinCertificaat</h3>
        <div class="line"></div>
        <h4>Vorige Vaccinaties</h4>
        <table>
            <tr>
                <th>Vcid</th>
                <th>Date</th>
                <th>Soort</th>
                <th>Dosis</th>
            </tr>
            <c:forEach var="vaccins" items="${sessionScope.vaccins}">
                <tr>
                    <td><c:out value="${vaccins.getVcid()}"/></td>
                    <td><c:out value="${vaccins.getDtm()}"/></td>
                    <td><c:out value="${vaccins.getSoort()}"/></td>
                    <td><c:out value="${vaccins.getNr()}"/></td>
                </tr>
            </c:forEach>
        </table>        
        
        <div class="line"></div>
        <%-- 
        ========================================================================================
        // Adding a new vaccin certificate 
        ========================================================================================        
        --%>
        
        <form name="Vacadd"  method="post" action="<c:url value='/BeheerServlet'/> " onSubmit="return validateVacForm()">
            
            <table>
                <tr>
                    <td><label for="datum">Datum: </label></td>
                    <td><input type="date" name="datum"><br></td>
                </tr>
                <tr>
                    <td><label for="soort">Soort:  </label></td>
                    <td><select name="soort">
                                
                                <c:forEach var="Soorten" items="${sessionScope.VaccinSoorten}">
                                    
                                        <option value="${Soorten}" ${Soorten == sessionScope.VacSoort ? 'selected' : ''}>
                                            ${Soorten}
                                        </option>
                                    
                                </c:forEach>
                            
                        </select></td>
                </tr>
                <tr>
                    <td><label for="dosis">Dosis: </label></td>
                    <td><input type="number" name="dosis" value="<c:out value="${sessionScope.VacNrNxt}"/>"><br></td>
                </tr>
            </table>
            <input type="submit" name="submitknop" value="Vaccin Toevoegen">
        </form>
        
        <%-- 
        ========================================================================================
        // Adding a new test certificate 
        ========================================================================================        
        --%>        
        <h3>TestCertificaat</h3>
        <div class="line"></div>
        
        <form name="Testadd" method="post" action="<c:url value='/BeheerServlet'/>" onSubmit="return validateTestFrom()">
            
            <table>
                <tr>
                    <td><label for="datum">Datum: </label></td>
                    <td><input type="date" name="datum"><br></td>
                </tr>
                <tr>
                    <td><label for="res">Resultaat:  </label></td>
                    <td><select name="res">
                            <option value="0">Negatief</option>
                            <option value="1">Positief</option>
                        </select></td>
                </tr>
            </table>
            <input type="submit" name="submitknop" value="Test Toevoegen">
        </form>
    </body>
    <%@ include file="../footer.jsp" %>
</html>
