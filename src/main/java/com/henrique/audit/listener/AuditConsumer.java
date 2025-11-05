package com.henrique.audit.listener;

import com.henrique.audit.dto.AuditEventRequestDTO;
import com.henrique.audit.service.AuditEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AuditConsumer {

    private final AuditEventService auditEventService;
    private static final Logger log = LoggerFactory.getLogger(AuditConsumer.class);

    public AuditConsumer(AuditEventService auditEventService) {
        this.auditEventService = auditEventService;
    }

    @KafkaListener(topics = "${app.kafka.topics.audit-events}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(AuditEventRequestDTO event) {
        log.info("Consuming audit event: {}", event);
        auditEventService.saveEvent(event);
    }
}
