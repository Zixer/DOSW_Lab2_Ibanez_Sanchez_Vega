package edu.eci.dosw.reto3;

/*
Represents a motorboat.
*/
public class Motorboat extends Vehicle {

/*
Creates a motorboat.
@param model motorboat model
@param category vehicle category
@param maxSpeed maximum speed
@param price vehicle price
@param equipment special equipment
*/
public Motorboat(String model, Category category, int maxSpeed, double price, String equipment) {
        super("Motorboat", model, category, maxSpeed, price, equipment);
    }
}