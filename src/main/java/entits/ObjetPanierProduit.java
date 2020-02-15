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
    public class ObjetPanierProduit implements Commandable{
        private Produit produit ;
        private Integer quantite ;
        
        
        public ObjetPanierProduit(Produit produit,Integer quantite){
            this.produit  = produit;
            this.quantite = quantite;
        }

        public Produit getProduit() {
            return produit;
        }

        
        public Integer getId() {
            return produit.getId();
        }

    @Override
    public int getIdObjet() {
        return this.getId();
    }

    @Override
    public double getPrixTotale() {
         double prix  = (produit.getPrixOffre() == 0)?produit.getPrixProd():produit.getPrixOffre();
         return prix*this.quantite;
    }

    @Override
    public Integer getQuantite() {
        return this.quantite;
    }

    @Override
    public String getType (){
       return "produit";
    }

    @Override
    public String getNom() {
        return this.produit.getNomProd();
    }

    @Override
    public void setQuantite(int qte) {
        this.quantite = qte ;
    }

    @Override
    public String getDescription() {
        return produit.getProdDescription();
    }
        
        
    }    
