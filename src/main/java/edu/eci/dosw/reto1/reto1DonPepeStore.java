package edu.eci.dosw.reto1;

public final class reto1DonPepeStore {

    private reto1DonPepeStore() {
    }

    public static void run() {

        System.out.println("Running Challenge 1 - Don Pepe's Store...");

        Product tshirt = new Product("T-shirt", 20000);
        Product pants = new Product("Pants", 50000);
        Product cookies = new Product("Cookies", 500);
        Product naturalJuice = new Product("Natural Juice", 3000);

        ShoppingCart cart = new ShoppingCart();

        cart.addProduct(tshirt, 2);
        cart.addProduct(cookies, 3);
        cart.addProduct(naturalJuice, 5);

        DiscountPolicy discount = new FrequentCustomerDiscount();

        Receipt receipt = new Receipt(cart, discount);

        receipt.printReceipt();
    }
}