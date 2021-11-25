/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entitiy.NyGebruikers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
}
