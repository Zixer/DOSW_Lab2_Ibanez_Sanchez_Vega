package edu.eci.dosw.reto1;

public class Receipt {

    private final ShoppingCart cart;
    private final DiscountPolicy discountPolicy;

    public Receipt(ShoppingCart cart, DiscountPolicy discountPolicy) {
        this.cart = cart;
        this.discountPolicy = discountPolicy;
    }

    public void printReceipt() {

        double subtotal = cart.calculateSubtotal();
        double discount = discountPolicy.calculateDiscount(subtotal);
        double finalTotal = subtotal - discount;

        System.out.println("\n===== DON PEPE'S STORE =====");

        cart.getItems()
                .stream()
                .filter(item -> item.getQuantity() > 0)
                .forEach(item ->
                        System.out.println(
                                item.getQuantity()
                                        + " x "
                                        + item.getProduct().getName()
                                        + " = COP "
                                        + item.getTotal()
                        )
                );

        System.out.println("----------------------------");
        System.out.println("Subtotal: COP " + subtotal);
        System.out.println("Discount: " + discountPolicy.getDescription());
        System.out.println("Discount amount: COP " + discount);
        System.out.println("Total: COP " + finalTotal);
    }
}