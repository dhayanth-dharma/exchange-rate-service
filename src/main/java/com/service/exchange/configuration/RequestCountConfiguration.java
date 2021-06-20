package com.service.exchange.configuration;

import com.service.exchange.component.RequestCounterComponent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RequestCountConfiguration {
    @Bean("requestCounter")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public RequestCounterComponent getRequestCounter() {
        return new RequestCounterComponent();
    }

}
