package edu.eci.dosw.reto5;

/**
 * Adds chocolate to a coffee.
 */
public class ChocolateDecorator extends CoffeeDecorator {

    private static final double PRICE = 1500.0;

    /**
     * Creates a chocolate decorator.
     * @param coffee coffee to decorate
     */
    public ChocolateDecorator(Coffee coffee) {
        super(coffee);
    }

    /**
     * Adds chocolate to the coffee description.
     * @return updated description
     */
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Chocolate";
    }

    /**
     * Adds the chocolate price to the coffee.
     * @return updated price
     */
    @Override
    public double getPrice() {
        return coffee.getPrice() + PRICE;
    }
}