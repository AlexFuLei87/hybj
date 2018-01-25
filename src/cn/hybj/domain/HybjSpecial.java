package cn.hybj.domain;// default package

import java.io.File;
import java.util.Date;

/**
 * AbstractHybjSpecial entity provides the base persistence definition of the
 * HybjSpecial entity. @author MyEclipse Persistence Tools
 */

public class HybjSpecial implements java.io.Serializable {

	// Fields

	private Integer id;
	private String specialName;
	private String itemName;
	private String cp;
	private String createTime;
	private String status;
	private String specialStatus;
	private String verifyTime;
	private String feedback;
	private String onDate;
	private String drawPart;

	// Property accessors


	public String getDrawPart() {
		return drawPart;
	}

	public void setDrawPart(String drawPart) {
		this.drawPart = drawPart;
	}

	public String getOnDate() {
		return onDate;
	}

	public void setOnDate(String onDate) {
		this.onDate = onDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpecialName() {
		return this.specialName;
	}

	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpecialStatus() {
		return this.specialStatus;
	}

	public void setSpecialStatus(String specialStatus) {
		this.specialStatus = specialStatus;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
}