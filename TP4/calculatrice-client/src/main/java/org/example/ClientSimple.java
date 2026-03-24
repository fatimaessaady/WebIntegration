package org.example;

import java.io.*;
import java.net.*;

public class ClientSimple {
    public static void main(String[] args) {
        System.out.println("=== CLIENT SOAP SIMPLE ===\n");
        
        // Vérifier que le serveur est accessible
        if (!serveurAccessible()) {
            System.out.println("❌ Serveur non accessible!");
            System.out.println("Assurez-vous que le serveur est démarré sur http://localhost:9000");
            return;
        }
        
        System.out.println("✅ Serveur accessible\n");
        
        // Tests
        testOperation("add", 15, 25, "15 + 25");
        testOperation("subtract", 50, 20, "50 - 20");
        testOperation("multiply", 12, 8, "12 * 8");
        testOperation("divide", 100, 4, "100 / 4");
        
        System.out.println("\n--- Test division par zéro ---");
        String resultat = appelSOAP("divide", 10, 0);
        System.out.println("10 / 0 = " + resultat);
    }
    
    private static boolean serveurAccessible() {
        try {
            URL url = new URL("http://localhost:9000/calculatrice?wsdl");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.connect();
            return conn.getResponseCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static void testOperation(String operation, double a, double b, String description) {
        String resultat = appelSOAP(operation, a, b);
        System.out.println(description + " = " + resultat);
    }
    
    private static String appelSOAP(String operation, double a, double b) {
        try {
            URL url = new URL("http://localhost:9000/calculatrice");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setDoOutput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            
            String soap = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:ws=\"http://ws.example.org/\">" +
                "<soapenv:Header/>" +
                "<soapenv:Body>" +
                "<ws:" + operation + ">" +
                "<nbr1>" + a + "</nbr1>" +
                "<nbr2>" + b + "</nbr2>" +
                "</ws:" + operation + ">" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
            
            OutputStream out = conn.getOutputStream();
            out.write(soap.getBytes());
            out.close();
            
            // Vérifier le code de réponse
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return "Erreur HTTP " + responseCode;
            }
            
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
            } else if (resp.contains("fault")) {
                return "Erreur: Division par zéro!";
            } else {
                return "Résultat trouvé";
            }
            
        } catch (Exception e) {
            return "Erreur: " + e.getMessage();
        }
    }
}