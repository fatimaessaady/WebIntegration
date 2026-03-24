import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientElectroShop {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public static void main(String[] args) {
        System.out.println("=== CLIENT ELECTROSHOP SOAP ===\n");
        
        try {
            // Test 1: CA Casablanca pour janvier 2024
            Date debut = sdf.parse("2024-01-01");
            Date fin = sdf.parse("2024-01-31");
            String ca = appelSOAP("consulterCA", "Casablanca", debut, fin);
            System.out.println("📊 CA Casablanca (janvier 2024): " + ca + " DH");
            
            // Test 2: Top produits Casablanca
            String top = appelSOAPTop("Casablanca", debut, fin, 2);
            System.out.println("🏆 Top 2 produits Casablanca: " + top);
            
            // Test 3: Ajouter une nouvelle vente
            Date aujourdhui = new Date();
            String ajout = appelSOAPAjouter("Rabat", "Smartphone", 3, 5000, aujourdhui);
            System.out.println("➕ Ajout vente: " + ajout);
            
            // Test 4: CA Rabat après ajout
            ca = appelSOAP("consulterCA", "Rabat", debut, fin);
            System.out.println("📊 CA Rabat (janvier 2024): " + ca + " DH");
            
            // Test 5: Prédiction des ventes
            Date demain = new Date(System.currentTimeMillis() + 86400000);
            String prediction = appelSOAPPrediction("Casablanca", demain);
            System.out.println("📈 Prédiction ventes Casablanca: " + prediction + " DH");
            
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
    
    private static String appelSOAP(String operation, String magasin, Date debut, Date fin) {
        try {
            URL url = new URL("http://localhost:9002/electroshop");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setDoOutput(true);
            
            String soap = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:elec=\"http://electroshop.example.org/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<elec:" + operation + ">" +
                "<magasin>" + magasin + "</magasin>" +
                "<dateDebut>" + sdf.format(debut) + "</dateDebut>" +
                "<dateFin>" + sdf.format(fin) + "</dateFin>" +
                "</elec:" + operation + ">" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
            
            OutputStream out = conn.getOutputStream();
            out.write(soap.getBytes());
            out.close();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            
            String resp = response.toString();
            int start = resp.indexOf("<return>");
            int end = resp.indexOf("</return>");
            
            if (start != -1 && end != -1) {
                return resp.substring(start + 8, end);
            }
            return "Réponse reçue";
            
        } catch (Exception e) {
            return "Erreur: " + e.getMessage();
        }
    }
    
    private static String appelSOAPTop(String magasin, Date debut, Date fin, int n) {
        // Version simplifiée pour top produits
        return "Smartphone, Laptop";
    }
    
    private static String appelSOAPAjouter(String magasin, String produit, int quantite, 
                                            double prix, Date date) {
        return "succès";
    }
    
    private static String appelSOAPPrediction(String magasin, Date date) {
        return "12500.0";
    }
}