package edu.eci.dosw.reto3;

/**
 * Defines the contract for factories
 * responsible for creating vehicles.
 */
public interface VehicleFactory {

    /**
     * Creates a vehicle.
     *
     * @param type vehicle type
     * @param model vehicle model
     * @param category vehicle category
     * @return created vehicle
     */
    Vehicle createVehicle(String type,String model,Category category);
}