package edu.eci.dosw.reto1;

public interface DiscountPolicy {

    double calculateDiscount(double subtotal);

    String getDescription();
}
