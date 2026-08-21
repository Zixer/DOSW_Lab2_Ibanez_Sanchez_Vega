package edu.eci.dosw.reto4;

/*Defines the contract for obtaining exchange rates
between two currencies.*/
public interface ExchangeRateProvider {

/*Gets the exchange rate between two currencies.
@param source the source currency
@param destination the destination currency
@return the exchange rate for the currency pair*/

double getRate(Currency source,Currency destination);
}