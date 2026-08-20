package edu.eci.dosw.reto3;

/**
 * Implements the discount rules for
 * vehicle purchases.
 *
 * Discount rules:
 * - 1 vehicle: 0%
 * - 2 vehicles: 5%
 * - 3 or more vehicles: 10%
 */
public class PurchaseDiscountStrategy implements DiscountStrategy {

    /**
     * Calculates the purchase discount based
     * on the number of selected vehicles.
     *
     * @param subtotal total price before discount
     * @param numberOfVehicles number of vehicles
     * @return calculated discount amount
     */
    @Override
    public double calculateDiscount(double subtotal,int numberOfVehicles) {

        if (numberOfVehicles >= 3) {
            return subtotal * 0.10;
        }

        if (numberOfVehicles == 2) {
            return subtotal * 0.05;
        }

        return 0.0;
    }
}