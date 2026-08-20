package edu.eci.dosw.reto3;

/**
 * Factory responsible for creating land vehicles
 */
public class LandVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String type, String model, Category category) {
        return switch (type.toLowerCase()) {
            case "car" -> new Car(model,category,getSpeed(category, 180),getPrice(category, 30000),getEquipment(category));
            case "bicycle" -> new Bicycle(model, category, getSpeed(category, 40), getPrice(category, 1000), getEquipment(category));
            case "motorcycle" -> new Motorcycle(model, category, getSpeed(category, 200), getPrice(category, 15000), getEquipment(category));
            default ->throw new IllegalArgumentException("Invalid land vehicle.");
        };
    }

    /**
     * Calculates the price according to the vehicle category
     */
    private double getPrice(Category category, double basePrice) {
        return switch (category) {
            case ECONOMY -> basePrice;
            case LUXURY -> basePrice * 2;
            case USED -> basePrice * 0.65;
        };
    }

    /**
     * Calculates the maximum speed according to the vehicle category
     */
    private int getSpeed(Category category, int baseSpeed) {
        return switch (category) {
            case ECONOMY -> baseSpeed;
            case LUXURY -> baseSpeed + 50;
            case USED -> baseSpeed - 20;
        };
    }

    /**
     * Returns equipment according to the vehicle category
     */
    private String getEquipment(Category category) {
        return switch (category) {
            case ECONOMY -> "Standard equipment";
            case LUXURY -> "Premium seats, GPS and advanced technology";
            case USED -> "Basic equipment";
        };
    }
}