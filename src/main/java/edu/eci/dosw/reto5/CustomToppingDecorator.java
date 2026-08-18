package edu.eci.dosw.reto5;

/*Decorator that allows a custom topping
with a custom name and price.*/
public class CustomToppingDecorator extends CoffeeDecorator {

    private final String toppingName;
    private final double toppingPrice;

/* Creates a custom topping decorator.
@param coffee coffee to decorate
@param toppingName custom topping name
@param toppingPrice custom topping price*/
public CustomToppingDecorator(Coffee coffee,String toppingName,double toppingPrice) {

        super(coffee);

        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
    }

/* Adds the custom topping name
to the description.
@return updated description*/
@Override
public String getDescription() {
 return coffee.getDescription()+ ", "+ toppingName;}

/* Adds the custom topping price
to the coffee price.
@return updated coffee price*/
@Override
public double getPrice() {
 return coffee.getPrice()+ toppingPrice;}
}