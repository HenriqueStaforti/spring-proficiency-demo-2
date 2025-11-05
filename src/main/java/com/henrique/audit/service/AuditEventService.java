package com.henrique.audit.service;

import com.henrique.audit.dto.AuditEventRequestDTO;
import com.henrique.audit.model.AuditEvent;
import com.henrique.audit.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuditEventService {
    private final AuditRepository auditRepository;
    private final Logger log = LoggerFactory.getLogger(AuditEventService.class);

    public AuditEventService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public void saveEvent(AuditEventRequestDTO dto) {
        try {
            AuditEvent auditEvent = AuditEvent.builder()
                    .action(dto.action())
                    .entityType(dto.entityType())
                    .entityId(dto.entityId())
                    .username(dto.username())
                    .source(dto.source())
                    .timestamp(dto.timestamp())
                    .description(dto.description())
                    .build();
            auditRepository.save(auditEvent);
            log.info("Audit event saved successfully: {}", auditEvent);
        } catch (Exception e) {
            log.error("Failed to save audit event: {}", dto, e);
        }
    }
}
