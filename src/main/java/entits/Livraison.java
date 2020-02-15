/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "livraison")
@NamedQueries({
    @NamedQuery(name = "Livraison.findAll", query = "SELECT l FROM Livraison l")})
public class Livraison implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "adresse_liv")
    private String adresseLiv;
    @Basic(optional = false)
    @Column(name = "date_liv")
    private String dateLiv;
    @Basic(optional = false)
    @Column(name = "temps_liv")
    private String tempsLiv;
    @Basic(optional = false)
    @Column(name = "num_Tel")
    private int numTel;
    @OneToOne(mappedBy = "idLivraison")
    private Commande commande;
    @JoinColumn(name = "IdTransporteur", referencedColumnName = "id")
    @ManyToOne
    private Transporteur idTransporteur;

    public Livraison() {
    }

    public Livraison(Integer id) {
        this.id = id;
    }

    public Livraison(Integer id, String adresseLiv, String dateLiv, String tempsLiv, int numTel) {
        this.id = id;
        this.adresseLiv = adresseLiv;
        this.dateLiv = dateLiv;
        this.tempsLiv = tempsLiv;
        this.numTel = numTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresseLiv() {
        return adresseLiv;
    }

    public void setAdresseLiv(String adresseLiv) {
        this.adresseLiv = adresseLiv;
    }

    public String getDateLiv() {
        return dateLiv;
    }

    public void setDateLiv(String dateLiv) {
        this.dateLiv = dateLiv;
    }

    public String getTempsLiv() {
        return tempsLiv;
    }

    public void setTempsLiv(String tempsLiv) {
        this.tempsLiv = tempsLiv;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Transporteur getIdTransporteur() {
        return idTransporteur;
    }

    public void setIdTransporteur(Transporteur idTransporteur) {
        this.idTransporteur = idTransporteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livraison)) {
            return false;
        }
        Livraison other = (Livraison) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entits.Livraison[ id=" + id + " ]";
    }
    
}
