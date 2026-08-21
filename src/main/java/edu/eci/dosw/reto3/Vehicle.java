package edu.eci.dosw.reto3;

/**
 * Represents a generic vehicle in the dealership.
 */
public abstract class Vehicle {

    private final String type;
    private final String model;
    private final Category category;
    private final int maxSpeed;
    private final double price;
    private final String specialEquipment;

    /**
     * Creates a vehicle with its main characteristics.
     *
     * @param type vehicle type
     * @param model vehicle model
     * @param category vehicle category
     * @param maxSpeed maximum speed
     * @param price vehicle price
     * @param specialEquipment special equipment
     */
    protected Vehicle(
            String type,
            String model,
            Category category,
            int maxSpeed,
            double price,
            String specialEquipment) {

        this.type = type;
        this.model = model;
        this.category = category;
        this.maxSpeed = maxSpeed;
        this.price = price;
        this.specialEquipment = specialEquipment;
    }

    /**
     * Returns the vehicle type.
     *
     * @return vehicle type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the vehicle model.
     *
     * @return vehicle model
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the vehicle category.
     *
     * @return vehicle category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Returns the maximum speed.
     *
     * @return maximum speed
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Returns the vehicle price.
     *
     * @return vehicle price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the vehicle special equipment.
     *
     * @return special equipment
     */
    public String getSpecialEquipment() {
        return specialEquipment;
    }

    /**
     * Displays the complete information
     * of the vehicle.
     */
    public void showInformation() {

        System.out.println("------------------------------");
        System.out.println("Type: " + type);
        System.out.println("Model: " + model);
        System.out.println("Category: " + category);
        System.out.println("Maximum speed: " + maxSpeed + " km/h");
        System.out.printf("Price: $%,.2f%n", price);
        System.out.println(
                "Special equipment: " + specialEquipment
        );
    }
}