package com.henrique.audit.repository;

import com.henrique.audit.entity.AuditEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<AuditEvent, String> {
}
