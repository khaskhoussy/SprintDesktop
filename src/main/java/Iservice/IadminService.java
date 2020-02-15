/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.LigneService;
import entits.User;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author aisce
 */
public interface IadminService {
    public int ajouterAdmin(User a);
    public int ajouterJardinier(User a);
    public int ajouterExpert(User a);
    public int ajouterPaysagist(User a);
    public void supprimerJardinier(User a);
    //public List<User>chercherJardinier();
     public String chercherJardinier1(String use);
     public String chercherPassword(String use);
     public List<User> afficherPaysagist();
     public List<User> afficherExpert() ;
     public List<User> afficherClient();
     public List<User> chercherNom(String nom) ;
     public List<User> chercherPrenom(String prenom) ;
     public List<User> chercheremail(String email);
     public List<User> cherchertelephone(String telephone);
    public String modifNom(int idu);
     public String modifPrenom(int idu);
     public String modifEmail(int idu);
     public String modifTelephone(int idu);
     public String modifPassword(int idu);
     public int  modfierCompte(User a ,User b);
     public int  modfierComptee(User a);
     public int  modifId(String use);
     public List<User> afficherStaff();
      public String modifImg(String use);
       public int ajouterimg(User a,String u);
       public List<User> afficherGlobal();
       public String recupTel(String use);
     public String recupMdp(String use);
     public List<LigneService> getLigneServicesByEmployer(User user);
}
