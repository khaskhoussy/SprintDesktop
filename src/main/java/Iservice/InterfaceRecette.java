/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.Recette;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dreamer
 */
public interface InterfaceRecette 
{
 public int AjouterRec(Recette r); 
 public int  SupprimerRec(int a);
 public void ModifierRec(Recette r);
 public ArrayList<Recette> AfficherRecette() ;
 public ArrayList<Recette> AfficherRecette(String type) ;
 public List<Recette> RechercherRec(String type);


}
