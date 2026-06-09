package com.example.telegram_alert.model;

import lombok.Data;

@Data
public class GoldPriceResponse {

    private String status;
    private GoldData data;

}
