package Iservice;

import entits.Geste;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dreamer
 */
public interface InterfaceGeste {
    public int AjouterGeste(Geste g); 
 public int SupprimerGeste(int a);
 public void ModifierGeste(Geste g);
 public ArrayList<Geste> AfficherGeste() ;
 public ArrayList<Geste> AfficherGeste(String desc_geste) ;
 public List<Geste> RechercherGeste(String mois_geste);
    
}
