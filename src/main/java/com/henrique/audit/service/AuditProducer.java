package com.henrique.audit.service;

import com.henrique.audit.dto.AuditEventRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuditProducer {

    private final KafkaTemplate<String, AuditEventRequestDTO> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(AuditProducer.class);

    @Value("${app.kafka.topics.audit-events}")
    private String topic;

    public AuditProducer(KafkaTemplate<String, AuditEventRequestDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(AuditEventRequestDTO dto) {
        log.info("Publishing audit event: {} on topic {}", dto, topic);
        kafkaTemplate.send(topic, dto)
            .whenComplete((result, exception) -> {
                if (exception != null) {
                    log.error("Failed to publish audit event: {}", exception.getMessage(), exception);
                } else {
                    log.info("Audit event published successfully. Offset: {}", result.getRecordMetadata().offset());
                }
            });
    }
}
