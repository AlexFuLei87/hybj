package cn.hybj.domain;// default package

/**
 * AbstractHybjOutline entity provides the base persistence definition of the
 * HybjOutline entity. @author MyEclipse Persistence Tools
 */

public class HybjOutline implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ggName;
	private String outline;
	private String createTime;
	private String details;
	private String status;

	// Constructors

	/** default constructor */


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGgName() {
		return this.ggName;
	}

	public void setGgName(String ggName) {
		this.ggName = ggName;
	}

	public String getOutline() {
		return this.outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}