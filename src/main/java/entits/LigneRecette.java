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
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "ligne_recette")
@NamedQueries({
    @NamedQuery(name = "LigneRecette.findAll", query = "SELECT l FROM LigneRecette l")})
public class LigneRecette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantite_rec")
    private int quantiteRec;
    @Basic(optional = false)
    @Column(name = "rec_description")
    private String recDescription;
    @JoinColumn(name = "IdProduit", referencedColumnName = "id")
    @ManyToOne
    private Produit idProduit;
    @JoinColumn(name = "IdRecette", referencedColumnName = "id")
    @ManyToOne
    private Recette idRecette;

    public LigneRecette() {
    }

    public LigneRecette(Integer id) {
        this.id = id;
    }

    public LigneRecette(Integer id, int quantiteRec, String recDescription) {
        this.id = id;
        this.quantiteRec = quantiteRec;
        this.recDescription = recDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantiteRec() {
        return quantiteRec;
    }

    public void setQuantiteRec(int quantiteRec) {
        this.quantiteRec = quantiteRec;
    }

    public String getRecDescription() {
        return recDescription;
    }

    public void setRecDescription(String recDescription) {
        this.recDescription = recDescription;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Produit idProduit) {
        this.idProduit = idProduit;
    }

    public Recette getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(Recette idRecette) {
        this.idRecette = idRecette;
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
        if (!(object instanceof LigneRecette)) {
            return false;
        }
        LigneRecette other = (LigneRecette) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entits.LigneRecette[ id=" + id + " ]";
    }
    
}
