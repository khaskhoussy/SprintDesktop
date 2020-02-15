/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import entits.LignePack;
import entits.PackDecoration;
import java.util.List;

/**
 *
 * @author Dreamer
 */
public interface InterfacePack {
    public int ajouterPack(PackDecoration s);
    public void ajouterLignePack(LignePack lignePack);
    public int SupprimerPack(int a);
    public void ModifierPack(PackDecoration s);
    public List<PackDecoration> AfficherPack();
    public List<PackDecoration> AfficherPackByType(String type);
    public List<PackDecoration> RecherchePack(int id);
    
}
