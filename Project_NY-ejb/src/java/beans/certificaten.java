/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entitiy.NyGebruikers;
import entitiy.NyTestcertificaat;
import entitiy.NyVaccincertificaat;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Yannick
 * Hier komt code om alle certificaten op te halen van een burger.
 */
@Stateless
public class certificaten implements certificatenLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext private EntityManager em;
    
    public List getTestCertificaten(String BurgerID) {
        return em.createQuery("Select l from NyTestcertificaat l where l.bid=?1").setParameter(1, em.find(NyGebruikers.class, BurgerID)).getResultList();
    }
    public List getVaccinCertificaten(String BurgerID) {
        return em.createQuery("Select l from NyVaccincertificaat l where l.bid=?1").setParameter(1, em.find(NyGebruikers.class, BurgerID)).getResultList();
    }
    
    
    public NyTestcertificaat scanTestCertificaten(String CertID) throws NoResultException {
        return (NyTestcertificaat) em.createQuery("Select l from NyTestcertificaat l where l.tcid=?1").setParameter(1, parseInt(CertID)).getSingleResult();
    }
    public NyVaccincertificaat scanVaccinCertificaten(String CertID) throws NoResultException {
        return (NyVaccincertificaat) em.createQuery("Select l from NyVaccincertificaat l where l.vcid=?1").setParameter(1, parseInt(CertID)).getSingleResult();
    }
    
    public NyGebruikers zoekBurger(String BurgerID)
    {
        return (NyGebruikers) em.createQuery("Select l from NyGebruikers l where l.bid=?1").setParameter(1, BurgerID).getSingleResult();
    }
    
    public List getTestCertificateById(String testID){
        return em.createNamedQuery("NyTestcertificaat.findByTcid").setParameter("tcid", testID).getResultList();
    }
    public List getVaccinCertificateById(String vacID){
        return em.createNamedQuery("NyVaccincertificaat.findByVcid").setParameter("vcid", vacID).getResultList();
    }
}
