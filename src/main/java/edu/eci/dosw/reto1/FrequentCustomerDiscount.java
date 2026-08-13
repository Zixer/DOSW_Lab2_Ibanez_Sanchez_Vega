package edu.eci.dosw.reto1;

public class FrequentCustomerDiscount implements DiscountPolicy {

    @Override
    public double calculateDiscount(double subtotal) {
        return subtotal * 0.10;
    }

    @Override
    public String getDescription() {
        return "Frequent customer - 10%";
    }
}
