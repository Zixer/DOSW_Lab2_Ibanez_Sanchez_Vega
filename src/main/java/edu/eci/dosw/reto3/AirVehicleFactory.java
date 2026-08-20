package edu.eci.dosw.reto3;

public class AirVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String type,String model,Category category) {

        return switch (type.toLowerCase()) {

            case "airplane" ->
                    new Airplane(model,category,getSpeed(category, 850),getPrice(category, 2000000),getEquipment(category));

            case "lightaircraft" ->
                    new LightAircraft(model,category,getSpeed(category, 350),getPrice(category, 500000),getEquipment(category));

            case "helicopter" ->
                    new Helicopter(model,category,getSpeed(category, 300),getPrice(category, 800000),getEquipment(category));

            default ->
                    throw new IllegalArgumentException( "Invalid air vehicle.");
        };
    }

    private double getPrice(Category category,double basePrice) {

        return switch (category) {
            case ECONOMY -> basePrice;
            case LUXURY -> basePrice * 2;
            case USED -> basePrice * 0.65;
        };
    }

    private int getSpeed(Category category,int baseSpeed) {

        return switch (category) {
            case ECONOMY -> baseSpeed;
            case LUXURY -> baseSpeed + 100;
            case USED -> baseSpeed - 50;
        };
    }

    private String getEquipment(Category category) {

        return switch (category) {

            case ECONOMY -> "Standard aviation equipment";

            case LUXURY -> "Premium cabin, autopilot and advanced avionics";

            case USED -> "Basic aviation equipment";
        };
    }
}