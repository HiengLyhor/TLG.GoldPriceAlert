package com.example.telegram_alert.util;

public class GoldConverter {

    // 1 Troy Ounce = 8.29426 Chi (Vietnamese unit, 1 chi = 3.75g)
    private static final double TOZ_TO_CHI = 8.29426;
    public static double tozToChi(double pricePerToz) {
        return pricePerToz / TOZ_TO_CHI;
    }
    private GoldConverter() {}

}
