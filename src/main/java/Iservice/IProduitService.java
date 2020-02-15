/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.OffrePromotion;
import entits.Produit;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface IProduitService {
     public void creerProduit(Produit p);
    public void modifierProduit(Produit p);
    public void supprimerProduit(Produit p);
    public List<Produit> afficherproduits();
    public void affecterPromotion(Produit p , OffrePromotion o);
    public List<Produit> filtrer(String catnom);
    public void updateQuantite(Produit p, int quantite);
    public void updateQuantitePlus(Produit p, int quantite);
    public List<Produit> afficherproduitss();
  
    
}
