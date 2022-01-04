/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import beans.certificatenLocal;
import entitiy.NyTestcertificaat;
import entitiy.NyVaccincertificaat;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yannick Saelen & Niels Serlet
 */
public class BeheerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB private certificatenLocal certificaten;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BeheerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BeheerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessie;
        //setting all the possible vaccins in an Arraylist 
        ArrayList soorten = new ArrayList();
        soorten.add("Pfizer");soorten.add("Moderna");soorten.add("AstraZeneca");soorten.add("Janssens");
        
        //==========================================================================================================================================
        //check which submitbutton has been pressed, act accordingly
        //==========================================================================================================================================
        switch(request.getParameter("submitknop")){
            
            //======================================================================================================================================
            //Forward to the right beheer page
            //======================================================================================================================================
            case("GA"):
                sessie = request.getSession();
                //Check which page has been requested
                switch(request.getParameter("actie")){
                    case "1":
                        // Trying to clear previously set variables
                         try{
                            sessie.removeAttribute("vaccins");
                            sessie.removeAttribute("VacSoort");
                            sessie.removeAttribute("VacNrNxt");
                            sessie.removeAttribute("burger");
                            sessie.removeAttribute("vaccins");
                            sessie.removeAttribute("tests");
                        }
                        catch(Exception E){
                            
                        }
                         // Forward to right page
                        response.sendRedirect("Beheer/toevoegen.jsp");
                        break;
                    case "2":
                        // Trying to clear previously set variables
                        try{
                            sessie.removeAttribute("TestBID");
                            sessie.removeAttribute("TestID");
                            sessie.removeAttribute("DBDATE");
                            sessie.removeAttribute("TestDate");
                            sessie.removeAttribute("TestResult");
                        }
                        catch(Exception E){
                            
                        }
                        
                        try{
                            sessie.removeAttribute("burger");
                            sessie.removeAttribute("VacBID");
                            sessie.removeAttribute("VacID");
                            sessie.removeAttribute("DBDATE");
                            sessie.removeAttribute("VacDate");
                            sessie.removeAttribute("VacSoort");
                            sessie.removeAttribute("VacNr");
                            sessie.removeAttribute("Vaccincertificate");
                            sessie.removeAttribute("VacSelected");
                            
                            sessie.removeAttribute("burger");
                            sessie.removeAttribute("vaccins");
                            sessie.removeAttribute("tests");
                        }
                        catch(Exception E){
                            
                        }
                        // Forward to right page
                        response.sendRedirect("Beheer/aanpassen.jsp");
                        break;
                }
                break;
            //======================================================================================================================================
                
                
                
            //======================================================================================================================================
            //Prepping the page for changing a value 
            //======================================================================================================================================
            case("Aanpassen"):
                sessie = request.getSession();
                //Getting the certificate ID
                String certID = request.getParameter("ID");
                
                //Setting a default vaccin should the other method fail
                sessie.setAttribute("VacSoort", "Pfizer");
                
                //Checking which type of page has been requested 
                switch(request.getParameter("CerType")){
                    
                    case "test":
                        //Setting all the right attributes 
                        try{
                        NyTestcertificaat Tcert = certificaten.scanTestCertificaten(certID);
                        sessie.setAttribute("TestBID", Tcert.getBid().getGebruikersnaam());
                        sessie.setAttribute("TestID", Tcert.getTcid());
                        sessie.setAttribute("DBDATE", Tcert.getDtm());
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                        String strDate = dateFormat.format(Tcert.getDtm()); 
                        sessie.setAttribute("TestDate", strDate);
                        sessie.setAttribute("TestResult", Tcert.getRes());
                        }
                        catch(Exception E){
                            
                        }
                        // Removing all the non needed attributes
                        try{
                            sessie.removeAttribute("VacBID");
                            sessie.removeAttribute("VacID");
                            sessie.removeAttribute("DBDATE");
                            sessie.removeAttribute("VacDate");
                            sessie.removeAttribute("VacSoort");
                            sessie.removeAttribute("VacNr");
                            sessie.removeAttribute("Vaccincertificate");
                            sessie.removeAttribute("VacSelected");
                        }
                        catch(Exception E){
                            
                        }
                        
                        //forward to the right page
                        response.sendRedirect("Beheer/chngTst.jsp");
                        break;
                    case "vaccin":
                        //Setting all the right attributes for the page to function
                        try{
                        NyVaccincertificaat Vcert = certificaten.scanVaccinCertificaten(certID);
                        sessie.setAttribute("VacBID", Vcert.getBid().getGebruikersnaam());
                        sessie.setAttribute("VacID", Vcert.getVcid());
                        sessie.setAttribute("DBDATE", Vcert.getDtm());
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                        String strDate = dateFormat.format(Vcert.getDtm()); 
                        sessie.setAttribute("VacDate", strDate);
                        sessie.setAttribute("VacSoort", Vcert.getSoort());
                        sessie.setAttribute("VacNr",Vcert.getNr());
                        sessie.setAttribute("Vaccincertificate", Vcert);
                        
                        sessie.setAttribute("VaccinSoorten", soorten);
                        sessie.setAttribute("VacSelected","selected");
                        }
                        catch(Exception e){
                            
                        }
                        // Removing all the non needed attributes
                        try{
                            sessie.removeAttribute("TestBID");
                            sessie.removeAttribute("TestID");
                            sessie.removeAttribute("DBDATE");
                            sessie.removeAttribute("TestDate");
                            sessie.removeAttribute("TestResult");
                        }
                        catch(Exception E){
                            
                        }
                        response.sendRedirect("Beheer/chngVac.jsp");
                        break;
                }
                break;
            //======================================================================================================================================    
                
                
                
            //======================================================================================================================================
            //Updating a test in the database 
            //======================================================================================================================================
            case("Update Test"):
                sessie = request.getSession();
                
                String Tid = request.getParameter("id");
                String datumNT = request.getParameter("datum");
                String resN = request.getParameter("res");
                String bidNT = (String) request.getParameter("bid");
                certificaten.UpdateTestCertificaat(datumNT, resN, bidNT, Tid);
                response.sendRedirect("Beheer/beheer.jsp");
                break;
            //======================================================================================================================================    
             
                
            
            //======================================================================================================================================
            //Updating a vaccin in the database 
            //======================================================================================================================================
            case("Update Vaccin"):
                sessie = request.getSession();
                String Vid = request.getParameter("id");
                String datumNV = request.getParameter("datum");
                String Nsoort = request.getParameter("soort");
                String Ndosis = request.getParameter("dosis");
                String NbidV = (String) request.getParameter("bid");
                certificaten.UpdateVaccinCertificaat(datumNV, Nsoort, Ndosis, NbidV, Vid);
                response.sendRedirect("Beheer/beheer.jsp");
                break;
            //======================================================================================================================================    
                
                
            
            //======================================================================================================================================
            //Adding a vaccin in the database 
            //======================================================================================================================================
            case("Vaccin Toevoegen"):
                sessie = request.getSession();
                String datumV = request.getParameter("datum");
                String soort = request.getParameter("soort");
                String dosis = request.getParameter("dosis");
                String bidV = (String) sessie.getAttribute("burger");
                certificaten.setVaccinCertificaat(datumV, soort, dosis, bidV);
                
                response.sendRedirect("Beheer/beheer.jsp");
                break;
            //======================================================================================================================================
                
                
            
            //======================================================================================================================================
            //Updating a test in the database 
            //======================================================================================================================================
            case("Test Toevoegen"):
                sessie = request.getSession();
                String datumT = request.getParameter("datum");
                String res = request.getParameter("res");
                String bidT = (String) sessie.getAttribute("burger");
                certificaten.setTestCertificaat(datumT, res, bidT);
                
                response.sendRedirect("Beheer/beheer.jsp");
                break;
            //======================================================================================================================================
            
                
                
            //======================================================================================================================================
            //Showing all the vaccin certificates from the civilian in question when trying to add a new one
            //======================================================================================================================================
            case("Toon Certificaten"):
                sessie = request.getSession();                
                sessie.setAttribute("burger", request.getParameter("burgerid"));
                String BurgerID = (String) sessie.getAttribute("burger");
                
                //Getting all the certificates of a civilian in attributes to use on the website 
                List vaccins = certificaten.getVaccinCertificaten(BurgerID);
                List tests = certificaten.getTestCertificaten(BurgerID);
                sessie.setAttribute("vaccins", vaccins);
                sessie.setAttribute("tests", tests);
                
                //Setting the right attributes to preload the addition of a new vaccin certificate 
                NyVaccincertificaat lastCert = (NyVaccincertificaat) vaccins.get(vaccins.size()-1);
                String Soort = lastCert.getSoort();
                String nextVacnr = Integer.toString(lastCert.getNr() + 1);
                sessie.setAttribute("VacSoort", Soort);
                
                sessie.setAttribute("VacNrNxt", nextVacnr);
                
                //setting all possible vaccins in an attribute 
                sessie.setAttribute("VaccinSoorten", soorten);
                
                
                response.sendRedirect("Beheer/toevoegen.jsp");
                break;
            //======================================================================================================================================
                
                
                
            //======================================================================================================================================
            //Showing all the certificates for a civilian when trying to update the certificates   
            //======================================================================================================================================
            case("Toon Certificaten om aan te passen"):
                sessie = request.getSession();
                
                //setting the civilian-id
                sessie.setAttribute("burger", request.getParameter("burgerid"));     
                
                //Call upon function to set the attributes to get all the certificates from 
                getCertificates(request, sessie, soorten);
                
                response.sendRedirect("Beheer/aanpassen.jsp");
                break;
            //======================================================================================================================================
               
                
                
            //======================================================================================================================================
            //Deleting a certificate from the database
            //======================================================================================================================================
            case("Delete"):
                sessie = request.getSession();
                
                //Checking which certificate type is requested to be deleted 
                String CerType = request.getParameter("CerType");
                if((CerType.equalsIgnoreCase("test")== false) && (CerType.equalsIgnoreCase("vaccin") == false)){
                    response.sendRedirect("Beheer/beheer.jsp");
                        break;
                }
                else if(CerType.equalsIgnoreCase("test")){
                    String DelTid = (String) request.getParameter("TID");
                    certificaten.DeleteTestCertificate(DelTid);
                }
                else if(CerType.equalsIgnoreCase("vaccin")){
                    String DelVid = (String) request.getParameter("VID");
                    certificaten.DeleteVacCertificate(DelVid);
                }
                
                //Update the certificate attributes with the updated database values
                getCertificates(request, sessie, soorten);
                
                response.sendRedirect("Beheer/aanpassen.jsp");
                
                break;
            //======================================================================================================================================
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    //==============================================================================================================================================
    //Standardised function to set the right values to show all the certificates 
    //==============================================================================================================================================
    public void getCertificates(HttpServletRequest request, HttpSession sessie, ArrayList soorten){
        
        sessie = request.getSession();
        String BurgerIDchang = (String) sessie.getAttribute("burger");

        List vaccinschange = certificaten.getVaccinCertificaten(BurgerIDchang);
        List testschange = certificaten.getTestCertificaten(BurgerIDchang);

        sessie.setAttribute("vaccins", vaccinschange);
        sessie.setAttribute("tests", testschange);

        sessie.setAttribute("VaccinSoorten", soorten);
    }
    //==============================================================================================================================================
}
