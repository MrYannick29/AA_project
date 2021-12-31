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
 * @author Yannick Saelen
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
        ArrayList soorten = new ArrayList();
        soorten.add("Pfizer");soorten.add("Moderna");soorten.add("AstraZeneca");soorten.add("Janssens");
        
        switch(request.getParameter("submitknop")){
            case("GA"):
                sessie = request.getSession();
                switch(request.getParameter("actie")){
                    case "1":
                         try{
                            sessie.removeAttribute("vaccins");
                        }
                        catch(Exception E){
                            
                        }
                        response.sendRedirect("Beheer/toevoegen.jsp");
                        break;
                    case "2":
                        response.sendRedirect("Beheer/aanpassen.jsp");
                        break;
                }
                break;
            
            case("Get Certificaat"):
                sessie = request.getSession();
                String certID = request.getParameter("ID");
                
                sessie.setAttribute("VacSoort", "Pfizer");
                
                switch(request.getParameter("CerType")){
                    case "test":
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
                        
                        response.sendRedirect("Beheer/aanpassen.jsp");
                        break;
                    case "vaccin":
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
                        try{
                            sessie.removeAttribute("TestBID");
                            sessie.removeAttribute("TestID");
                            sessie.removeAttribute("DBDATE");
                            sessie.removeAttribute("TestDate");
                            sessie.removeAttribute("TestResult");
                        }
                        catch(Exception E){
                            
                        }
                        response.sendRedirect("Beheer/aanpassen.jsp");
                        break;
                }
                break;
            
            case("Update Test"):
                sessie = request.getSession();
                
                String Tid = request.getParameter("id");
                String datumNT = request.getParameter("datum");
                String resN = request.getParameter("res");
                String bidNT = (String) request.getParameter("bid");
                certificaten.UpdateTestCertificaat(datumNT, resN, bidNT, Tid);
                response.sendRedirect("Beheer/beheer.jsp");
                break;
            
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
                
            case("Vaccin Toevoegen"):
                sessie = request.getSession();
                String datumV = request.getParameter("datum");
                String soort = request.getParameter("soort");
                String dosis = request.getParameter("dosis");
                String bidV = (String) sessie.getAttribute("burger");
                certificaten.setVaccinCertificaat(datumV, soort, dosis, bidV);
                
                response.sendRedirect("Beheer/beheer.jsp");
                break;
            case("Test Toevoegen"):
                sessie = request.getSession();
                String datumT = request.getParameter("datum");
                String res = request.getParameter("res");
                String bidT = (String) sessie.getAttribute("burger");
                certificaten.setTestCertificaat(datumT, res, bidT);
                
                response.sendRedirect("Beheer/beheer.jsp");
                break;
                
            case("Toon Certificaten"):
                sessie = request.getSession();                
                sessie.setAttribute("burger", request.getParameter("burgerid"));
                String BurgerID = (String) sessie.getAttribute("burger");
            
                List vaccins = certificaten.getVaccinCertificaten(BurgerID);
                NyVaccincertificaat lastCert = (NyVaccincertificaat) vaccins.get(vaccins.size()-1);
                String Soort = lastCert.getSoort();
                String nextVacnr = Integer.toString(lastCert.getNr() + 1);
                sessie.setAttribute("VacSoort", Soort);
                sessie.setAttribute("vaccins", vaccins);
                sessie.setAttribute("VacNrNxt", nextVacnr);
                
                sessie.setAttribute("VaccinSoorten", soorten);
                
                response.sendRedirect("Beheer/toevoegen.jsp");
                break;
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

}
