/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javafx.scene.control.ChoiceBox;
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
 * @author aisce
 */
@Entity
@Table(name = "sujet")
@NamedQueries({
    @NamedQuery(name = "Sujet.findAll", query = "SELECT s FROM Sujet s")})
public class Sujet implements Serializable {

   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Nom_sujet")
    private String nomsujet;
    @JoinColumn(name = "IdUser", referencedColumnName = "id")
    @ManyToOne
    private Integer idUser;
    @OneToMany(mappedBy = "idSujet")
    @JoinColumn(name = "IdCategorie", referencedColumnName = "id")
    @ManyToOne
    private String Categorie;
    @OneToMany(mappedBy = "Categorie")
    @ManyToOne
    private String datepub;
    @OneToMany(mappedBy = "DatePub")
    @ManyToOne
    private String description;
    @OneToMany(mappedBy = "description")
    private ArrayList<Commentaire> commentaireCollection;

    public Sujet(Integer id, String nomsujet, Integer idUser, String Categorie, String datepub, String description) {
        this.id = id;
        this.nomsujet = nomsujet;
        this.idUser = idUser;
        this.Categorie = Categorie;
        this.datepub = datepub;
        this.description = description;
    }
    
    public Sujet(String nomsujet, String Categorie, String datepub, String description) {
        
        this.nomsujet = nomsujet;
        this.Categorie = Categorie;
        this.datepub = datepub;
        this.description = description;
    }
    
    public Sujet(String nomsujet, Integer idUser, String Categorie, String datepub, String description) {
        
        this.nomsujet = nomsujet;
        this.idUser = idUser;
        this.Categorie = Categorie;
        this.datepub = datepub;
        this.description = description;
    }

    public Sujet() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomsujet() {
        return nomsujet;
    }

    public void setNomsujet(String nomsujet) {
        this.nomsujet = nomsujet;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }

    public String getDatepub() {
        return datepub;
    }

    public void setDatepub(String datepub) {
        this.datepub = datepub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Sujet other = (Sujet) obj;
        if (!Objects.equals(this.nomsujet, other.nomsujet)) {
            return false;
        }
        if (!Objects.equals(this.Categorie, other.Categorie)) {
            return false;
        }
        if (!Objects.equals(this.datepub, other.datepub)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "Sujet{" + "id=" + id + ", nomsujet=" + nomsujet + ", idUser=" + idUser + ", Categorie=" + Categorie + ", datepub=" + datepub + ", description=" + description + '}';
    }

}
