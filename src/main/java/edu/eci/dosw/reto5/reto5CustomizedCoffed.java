package edu.eci.dosw.reto5; 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Executes Challenge 5 - Creative Coffee.
 *
 * Demonstrates the Decorator pattern by allowing
 * multiple toppings to be dynamically added
 * to a basic coffee.
 */
public final class reto5CustomizedCoffed {

    /**
     * Prevents instances of this utility class.
     */
    private reto5CustomizedCoffed() {
    }

    /**
     * Executes Challenge 5.
     *
     * Allows the user to create multiple coffees,
     * add any number of toppings to each coffee
     * and display the final order summary.
     */
    public static void run() {

        Scanner scanner = new Scanner(System.in);

        List<Coffee> coffees = new ArrayList<>();

        System.out.println("\n================================");

        System.out.println("   CHALLENGE 5 - CREATIVE COFFEE");

        System.out.println("================================");

        boolean createMoreCoffees = true;

        while (createMoreCoffees) {

            Coffee coffee = new BasicCoffee();

            System.out.println("\nNew coffee created.");

            System.out.printf("Base price: COP %,.0f%n",coffee.getPrice());

            boolean addToppings = true;

            while (addToppings) {

                showToppingMenu();

                int option = scanner.nextInt();

                scanner.nextLine();

                switch (option) {

                    case 1 -> coffee = new MilkDecorator(coffee);

                    case 2 -> coffee =new ChocolateDecorator(coffee);

                    case 3 -> coffee = new CaramelDecorator(coffee);

                    case 4 -> coffee = new WhippedCreamDecorator(coffee);

                    case 5 -> coffee = new MintDecorator(coffee);

                    case 6 -> coffee = createCustomTopping(scanner,coffee);

                    case 0 -> addToppings = false;

                    default -> System.out.println("Invalid option.");
                }
            }

            coffees.add(coffee);

            showCoffee(coffee);

            System.out.println("\nDo you want to create another coffee?");

            System.out.println("1. Yes");
            System.out.println("2. No");

            int option = scanner.nextInt();

            scanner.nextLine();

            createMoreCoffees = option == 1;
        }

        showSummary(coffees);
    }

    /**
     * Displays the menu containing all
     * available topping options.
     */
    private static void showToppingMenu() {

        System.out.println("\nSelect a topping:");

        System.out.println("1. Milk - COP 1,000");

        System.out.println("2. Chocolate - COP 1,500");

        System.out.println("3. Caramel - COP 1,200");

        System.out.println("4. Whipped Cream - COP 2,000");

        System.out.println("5. Mint - COP 1,300");

        System.out.println("6. Custom Topping");

        System.out.println("0. Finish Coffee");
    }

    /**
     * Creates a custom topping using a name
     * and price entered by the user.
     *
     * @param scanner scanner used to read input
     * @param coffee current coffee being decorated
     * @return coffee decorated with the custom topping
     */
    private static Coffee createCustomTopping(Scanner scanner,Coffee coffee) {

        System.out.print("Enter topping name: ");

        String name = scanner.nextLine();

        System.out.print("Enter topping price: ");

        double price = scanner.nextDouble();

        scanner.nextLine();

        return new CustomToppingDecorator(coffee,name,price);
    }

    /**
     * Displays the description and final price
     * of one coffee.
     *
     * @param coffee coffee to display
     */
    private static void showCoffee(Coffee coffee) {

        System.out.println("\nCoffee completed:");

        System.out.println("Description: "+ coffee.getDescription());

        System.out.printf("Price: COP %,.0f%n",coffee.getPrice());
    }

    /**
     * Displays all coffees created and calculates
     * the total price of the complete order
     * using Java Streams.
     *
     * @param coffees list of created coffees
     */
    private static void showSummary(List<Coffee> coffees) {

        System.out.println("\n================================");

        System.out.println("         ORDER SUMMARY");

        System.out.println("================================");

        for (int i = 0;i < coffees.size();i++) {

            Coffee coffee = coffees.get(i);

            System.out.println("\nCoffee #" + (i + 1));

            System.out.println("Description: "+ coffee.getDescription());

            System.out.printf("Price: COP %,.0f%n",coffee.getPrice());
        }

        double total = coffees.stream().mapToDouble(Coffee::getPrice).sum();

        System.out.println("\n--------------------------------");

        System.out.printf("TOTAL ORDER: COP %,.0f%n",total);

        System.out.println("--------------------------------");
    }
}