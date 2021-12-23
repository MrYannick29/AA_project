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
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author r0744479
 */
public class ScanServlet extends HttpServlet {

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
            out.println("<title>Servlet ScanServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ScanServlet at " + request.getContextPath() + "</h1>");
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
        if(request.getParameter("submitknop").equals("scanid"))
        {
            String certid = request.getParameter("certificaatid");
            Calendar cal;
            String status = "?";
            String burgerID = "UNKNOW";
            try {
                NyVaccincertificaat Vcert = certificaten.scanVaccinCertificaten(certid);
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -14);
                if(Vcert.getDtm().before(cal.getTime()) && Vcert.getNr() > 1)
                {
                    status = "SAFE";
                }
                else
                {
                    status = "NOTSAFE";
                }
                burgerID = Vcert.getBid().getGebruikersnaam();
            } catch (Exception e) {
            }
            try {
                NyTestcertificaat Tcert = certificaten.scanTestCertificaten(certid);
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -3);
                if(Tcert.getDtm().after(cal.getTime()) && Tcert.getRes()==0)
                {
                    status = "SAFE";
                }
                else
                {
                    status = "NOTSAFE";
                }
                burgerID = Tcert.getBid().getGebruikersnaam();
            } catch (Exception e) {
            }
            
            HttpSession sessie = request.getSession();
            sessie.setAttribute("status", status);
            sessie.setAttribute("burgerID", burgerID);
            
            response.sendRedirect("Open/scanres.jsp");

        }
        if(request.getParameter("submitknop").equals("scanopnieuw"))
        {
            response.sendRedirect("Open/scannen.jsp");
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
