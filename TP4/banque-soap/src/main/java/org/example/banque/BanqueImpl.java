package org.example.banque;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService(endpointInterface = "org.example.banque.IBanque")
public class BanqueImpl implements IBanque {
    
    private Map<Integer, Compte> comptes = new HashMap<>();
    
    public BanqueImpl() {
        // Comptes de test
        comptes.put(1, new Compte(1, "Alice", 5000));
        comptes.put(2, new Compte(2, "Bob", 3000));
        comptes.put(3, new Compte(3, "Charlie", 7000));
        System.out.println("Banque initialisée avec 3 comptes");
    }
    
    @Override
    public double consulterSolde(int id) {
        Compte compte = comptes.get(id);
        if (compte != null) {
            System.out.println("Consultation solde ID " + id + ": " + compte.getSolde());
            return compte.getSolde();
        }
        System.out.println("Compte ID " + id + " non trouvé");
        return -1;
    }
    
    @Override
    public boolean retirer(int id, double montant) {
        Compte compte = comptes.get(id);
        if (compte != null && compte.getSolde() >= montant) {
            compte.setSolde(compte.getSolde() - montant);
            System.out.println("Retrait ID " + id + ": " + montant + " - Nouveau solde: " + compte.getSolde());
            return true;
        }
        System.out.println("Retrait impossible ID " + id + " - Solde: " + 
                           (compte != null ? compte.getSolde() : "inexistant"));
        return false;
    }
    
    @Override
    public boolean deposer(int id, double montant) {
        Compte compte = comptes.get(id);
        if (compte != null) {
            compte.setSolde(compte.getSolde() + montant);
            System.out.println("Dépôt ID " + id + ": " + montant + " - Nouveau solde: " + compte.getSolde());
            return true;
        }
        return false;
    }
    
    @Override
    public Compte getCompte(int id) {
        System.out.println("Récupération du compte ID " + id);
        return comptes.get(id);
    }
}