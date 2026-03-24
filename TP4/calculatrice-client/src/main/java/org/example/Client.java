package org.example;

import org.example.ws.ICalculatrice;
import org.example.ws.CalculatriceImpService;

public class Client {
    public static void main(String[] args) {
        System.out.println("=== CLIENT CALCULATRICE SOAP ===");
        
        try {
            CalculatriceImpService service = new CalculatriceImpService();
            ICalculatrice port = service.getCalculatorPort();
            
            System.out.println("\n15 + 25 = " + port.add(15, 25));
            System.out.println("50 - 20 = " + port.subtract(50, 20));
            System.out.println("12 * 8 = " + port.multiply(12, 8));
            System.out.println("100 / 4 = " + port.divide(100, 4));
            
            try {
                System.out.println("10 / 0 = " + port.divide(10, 0));
            } catch (Exception e) {
                System.out.println("Division par zéro: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}