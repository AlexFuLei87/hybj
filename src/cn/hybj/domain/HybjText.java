package cn.hybj.domain;

import java.util.Date;

/**
 * PO持久层对象，对应数据库表hybj_Text
 *
 */
@SuppressWarnings("serial")
public class HybjText implements java.io.Serializable {
	
	private String textID;
	private String textName;
	private Date textDate;
	private String textRemark;
	
	public String getTextID() {
		return textID;
	}
	public void setTextID(String textID) {
		this.textID = textID;
	}
	public String getTextName() {
		return textName;
	}
	public void setTextName(String textName) {
		this.textName = textName;
	}
	public Date getTextDate() {
		return textDate;
	}
	public void setTextDate(Date textDate) {
		this.textDate = textDate;
	}
	public String getTextRemark() {
		return textRemark;
	}
	public void setTextRemark(String textRemark) {
		this.textRemark = textRemark;
	}
	
	
}
