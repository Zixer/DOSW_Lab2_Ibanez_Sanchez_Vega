package edu.ecu.dosw.reto3;

/**
 * Represents a car
 */
public class Car extends Vehicle {

    /**
     * Creates a car
     * @param model car model
     * @param category car category
     * @param maxSpeed maximum speed
     * @param price car price
     * @param equipment special equipment
     */
    public Car(String model, Category category, int maxSpeed, double price, String equipment) {
        super("Car", model, category, maxSpeed, price, equipment);
    }
}
