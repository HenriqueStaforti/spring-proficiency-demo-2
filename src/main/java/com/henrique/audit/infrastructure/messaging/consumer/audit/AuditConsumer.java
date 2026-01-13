package com.henrique.audit.infrastructure.messaging.consumer.audit;

import com.henrique.audit.dto.audit.AuditEventDTO;
import com.henrique.audit.service.audit.AuditEventService;
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

    @KafkaListener(topics = "${app.kafka.topics.audit-events}", groupId = "${app.kafka.group-ids.audit-service-group}")
    public void consume(AuditEventDTO event) {
        log.debug("Consuming audit event: {}", event);
        auditEventService.saveEvent(event);
    }
}
