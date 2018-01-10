package cn.hybj.web.form;





/**
 * VO值对象，对应页面表单的属性值
 *
 */
@SuppressWarnings("serial")
public class HybjLogForm implements java.io.Serializable {
	
	private String logID;      //主键ID
	private String ipAddress;  //IP地址
	private String opeName;    //操作人
	private String opeTime;    //操作时间
	private String details;    //操作明细
	
	//定义String类型的数组对象，用于获取待删除的日志ID
	private String [] logid;
	
	public String[] getLogid() {
		return logid;
	}
	public void setLogid(String[] logid) {
		this.logid = logid;
	}
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
	public String getOpeTime() {
		return opeTime;
	}
	public void setOpeTime(String opeTime) {
		this.opeTime = opeTime;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
}
