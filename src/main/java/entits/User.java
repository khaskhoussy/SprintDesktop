/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entits;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javafx.scene.image.Image;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements Serializable {
    private String nom;
    private String prenom;
    private String telephone;
    private Image photo;
    private String img;

    public String getImg() {
        return img;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public void setImg(String img) {
        this.img = img;
    }
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Lob
    @Column(name = "roles")
    private String roles;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "username_canonical")
    private String usernameCanonical;
    @Basic(optional = false)
    @Column(name = "email_canonical")
    private String emailCanonical;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "salt")
    private String salt;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "confirmation_token")
    private String confirmationToken;
    @Column(name = "password_requested_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordRequestedAt;
    @OneToMany(mappedBy = "idUser")
    private Collection<Participation> participationCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<ListeTravail> listeTravailCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<Commande> commandeCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<Reclamation> reclamationCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<Sujet> sujetCollection;
    @OneToMany(mappedBy = "idClient")
    private Collection<Commentaire> commentaireCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<LigneService> ligneServiceCollection;

    public User() {
        
    }

    public User(String nom, String prenom, String telephone, String email, String password, String roles, String username, String usernameCanonical, String emailCanonical,int id ) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.emailCanonical = emailCanonical;
    }

    public User( Integer id,String nom, String prenom,String email, String telephone,  String roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    

    public User(String nom, String prenom, String email, String password, String username) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.username = username;
    }
    

    public User(String nom, String prenom, String telephone, String email, String password, String username ,String img ) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.username = username;
        this.img=img;
        //Image image = new Image("/Images/Default.jpg");
    }
    public User(String nom, String prenom, String telephone, String email, String password, String username) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.username = username;
        
        //Image image = new Image("/Images/Default.jpg");
    }
    
    public User(String nom, String prenom, String telephone, String email, String password, String username,Image photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.username = username;
        this.photo= photo; 
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String email, String password, String roles, String username, String usernameCanonical, String emailCanonical, boolean enabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
    }

    public User(String nom, String prenom, String telephone, Integer id, String email, String password, String roles, String username) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public Collection<Participation> getParticipationCollection() {
        return participationCollection;
    }

    public void setParticipationCollection(Collection<Participation> participationCollection) {
        this.participationCollection = participationCollection;
    }

    public Collection<ListeTravail> getListeTravailCollection() {
        return listeTravailCollection;
    }

    public void setListeTravailCollection(Collection<ListeTravail> listeTravailCollection) {
        this.listeTravailCollection = listeTravailCollection;
    }

    public Collection<Commande> getCommandeCollection() {
        return commandeCollection;
    }

    public void setCommandeCollection(Collection<Commande> commandeCollection) {
        this.commandeCollection = commandeCollection;
    }

    public Collection<Reclamation> getReclamationCollection() {
        return reclamationCollection;
    }

    public void setReclamationCollection(Collection<Reclamation> reclamationCollection) {
        this.reclamationCollection = reclamationCollection;
    }

    public Collection<Sujet> getSujetCollection() {
        return sujetCollection;
    }

    public void setSujetCollection(Collection<Sujet> sujetCollection) {
        this.sujetCollection = sujetCollection;
    }

    public Collection<Commentaire> getCommentaireCollection() {
        return commentaireCollection;
    }

    public void setCommentaireCollection(Collection<Commentaire> commentaireCollection) {
        this.commentaireCollection = commentaireCollection;
    }

    public Collection<LigneService> getLigneServiceCollection() {
        return ligneServiceCollection;
    }

    public void setLigneServiceCollection(Collection<LigneService> ligneServiceCollection) {
        this.ligneServiceCollection = ligneServiceCollection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entits.User[ id=" + id + " ]";
    }

}
