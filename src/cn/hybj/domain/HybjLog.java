package cn.hybj.domain;

import java.util.Date;

/**
 * PO持久层对象，对应数据库表hybj_Log
 *
 */
@SuppressWarnings("serial")
public class HybjLog implements java.io.Serializable {
	
	private String logID;      //主键ID
	private String ipAddress;  //IP地址
	private String opeName;    //操作人
	private Date opeTime;      //操作时间
	private String details;    //操作明细
	
	public String getLogID() {
		return logID;
	}
	public void setLogID(String logID) {
		this.logID = logID;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getOpeName() {
		return opeName;
	}
	public void setOpeName(String opeName) {
		this.opeName = opeName;
	}
	public Date getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(Date opeTime) {
		this.opeTime = opeTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
