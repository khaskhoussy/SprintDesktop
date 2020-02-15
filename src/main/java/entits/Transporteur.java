/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "transporteur")
@NamedQueries({
    @NamedQuery(name = "Transporteur.findAll", query = "SELECT t FROM Transporteur t")})
public class Transporteur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "Tel")
    private int tel;
    @OneToMany(mappedBy = "idTransporteur")
    private Collection<Livraison> livraisonCollection;

    public Transporteur() {
    }

    public Transporteur(Integer id) {
        this.id = id;
    }

    public Transporteur(Integer id, String nom, int tel) {
        this.id = id;
        this.nom = nom;
        this.tel = tel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Collection<Livraison> getLivraisonCollection() {
        return livraisonCollection;
    }

    public void setLivraisonCollection(Collection<Livraison> livraisonCollection) {
        this.livraisonCollection = livraisonCollection;
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
        if (!(object instanceof Transporteur)) {
            return false;
        }
        Transporteur other = (Transporteur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entits.Transporteur[ id=" + id + " ]";
    }
    
}
