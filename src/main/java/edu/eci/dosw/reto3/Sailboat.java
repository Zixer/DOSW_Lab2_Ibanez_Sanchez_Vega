package edu.eci.dosw.reto3;

/*
Represents a sailboat.*/
public class Sailboat extends Vehicle {

/*
Creates a sailboat.*
@param model sailboat model
@param category vehicle category
@param maxSpeed maximum speed
@param price vehicle price
@param equipment special equipment
*/
public Sailboat(String model, Category category, int maxSpeed, double price, String equipment) {
    super("Sailboat", model, category, maxSpeed, price, equipment);
    }
}