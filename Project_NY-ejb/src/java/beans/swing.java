/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entitiy.NyTestcertificaat;
import entitiy.NyVaccincertificaat;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yannick
 */

@Stateless
public class swing implements swingRemote {

    @PersistenceContext private EntityManager em;
    @EJB private certificatenLocal certificaten;
    
    public String scanVaccinCertificaten(String certID) {
        System.out.println(certID);
        String status = "?";
        String burgerID = "UNKNOW";
        try {
                NyVaccincertificaat Vcert = certificaten.scanVaccinCertificaten(certID);
                System.out.println(Vcert);
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -14);
                if(Vcert.getDtm().before(cal.getTime()) && Vcert.getNr()>1)
                {
                    status = "SAFE";
                }
                else
                {
                    status = "NOTSAFE";
                }
            burgerID = Vcert.getBid().getGebruikersnaam();
            } catch (Exception e) {
                System.out.println("Error catch");
            }
        return burgerID + "/" + status;
    }
    public String scanTestCertificaten(String certID) {
        System.out.println(certID);
        String status = "?";
        String burgerID = "UNKNOW";
        try {
                NyTestcertificaat Tcert = certificaten.scanTestCertificaten(certID);
                Calendar cal = Calendar.getInstance();
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
        return burgerID + "/" + status;
    }
}
