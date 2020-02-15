/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.Sujet;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author hp
 */
public interface ISujetService {
        public void ajouterSujet(Sujet a);
        public void modifierSujet(Sujet a);
        public void supprimerSujet(Sujet a);
        public Collection<Sujet> afficherSujet();
        public List<Sujet> afficherSujetPlante();
        public List<Sujet> afficherSujetAccessoire() ;
        public List<Sujet> afficherSujetDivers();
        public List<Sujet> chercherSujet(String nomsujet) ;
}


