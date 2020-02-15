/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "evenement")
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e")})
public class Evenement implements Serializable {

      private int id;
    private String datedebut;
    private String datefin;
    private String description;
    private String lieu;
    private int nbrParticipants;
    private int eveprix;
    private String image;
    private int idOffre;
    private double prix_offre ;
    private OffrePromotion offre ; 
    private String nom ;
    

    public Evenement() {
    }

    public Evenement(int id) {
        this.id = id;
    }

    public Evenement(Integer id, String datedebut, String datefin, String description, String lieu, int nbrParticipants, String image) {
        this.id = id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.lieu = lieu;
        this.nbrParticipants = nbrParticipants;
        this.image = image;
    }

    public Evenement(int id, String datedebut, String datefin, String description, String lieu, int eveprix) {
        this.id = id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.lieu = lieu;
        this.eveprix = eveprix;
    }

    
    
    public Evenement(String datedebut, String datefin, String description, String lieu, int nbrParticipants, int eveprix, String image) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.lieu = lieu;
        this.nbrParticipants = nbrParticipants;
        this.eveprix = eveprix;
        this.image = image;
    }
    
    
    

    public Evenement(String datedebut, String datefin, String description, String lieu, int eveprix) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.lieu = lieu;
        this.eveprix = eveprix;
    }

    public Evenement(String datedebut, String datefin, String description, String lieu, int nbrParticipants, int eveprix, String image, String nom) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.lieu = lieu;
        this.nbrParticipants = nbrParticipants;
        this.eveprix = eveprix;
        this.image = image;
        this.nom = nom;
    }

    public Evenement(int id, String datedebut, String datefin, String description, String lieu, int nbrParticipants, int eveprix, String image, String nom) {
        this.id = id;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.description = description;
        this.lieu = lieu;
        this.nbrParticipants = nbrParticipants;
        this.eveprix = eveprix;
        this.image = image;
        this.nom = nom;
    }

   
    
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getNbrParticipants() {
        return nbrParticipants;
    }

    public void setNbrParticipants(int nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getEveprix() {
        return eveprix;
    }

    public void setEveprix(int eveprix) {
        this.eveprix = eveprix;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public double getPrix_offre() {
        return prix_offre;
    }

    public void setPrix_offre(double prix_offre) {
        this.prix_offre = prix_offre;
    }

    public OffrePromotion getOffre() {
        return offre;
    }

    public void setOffre(OffrePromotion offre) {
        this.offre = offre;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nbrParticipants != other.nbrParticipants) {
            return false;
        }
        if (this.eveprix != other.eveprix) {
            return false;
        }
        if (this.idOffre != other.idOffre) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix_offre) != Double.doubleToLongBits(other.prix_offre)) {
            return false;
        }
        if (!Objects.equals(this.datedebut, other.datedebut)) {
            return false;
        }
        if (!Objects.equals(this.datefin, other.datefin)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", datedebut=" + datedebut + ", datefin=" + datefin + ", description=" + description + ", lieu=" + lieu + ", nbrParticipants=" + nbrParticipants + ", eveprix=" + eveprix + ", image=" + image + ", idOffre=" + idOffre + ", prix_offre=" + prix_offre + '}';
    }
 
}
