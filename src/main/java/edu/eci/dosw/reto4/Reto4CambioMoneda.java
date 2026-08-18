package edu.eci.dosw.reto4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Executes Challenge 4 - Currency Exchange.
 */
public final class Reto4CambioMoneda {

    /**
     * Prevents the creation of objects of this class.
     */
    private Reto4CambioMoneda() {
    }

    /**
     * Executes Challenge 4.
     *
     * Allows the user to enter multiple transactions
     * and convert one amount into one or more
     * destination currencies.
     */
    public static void run() {

        Scanner scanner = new Scanner(System.in);

        ExchangeRateProvider rateProvider =new FixedExchangeRateProvider();

        ConversionStrategy strategy =new CurrencyConversionStrategy(rateProvider);

        CurrencyConverterService converter =new CurrencyConverterService(strategy);

        List<ConversionResult> transactions =new ArrayList<>();

        System.out.println("\n================================");
        System.out.println("CHALLENGE 4 - CURRENCY EXCHANGE");
        System.out.println("================================");

        boolean continueTransactions = true;

        while (continueTransactions) {

            System.out.print( "\nEnter amount: ");

            double amount = scanner.nextDouble();

            Currency source =selectCurrency(scanner,"\nSelect source currency:");

            System.out.print("\nHow many destination currencies? ");

            int numberOfDestinations = scanner.nextInt();

            for (int i = 0;i < numberOfDestinations;i++) {

                Currency destination = selectCurrency(scanner,"\nSelect destination currency "+ (i + 1)+ ":");

                ConversionResult result =converter.convert(amount,source,destination);

                transactions.add(result);

                System.out.println("\nConversion completed:");

                result.showInformation();
            }

            System.out.println("\nDo you want to enter another transaction?");

            System.out.println("1. Yes");
            System.out.println("2. No");

            int option =scanner.nextInt();

            continueTransactions = option == 1;
        }

        showSummary(transactions);
    }

    /**
     * Displays the supported currencies and returns
     * the currency selected by the user.
     *
     * @param scanner scanner used to read the input
     * @param message message displayed before the options
     * @return the selected currency
     * @throws IllegalArgumentException if the option
     *         is invalid
     */
    private static Currency selectCurrency(Scanner scanner,String message) {

        System.out.println(message);

        System.out.println("1. USD");
        System.out.println("2. EUR");
        System.out.println("3. JPY");
        System.out.println("4. COP");

        int option = scanner.nextInt();

        return switch (option) {

            case 1 -> Currency.USD;
            case 2 -> Currency.EUR;
            case 3 -> Currency.JPY;
            case 4 -> Currency.COP;

            default ->
                    throw new IllegalArgumentException("Invalid currency.");
        };
    }

    /**
     * Displays all completed transactions and
     * calculates totals grouped by destination
     * currency using Java Streams.
     *
     * @param transactions list of completed conversions
     */
    private static void showSummary(List<ConversionResult> transactions) {

        System.out.println("\n================================");
        System.out.println("       EXCHANGE SUMMARY");
        System.out.println("================================");

        transactions.forEach(ConversionResult::showInformation);

        Map<Currency, Double> totals = transactions.stream()
                .collect(Collectors.groupingBy(ConversionResult::getDestinationCurrency,Collectors.summingDouble(ConversionResult::getConvertedAmount)));

        System.out.println("\n--------------------------------");
        System.out.println("TOTALS BY DESTINATION CURRENCY");
        System.out.println("--------------------------------");

        totals.forEach((currency, total) ->
                                System.out.printf(
                                "%s: %.2f%n",
                                currency,
                                total
                                )
        );
    }
}