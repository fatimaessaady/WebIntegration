package org.example.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "org.example.ws.ICalculatrice")
public class CalculatriceImp implements ICalculatrice {
    
    @Override
    public double add(double x, double y) {
        System.out.println("Addition de " + x + " et " + y);
        return x + y;
    }
    
    @Override
    public double subtract(double x, double y) {
        System.out.println("Soustraction de " + x + " et " + y);
        return x - y;
    }
    
    @Override
    public double multiply(double x, double y) {
        System.out.println("Multiplication de " + x + " et " + y);
        return x * y;
    }
    
    @Override
    public double divide(double x, double y) {
        if (y == 0) {
            throw new ArithmeticException("Division par zéro impossible");
        }
        System.out.println("Division de " + x + " par " + y);
        return x / y;
    }
}