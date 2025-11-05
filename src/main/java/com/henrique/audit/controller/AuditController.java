package com.henrique.audit.controller;

import com.henrique.audit.dto.AuditEventRequestDTO;
import com.henrique.audit.service.AuditProducer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Audit Controller")
@RestController
@RequestMapping("/api/audit")
public class AuditController {
    private final AuditProducer auditProducer;

    public AuditController(AuditProducer auditProducer) {
        this.auditProducer = auditProducer;
    }

    @Operation(summary = "Publish an audit event")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Accepted"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<Void> publishAuditEvent(@Valid @RequestBody AuditEventRequestDTO dto) {
        auditProducer.publish(dto);
        return ResponseEntity.accepted().build();
    }
}
