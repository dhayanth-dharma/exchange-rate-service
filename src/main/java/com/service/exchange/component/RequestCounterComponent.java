package com.service.exchange.component;

import java.util.HashMap;
/*
SINGLETON CLASS THAT HOLDS REQUEST COUNT FOR EACH REQUEST
 */
public class RequestCounterComponent {
    HashMap<String, Integer> request;
    public RequestCounterComponent(){
        request=new HashMap<>();
    }


    public Integer getRequestCount(String currency){
        return request.getOrDefault(currency.toUpperCase(), null);
    }


    public void setRequestCount(String currency){
        request.put(currency.toUpperCase(), request.getOrDefault(currency.toUpperCase(), 0) + 1);
    }

}
