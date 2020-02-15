/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.Evenement;
import entits.OffrePromotion;
import entits.Participation;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author FARAH
 */
public interface Ieventservice {
    public void creerevent(Evenement e);
public void deleteevent(Evenement e);
public void updateevent(Evenement e);
//public ArrayList<Evenement> afficherevent();
public boolean RechercherparLieu(String lieu);
public void participer(Participation p) throws SQLException;
public void annulerparticiper(Participation p) throws SQLException;
public void affecterPromotion(Evenement e , OffrePromotion o);
public ArrayList <Evenement> afficherevent();

    
}
