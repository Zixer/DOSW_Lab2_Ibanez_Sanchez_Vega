package reto4;

/* 
Service responsible for performing
currency conversions.
*/

public class CurrencyConverterService {

    private final ConversionStrategy conversionStrategy;

/* 
Creates the currency converter service.
@param conversionStrategy strategy usedfor conversions
*/

public CurrencyConverterService(
     ConversionStrategy conversionStrategy) {this.conversionStrategy = conversionStrategy;}

/*
Performs a currency conversion.
@param amount the amount to convert
@param source the source currency
@param destination the destination currency
@return an object containing the conversion result
*/

public ConversionResult convert(double amount, Currency source, Currency destination) {

        double convertedAmount =
                conversionStrategy.convert(amount, source, destination);
        return new ConversionResult(amount, source, convertedAmount, destination);
    }
}