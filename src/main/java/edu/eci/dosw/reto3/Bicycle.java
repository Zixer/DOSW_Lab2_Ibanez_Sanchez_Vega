package edu.eci.dosw.reto3;

/**
 * Represents a bicycle
 */
public class Bicycle extends Vehicle {

    /**
     * Creates a bicycle
     * @param model bicycle model
     * @param category bicycle category
     * @param maxSpeed maximum speed
     * @param price bicycle price
     * @param equipment special equipment
     */
    public Bicycle(String model, Category category, int maxSpeed, double price, String equipment) {
        super("Bicycle", model, category, maxSpeed, price, equipment);
    }
}