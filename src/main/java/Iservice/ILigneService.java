/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.Commande;
import entits.LigneService;
import entits.User;
import java.util.List;

/**
 *
 * @author abdelli
 */
public interface ILigneService {
    public int creerLigneService(LigneService ls);
    public void modifierLigneService(LigneService ls);
    public void supprimerLigneService(LigneService ls);
    public List<LigneService> afficherLigneService();     
    public List<LigneService> getLigneService(Commande commande);
    public List<LigneService> getLigneServiceByUser(User user);
}
