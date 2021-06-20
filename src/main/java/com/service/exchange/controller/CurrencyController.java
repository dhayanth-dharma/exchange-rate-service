package com.service.exchange.controller;



import com.service.exchange.service.CurrencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/currency")
@Api(value = "CURRENCY", description = "CURRENCY CONTROLLER")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    /*
    Get list of supported currencies
    Ret: Currency list
     */
    @ApiOperation(value = "GET LIST OF SUPPORTED CURRENCIES")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 400, message = "Bad request!!!") })
    @GetMapping(path = "/")
    public ResponseEntity getCurrencyList() {
        try{
            List<String> currencyList=currencyService.getSupportedCurrencyList();
            return new ResponseEntity(currencyList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    Get request count
    Args: currency
    Ret: count
     */
    @ApiOperation(value = "GET REQUEST COUNT FOR A GIVEN CURRENCY")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 400, message = "Bad request!!!") })
    @GetMapping(path = "/request-count")
    public ResponseEntity getRequestCount(@NotNull @RequestParam("currency") String currency) {
        if(!currency.isEmpty()){
            try{
                Integer count=currencyService.getRequestCount(currency);
                return (count!=null)?
                        new ResponseEntity(count,HttpStatus.OK):
                        new ResponseEntity("Currency data not available",HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
            return new ResponseEntity("EMPTY INPUTS", HttpStatus.BAD_REQUEST);
    }
}
