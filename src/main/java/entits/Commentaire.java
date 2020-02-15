/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import javafx.scene.control.Button;
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
 * @author aisce
 */
@Entity
@Table(name = "commentaire")
@NamedQueries({
    @NamedQuery(name = "Commentaire.findAll", query = "SELECT c FROM Commentaire c")})
public class Commentaire implements Serializable {
  private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "description_commentaire")
    private String descriptionCommentaire;
    @Basic(optional = false)
    @Column(name = "DatePub")
    private String DatePub;
    @JoinColumn(name = "IdClient", referencedColumnName = "id")
    @ManyToOne
    private Integer idClient;
    @JoinColumn(name = "IdSujet", referencedColumnName = "id")
    @ManyToOne
    private Integer idSujet;
    private String nomClient;
    private String DatePublication;
    private Button button ;
    public Commentaire() {
    }

    public Commentaire(Integer id) {
        this.id = id;
    }

    public Commentaire(Integer id, String descriptionCommentaire) {
        this.id = id;
        this.descriptionCommentaire = descriptionCommentaire;
    }

    public Commentaire(String descriptionCommentaire, Integer idClient ,String DatePub, Integer idSujet) {
        this.descriptionCommentaire = descriptionCommentaire;
        this.idClient = idClient;
        this.DatePub = DatePub;
        this.idSujet = idSujet;
    }

    public Commentaire(int i, String haha, int i0, int i1) {
        this.id = id;
        this.descriptionCommentaire = descriptionCommentaire;
        this.idClient = idClient;
        this.idSujet = idSujet;
        this.button = new Button("send");
    }

    public String getDatePublication() {
        return DatePublication;
    }

    public void setDatePublication(String DatePublication) {
        this.DatePublication = DatePublication;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescriptionCommentaire() {
        return descriptionCommentaire;
    }
    
    public void setDescriptionCommentaire(String descriptionCommentaire) {
        this.descriptionCommentaire = descriptionCommentaire;
    }

    public Integer getIdClient() {
        return idClient;
    }
    

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public String getNomClient() {
        return nomClient;
    }
    
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public Integer getIdSujet() {
        return idSujet;
    }

    public void setIdSujet(int idSujet) {
        this.idSujet = idSujet;
    }

    public String getDatePub() {
        return DatePub;
    }

    public void setDatePub(String DatePub) {
        this.DatePub = DatePub;
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
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "descriptionCommentaire=" + descriptionCommentaire + ", DatePub=" + DatePub + ", nomClient=" + nomClient + '}';
    }

    public int executeUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   


}
