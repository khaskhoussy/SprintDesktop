/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.io.Serializable;
import java.util.Objects;
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
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "ligne_recette")
@NamedQueries({
    @NamedQuery(name = "LigneRecette.findAll", query = "SELECT l FROM LigneRecette l")})
public class LignePack implements Serializable {
    
 private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantite_rec")
    private int quantite;
    @Basic(optional = false)
    @Column(name = "rec_description")
    private String description;
    @JoinColumn(name = "IdProduit", referencedColumnName = "id")
    @ManyToOne
    private Produit idProduit;
    @JoinColumn(name = "IdRecette", referencedColumnName = "id")
    @ManyToOne
    private PackDecoration idPack;

    public LignePack() {
    }

    public LignePack(Integer id) {
        this.id = id;
    }

    public LignePack(Integer id, int quantite, String description) {
        this.id = id;
        this.quantite = quantite;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public PackDecoration getIdPack() {
        return idPack;
    }

    public void setIdPack(PackDecoration idPack) {
        this.idPack = idPack;
    }
    
    public String getNomProd(){
        return idProduit.getNomProd();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + this.quantite;
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.idProduit);
        hash = 59 * hash + Objects.hashCode(this.idPack);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LignePack other = (LignePack) obj;
        if (this.quantite != other.quantite) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idProduit, other.idProduit)) {
            return false;
        }
        if (!Objects.equals(this.idPack, other.idPack)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LignePack{" + "id=" + id + ", quantite=" + quantite + ", description=" + description + ", idProduit=" + idProduit + ", idPack=" + idPack + '}';
    }
}
