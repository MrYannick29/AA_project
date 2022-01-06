/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entitiy.NyGebruikers;
import entitiy.NyTestcertificaat;
import entitiy.NyVaccincertificaat;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yannick Saelen & Niels Serlet
 * The interface necessary for the sessionbean "certificaten"
 */
@Local
public interface certificatenLocal {
    List getTestCertificaten(String BurgerID);
    List getVaccinCertificaten(String BurgerID);
    
    NyTestcertificaat scanTestCertificaten(String CertID);
    NyVaccincertificaat scanVaccinCertificaten(String CertID);
    
    NyGebruikers zoekBurger(String BurgerID);
    
    void setVaccinCertificaat(String datum, String soort, String dosis, String bid);
    void setTestCertificaat(String datum, String res, String bid);
    
    void UpdateVaccinCertificaat(String datum, String soort, String dosis, String bid, String Vid);
    void UpdateTestCertificaat(String datum, String result, String bid, String tid);
    
    void DeleteVacCertificate(String id);
    void DeleteTestCertificate(String id);
}
