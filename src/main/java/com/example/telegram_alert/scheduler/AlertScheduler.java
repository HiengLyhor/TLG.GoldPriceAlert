package com.example.telegram_alert.scheduler;

import com.example.telegram_alert.model.GoldPriceResponse;
import com.example.telegram_alert.model.XAUPrice;
import com.example.telegram_alert.service.ApiService;
import com.example.telegram_alert.service.TelegramService;
import com.example.telegram_alert.util.GoldConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlertScheduler {

    private final ApiService apiService;
    private final TelegramService telegramService;

    // Uncomment this, if you want it run on application start
    /*
        @EventListener(ApplicationReadyEvent.class)
        public void runOnStartup() {
            log.info("Startup trigger fired.");
            runJob();
        }
    */

    @Scheduled(cron = "${cron.schedule}")
    public void runJob() {
        GoldPriceResponse response = apiService.fetchGoldPrice();

        if (response == null || !"success".equals(response.getStatus())) {
            log.error("Failed to fetch gold price.");
            return;
        }

        XAUPrice xau = response.getData().getMetalPrices().get("XAU");
        if (xau == null) return;

        // 1. Data Preparation
        double pricePerChi = GoldConverter.tozToChi(xau.getPrice());
        String statusEmoji = (xau.getChange() >= 0) ? "🟢" : "🔴";

        // 2. Minimalist Time Format: 16:45, Mar 10
        String time = LocalDateTime.now(ZoneId.of("Asia/Bangkok"))
                .format(DateTimeFormatter.ofPattern("HH:mm, MMM d", Locale.ENGLISH));

        // 3. Constructing Option 6
        // Format: [Emoji] $[Price] /Chỉ
        //         🕒 [Time]
        String message = String.format(
                "%s <b>$%.2f</b> /Chỉ\n" +
                        "🕒 <i>%s</i>",
                statusEmoji,
                pricePerChi,
                time
        );

        telegramService.sendMessage(message);
        log.info("Minimalist alert sent: {}", pricePerChi);
    }

}
