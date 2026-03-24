package org.example.ws;

import javax.xml.ws.Endpoint;

public class ServeurSOAP {
    public static void main(String[] args) {
        String url = "http://localhost:9000/calculatrice";
        
        System.out.println("=== DÉMARRAGE DU SERVEUR SOAP ===");
        System.out.println("URL du service: " + url);
        
        Endpoint.publish(url, new CalculatriceImp());
        
        System.out.println("✅ Service SOAP démarré avec succès!");
        System.out.println("📄 WSDL disponible sur: " + url + "?wsdl");
    }
}