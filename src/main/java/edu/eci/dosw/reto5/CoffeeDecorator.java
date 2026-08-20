package edu.eci.dosw.reto5; 

/* 
Base decorator for coffee objects.
Stores a reference to another Coffee object
so additional behavior can be added dynamically.
*/

public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee coffee;

/*
Creates a decorator that wraps another coffee.
@param coffee coffee being decorated
*/
protected CoffeeDecorator(Coffee coffee) {this.coffee = coffee;}}
