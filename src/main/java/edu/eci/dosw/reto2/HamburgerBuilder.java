package edu.eci.dosw.reto2;

import java.util.ArrayList;
import java.util.List;

public class HamburgerBuilder implements Builder {

    private List<Ingredient> ingredients;

    public HamburgerBuilder() {
        ingredients = new ArrayList<>();
    }

    @Override
    public Builder addBread() {
        ingredients.add(new Ingredient("Pan", 2.00));
        return this;
    }

    @Override
    public Builder addMeat() {
        ingredients.add(new Ingredient("Carne", 5.00));
        return this;
    }

    @Override
    public Builder addCheese() {
        ingredients.add(new Ingredient("Queso", 1.50));
        return this;
    }

    @Override
    public Builder addLettuce() {
        ingredients.add(new Ingredient("Lechuga", 0.80));
        return this;
    }

    @Override
    public Builder addTomato() {
        ingredients.add(new Ingredient("Tomate", 0.80));
        return this;
    }

    @Override
    public Builder addBacon() {
        ingredients.add(new Ingredient("Tocino", 2.00));
        return this;
    }

    @Override
    public Builder addBBQSauce() {
        ingredients.add(new Ingredient("Salsa BBQ", 1.00));
        return this;
    }

    @Override
    public Hamburger build() {
        return new Hamburger(ingredients);
    }
}
