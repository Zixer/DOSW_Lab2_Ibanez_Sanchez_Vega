package edu.eci.dosw.reto2;

import java.util.Scanner;

public final class reto2FiveStarChef {

    private reto2FiveStarChef() {
    }

    public static void run() {

        System.out.println("\n====================================");
        System.out.println("     RETO 2 - CHEF HAMBURGUESA");
        System.out.println("====================================");

        Scanner scanner = new Scanner(System.in);

        Builder builder = new HamburgerBuilder();

        boolean finish = false;

        while (!finish) {

            System.out.println("\nSeleccione un ingrediente:");
            System.out.println("1. Pan        - $2.00");
            System.out.println("2. Carne      - $5.00");
            System.out.println("3. Queso      - $1.50");
            System.out.println("4. Lechuga    - $0.80");
            System.out.println("5. Tomate     - $0.80");
            System.out.println("6. Tocino     - $2.00");
            System.out.println("7. Salsa BBQ  - $1.00");
            System.out.println("8. Terminar");

            System.out.print("Opción: ");

            int option = scanner.nextInt();

            switch (option) {

                case 1:
                    builder.addBread();
                    System.out.println("Pan agregado.");
                    break;

                case 2:
                    builder.addMeat();
                    System.out.println("Carne agregada.");
                    break;

                case 3:
                    builder.addCheese();
                    System.out.println("Queso agregado.");
                    break;

                case 4:
                    builder.addLettuce();
                    System.out.println("Lechuga agregada.");
                    break;

                case 5:
                    builder.addTomato();
                    System.out.println("Tomate agregado.");
                    break;

                case 6:
                    builder.addBacon();
                    System.out.println("Tocino agregado.");
                    break;

                case 7:
                    builder.addBBQSauce();
                    System.out.println("Salsa BBQ agregada.");
                    break;

                case 8:
                    finish = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        }

        Hamburger hamburger = builder.build();

        hamburger.showSummary();

        scanner.close();
    }
}