package edu.eci.dosw.reto5;

/**
 * Adds milk to a coffee
 */
public class MilkDecorator extends CoffeeDecorator {

    private static final double PRICE = 1000.0;

    /**
     * Creates a milk decorator
     * @param coffee coffee to which milk will be added
     */
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    /**
     * Adds milk to the current coffee description
     * @return updated coffee description
     */
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    /**
     * Adds the price of milk to the current coffee price
     * @return updated coffee price
     */
    @Override
    public double getPrice() {
        return coffee.getPrice() + PRICE;
    }
}
