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
        
        switch(request.getParameter("submitknop")){
            case("GA"):
                HttpSession sessie = request.getSession();
                sessie.setAttribute("burger", request.getParameter("burgerid"));
                switch(request.getParameter("actie")){
                    case "1":
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
                List certificaat;
                switch(request.getParameter("actie")){
                    case "1":
                        NyTestcertificaat Tcert = certificaten.scanTestCertificaten(certID);
                        sessie.setAttribute("Testcertificate", Tcert);
                        
                        response.sendRedirect("Beheer/aanpassen.jsp");
                        break;
                    case "2":
                        NyVaccincertificaat Vcert = certificaten.scanVaccinCertificaten(certID);
                        sessie.setAttribute("Vaccincertificate", Vcert);
                        response.sendRedirect("Beheer/aanpassen.jsp");
                        break;
                }
                break;
            
            case("Update Test"):
                //ToDO Add a way to update the database, also in the beans
                response.sendRedirect("Beheer/beheer.jsp");
                break;
            
            case("Update Vaccin"):
                //ToDO Add a way to update the database, also in the beans
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
