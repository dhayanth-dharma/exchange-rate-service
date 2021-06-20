package com.service.exchange.service;


import com.service.exchange.component.CurrencyExchangeRateComponent;
import com.service.exchange.component.RequestCounterComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    @Autowired
    private RequestCounterComponent requestCounterComponent;
    @Autowired
    private CurrencyExchangeRateComponent currencyExchangeComponent;

    public List<String> getSupportedCurrencyList( ){
        return currencyExchangeComponent.
                getCurrencyExchangeList().
                keySet().
                stream().
                sorted().
                collect(Collectors.toList());
    }

    public void increaseRequestCount(final String currency){
        requestCounterComponent.setRequestCount(currency.toUpperCase());
    }
    public Integer getRequestCount(final String currency){
        return requestCounterComponent.getRequestCount(currency.toUpperCase());
    }

}

