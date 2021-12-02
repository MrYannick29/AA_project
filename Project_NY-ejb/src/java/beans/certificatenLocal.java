/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entitiy.NyGebruikers;
import entitiy.NyTestcertificaat;
import entitiy.NyVaccincertificaat;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yannick
 */
@Local
public interface certificatenLocal {
    List getTestCertificaten(String BurgerID);
    List getVaccinCertificaten(String BurgerID);
    
    NyTestcertificaat scanTestCertificaten(String CertID);
    NyVaccincertificaat scanVaccinCertificaten(String CertID);
    
    NyGebruikers zoekBurger(String BurgerID);
}
