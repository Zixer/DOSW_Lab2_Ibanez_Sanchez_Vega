package edu.eci.dosw.reto5; 

/* 
Defines the common behavior for a coffee.
Both the basic coffee and all decorators
implement this interface.
*/
public interface Coffee {

/* 
Returns the complete description of the coffee.
@return coffee description
*/

String getDescription();

/**
Returns the total price of the coffee,
including all added toppings.
@return total coffee price*/
double getPrice();
}