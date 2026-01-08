package com.henrique.audit.dto.groq;

public record GroqUsageDto(
        Integer prompt_tokens,
        Integer completion_tokens,
        Integer total_tokens
) {
}
