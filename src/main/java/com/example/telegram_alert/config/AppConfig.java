package com.example.telegram_alert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Data
@Configuration
@ConfigurationProperties(prefix = "telegram")
public class AppConfig {

    private String botToken;
    private String chatId;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
