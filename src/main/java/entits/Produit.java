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
   import javax.persistence.JoinColumn;
   import javax.persistence.ManyToOne;
   import javax.persistence.NamedQueries;
   import javax.persistence.NamedQuery;
   import javax.persistence.OneToMany;
   import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "produit")
@NamedQueries({
    @NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")})
public class Produit implements Serializable {

      private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom_prod")
    private String nomProd;
    @Basic(optional = false)
    @Column(name = "prix_prod")
    private double prixProd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_offre")
    private Double prixOffre;
    @Basic(optional = false)
    @Column(name = "prod_description")
    private String prodDescription;
    @Basic(optional = false)
    @Column(name = "quantite")
    private int quantite;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @JoinColumn(name = "IdCategorie", referencedColumnName = "id")
  
    private int idCategorie;
    @JoinColumn(name = "IdOffre", referencedColumnName = "id")
   
    private int idOffre;
    @OneToMany(mappedBy = "idProduit")
    private Collection<LigneCommande> ligneCommandeCollection;
    @OneToMany(mappedBy = "idProduit")
    private Collection<LigneRecette> ligneRecetteCollection;
    @OneToMany(mappedBy = "idProduit")
    private Collection<QuantiteDeco> quantiteDecoCollection;
   
    private OffrePromotion offre ;
    

    public Produit() {
    }

    public Produit(Integer id) {
        this.id = id;
    }

    public Produit(Integer id, String nomProd, double prixProd, String prodDescription, int quantite, String image) {
        this.id = id;
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.prodDescription = prodDescription;
        this.quantite = quantite;
        this.image = image;
    }

    public Produit(String nomProd, double prixProd, String prodDescription, int quantite, String image, int idCategorie) {
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.prodDescription = prodDescription;
        this.quantite = quantite;
        this.image = image;
        this.idCategorie = idCategorie;
    }
    

    public Produit(String nomProd, double prixProd, Double prixOffre, String prodDescription, int quantite, String image) {
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.prixOffre = prixOffre;
        this.prodDescription = prodDescription;
        this.quantite = quantite;
        this.image = image;
    }

    public Produit(String nomProd, double prixProd, Double prixOffre, String prodDescription, int quantite, String image, int idCategorie, int idOffre) {
        this.nomProd = nomProd;
        this.prixProd = prixProd;
        this.prixOffre = prixOffre;
        this.prodDescription = prodDescription;
        this.quantite = quantite;
        this.image = image;
        this.idCategorie = idCategorie;
        this.idOffre = idOffre;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public double getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(double prixProd) {
        this.prixProd = prixProd;
    }

    public Double getPrixOffre() {
        return prixOffre;
    }

    public void setPrixOffre(Double prixOffre) {
        this.prixOffre = prixOffre;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public OffrePromotion getOffre() {
        return offre;
    }

    public void setOffre(OffrePromotion offre) {
        this.offre = offre;
    }
    

  

    public Collection<LigneCommande> getLigneCommandeCollection() {
        return ligneCommandeCollection;
    }

    public void setLigneCommandeCollection(Collection<LigneCommande> ligneCommandeCollection) {
        this.ligneCommandeCollection = ligneCommandeCollection;
    }

    public Collection<LigneRecette> getLigneRecetteCollection() {
        return ligneRecetteCollection;
    }

    public void setLigneRecetteCollection(Collection<LigneRecette> ligneRecetteCollection) {
        this.ligneRecetteCollection = ligneRecetteCollection;
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
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nomProd=" + nomProd + ", prixProd=" + prixProd + ", prixOffre=" + prixOffre + ", prodDescription=" + prodDescription + ", quantite=" + quantite + ", image=" + image + ", idCategorie=" + idCategorie + ", idOffre=" + idOffre + ", ligneCommandeCollection=" + ligneCommandeCollection + ", ligneRecetteCollection=" + ligneRecetteCollection + ", quantiteDecoCollection=" + quantiteDecoCollection + ", offre=" + offre + '}';
    }

    
}
