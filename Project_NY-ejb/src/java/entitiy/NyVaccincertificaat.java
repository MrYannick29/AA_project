/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitiy;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yannick
 */
@Entity
@Table(name = "NY_VACCINCERTIFICAAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NyVaccincertificaat.findAll", query = "SELECT n FROM NyVaccincertificaat n")
    , @NamedQuery(name = "NyVaccincertificaat.findByVcid", query = "SELECT n FROM NyVaccincertificaat n WHERE n.vcid = :vcid")
    , @NamedQuery(name = "NyVaccincertificaat.findByDtm", query = "SELECT n FROM NyVaccincertificaat n WHERE n.dtm = :dtm")
    , @NamedQuery(name = "NyVaccincertificaat.findBySoort", query = "SELECT n FROM NyVaccincertificaat n WHERE n.soort = :soort")})
public class NyVaccincertificaat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "VCID")
    private Integer vcid;
    @Column(name = "DTM")
    @Temporal(TemporalType.DATE)
    private Date dtm;
    @Size(max = 50)
    @Column(name = "SOORT")
    private String soort;
    @JoinColumn(name = "BID", referencedColumnName = "GEBRUIKERSNAAM")
    @ManyToOne
    private NyGebruikers bid;

    public NyVaccincertificaat() {
    }

    public NyVaccincertificaat(Integer vcid) {
        this.vcid = vcid;
    }

    public Integer getVcid() {
        return vcid;
    }

    public void setVcid(Integer vcid) {
        this.vcid = vcid;
    }

    public Date getDtm() {
        return dtm;
    }

    public void setDtm(Date dtm) {
        this.dtm = dtm;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public NyGebruikers getBid() {
        return bid;
    }

    public void setBid(NyGebruikers bid) {
        this.bid = bid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vcid != null ? vcid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NyVaccincertificaat)) {
            return false;
        }
        NyVaccincertificaat other = (NyVaccincertificaat) object;
        if ((this.vcid == null && other.vcid != null) || (this.vcid != null && !this.vcid.equals(other.vcid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiy.NyVaccincertificaat[ vcid=" + vcid + " ]";
    }
    
}
