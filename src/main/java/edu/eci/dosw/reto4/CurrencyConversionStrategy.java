package reto4;

/**
 * Implements the currency conversion strategy
 * using an exchange rate provider.
 */
public class CurrencyConversionStrategy implements ConversionStrategy {

    private final ExchangeRateProvider rateProvider;

    /**
     * Creates the conversion strategy with an exchange rate provider.
     * @param rateProvider provider used to obtain the exchange rates
     */
    public CurrencyConversionStrategy(ExchangeRateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }
    /**
     * Converts an amount using the correct exchange
     * rate for the selected currencies.
     * @param amount the amount to convert
     * @param source the source currency
     * @param destination the destination currency
     * @return the converted amount
     */
    @Override
    public double convert(double amount, Currency source, Currency destination) {
        double rate =
            rateProvider.getRate(source,destination);
        return amount * rate;
    }
}

