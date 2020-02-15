/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import entits.ObjetPanierProduit;
import entits.LigneCommande;
import java.util.ArrayList;
import entits.Produit;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "commande")
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")})
  public class Commande implements Serializable {
    ////////////////////////////////////////////////////
    private List<LigneCommande> produits;
    List<ObjetPanierProduit> listt  = new ArrayList<>();
////////////////////////////////////////////////////////
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date_validation")
    private String dateValidation;
    @Basic(optional = false)
    @Column(name = "prix_total")
    private Integer prixTotal;
    @JoinColumn(name = "idLivraison", referencedColumnName = "id")
    @OneToOne
    private Livraison idLivraison;
    @JoinColumn(name = "IdUser", referencedColumnName = "id")
    @ManyToOne
    private User idUser;
    @OneToMany(mappedBy = "idCommande")
    private Collection<LigneCommande> ligneCommandeCollection;
    @OneToMany(mappedBy = "idCommande")
    private Collection<Reclamation> reclamationCollection;
    @OneToMany(mappedBy = "idCommande")
    private Collection<LigneService> ligneServiceCollection;
    
     
     
    public Commande() {
    }

    public Commande(Integer id) {
        this.id = id;
    }

    public Commande(Integer id, String dateValidation, Integer prixTotal) {
        this.id = id;
        this.dateValidation = dateValidation;
        this.prixTotal = prixTotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(String dateValidation) {
        this.dateValidation = dateValidation;
    }

    public Integer getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Integer prixTotal) {
        this.prixTotal = prixTotal ;
    }

    public Livraison getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(Livraison idLivraison) {
        this.idLivraison = idLivraison;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Collection<LigneCommande> getLigneCommandeCollection() {
        return ligneCommandeCollection;
    }

    public void setLigneCommandeCollection(Collection<LigneCommande> ligneCommandeCollection) {
        this.ligneCommandeCollection = ligneCommandeCollection;
    }

    public Collection<Reclamation> getReclamationCollection() {
        return reclamationCollection;
    }

    public void setReclamationCollection(Collection<Reclamation> reclamationCollection) {
        this.reclamationCollection = reclamationCollection;
    }

    public Collection<LigneService> getLigneServiceCollection() {
        return ligneServiceCollection;
    }

    public void setLigneServiceCollection(Collection<LigneService> ligneServiceCollection) {
        this.ligneServiceCollection = ligneServiceCollection;
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
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entits.Commande[ id=" + id + " ]";
    }
    
}
