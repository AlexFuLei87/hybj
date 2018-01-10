package cn.hybj.web.form;

import java.io.File;


/**
 * VO值对象，对应页面表单的属性值(首页显示)
 */
@SuppressWarnings("serial")
public class HybjReportForm implements java.io.Serializable {
	
    private int id;
	private String itemName;
	private String programaName;
	private String cp;
	private String onlineTime;
	private String preOnlineTime;
	private boolean isjh;
	private boolean isCharge;
	private String remarks;
    private String type;
    private String status;
    private boolean isSubmit;
	private String feedback;
    
	private File file;
	
	
    
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isSubmit() {
		return isSubmit;
	}
	public void setSubmit(boolean isSubmit) {
		this.isSubmit = isSubmit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isIsjh() {
		return isjh;
	}

	public void setIsjh(boolean isjh) {
		this.isjh = isjh;
	}

	public boolean isCharge() {
		return isCharge;
	}

	public void setIsCharge(boolean isCharge) {
		this.isCharge = isCharge;
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getProgramaName() {
		return programaName;
	}
	public void setProgramaName(String programaName) {
		this.programaName = programaName;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}
	public String getPreOnlineTime() {
		return preOnlineTime;
	}
	public void setPreOnlineTime(String preOnlineTime) {
		this.preOnlineTime = preOnlineTime;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
	
	
	
}
