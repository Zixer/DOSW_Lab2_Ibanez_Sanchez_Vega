package edu.eci.dosw.reto4;

public interface ConversionStrategy {

/**
 * Converts an amount from one currency to another.
 * @param amount the amount to convert
 * @param source the source currency
 * @param destination the destination currency
 * @return the converted amount
 */
    double convert(double amount, Currency source, Currency destination);
}