package org.example.electroshop;

import javax.jws.WebService;
import java.util.*;
import java.util.stream.Collectors;

@WebService(endpointInterface = "org.example.electroshop.IElectroShop")
public class ElectroShopImpl implements IElectroShop {
    
    private List<Vente> ventes = new ArrayList<>();
    private int nextId = 1;
    
    public ElectroShopImpl() {
        // Données de test
        Calendar cal = Calendar.getInstance();
        
        // Ventes Casablanca
        cal.set(2024, Calendar.JANUARY, 15);
        ajouterVenteTest("Casablanca", "Laptop", 5, 8000, cal.getTime());
        cal.set(2024, Calendar.JANUARY, 16);
        ajouterVenteTest("Casablanca", "Smartphone", 10, 5000, cal.getTime());
        cal.set(2024, Calendar.JANUARY, 17);
        ajouterVenteTest("Casablanca", "Tablette", 8, 3000, cal.getTime());
        cal.set(2024, Calendar.JANUARY, 18);
        ajouterVenteTest("Casablanca", "Laptop", 3, 8000, cal.getTime());
        
        // Ventes Rabat
        cal.set(2024, Calendar.JANUARY, 15);
        ajouterVenteTest("Rabat", "Smartphone", 8, 5000, cal.getTime());
        cal.set(2024, Calendar.JANUARY, 16);
        ajouterVenteTest("Rabat", "Laptop", 4, 8000, cal.getTime());
        cal.set(2024, Calendar.JANUARY, 17);
        ajouterVenteTest("Rabat", "Tablette", 6, 3000, cal.getTime());
        
        // Ventes Tanger
        cal.set(2024, Calendar.JANUARY, 15);
        ajouterVenteTest("Tanger", "Laptop", 2, 8000, cal.getTime());
        cal.set(2024, Calendar.JANUARY, 16);
        ajouterVenteTest("Tanger", "Smartphone", 5, 5000, cal.getTime());
        
        System.out.println("ElectroShop initialisé avec " + ventes.size() + " ventes");
    }
    
    private void ajouterVenteTest(String magasin, String produit, int quantite, 
                                   double prixUnitaire, Date date) {
        ventes.add(new Vente(nextId++, magasin, produit, quantite, prixUnitaire, date));
    }
    
    @Override
    public boolean ajouterVente(String magasin, String produit, int quantite, 
                                 double prixUnitaire, Date dateVente) {
        Vente vente = new Vente(nextId++, magasin, produit, quantite, prixUnitaire, dateVente);
        ventes.add(vente);
        System.out.println("✅ Vente ajoutée: " + magasin + " - " + produit + 
                           " - " + quantite + " unités");
        return true;
    }
    
    @Override
    public double consulterCA(String magasin, Date dateDebut, Date dateFin) {
        double ca = ventes.stream()
                .filter(v -> v.getMagasin().equals(magasin))
                .filter(v -> !v.getDateVente().before(dateDebut) && 
                            !v.getDateVente().after(dateFin))
                .mapToDouble(Vente::getMontantTotal)
                .sum();
        
        System.out.println("CA " + magasin + " du " + dateDebut + " au " + dateFin + ": " + ca);
        return ca;
    }
    
    @Override
    public List<String> topProduits(String magasin, Date dateDebut, 
                                     Date dateFin, int n) {
        Map<String, Double> ventesParProduit = ventes.stream()
                .filter(v -> v.getMagasin().equals(magasin))
                .filter(v -> !v.getDateVente().before(dateDebut) && 
                            !v.getDateVente().after(dateFin))
                .collect(Collectors.groupingBy(
                    Vente::getProduit,
                    Collectors.summingDouble(Vente::getMontantTotal)
                ));
        
        List<String> top = ventesParProduit.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        System.out.println("Top " + n + " produits " + magasin + ": " + top);
        return top;
    }
    
    @Override
    public double predireVentes(String magasin, Date date) {
        // Récupérer les 5 derniers jours
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        List<Double> ventesHistorique = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date jour = cal.getTime();
            double ca = consulterCA(magasin, jour, jour);
            ventesHistorique.add(ca);
        }
        
        // Régression linéaire simple
        if (ventesHistorique.size() < 2) {
            return 0;
        }
        
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;
        for (int i = 0; i < ventesHistorique.size(); i++) {
            sumX += i;
            sumY += ventesHistorique.get(i);
            sumXY += i * ventesHistorique.get(i);
            sumX2 += i * i;
        }
        
        int n = ventesHistorique.size();
        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;
        
        double prediction = intercept + slope * n;
        
        System.out.println("📈 Prédiction pour " + magasin + " le " + date + ": " + prediction);
        return Math.max(0, prediction);
    }
}