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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

/**
 *
 * @author Yannick Saelen
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
    
    public void setVaccinCertificaat(String datum, String soort, String dosis, String bid)
    {
        NyVaccincertificaat newVaccin = new NyVaccincertificaat();
        int idV = (int)em.createQuery("select max(n.vcid) from NyVaccincertificaat n").getSingleResult();
        int idT = (int)em.createQuery("select max(n.tcid) from NyTestcertificaat n").getSingleResult();
        if(idV>idT)
        {
            newVaccin.setVcid(idV+1);
        }
        else
        {
            newVaccin.setVcid(idT+1);
        }
        newVaccin.setBid(em.find(NyGebruikers.class, bid));
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsed = format.parse(datum);
            newVaccin.setDtm(parsed);
        } catch (ParseException ex) {
            Logger.getLogger(certificaten.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        newVaccin.setSoort(soort);
        newVaccin.setNr(Integer.parseInt(dosis));
        
        em.persist(newVaccin);
        
    }
    public void setTestCertificaat(String datum, String res, String bid)
    {
        NyTestcertificaat newTest = new NyTestcertificaat();
        int idV = (int)em.createQuery("select max(n.vcid) from NyVaccincertificaat n").getSingleResult();
        int idT = (int)em.createQuery("select max(n.tcid) from NyTestcertificaat n").getSingleResult();
        if(idV>idT)
        {
            newTest.setTcid(idV+1);
        }
        else
        {
            newTest.setTcid(idT+1);
        }
        newTest.setBid(em.find(NyGebruikers.class, bid));
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsed = format.parse(datum);
            newTest.setDtm(parsed);
        } catch (ParseException ex) {
            Logger.getLogger(certificaten.class.getName()).log(Level.SEVERE, null, ex);
        }

        newTest.setRes(Integer.parseInt(res));
        
        em.persist(newTest);
    }
    public void UpdateVaccinCertificaat(String datum, String soort, String dosis, String bid, String Vid)
    {
        NyVaccincertificaat upVaccin = scanVaccinCertificaten(Vid);
        upVaccin.setVcid(Integer.parseInt(Vid));
        
        upVaccin.setBid(em.find(NyGebruikers.class, bid));
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsed = format.parse(datum);
            upVaccin.setDtm(parsed);
        } catch (ParseException ex) {
            Logger.getLogger(certificaten.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        upVaccin.setSoort(soort);
        System.out.println(dosis);
        upVaccin.setNr(Integer.parseInt(dosis));
        
        em.persist(upVaccin);
        
    }
    public void UpdateTestCertificaat(String datum, String res, String bid, String tid)
    {
        NyTestcertificaat newTest = scanTestCertificaten(tid);
        newTest.setTcid(Integer.parseInt(tid));
        
        newTest.setBid(em.find(NyGebruikers.class, bid));
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsed = format.parse(datum);
            newTest.setDtm(parsed);
        } catch (ParseException ex) {
            Logger.getLogger(certificaten.class.getName()).log(Level.SEVERE, null, ex);
        }

        newTest.setRes(Integer.parseInt(res));
        
        em.persist(newTest);
    }
    
    public void DeleteTestCertificate(String id){

        NyTestcertificaat delTest = scanTestCertificaten(id);
        em.remove(delTest);
        
    }
    public void DeleteVacCertificate(String id){
        
        NyVaccincertificaat delVac = scanVaccinCertificaten(id);
        em.remove(delVac);
        
    }
}
