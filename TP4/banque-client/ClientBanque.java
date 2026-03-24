import java.io.*;
import java.net.*;

public class ClientBanque {
    public static void main(String[] args) {
        System.out.println("=== CLIENT BANQUE SOAP ===\n");
        
        // Test consultation solde
        String solde = appelSOAP("consulterSolde", "1", "0");
        System.out.println("Solde Alice (ID 1): " + solde);
        
        // Test retrait
        String retrait = appelSOAP("retirer", "1", "500");
        System.out.println("Retrait 500€: " + retrait);
        
        // Vérifier nouveau solde
        solde = appelSOAP("consulterSolde", "1", "0");
        System.out.println("Nouveau solde Alice: " + solde);
        
        // Test dépôt
        String depot = appelSOAP("deposer", "1", "200");
        System.out.println("Dépôt 200€: " + depot);
        
        // Vérifier nouveau solde
        solde = appelSOAP("consulterSolde", "1", "0");
        System.out.println("Solde final Alice: " + solde);
        
        // Test compte inexistant
        solde = appelSOAP("consulterSolde", "99", "0");
        System.out.println("Compte inexistant (ID 99): " + solde);
    }
    
    private static String appelSOAP(String operation, String id, String montant) {
        try {
            URL url = new URL("http://localhost:9001/banque");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setDoOutput(true);
            
            String soap = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:ban=\"http://banque.example.org/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<ban:" + operation + ">" +
                "<id>" + id + "</id>" +
                (operation.equals("consulterSolde") ? "" : "<montant>" + montant + "</montant>") +
                "</ban:" + operation + ">" +
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
            
            // Extraire le résultat
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
}