package cn.hybj.domain;
// default package

/**
 * AbstractHybjReport entity provides the base persistence definition of the
 * HybjReport entity. @author MyEclipse Persistence Tools
 */

public class HybjReport implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer number;
	private String itemName;
	private String programaName;
	private String type;
	private Boolean isCharge;
	private String onlineTime;
	private String preonlineTime;
	private Boolean isCopyright;
	private String remarks;
	private String createTime;
	private Boolean isSubmit;
	private String cp;
	private Boolean isJh;
	private String status;
	private String user;
	private Integer episode;
	private String region;
	private String director;
	private String mianActor;
	private String feedback;
	private String language;
	private String offlineTime;
	private Boolean zqIsonline;
	private String verifyTime;
	
	
	// Constructors



	// Property accessors

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getProgramaName() {
		return this.programaName;
	}

	public void setProgramaName(String programaName) {
		this.programaName = programaName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsCharge() {
		return this.isCharge;
	}

	public void setIsCharge(Boolean isCharge) {
		this.isCharge = isCharge;
	}

	public String getOnlineTime() {
		return this.onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getPreonlineTime() {
		return this.preonlineTime;
	}

	public void setPreonlineTime(String preonlineTime) {
		this.preonlineTime = preonlineTime;
	}

	public Boolean getIsCopyright() {
		return this.isCopyright;
	}

	public void setIsCopyright(Boolean isCopyright) {
		this.isCopyright = isCopyright;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsSubmit() {
		return this.isSubmit;
	}

	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public Boolean getIsJh() {
		return this.isJh;
	}

	public void setIsJh(Boolean isJh) {
		this.isJh = isJh;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getEpisode() {
		return this.episode;
	}

	public void setEpisode(Integer episode) {
		this.episode = episode;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getMianActor() {
		return this.mianActor;
	}

	public void setMianActor(String mianActor) {
		this.mianActor = mianActor;
	}



	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getOfflineTime() {
		return this.offlineTime;
	}

	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}

	public Boolean getZqIsonline() {
		return this.zqIsonline;
	}

	public void setZqIsonline(Boolean zqIsonline) {
		this.zqIsonline = zqIsonline;
	}

}