/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.Commentaire;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author hp
 */
public interface ICommentaireService {
    public void ajouterCommentaire(Commentaire a);
    public void ViderTableCommentaire(Commentaire a);
    public void supprimerCommentaire(Commentaire a);
    public List<Commentaire> afficherCommentaire(int IdSujet);
}
