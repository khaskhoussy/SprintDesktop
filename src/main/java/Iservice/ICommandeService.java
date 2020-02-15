/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;
import entits.Commande;
import entits.LigneCommande;
import java.util.List;

/**
 *
 * @author abdelli
 */
public interface ICommandeService {
    public int creerCommande(Commande p);
    public void modifierCommande(Commande p);
    public void supprimerCommande(Commande p);
    public List<Commande> afficherCommande();
public List<LigneCommande> getLigneCommande(Commande commande);    
}
