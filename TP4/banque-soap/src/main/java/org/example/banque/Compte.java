package org.example.banque;

import java.io.Serializable;
import java.util.Date;

public class Compte implements Serializable {
    private int id;
    private String nom;
    private double solde;
    private Date dateCreation;
    
    public Compte() {}
    
    public Compte(int id, String nom, double solde) {
        this.id = id;
        this.nom = nom;
        this.solde = solde;
        this.dateCreation = new Date();
    }
    
    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getSolde() { return solde; }
    public Date getDateCreation() { return dateCreation; }
    
    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setSolde(double solde) { this.solde = solde; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    
    @Override
    public String toString() {
        return "Compte{id=" + id + ", nom='" + nom + "', solde=" + solde + "}";
    }
}