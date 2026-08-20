package edu.eci.dosw.reto5; 

/**
 * Adds caramel to a coffee.
 */
public class CaramelDecorator extends CoffeeDecorator {

    private static final double PRICE = 1200.0;

    /**
     * Creates a caramel decorator
     * @param coffee coffee to decorate
     */
    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }

    /**
     * Adds caramel to the description
     * @return updated coffee description
     */
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Caramel";
    }

    /**
     * Adds the caramel price
     * @return updated coffee price
     */
    @Override
    public double getPrice() {
        return coffee.getPrice() + PRICE;
    }
}