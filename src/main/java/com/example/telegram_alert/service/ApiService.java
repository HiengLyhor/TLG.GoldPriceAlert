package com.example.telegram_alert.service;

import com.example.telegram_alert.model.GoldPriceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {

    private final RestTemplate restTemplate;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    public GoldPriceResponse fetchGoldPrice() {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-API-KEY", apiKey);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<GoldPriceResponse> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    entity,
                    GoldPriceResponse.class
            );

            log.info("Gold price fetched successfully.");
            return response.getBody();

        } catch (Exception e) {
            log.error("Failed to fetch gold price: {}", e.getMessage());
            return null;
        }
    }

}
