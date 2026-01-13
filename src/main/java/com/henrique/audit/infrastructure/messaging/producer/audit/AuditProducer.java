package com.henrique.audit.infrastructure.messaging.producer.audit;

import com.henrique.audit.dto.audit.AuditEventDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AuditProducer {

    private final KafkaTemplate<String, AuditEventDTO> kafkaTemplate;
    private static final Logger log = LoggerFactory.getLogger(AuditProducer.class);

    @Value("${app.kafka.topics.audit-events}")
    private String topic;

    public AuditProducer(KafkaTemplate<String, AuditEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(AuditEventDTO dto) {
        log.debug("Publishing audit event: {} on topic {}", dto, topic);
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
