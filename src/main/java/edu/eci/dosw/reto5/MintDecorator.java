package edu.eci.dosw.reto5;

/* Adds mint to a coffee.*/
public class MintDecorator extends CoffeeDecorator {

    private static final double PRICE = 1300.0;

    
/*Creates a mint decorator.
@param coffee coffee to decorate*/
public MintDecorator(Coffee coffee) {
 super(coffee);}

    
/*Adds mint to the coffee description.*
@return updated description*/
@Override
public String getDescription() {
 return coffee.getDescription() + ", Mint";}

    
/*Adds the mint price.
@return updated price*/
@Override
public double getPrice() {
 return coffee.getPrice() + PRICE;}
}