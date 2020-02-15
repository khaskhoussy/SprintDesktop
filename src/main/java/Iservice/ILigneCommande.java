/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.LigneCommande;
import java.util.List;

/**
 *
 * @author abdelli
 */
public interface ILigneCommande {   
public void creeLigneCommande(LigneCommande lc);
public void modifierLigneCommande(LigneCommande lc);
public void supprimerLigneCommande(LigneCommande lc);
public List<LigneCommande> afficherLigneCommande(); 
}
