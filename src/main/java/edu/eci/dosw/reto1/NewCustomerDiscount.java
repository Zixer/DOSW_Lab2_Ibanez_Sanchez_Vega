package edu.eci.dosw.reto1;

public class NewCustomerDiscount implements DiscountPolicy {

    @Override
    public double calculateDiscount(double subtotal) {
        return subtotal * 0.05;
    }

    @Override
    public String getDescription() {
        return "New customer - 5%";
    }
}