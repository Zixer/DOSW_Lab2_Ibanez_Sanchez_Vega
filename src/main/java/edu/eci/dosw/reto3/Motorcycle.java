package edu.eci.dosw.reto3;

/**
 * Represents a motorcycle
 */
public class Motorcycle extends Vehicle {

    /**
     * Creates a motorcycle
     * @param model motorcycle model
     * @param category motorcycle category
     * @param maxSpeed maximum speed
     * @param price motorcycle price
     * @param equipment special equipment
     */
    public Motorcycle(String model, Category category, int maxSpeed, double price, String equipment) {
        super("Motorcycle", model, category, maxSpeed, price,equipment);
    }
}