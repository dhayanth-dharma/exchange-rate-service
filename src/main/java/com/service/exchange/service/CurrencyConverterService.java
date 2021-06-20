package com.service.exchange.service;


import com.service.exchange.component.CurrencyExchangeRateComponent;
import com.service.exchange.component.RequestCounterComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterService {
    @Autowired
    private CurrencyExchangeRateComponent currencyExchangeComponent;
    @Autowired
    private RequestCounterComponent requestCounterComponent;
    public Double convertCurrency(final String sourceCurrency,
                                  final String targetCurrency,
                                  final Double amount ){
        Double targetCurrencyValue=currencyExchangeComponent.getRate(targetCurrency.toUpperCase());
        Double sourceCurrencyValue=currencyExchangeComponent.getRate(sourceCurrency.toUpperCase());

        //Increase request count
        if(targetCurrencyValue!=null)
            requestCounterComponent.setRequestCount(targetCurrency);
        if(sourceCurrencyValue!=null)
            requestCounterComponent.setRequestCount(sourceCurrency);

        return (targetCurrencyValue !=null && sourceCurrencyValue !=null)?(targetCurrencyValue/sourceCurrencyValue)*amount :null;
    }
}
