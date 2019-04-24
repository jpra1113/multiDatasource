package com.example.demo.service.sqlserver;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.sqlserver.SysAuditLog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysAuditLogServiceTest {

	@Autowired
	private SqlserverSysAuditLogService sysAuditLogService;
	
	@Test
	public void testFindAll() {
		List<SysAuditLog> list = sysAuditLogService.findAll();
		System.err.println("List<SysAuditLog> list size = " + list.size());
	}
}
