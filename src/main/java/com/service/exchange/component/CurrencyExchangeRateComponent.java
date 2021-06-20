package com.service.exchange.component;


import java.util.HashMap;
/*
SINGLETON CLASS THAT HOLDS CURRENT CURRENCY EXCHANGE RATES
 */
public class CurrencyExchangeRateComponent {
    HashMap<String, Double> currencyExchangeRate;

    public CurrencyExchangeRateComponent(){
        currencyExchangeRate= new HashMap<>();
        currencyExchangeRate.put("EUR",1.0);
        currencyExchangeRate.put("USD",1.1898);
        currencyExchangeRate.put("JPY",131.12);
        currencyExchangeRate.put("BGN",1.9558);
        currencyExchangeRate.put("CZK",25.519);
        currencyExchangeRate.put("DKK",7.4364);
        currencyExchangeRate.put("GBP",0.85785);
        currencyExchangeRate.put("HUF",355.41);
        currencyExchangeRate.put("PLN",4.5492);
        currencyExchangeRate.put("RON",4.9249);
        currencyExchangeRate.put("SEK",10.233);
        currencyExchangeRate.put("CHF",1.0941);
        currencyExchangeRate.put("ISK",146.4);
        currencyExchangeRate.put("NOK",10.2868);
        currencyExchangeRate.put("HRK",7.4985);
        currencyExchangeRate.put("RUB",86.0172);
        currencyExchangeRate.put("TRY",10.3459);
        currencyExchangeRate.put("AUD",1.5832);
        currencyExchangeRate.put("BRL",5.9575);
        currencyExchangeRate.put("CAD",1.4721);
        currencyExchangeRate.put("CNY",7.6619);
        currencyExchangeRate.put("HKD",9.2379);
        currencyExchangeRate.put("IDR",17174.78);
        currencyExchangeRate.put("ILS",3.8901);
        currencyExchangeRate.put("INR",88.0297);
        currencyExchangeRate.put("KRW",347.13);
        currencyExchangeRate.put("MXN",24.3373);
        currencyExchangeRate.put("MYR",4.9246);
        currencyExchangeRate.put("NZD",1.7069);
        currencyExchangeRate.put("PHP",57.616);
        currencyExchangeRate.put("SGD",1.5978);
        currencyExchangeRate.put("THB",37.419);
        currencyExchangeRate.put("ZAR",16.8223);
    }

    public Double getRate(String currency){
        return currencyExchangeRate.getOrDefault(currency, null);
    }

    public HashMap<String, Double> getCurrencyExchangeList(){
        return currencyExchangeRate;
    }

}

