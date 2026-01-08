package com.henrique.audit.dto.groq;

public record GroqChoiceDto(
        Integer index,
        GroqResponseMessageDto message,
        String finish_reason) {
}
