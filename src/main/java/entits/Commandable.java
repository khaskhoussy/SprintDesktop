/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entits;

/**
 *
 * @author abdelli
 */
public interface Commandable {
    int getIdObjet();
    double getPrixTotale();
    Integer getQuantite();
    void setQuantite(int qte);
    String getType();
    String getNom();
    String getDescription();
    
}
