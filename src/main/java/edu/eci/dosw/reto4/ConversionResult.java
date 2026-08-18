package reto4;

/*
Represents the result of a currency conversion.
*/

public class ConversionResult {

    private final double originalAmount;
    private final Currency sourceCurrency;
    private final double convertedAmount;
    private final Currency destinationCurrency;

/* 
Creates a new conversion result.*
@param originalAmount the original amount
@param sourceCurrency the source currency
@param convertedAmount the converted amount
@param destinationCurrency the destination currency
*/

public ConversionResult(double originalAmount,Currency sourceCurrency,double convertedAmount,Currency destinationCurrency) {
        this.originalAmount = originalAmount;
        this.sourceCurrency = sourceCurrency;
        this.convertedAmount = convertedAmount;
        this.destinationCurrency = destinationCurrency;
    }

/*
Gets the original amount.
@return the original amoun
*/

public double getOriginalAmount() {return originalAmount;}

/*
Gets the source currency.
@return the source currency
*/

public Currency getSourceCurrency() {return sourceCurrency;}

/* 
Gets the converted amount.
@return the converted amount
*/

public double getConvertedAmount() {return convertedAmount;}

/** 
Gets the destination currency.
@return the destination currency
*/
public Currency getDestinationCurrency() {return destinationCurrency;}

/**  
Displays the conversion information.
*/
  public void showInformation() {

        System.out.printf(
                "%.2f %s -> %.2f %s%n",
                originalAmount,
                sourceCurrency,
                convertedAmount,
                destinationCurrency
        );
    }
}