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
@Table(name = "liste_travail")
@NamedQueries({
    @NamedQuery(name = "ListeTravail.findAll", query = "SELECT l FROM ListeTravail l")})
public class ListeTravail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "description_travail")
    private String descriptionTravail;
    @Basic(optional = false)
    @Column(name = "date_travail")
    private String dateTravail;
    @JoinColumn(name = "IdUser", referencedColumnName = "id")
    @ManyToOne
    private User idUser;
    @OneToMany(mappedBy = "idTravail")
    private Collection<AlbumPhoto> albumPhotoCollection;
    private String image;
    public ListeTravail() {
    }

    public ListeTravail(String descriptionTravail, String dateTravail, User idUser, String image) {
        this.descriptionTravail = descriptionTravail;
        this.dateTravail = dateTravail;
        this.idUser = idUser;
        this.image = image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ListeTravail(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public ListeTravail(Integer id, String descriptionTravail, String dateTravail, User idUser) {
        this.id = id;
        this.descriptionTravail = descriptionTravail;
        this.dateTravail = dateTravail;
        this.idUser = idUser;
    }

    public ListeTravail(String descriptionTravail, String dateTravail, User idUser) {
        this.descriptionTravail = descriptionTravail;
        this.dateTravail = dateTravail;
        this.idUser = idUser;
    }

    public ListeTravail(String descriptionTravail, String dateTravail) {
        this.descriptionTravail = descriptionTravail;
        this.dateTravail = dateTravail;
    }
    
    
    public ListeTravail(Integer id, String descriptionTravail, String dateTravail) {
        this.id = id;
        this.descriptionTravail = descriptionTravail;
        this.dateTravail = dateTravail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescriptionTravail() {
        return descriptionTravail;
    }

    public void setDescriptionTravail(String descriptionTravail) {
        this.descriptionTravail = descriptionTravail;
    }

    public String getDateTravail() {
        return dateTravail;
    }

    public void setDateTravail(String dateTravail) {
        this.dateTravail = dateTravail;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Collection<AlbumPhoto> getAlbumPhotoCollection() {
        return albumPhotoCollection;
    }

    public void setAlbumPhotoCollection(Collection<AlbumPhoto> albumPhotoCollection) {
        this.albumPhotoCollection = albumPhotoCollection;
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
        if (!(object instanceof ListeTravail)) {
            return false;
        }
        ListeTravail other = (ListeTravail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entits.ListeTravail[ id=" + id + " ]";
    }
    
}
