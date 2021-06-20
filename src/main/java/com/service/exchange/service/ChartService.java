package com.service.exchange.service;

import com.service.exchange.component.CurrencyExchangeRateComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChartService {
    @Autowired
    private CurrencyExchangeRateComponent currencyExchangeComponent;
    @Value("${ecb.currency.chart.link}")
    String ecbCurrencyChartLink;
    @Value("${xe.currency.chart.link}")
    String xeCurrencyChartLink;
    public String getECB_ChartLink(final String currency ){
        return (currencyExchangeComponent.getRate(currency.toUpperCase()) !=null)?
                ecbCurrencyChartLink+"eurofxref-graph-"+ currency.toLowerCase()+".en.html":null;
    }

    public String getXE_ChartLink(final String fromCurrency,  final String toCurrency){
        return xeCurrencyChartLink+"Amount=1&From="+fromCurrency.toUpperCase()+"&To="+toCurrency.toUpperCase();
    }
}
