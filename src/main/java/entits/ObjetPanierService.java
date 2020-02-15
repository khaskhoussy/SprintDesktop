/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author abdelli
 */
public class ObjetPanierService implements Commandable{
    private User user ;
    private Date dateDebut;
    private Date dateFin;

    @Override
    public int getIdObjet() {
        return user.getId();
    }

    @Override
    public double getPrixTotale() {
        String role  = user.getRoles();
        if(role.equals("Jardinier"))  return 35 ;
        if(role.equals("Paysagiste")) return 40;
        return 0;
    }

    @Override
    public Integer getQuantite() {
        return 1;
    }

    @Override
    public String getType() {
        return "user";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String getNom() {
        String role  = user.getRoles();
        if(role.equals("Jardinier"))  return "Jardinier("+this.user.getNom()+" "+user.getPrenom()+")" ;
        if(role.equals("Paysagiste"))  return "Pasagiste("+this.user.getNom()+" "+user.getPrenom()+")" ;
        return "";
    }

    @Override
    public void setQuantite(int qte) {
        
    }

    @Override
    public String getDescription() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(dateDebut)+"-->"+dateFormat.format(dateFin);
    }
    
    
    
}
