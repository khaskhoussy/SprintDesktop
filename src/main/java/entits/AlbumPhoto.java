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
@Table(name = "album_photo")
@NamedQueries({
    @NamedQuery(name = "AlbumPhoto.findAll", query = "SELECT a FROM AlbumPhoto a")})
public class AlbumPhoto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "description_album")
    private String descriptionAlbum;
    @JoinColumn(name = "IdTravail", referencedColumnName = "id")
    @ManyToOne
    private ListeTravail idTravail;

    public AlbumPhoto() {
    }

    public AlbumPhoto(Integer id) {
        this.id = id;
    }

    public AlbumPhoto(Integer id, String image, String descriptionAlbum) {
        this.id = id;
        this.image = image;
        this.descriptionAlbum = descriptionAlbum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescriptionAlbum() {
        return descriptionAlbum;
    }

    public void setDescriptionAlbum(String descriptionAlbum) {
        this.descriptionAlbum = descriptionAlbum;
    }

    public ListeTravail getIdTravail() {
        return idTravail;
    }

    public void setIdTravail(ListeTravail idTravail) {
        this.idTravail = idTravail;
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
        if (!(object instanceof AlbumPhoto)) {
            return false;
        }
        AlbumPhoto other = (AlbumPhoto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entits.AlbumPhoto[ id=" + id + " ]";
    }
    
}
