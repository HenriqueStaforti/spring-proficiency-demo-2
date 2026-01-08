package com.henrique.audit.infrastructure.client.groq;

import com.henrique.audit.dto.groq.GroqChatCompletionRequestDto;
import com.henrique.audit.dto.groq.GroqChatCompletionResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
public class GroqClient {

    private static final Logger log = LoggerFactory.getLogger(GroqClient.class);

    private final WebClient webClient;

    @Value("${app.groq.api.completionUrl}")
    private String completionUri;

    public GroqClient(@Qualifier("groqWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "groqAI", fallbackMethod = "groqFallback")
    public GroqChatCompletionResponseDto chat(GroqChatCompletionRequestDto request) {
        return webClient.post()
                        .uri(completionUri)
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(GroqChatCompletionResponseDto.class)
                        .timeout(Duration.ofSeconds(3))
                        .block();
    }

    private GroqChatCompletionResponseDto groqFallback(GroqChatCompletionRequestDto request, Throwable ex)  {
        log.error("groqFallback triggered for request {}", request, ex);
        return null;
    }

}
