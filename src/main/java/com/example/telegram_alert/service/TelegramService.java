package com.example.telegram_alert.service;

import com.example.telegram_alert.config.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramService {

    private final AppConfig appConfig;
    private final RestTemplate restTemplate;
    private static final String TELEGRAM_API = "https://api.telegram.org/bot{token}/sendMessage";

    public void sendMessage(String text) {
        try {

            Map<String, String> params = new HashMap<>();
            params.put("token", appConfig.getBotToken());

            Map<String, String> body = new HashMap<>();
            body.put("chat_id", appConfig.getChatId());
            body.put("text", text);
            body.put("parse_mode", "HTML");

            restTemplate.postForObject(TELEGRAM_API, body, String.class, params);

            log.info("Telegram message sent successfully.");

        } catch (Exception e) {
            log.error("Failed to send Telegram message: {}", e.getMessage());
        }
    }

}
