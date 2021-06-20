package com.service.exchange.controller;



import com.service.exchange.service.CurrencyConverterService;
import io.swagger.annotations.Api;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/api/currency-converter")
@Api(value = "CURRENCY_CONVERTER", description = "CURRENCY CONVERTER CONTROLLER")

public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterService currencyConverterService;

    /*
    Convert currency
    Args: target currency, source currency, amount
    Ret: converted amount
     */
    @ApiOperation(value = "CONVERT AMOUNT FROM GIVEN CURRENCY TO ANOTHER")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 400, message = "Bad request!!!") })
    @GetMapping(path = "/convert")
    public ResponseEntity convertCurrency(@NotNull  @RequestParam("source_currency") String sourceCurrency,
                                          @NotNull @RequestParam("target_currency") String targetCurrency,
                                          @NotNull @RequestParam("amount") Double amount ) {
        if(!sourceCurrency.isEmpty() && !targetCurrency.isEmpty() && amount != null){
            try{
                Double convertedAmount=currencyConverterService.
                        convertCurrency(sourceCurrency,targetCurrency,amount);
                return (convertedAmount!=null)?
                        new ResponseEntity(convertedAmount,HttpStatus.OK):
                        new ResponseEntity("Currency data not available",HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
            return new ResponseEntity("EMPTY INPUTS", HttpStatus.BAD_REQUEST);
    }
}
