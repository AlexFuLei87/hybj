package cn.hybj.web.form;// default package

import java.io.File;

/**
 * AbstractHybjSpecial entity provides the base persistence definition of the
 * HybjSpecial entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class HybjSpecialForm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String specialName;
	private String itemName;
	private String cp;
	private String status;
	private File file;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpecialName() {
		return specialName;
	}

	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}