package cn.hybj.web.form;


/**
 * VO值对象，对应页面表单的属性值
 * VO对象与PO对象的关系：
 *     相同点：都是javabean
 *     不同点：PO对象中的属性关联数据库的字段
 *            VO对象中的属性可以随意增加、修改、删除，对应的页面表单属性
 *
 */
@SuppressWarnings("serial")
public class HybjTextForm implements java.io.Serializable {
	
	private String textID;
	private String textName;
	private String textDate;
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
	public String getTextDate() {
		return textDate;
	}
	public void setTextDate(String textDate) {
		this.textDate = textDate;
	}
	public String getTextRemark() {
		return textRemark;
	}
	public void setTextRemark(String textRemark) {
		this.textRemark = textRemark;
	}
	
	
	
}
