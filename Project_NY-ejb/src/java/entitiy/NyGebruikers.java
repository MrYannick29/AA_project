/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiy;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yannick
 */
@Entity
@Table(name = "NY_GEBRUIKERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NyGebruikers.findAll", query = "SELECT n FROM NyGebruikers n")
    , @NamedQuery(name = "NyGebruikers.findByGebruikersnaam", query = "SELECT n FROM NyGebruikers n WHERE n.gebruikersnaam = :gebruikersnaam")
    , @NamedQuery(name = "NyGebruikers.findByPaswoord", query = "SELECT n FROM NyGebruikers n WHERE n.paswoord = :paswoord")})
public class NyGebruikers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "GEBRUIKERSNAAM")
    private String gebruikersnaam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASWOORD")
    private String paswoord;
    @OneToMany(mappedBy = "bid")
    private Collection<NyTestcertificaat> nyTestcertificaatCollection;
    @OneToMany(mappedBy = "bid")
    private Collection<NyVaccincertificaat> nyVaccincertificaatCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "nyGebruikers")
    private NyGroepen nyGroepen;

    public NyGebruikers() {
    }

    public NyGebruikers(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public NyGebruikers(String gebruikersnaam, String paswoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = paswoord;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    @XmlTransient
    public Collection<NyTestcertificaat> getNyTestcertificaatCollection() {
        return nyTestcertificaatCollection;
    }

    public void setNyTestcertificaatCollection(Collection<NyTestcertificaat> nyTestcertificaatCollection) {
        this.nyTestcertificaatCollection = nyTestcertificaatCollection;
    }

    @XmlTransient
    public Collection<NyVaccincertificaat> getNyVaccincertificaatCollection() {
        return nyVaccincertificaatCollection;
    }

    public void setNyVaccincertificaatCollection(Collection<NyVaccincertificaat> nyVaccincertificaatCollection) {
        this.nyVaccincertificaatCollection = nyVaccincertificaatCollection;
    }

    public NyGroepen getNyGroepen() {
        return nyGroepen;
    }

    public void setNyGroepen(NyGroepen nyGroepen) {
        this.nyGroepen = nyGroepen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gebruikersnaam != null ? gebruikersnaam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NyGebruikers)) {
            return false;
        }
        NyGebruikers other = (NyGebruikers) object;
        if ((this.gebruikersnaam == null && other.gebruikersnaam != null) || (this.gebruikersnaam != null && !this.gebruikersnaam.equals(other.gebruikersnaam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiy.NyGebruikers[ gebruikersnaam=" + gebruikersnaam + " ]";
    }
    
}
