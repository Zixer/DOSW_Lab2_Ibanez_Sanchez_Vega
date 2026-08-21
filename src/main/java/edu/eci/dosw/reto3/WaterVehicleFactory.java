package edu.eci.dosw.reto3;

public class WaterVehicleFactory
        implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String type, String model, Category category) {
        return switch (type.toLowerCase()) {
            
            case "motorboat" -> new Motorboat(model, category, getSpeed(category, 100), getPrice(category, 50000), getEquipment(category));
            case "sailboat" -> new Sailboat(model, category, getSpeed(category, 60), getPrice(category, 40000), getEquipment(category));
            case "jetski" -> new JetSki(model, category, getSpeed(category, 120), getPrice(category, 18000), getEquipment(category));

            default -> throw new IllegalArgumentException("Invalid water vehicle.");
        };
    }

    private double getPrice(Category category,double basePrice) {

        return switch (category) {
            case ECONOMY -> basePrice;
            case LUXURY -> basePrice * 2;
            case USED -> basePrice * 0.65;
        };
    }

    private int getSpeed(Category category, int baseSpeed) {

        return switch (category) {
            case ECONOMY -> baseSpeed;
            case LUXURY -> baseSpeed + 30;
            case USED -> baseSpeed - 15;
        };
    }

    private String getEquipment(Category category) {

        return switch (category) {

            case ECONOMY -> "Standard navigation equipment";
            case LUXURY -> "GPS, premium interior and advanced navigation";
            case USED -> "Basic navigation equipment";
        };
    }
}