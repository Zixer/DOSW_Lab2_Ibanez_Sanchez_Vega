package edu.eci.dosw.reto6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Executes Challenge 6 - Technical Support System.
 *
 * Demonstrates the Chain of Responsibility pattern
 * by passing support tickets through technicians
 * until one of them can resolve the ticket.
 */
public final class reto6TalktoTechnicalSupport {

    /**
     * Prevents instances of this utility class.
     */
    private reto6TalktoTechnicalSupport() {
    }

    /**
     * Executes Challenge 6.
     *
     * Allows the user to create multiple tickets,
     * processes each ticket through the technician
     * chain and displays final statistics.
     */
    public static void run() {

        Scanner scanner = new Scanner(System.in);

        List<Ticket> tickets = new ArrayList<>();

        Technician basic = new BasicTechnician("Daniel - Basic Technician");

        Technician intermediate = new IntermediateTechnician("Laura - Intermediate Technician");

        Technician advanced = new AdvancedTechnician("Carlos - Advanced Technician");

        basic.setNext(intermediate).setNext(advanced);

        System.out.println("\n================================");

        System.out.println("CHALLENGE 6 - TECHNICAL SUPPORT");

        System.out.println("================================");

        boolean createMoreTickets = true;
        int ticketId = 1;

        while (createMoreTickets) {

            System.out.println("\nCreating Ticket #"+ ticketId);

            System.out.print("Enter ticket description: ");

            String description = scanner.nextLine();

            DifficultyLevel difficulty = selectDifficulty(scanner);

            Priority priority = selectPriority(scanner);

            scanner.nextLine();

            Ticket ticket = new Ticket(ticketId,description,difficulty,priority);

            tickets.add(ticket);

            System.out.println("\nProcessing ticket...");

            basic.handleTicket(ticket);

            System.out.println("\nDo you want to create another ticket?");

            System.out.println("1. Yes");
            System.out.println("2. No");

            int option = scanner.nextInt();

            scanner.nextLine();

            createMoreTickets = option == 1;

            ticketId++;
        }

        showSummary(tickets);
    }

    /**
     * Displays the available difficulty levels
     * and returns the user's selection.
     *
     * @param scanner scanner used to read input
     * @return selected difficulty level
     * @throws IllegalArgumentException if the option
     *         is invalid
     */
    private static DifficultyLevel selectDifficulty(Scanner scanner) {

        System.out.println("\nSelect difficulty:");

        System.out.println("1. Basic");
        System.out.println("2. Intermediate");
        System.out.println("3. Advanced");

        int option = scanner.nextInt();

        return switch (option) {

            case 1 ->
                    DifficultyLevel.BASIC;

            case 2 ->
                    DifficultyLevel.INTERMEDIATE;

            case 3 ->
                    DifficultyLevel.ADVANCED;

            default ->
                    throw new IllegalArgumentException("Invalid difficulty.");
        };
    }

    /**
     * Displays the available priorities
     * and returns the user's selection.
     *
     * @param scanner scanner used to read input
     * @return selected priority
     * @throws IllegalArgumentException if the option
     *         is invalid
     */
    private static Priority selectPriority(Scanner scanner) {

        System.out.println("\nSelect priority:");

        System.out.println("1. Low");
        System.out.println("2. Medium");
        System.out.println("3. High");

        int option = scanner.nextInt();

        return switch (option) {

            case 1 ->
                    Priority.LOW;

            case 2 ->
                    Priority.MEDIUM;

            case 3 ->
                    Priority.HIGH;

            default ->
                    throw new IllegalArgumentException("Invalid priority.");
        };
    }

    /**
     * Displays the final information for every
     * ticket and then shows support statistics.
     *
     * @param tickets list of processed tickets
     */
    private static void showSummary(List<Ticket> tickets) {

        System.out.println("\n================================");

        System.out.println("        SUPPORT SUMMARY");

        System.out.println("================================");

        for (Ticket ticket : tickets) {

            System.out.println("\nTicket #"+ ticket.getId());

            System.out.println("Description: "+ ticket.getDescription());

            System.out.println("Difficulty: "+ ticket.getDifficultyLevel());

            System.out.println("Priority: "+ ticket.getPriority());

            System.out.println("Technician path: "+ String.join(" -> ",ticket.getTechnicianPath()));

            if (ticket.isResolved()) {

                System.out.println("Resolved by: "+ ticket.getResolvedBy());

            } else {

                System.out.println("Status: PENDING ESCALATION");
            }
        }

        showStatistics(tickets);
    }

    /**
     * Calculates and displays ticket statistics
     * using Java Streams.
     *
     * Statistics include:
     * - tickets grouped by difficulty,
     * - resolved tickets,
     * - pending tickets,
     * - tickets that passed through multiple technicians,
     * - average priority of resolved tickets.
     *
     * @param tickets list of processed tickets
     */
    private static void showStatistics(List<Ticket> tickets) {

        Map<DifficultyLevel, Long> ticketsByLevel = tickets.stream().collect(
                                Collectors.groupingBy(Ticket::getDifficultyLevel,Collectors.counting())
                                );

        long resolvedTickets = tickets.stream().filter(Ticket::isResolved).count();

        long pendingTickets = tickets.stream().filter(ticket ->!ticket.isResolved()).count();

        long escalatedTickets = tickets.stream().filter(ticket ->ticket.getTechnicianPath().size() > 1).count();

        double averagePriority = tickets.stream().filter(Ticket::isResolved)
                                                        .mapToInt(ticket ->ticket.getPriority().getValue())
                                                            .average()
                                                            .orElse(0);

        System.out.println("\n================================");

        System.out.println("          STATISTICS");

        System.out.println("================================");

        System.out.println("\nTickets by difficulty:");

        ticketsByLevel.forEach((level, amount) ->System.out.println(level + ": "+ amount));

        System.out.println("\nResolved tickets: "+ resolvedTickets);

        System.out.println("Pending tickets: "+ pendingTickets);

        System.out.println("Tickets that passed through multiple technicians: "+ escalatedTickets);

        System.out.printf("Average priority of resolved tickets: %.2f%n",averagePriority);
    }
}