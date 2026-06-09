package com.example.telegram_alert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class GoldData {

    private Long timestamp;
    @JsonProperty("base_currency")
    private String baseCurrency;
    private String metals;
    @JsonProperty("weight_unit")
    private String weightUnit;
    @JsonProperty("metal_prices")
    private Map<String, XAUPrice> metalPrices;

}
