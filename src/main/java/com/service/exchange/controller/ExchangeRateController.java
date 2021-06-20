package com.service.exchange.controller;



import com.service.exchange.service.CurrencyExchangeService;
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

@RestController
@RequestMapping("/api/exchange")
@Api(value = "EXCHANGE", description = "EXCHANGE RATE CONTROLLER")
public class ExchangeRateController {
    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    /*
    Get ECB Reference rate for Euro currency pair
    Args: Currency
    Ret: Currency pair
     */
    @ApiOperation(value = "ECB REFERENCE RATE FOR CURRENCY PAIR (EURO)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 400, message = "Bad request!!!") })
    @GetMapping(path = "/euro")
    public ResponseEntity getEuroCurrencyPair(@NotNull @RequestParam("currency") String currency) {
        if(!currency.isEmpty()){
            try{
                Double currencyValue=currencyExchangeService.getEuroExchangeRatePair(currency);
                return (currencyValue!=null)?
                        new ResponseEntity(String.format("1 %s / %s Euro",currency.toUpperCase(),currencyValue.toString()), HttpStatus.OK):
                        new ResponseEntity("Currency data not available",HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
            return new ResponseEntity("EMPTY INPUTS", HttpStatus.BAD_REQUEST);
    }


    /*
    Get ECB Reference rate for Other currency pair
    Args: Currency one , Currency two
    Ret: Currency pair
     */
    @ApiOperation(value = "ECB REFERENCE RATE FOR CURRENCY PAIR (EURO)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 400, message = "Bad request!!!") })
    @GetMapping(path = "/other")
    public ResponseEntity getOtherCurrencyPair(@NotNull @RequestParam("currencyOne") String currencyOne,
                                               @NotNull @RequestParam("currencyTwo") String currencyTwo) {
        if(!currencyOne.isEmpty() && !currencyOne.isEmpty()){
            try{
                Double currencyTwoAmount=currencyExchangeService.getExchangeRatePair(currencyOne,currencyTwo);
                return (currencyTwoAmount!=null)?
                        new ResponseEntity(String.format("1 %s / %s %s",
                                currencyOne.toUpperCase(),currencyTwoAmount.toString(), currencyTwo.toUpperCase()), HttpStatus.OK):
                        new ResponseEntity("Currency data not available",HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
            return new ResponseEntity("EMPTY INPUTS", HttpStatus.BAD_REQUEST);
    }

}
