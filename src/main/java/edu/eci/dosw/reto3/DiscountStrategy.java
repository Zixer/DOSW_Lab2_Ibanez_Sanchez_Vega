package edu.eci.dosw.reto3;

/*
Defines the strategy used to calculate a discount for a vehicle purchase.
*/
public interface DiscountStrategy {

/*
Calculates the discount according to the subtotal and number of purchased vehicles.
@param subtotal total price before discount
@param numberOfVehicles number of purchased vehicles
@return discount amount
*/
double calculateDiscount(double subtotal, int numberOfVehicles);
}