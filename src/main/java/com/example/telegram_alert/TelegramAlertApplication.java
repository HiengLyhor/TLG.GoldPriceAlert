package com.example.telegram_alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelegramAlertApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramAlertApplication.class, args);
	}

}
