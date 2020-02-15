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
@Table(name = "pack_decoration")
@NamedQueries({
    @NamedQuery(name = "PackDecoration.findAll", query = "SELECT p FROM PackDecoration p")})
public class PackDecoration implements Serializable,Commandable {
 private String image;
    private String nom;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }
    private String type;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "description_pack")
    private String descriptionPack;
    @Basic(optional = false)
    @Column(name = "prixP")
    private double prixP;
    @OneToMany(mappedBy = "idPack")
    
    private Collection<QuantiteDeco> quantiteDecoCollection;
   

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PackDecoration() {
    }

    public PackDecoration(Integer id) {
        this.id = id;
    }

    public PackDecoration(Integer id, String descriptionPack, double prixP,String image) {
        this.id = id;
        this.descriptionPack = descriptionPack;
        this.prixP = prixP;
        this.image=image;
    }
    public PackDecoration(Integer id, String descriptionPack, double prixP) {
        this.id = id;
        this.descriptionPack = descriptionPack;
        this.prixP = prixP;
        this.image=image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescriptionPack() {
        return descriptionPack;
    }

    public void setDescriptionPack(String descriptionPack) {
        this.descriptionPack = descriptionPack;
    }

    public double getPrixP() {
        return prixP;
    }

    public void setPrixP(double prixP) {
        this.prixP = prixP;
    }

    public Collection<QuantiteDeco> getQuantiteDecoCollection() {
        return quantiteDecoCollection;
    }

    public void setQuantiteDecoCollection(Collection<QuantiteDeco> quantiteDecoCollection) {
        this.quantiteDecoCollection = quantiteDecoCollection;
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
        if (!(object instanceof PackDecoration)) {
            return false;
        }
        PackDecoration other = (PackDecoration) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

 @Override
    public int getIdObjet() {
        return this.id;
    }

    @Override
    public double getPrixTotale() {
         return this.prixP;
    }

    @Override
    public Integer getQuantite() {
        return 1;
    }

    @Override
    public String getType (){
       return "pack";
    }

    @Override
    public String getNom() {
         return "Pack Decoration N°"+this.id;
    }

    @Override
    public void setQuantite(int qte) {
        
    }

    @Override
    public String getDescription() {
        return descriptionPack;
    }
    
}
