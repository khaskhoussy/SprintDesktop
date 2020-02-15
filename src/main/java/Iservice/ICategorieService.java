/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.Categorie;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface ICategorieService {
    public void creerCategorie(Categorie c);
    public void modifierCategorie(Categorie c);
    public void supprimerCategorie(Categorie c);
    public List<Categorie> affichercategories();
    public int checkCategorie(Categorie c);
    public boolean rechercherparNom(String nom_cat);
    
    //public List<categorie> rechercher(String plante);
    
}
