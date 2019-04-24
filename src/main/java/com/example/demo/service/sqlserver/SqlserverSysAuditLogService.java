package com.example.demo.service.sqlserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.sqlserver.SysAuditLog;
import com.example.demo.repositories.sqlserver.SysAuditLogRepository;


@Service
public class SqlserverSysAuditLogService {

	@Autowired
	private SysAuditLogRepository sysAuditLogRepository;

	public List<SysAuditLog> findAll() {
		return sysAuditLogRepository.findAll();
	}
}
