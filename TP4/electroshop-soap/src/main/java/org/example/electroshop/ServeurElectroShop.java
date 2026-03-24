package org.example.electroshop;

import javax.xml.ws.Endpoint;

public class ServeurElectroShop {
    public static void main(String[] args) {
        String url = "http://localhost:9002/electroshop";
        
        System.out.println("=== SERVEUR ELECTROSHOP SOAP ===");
        System.out.println("URL: " + url);
        
        Endpoint.publish(url, new ElectroShopImpl());
        
        System.out.println("✅ Service ElectroShop démarré!");
        System.out.println("📄 WSDL: " + url + "?wsdl");
    }
}