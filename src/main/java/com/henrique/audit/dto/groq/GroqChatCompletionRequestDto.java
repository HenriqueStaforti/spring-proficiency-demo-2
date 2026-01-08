package com.henrique.audit.dto.groq;

import java.util.List;

public record GroqChatCompletionRequestDto(
        List<GroqMessageDto> messages,
        String model,
        Double temperature,
        Integer max_completion_tokens,
        Boolean stream,
        String reasoning_effort,
        Object stop
) {
}
