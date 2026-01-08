package com.henrique.audit.repository.audit;

import com.henrique.audit.entity.audit.AuditEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository<AuditEvent, String> {
}
