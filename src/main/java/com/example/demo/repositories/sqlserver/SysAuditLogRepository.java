package com.example.demo.repositories.sqlserver;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.sqlserver.SysAuditLog;


public interface SysAuditLogRepository extends JpaRepository<SysAuditLog, Long> {

	SysAuditLog findByLogId(String logId);
}