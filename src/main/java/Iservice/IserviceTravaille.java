/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;
import entits.ListeTravail;
import entits.User;
import java.util.List;
/**
 *
 * @author aisce
 */
public interface IserviceTravaille {
   public int ajoutTravaille(ListeTravail a);
    public String afficherTravaille(int use);
   public List<ListeTravail> afficherTravail(int idu);
    
}
