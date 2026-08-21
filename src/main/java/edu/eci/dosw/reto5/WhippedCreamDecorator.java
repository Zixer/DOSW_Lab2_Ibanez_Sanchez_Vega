package reto5;

/**
 * Adds whipped cream to a coffee.
 */
public class WhippedCreamDecorator
        extends CoffeeDecorator {

    private static final double PRICE = 2000.0;

    /**
     * Creates a whipped cream decorator
     * @param coffee coffee to decorate
     */
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    /**
     * Adds whipped cream to the description
     * @return updated description
     */
    @Override
    public String getDescription() {
        return coffee.getDescription()
                + ", Whipped Cream";
    }

    /**
     * Adds the whipped cream price
     * @return updated price
     */
    @Override
    public double getPrice() {
        return coffee.getPrice() + PRICE;
    }
}