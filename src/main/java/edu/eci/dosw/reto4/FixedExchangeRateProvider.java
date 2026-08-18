package edu.eci.dosw.reto4;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides fixed exchange rates for the currencies
 * supported by the application.
 */
public class FixedExchangeRateProvider
        implements ExchangeRateProvider {

    private final Map<String, Double> rates =
            new HashMap<>();
    /**
     * Initializes the exchange rates for each
     * supported currency pair.
     */
    public FixedExchangeRateProvider() {

        rates.put("USD_COP", 4000.0);
        rates.put("COP_USD", 1.0 / 4000.0);
        rates.put("EUR_COP", 4400.0);
        rates.put("COP_EUR", 1.0 / 4400.0);
        rates.put("JPY_COP", 27.0);
        rates.put("COP_JPY", 1.0 / 27.0);
        rates.put("USD_EUR", 0.91);
        rates.put("EUR_USD", 1.10);
        rates.put("USD_JPY", 148.0);
        rates.put("JPY_USD", 1.0 / 148.0);
        rates.put("EUR_JPY", 162.0);
        rates.put("JPY_EUR", 1.0 / 162.0);
    }

    /**
     * Searches for the correct exchange rate according
     * to the source and destination currencies.
     *
     * @param source the source currency
     * @param destination the destination currency
     * @return the exchange rate for the selected pair
     * @throws IllegalArgumentException if the rate
     *         does not exist
     */
    @Override
    public double getRate(Currency source, Currency destination) {
        if (source == destination) { return 1.0;}
        
        String key = source.name() + "_" + destination.name();
        Double rate = rates.get(key);

        if (rate == null) {
            throw new IllegalArgumentException( "Exchange rate not available for " + source + " -> " + destination);
        }
        return rate;
    }
}   