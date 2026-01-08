package com.henrique.audit.infrastructure.client.groq.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GroqWebClientConfig {

    @Bean
    @Qualifier("groqWebClient")
    WebClient webClient(
        @Value("${app.groq.api.baseUrl}") String baseUrl,
        @Value("${app.groq.api.key}") String apiKey) {
            return WebClient.builder()
                    .baseUrl(baseUrl)
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
        }
}
