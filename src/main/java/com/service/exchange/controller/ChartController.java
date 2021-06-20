package com.service.exchange.controller;

import com.service.exchange.service.ChartService;
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
@RequestMapping("/api/chart")
@Api(value = "CHART", description = "CURRENCY CHART CONTROLLER")
public class ChartController {
    @Autowired
    private ChartService chartService;

    /*
    GET ECB CHART LINK AGAINST EURO CURRENCY
    Args: currency
    Ret: chart link
     */
    @ApiOperation(value = "GET ECB CHART LINK AGAINST EURO/CURRENCY PAIR")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 400, message = "Bad request!!!") })
    @GetMapping(path = "/{currency}")
    public ResponseEntity getECB_CurrencyChart(@NotNull @RequestParam("currency") String currency) {
        if(!currency.isEmpty()){
            try{
                return (currency!=null)?
                        new ResponseEntity(chartService.getECB_ChartLink(currency), HttpStatus.OK):
                        new ResponseEntity("Currency data not available",HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
            return new ResponseEntity("EMPTY INPUTS", HttpStatus.BAD_REQUEST);
    }


    /*
    GET XE CHART LINK FOR GIVEN CURRENCY
    Args: currency one , currency two
    Ret: chart link
     */
    @ApiOperation(value = "GET XE CHART LINK FOR GIVEN CURRENCY PAIR")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 500, message = "internal server error!!!"),
            @ApiResponse(code = 400, message = "Bad request!!!") })
    @GetMapping(path = "/{fromCurrency}/{toCurrency}")
    public ResponseEntity getXE_CurrencyChart(@NotNull @RequestParam("fromCurrency") String fromCurrency,
                                              @NotNull @RequestParam("toCurrency") String toCurrency) {
        if(!fromCurrency.isEmpty() && !toCurrency.isEmpty()){
            try{
                return new ResponseEntity(chartService.getXE_ChartLink(fromCurrency,toCurrency), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
            return new ResponseEntity("EMPTY INPUTS", HttpStatus.BAD_REQUEST);
    }

}

