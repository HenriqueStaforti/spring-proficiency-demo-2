package com.henrique.audit.dto.ai;

import com.henrique.audit.dto.groq.GroqUsageDto;
import com.henrique.audit.enums.audit.AuditAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.util.Map;

public record AIClassificationResultDto(
         String classification,
         GroqUsageDto usage
) {}
