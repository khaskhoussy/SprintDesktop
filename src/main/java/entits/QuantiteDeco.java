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
@Table(name = "quantite__deco")
@NamedQueries({
    @NamedQuery(name = "QuantiteDeco.findAll", query = "SELECT q FROM QuantiteDeco q")})
public class QuantiteDeco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantite_dec")
    private int quantiteDec;
    @Basic(optional = false)
    @Column(name = "description_deco")
    private String descriptionDeco;
    @JoinColumn(name = "IdProduit", referencedColumnName = "id")
    @ManyToOne
    private Produit idProduit;
    @JoinColumn(name = "IdPack", referencedColumnName = "id")
    @ManyToOne
    private PackDecoration idPack;

    public QuantiteDeco() {
    }

    public QuantiteDeco(Integer id) {
        this.id = id;
    }

    public QuantiteDeco(Integer id, int quantiteDec, String descriptionDeco) {
        this.id = id;
        this.quantiteDec = quantiteDec;
        this.descriptionDeco = descriptionDeco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantiteDec() {
        return quantiteDec;
    }

    public void setQuantiteDec(int quantiteDec) {
        this.quantiteDec = quantiteDec;
    }

    public String getDescriptionDeco() {
        return descriptionDeco;
    }

    public void setDescriptionDeco(String descriptionDeco) {
        this.descriptionDeco = descriptionDeco;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuantiteDeco)) {
            return false;
        }
        QuantiteDeco other = (QuantiteDeco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entits.QuantiteDeco[ id=" + id + " ]";
    }
    
}
