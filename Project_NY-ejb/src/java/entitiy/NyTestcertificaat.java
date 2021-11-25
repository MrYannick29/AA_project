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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yannick
 */
@Entity
@Table(name = "NY_TESTCERTIFICAAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NyTestcertificaat.findAll", query = "SELECT n FROM NyTestcertificaat n")
    , @NamedQuery(name = "NyTestcertificaat.findByTcid", query = "SELECT n FROM NyTestcertificaat n WHERE n.tcid = :tcid")
    , @NamedQuery(name = "NyTestcertificaat.findByDtm", query = "SELECT n FROM NyTestcertificaat n WHERE n.dtm = :dtm")
    , @NamedQuery(name = "NyTestcertificaat.findByRes", query = "SELECT n FROM NyTestcertificaat n WHERE n.res = :res")})
public class NyTestcertificaat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TCID")
    private Integer tcid;
    @Column(name = "DTM")
    @Temporal(TemporalType.DATE)
    private Date dtm;
    @Column(name = "RES")
    private Integer res;
    @JoinColumn(name = "BID", referencedColumnName = "GEBRUIKERSNAAM")
    @ManyToOne
    private NyGebruikers bid;

    public NyTestcertificaat() {
    }

    public NyTestcertificaat(Integer tcid) {
        this.tcid = tcid;
    }

    public Integer getTcid() {
        return tcid;
    }

    public void setTcid(Integer tcid) {
        this.tcid = tcid;
    }

    public Date getDtm() {
        return dtm;
    }

    public void setDtm(Date dtm) {
        this.dtm = dtm;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
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
        hash += (tcid != null ? tcid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NyTestcertificaat)) {
            return false;
        }
        NyTestcertificaat other = (NyTestcertificaat) object;
        if ((this.tcid == null && other.tcid != null) || (this.tcid != null && !this.tcid.equals(other.tcid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitiy.NyTestcertificaat[ tcid=" + tcid + " ]";
    }
    
}
