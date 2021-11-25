/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiy;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yannick
 */
@Entity
@Table(name = "NY_GROEPEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NyGroepen.findAll", query = "SELECT n FROM NyGroepen n")
    , @NamedQuery(name = "NyGroepen.findByGebruikersnaam", query = "SELECT n FROM NyGroepen n WHERE n.gebruikersnaam = :gebruikersnaam")
    , @NamedQuery(name = "NyGroepen.findByGroep", query = "SELECT n FROM NyGroepen n WHERE n.groep = :groep")})
public class NyGroepen implements Serializable {

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
    @Column(name = "GROEP")
    private String groep;
    @JoinColumn(name = "GEBRUIKERSNAAM", referencedColumnName = "GEBRUIKERSNAAM", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private NyGebruikers nyGebruikers;

    public NyGroepen() {
    }

    public NyGroepen(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public NyGroepen(String gebruikersnaam, String groep) {
        this.gebruikersnaam = gebruikersnaam;
        this.groep = groep;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getGroep() {
        return groep;
    }

    public void setGroep(String groep) {
        this.groep = groep;
    }

    public NyGebruikers getNyGebruikers() {
        return nyGebruikers;
    }

    public void setNyGebruikers(NyGebruikers nyGebruikers) {
        this.nyGebruikers = nyGebruikers;
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
        if (!(object instanceof NyGroepen)) {
            return false;
        }
        NyGroepen other = (NyGroepen) object;
        if ((this.gebruikersnaam == null && other.gebruikersnaam != null) || (this.gebruikersnaam != null && !this.gebruikersnaam.equals(other.gebruikersnaam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiy.NyGroepen[ gebruikersnaam=" + gebruikersnaam + " ]";
    }
    
}
