package com.service.exchange.service;

import com.service.exchange.component.CurrencyExchangeRateComponent;
import com.service.exchange.component.RequestCounterComponent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CurrencyConverterService.class,
        RequestCounterComponent.class, CurrencyExchangeRateComponent.class})
class CurrencyConverterServiceTest {
    @Autowired
    private CurrencyConverterService currencyConverterService;

    @Test
    void convertCurrencyTest() {
        //Arrange
        String fromCurrency="USD";
        String toCurrency="PLN";
        double amount=2.0;
        // Act
        double value=currencyConverterService.convertCurrency(fromCurrency,toCurrency,amount);
        //Assert
        Assertions.assertThat(value).isEqualTo(7.646999495713565);
    }
}