package org.example.banque;

import javax.xml.ws.Endpoint;

public class ServeurBanque {
    public static void main(String[] args) {
        String url = "http://localhost:9001/banque";
        
        System.out.println("=== SERVEUR BANQUE SOAP ===");
        System.out.println("URL: " + url);
        
        Endpoint.publish(url, new BanqueImpl());
        
        System.out.println("✅ Service banque démarré!");
        System.out.println("📄 WSDL: " + url + "?wsdl");
    }
}