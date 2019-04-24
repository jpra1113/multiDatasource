package com.example.demo.entity.sqlserver;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYSAUDITLOG")
public class SysAuditLog implements java.io.Serializable {

	private String logId;
	private String logPoint;
	private String messageId;
	private String serviceName;
	private String walletId;
	private String pan;
	private String context;
	private String filler;
	private Date createTime;

	@Id
	@Column(name = "LOGID", unique = true, nullable = false, length = 50)
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "LOGPOINT", nullable = false, length = 20)
	public String getLogPoint() {
		return logPoint;
	}

	public void setLogPoint(String logPoint) {
		this.logPoint = logPoint;
	}

	@Column(name = "MESSAGEID", nullable = false, length = 50)
	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Column(name = "SERVICENAME", nullable = false, length = 100)
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Column(name = "CONTEXT", length = 2000)
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	@Column(name = "WALLETID", length = 50)
	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	@Column(name = "PAN", length = 16)
	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	@Column(name = "FILLER", length = 100)
	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	@Column(name = "CREATETIME", length = 26)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}