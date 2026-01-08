package com.henrique.audit.service.ai;

import com.henrique.audit.dto.ai.AIClassificationResultDto;
import com.henrique.audit.dto.audit.AuditEventDTO;
import com.henrique.audit.dto.groq.GroqChatCompletionRequestDto;
import com.henrique.audit.dto.groq.GroqChatCompletionResponseDto;
import com.henrique.audit.dto.groq.GroqChoiceDto;
import com.henrique.audit.dto.groq.GroqMessageDto;
import com.henrique.audit.infrastructure.client.groq.GroqClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIClassificationService {

    private static final Logger log = LoggerFactory.getLogger(AIClassificationService.class);

    private final GroqClient groqClient;

    public AIClassificationService(GroqClient groqClient) {
        this.groqClient = groqClient;
    }

    public AIClassificationResultDto classify(AuditEventDTO event) {
        GroqChatCompletionRequestDto request = getGroqChatCompletionRequestDto(event);
        log.info("Groq request {}", request);

        GroqChatCompletionResponseDto response = groqClient.chat(request);
        log.info("Groq response: {}", response);

        if (response != null) {
            GroqChoiceDto choice = response.choices().get(0);
            return new AIClassificationResultDto(
                choice.message().content(),
                response.usage()
            );
        }

        return getUnavailableResult();
    }

    private GroqChatCompletionRequestDto getGroqChatCompletionRequestDto(AuditEventDTO event) {
        List<GroqMessageDto> messages = List.of(
            new GroqMessageDto(
                "system",
                """
                You are an audit classification assistant.
                Classify the event severity as LOW, MEDIUM or HIGH.
                Respond with a short justification in JSON format.
                """
            ),
            new GroqMessageDto(
                "user",
                event.toString()
            )
        );

        return new GroqChatCompletionRequestDto(
            messages,
            "openai/gpt-oss-120b",
            0.2,
            512,
            false,
            "medium",
            null
        );
    }

    private AIClassificationResultDto getUnavailableResult(){
        return new AIClassificationResultDto(
               """
               {
                 "severity": "UNKNOWN",
                 "reason": "AI classification unavailable"
               }
               """,
               null
       );
    }
}
