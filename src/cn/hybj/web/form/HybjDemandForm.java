package cn.hybj.web.form;// default package

import java.io.File;

/**
 * AbstractHybjDemand entity provides the base persistence definition of the
 * HybjDemand entity. @author MyEclipse Persistence Tools
 */

public class HybjDemandForm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cp;
	private String demandName;
	private String summary;
	private String createTime;
	private String completeTime;
	private String fenlei;
	private String xqDetails;
	private String attachmentName;
	private String attachmentUrl;
	private String status;
	private String towho;
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDemandName() {
		return this.demandName;
	}

	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCompleteTime() {
		return this.completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public String getFenlei() {
		return this.fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	public String getXqDetails() {
		return this.xqDetails;
	}

	public void setXqDetails(String xqDetails) {
		this.xqDetails = xqDetails;
	}

	public String getAttachmentName() {
		return this.attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTowho() {
		return this.towho;
	}

	public void setTowho(String towho) {
		this.towho = towho;
	}

}