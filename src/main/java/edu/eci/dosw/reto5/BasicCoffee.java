package edu.eci.dosw.reto5;

/* 
Represents the basic coffee without toppings.
*/

public class BasicCoffee implements Coffee {

    private static final double BASE_PRICE = 5000.0;

/* 
Returns the description of the basic coffee.
@return basic coffee description
*/
@Override 
public String getDescription() {return "Basic Coffee";}

/**     
Returns the base price of the coffee.
@return basic coffee price
*/
@Override 
public double getPrice() {return BASE_PRICE;}}