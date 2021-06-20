package com.service.exchange.configuration;


import com.service.exchange.component.CurrencyExchangeRateComponent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ExchangeRateConfiguration {
    @Bean("exchangeService")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CurrencyExchangeRateComponent getExchangeService() {
        return new CurrencyExchangeRateComponent();
    }
}
