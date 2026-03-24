package org.example.electroshop;

import java.io.Serializable;
import java.util.Date;

public class Vente implements Serializable {
    private int id;
    private String magasin;
    private String produit;
    private int quantite;
    private double prixUnitaire;
    private Date dateVente;
    private double montantTotal;
    
    public Vente() {}
    
    public Vente(int id, String magasin, String produit, int quantite, 
                 double prixUnitaire, Date dateVente) {
        this.id = id;
        this.magasin = magasin;
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.dateVente = dateVente;
        this.montantTotal = quantite * prixUnitaire;
    }
    
    // Getters
    public int getId() { return id; }
    public String getMagasin() { return magasin; }
    public String getProduit() { return produit; }
    public int getQuantite() { return quantite; }
    public double getPrixUnitaire() { return prixUnitaire; }
    public Date getDateVente() { return dateVente; }
    public double getMontantTotal() { return montantTotal; }
    
    // Setters
    public void setId(int id) { this.id = id; }
    public void setMagasin(String magasin) { this.magasin = magasin; }
    public void setProduit(String produit) { this.produit = produit; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public void setDateVente(Date dateVente) { this.dateVente = dateVente; }
    public void setMontantTotal(double montantTotal) { this.montantTotal = montantTotal; }
    
    @Override
    public String toString() {
        return String.format("Vente{id=%d, magasin='%s', produit='%s', quantite=%d, total=%.2f}",
                id, magasin, produit, quantite, montantTotal);
    }
}