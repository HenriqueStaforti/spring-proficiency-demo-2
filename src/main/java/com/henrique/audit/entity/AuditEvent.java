package com.henrique.audit.entity;

import com.henrique.audit.enums.AuditAction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "audit_events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEvent {
    @Id
    private String id;

    private AuditAction action;
    private String entityType;
    private String entityId;
    private String username;
    private String source;
    private String description;
    private Instant timestamp;
    private Map<String, Object> metadata;
}