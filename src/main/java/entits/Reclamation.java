/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.io.Serializable;
import java.util.Date;
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
public class Reclamation implements Serializable {

   
    private Integer id;
 
    private String objet;
  
    private String contenue;
  
    private Date dateRecla;
 
    private int idCommande;
  
    private int idUser;
    private String etat_recla;

    public String getEtat_recla() {
        return etat_recla;
    }

    public void setEtat_recla(String etat_recla) {
        this.etat_recla = etat_recla;
    }

    public Reclamation() {
    }

    public Reclamation(Integer id) {
        this.id = id;
    }

    public Reclamation(Integer id, String objet, String contenue, Date dateRecla) {
        this.id = id;
        this.objet = objet;
        this.contenue = contenue;
        this.dateRecla = dateRecla;
        this.etat_recla = etat_recla;
    }

    public Reclamation(String objet, String contenue, Date dateRecla, int idCommande, int idUser, String etat_recla) {
        this.objet = objet;
        this.contenue = contenue;
        this.dateRecla = dateRecla;
        this.idCommande = idCommande;
        this.idUser = idUser;
        this.etat_recla = etat_recla;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public Date getDateRecla() {
        return dateRecla;
    }

    public void setDateRecla(Date dateRecla) {
        this.dateRecla = dateRecla;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
        final Reclamation other = (Reclamation) obj;
        if (this.idCommande != other.idCommande) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (!Objects.equals(this.objet, other.objet)) {
            return false;
        }
        if (!Objects.equals(this.contenue, other.contenue)) {
            return false;
        }
        if (!Objects.equals(this.dateRecla, other.dateRecla)) {
            return false;
        }
        if (!Objects.equals(this.etat_recla, other.etat_recla)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", objet=" + objet + ", contenue=" + contenue + ", dateRecla=" + dateRecla + ", idCommande=" + idCommande + ", idUser=" + idUser + ", etat_recla=" + etat_recla + '}';
}
}
