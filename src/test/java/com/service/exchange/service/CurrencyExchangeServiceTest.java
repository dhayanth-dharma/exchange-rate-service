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
@ContextConfiguration(classes = {CurrencyExchangeService.class,
        RequestCounterComponent.class, CurrencyExchangeRateComponent.class})
class CurrencyExchangeServiceTest {
    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @Test
    void getEuroExchangeRatePairTest(){
        //Arrange
        String currency="PLN";
        //Act
        Double value=currencyExchangeService.getEuroExchangeRatePair(currency);
        //Assert
        Assertions.assertThat(value).isEqualTo(0.21981886925173658);
    }

    @Test
    void getExchangeRatePairTest(){
        String currencyOne="CHF";
        String currencyTwo="PLN";
        Double value=currencyExchangeService.getExchangeRatePair(currencyOne,currencyTwo);
        Assertions.assertThat(value).isEqualTo(0.240503824848325);
    }
}