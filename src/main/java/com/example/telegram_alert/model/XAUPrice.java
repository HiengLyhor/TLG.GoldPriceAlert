package com.example.telegram_alert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class XAUPrice {

    private double open;
    private double high;
    private double low;
    private double prev;
    private double change;

    @JsonProperty("change_percentage")
    private double changePercentage;

    private double price;
    private double ask;
    private double bid;

    @JsonProperty("price_24k")
    private double price24k;

    @JsonProperty("price_22k")
    private double price22k;

    @JsonProperty("price_18k")
    private double price18k;

}
