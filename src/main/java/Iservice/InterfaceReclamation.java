/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;


import entits.Commande;
import entits.Reclamation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dreamer
 */
public interface InterfaceReclamation 
{
 

    /**
     *
     * @param a
     */
public int AjouterReclamation(Reclamation s); 
public void SupprimerReclamation(int a);
public void ModifierReclamation(Reclamation s);
public ArrayList<Reclamation> AfficherReclamation();
 public List<Reclamation> AfficherReclamation2();
public List<Reclamation> RechercheReclamation(int id);
public ArrayList<Commande> GetIdReclamation(int id);

  
    
}
