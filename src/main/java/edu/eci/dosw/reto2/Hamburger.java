package edu.eci.dosw.reto2;

import java.util.List;

public class Hamburger {

    private final List<Ingredient> ingredients;

    public Hamburger(List<Ingredient> ingredients) { this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public double getTotalPrice() {
        return ingredients.stream().mapToDouble(Ingredient::getPrice).sum();
    }

    public void showSummary() {

        System.out.println("\n====================================");
        System.out.println("      HAMBURGUESA PERSONALIZADA");
        System.out.println("====================================");

        System.out.println("\nIngredientes seleccionados:");

        ingredients.forEach(ingredient -> System.out.println("- " + ingredient)
        );

        System.out.println("\nPrecio final: $" + String.format("%.2f", getTotalPrice()));

        System.out.println("====================================");
    }
}