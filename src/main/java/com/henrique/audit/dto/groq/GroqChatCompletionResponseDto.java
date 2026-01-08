package com.henrique.audit.dto.groq;

import java.util.List;

public record GroqChatCompletionResponseDto(
        List<GroqChoiceDto> choices,
        GroqUsageDto usage
) {
}
