package edu.eci.dosw.reto3;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Executes Challenge 3 - The Kingdom of Vehicles
 */
public final class reto3KingdomofVehicles {

    /**
     * Prevents instances of this utility class.
     */
    private reto3KingdomofVehicles() {
    }

    /**
     * Executes Challenge 3
     * Allows the user to select multiple vehicles and displays the final purchase summary
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        List<Vehicle> vehicles = new ArrayList<>();
        System.out.println("\n================================");
        System.out.println("CHALLENGE 3 - KINGDOM OF VEHICLES");
        System.out.println("================================");
        boolean continueBuying = true;
        while (continueBuying) {
            int family = selectFamily(scanner);
            VehicleFactory factory = getFactory(family);
            String type = selectVehicleType(scanner, family);
            Category category = selectCategory(scanner);
            scanner.nextLine();
            System.out.print("\nEnter vehicle model: ");
            String model = scanner.nextLine();
            Vehicle vehicle = factory.createVehicle(type, model, category);
            vehicles.add(vehicle);
            System.out.println("\nVehicle successfully created:");
            vehicle.showInformation();
            System.out.println("\nDo you want to add another vehicle?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int option = scanner.nextInt();
            continueBuying = option == 1;
        }
        showSummary(vehicles);
    }

    /**
     * Displays the vehicle families
     * @param scanner scanner used for user input
     * @return selected family
     */
    private static int selectFamily(Scanner scanner) {
        System.out.println("\nSelect vehicle family:");
        System.out.println("1. Land");
        System.out.println("2. Water");
        System.out.println("3. Air");
        return scanner.nextInt();
    }

    /**
     * Returns the factory corresponding to the selected family
     * @param family selected vehicle family
     * @return corresponding factory
     */
    private static VehicleFactory getFactory(int family) {
        return switch (family) {
            case 1 -> new LandVehicleFactory();
            case 2 -> new WaterVehicleFactory();
            case 3 -> new AirVehicleFactory();
            default -> throw new IllegalArgumentException("Invalid vehicle family.");
        };
    }

    /**
     * Displays the vehicle types according to the selected family
     * @param scanner scanner used for input
     * @param family selected family
     * @return selected vehicle type
     */
    private static String selectVehicleType(Scanner scanner,int family) {
        return switch (family) {
            case 1 -> {System.out.println("\nSelect land vehicle:");
                System.out.println("1. Car");
                System.out.println("2. Bicycle");
                System.out.println("3. Motorcycle");
                int option = scanner.nextInt();
                yield switch (option) {
                    case 1 -> "car";
                    case 2 -> "bicycle";
                    case 3 -> "motorcycle";
                    default -> throw new IllegalArgumentException( "Invalid land vehicle.");
                };
            }

            case 2 -> {System.out.println("\nSelect water vehicle:");
                System.out.println("1. Motorboat");
                System.out.println("2. Sailboat");
                System.out.println("3. Jet Ski");
                int option = scanner.nextInt();
                yield switch (option) {
                    case 1 -> "motorboat";
                    case 2 -> "sailboat";
                    case 3 -> "jetski";

                    default -> throw new IllegalArgumentException("Invalid water vehicle.");
                };
            }

            case 3 -> {System.out.println("\nSelect air vehicle:");
                System.out.println("1. Airplane");
                System.out.println("2. Light Aircraft");
                System.out.println("3. Helicopter");
                int option = scanner.nextInt();
                yield switch (option) {
                    case 1 -> "airplane";
                    case 2 -> "lightaircraft";
                    case 3 -> "helicopter";
                    default -> throw new IllegalArgumentException("Invalid air vehicle.");
                };
            }

            default -> throw new IllegalArgumentException("Invalid family.");
        };
    }

    /**
     * Displays the available vehicle categories
     * @param scanner scanner used for input
     * @return selected category
     */
    private static Category selectCategory(Scanner scanner) {
        System.out.println("\nSelect category:");
        System.out.println("1. Economy");
        System.out.println("2. Luxury");
        System.out.println("3. Used");
        int option = scanner.nextInt();
        return switch (option) {
            case 1 -> Category.ECONOMY;
            case 2 -> Category.LUXURY;
            case 3 -> Category.USED;
            default -> throw new IllegalArgumentException( "Invalid category.");
        };
    }

    /**
     * Displays all selected vehicles and calculates subtotal, discount and final total
     * Java Streams are used to calculate the purchase subtotal
     * @param vehicles selected vehicles
     */
    private static void showSummary(
            List<Vehicle> vehicles) {
        System.out.println( "\n================================");
        System.out.println("        PURCHASE SUMMARY");
        System.out.println("================================");
        vehicles.forEach(Vehicle::showInformation);

        // Java Stream used to accumulate prices.
        double subtotal = vehicles.stream().mapToDouble(Vehicle::getPrice).sum();
        DiscountStrategy discountStrategy = new PurchaseDiscountStrategy();
        double discount = discountStrategy.calculateDiscount( subtotal, vehicles.size());
        double finalTotal = subtotal - discount;
        System.out.println("\n--------------------------------");
        System.out.println("Number of vehicles: " + vehicles.size());
        System.out.printf("Subtotal: $%,.2f%n", subtotal);
        System.out.printf("Discount: $%,.2f%n", discount);
        System.out.printf("Final total: $%,.2f%n", finalTotal);
        System.out.println("--------------------------------");
    }
}