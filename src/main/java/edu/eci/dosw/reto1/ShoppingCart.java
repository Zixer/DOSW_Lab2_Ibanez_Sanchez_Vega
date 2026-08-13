package edu.eci.dosw.reto1;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public double calculateSubtotal() {
        return items.stream()
                .map(CartItem::getTotal)
                .reduce(0.0, Double::sum);
    }

    public List<CartItem> getItems() {
        return List.copyOf(items);
    }
}