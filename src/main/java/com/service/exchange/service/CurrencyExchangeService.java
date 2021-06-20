package com.service.exchange.service;


import com.service.exchange.component.CurrencyExchangeRateComponent;
import com.service.exchange.component.RequestCounterComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {
    @Autowired
    private CurrencyExchangeRateComponent currencyExchangeComponent;
    @Autowired
    private RequestCounterComponent requestCounterComponent;

    public Double getEuroExchangeRatePair(final String currency){
        Double currencyValue=currencyExchangeComponent.getRate(currency.toUpperCase());

        //Increase request count
        if(currencyValue!=null)
            requestCounterComponent.setRequestCount(currency);

        return (currencyValue!=null)?
                1/currencyExchangeComponent.getRate(currency.toUpperCase()):
                null;
    }

    public Double getExchangeRatePair(final String currencyOne, final String currencyTwo){
        Double currencyOneValue=currencyExchangeComponent.getRate(currencyOne.toUpperCase());
        Double currencyTwoValue=currencyExchangeComponent.getRate(currencyTwo.toUpperCase());

        //Increase request count
        if(currencyOneValue!=null)
            requestCounterComponent.setRequestCount(currencyOne);
        if(currencyTwoValue!=null)
            requestCounterComponent.setRequestCount(currencyTwo);

        return (currencyOneValue!=null && currencyTwoValue!=null)?
                currencyOneValue/currencyTwoValue:
                null;
    }
}
