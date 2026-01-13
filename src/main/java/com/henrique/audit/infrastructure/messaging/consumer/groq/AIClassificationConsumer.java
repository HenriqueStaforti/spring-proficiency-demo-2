package com.henrique.audit.infrastructure.messaging.consumer.groq;

import com.henrique.audit.dto.ai.AIClassificationResultDto;
import com.henrique.audit.dto.audit.AuditEventDTO;
import com.henrique.audit.service.ai.AIClassificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AIClassificationConsumer {

    private final AIClassificationService aiClassificationService;
    private static final Logger log = LoggerFactory.getLogger(AIClassificationConsumer.class);

    public AIClassificationConsumer(AIClassificationService aiClassificationService) {
        this.aiClassificationService = aiClassificationService;
    }

    @KafkaListener(topics = "${app.kafka.topics.audit-events}", groupId = "${app.kafka.group-ids.audit-ai-group}")
    public void consume(AuditEventDTO event) {
        log.debug("Consuming audit event: {}", event);

        AIClassificationResultDto result = aiClassificationService.classify(event);
        log.info("AI Classification Result: {}", result);
    }
}
