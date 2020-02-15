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
public interface IOffreService {
     public void creerOffre(OffrePromotion o);
    public void modifierOffre(OffrePromotion o);
    public void supprimerOffre(OffrePromotion o);
    public List<OffrePromotion> afficherOffre(String type);
    public List<OffrePromotion> afficheroffre();
    
}
